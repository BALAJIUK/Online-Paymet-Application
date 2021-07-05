package com.cg.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BankAccount;
import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.exception.BankAccountNotFoundException;
import com.cg.exception.BenificiaryNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.TransactionFailureException;
import com.cg.repositories.IAccountRepository;
import com.cg.repositories.IBenificiaryRepository;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;

@Service
public class WalletServiceImplementation implements WalletService {
	@Autowired
	IUserRepository userRepository;
	@Autowired
	WalletRepository walletRepository;
	@Autowired
	IAccountRepository accountRepository;
	@Autowired
	IBenificiaryRepository benificiaryRepository;
	@Autowired
	ITransactionService transactionService;

	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) {
		Customer customer = userRepository.getByMobileno(mobileno);
		if (customer == null) {
			throw new CustomerNotFoundException("Enter your registered mobile number correctly..");
		}
		if (customer.getWallet() == null) {
			Wallet wallet = new Wallet(amount);
			walletRepository.save(wallet);
			userRepository.addWallet(wallet, mobileno);
			return customer;
		}
		return null;
	}

	@Override
	public Customer showBalance(String mobileno) {
		Customer customer = userRepository.getByMobileno(mobileno);
		if (customer == null) {
			throw new CustomerNotFoundException("Enter your registered mobile number correctly..");
		} else {
			return customer;
		}
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		Customer customerSource = userRepository.getByMobileno(sourceMobileNo);
		Customer customerTarget = userRepository.getByMobileno(targetMobileNo);
		if (customerSource == null || customerTarget == null) {
			throw new CustomerNotFoundException("Mobile number not registered..");
		}
		Wallet walletSource = walletRepository.getByWalId(customerSource.getWallet().getWalletId());
		Wallet walletTarget = walletRepository.getByWalId(customerTarget.getWallet().getWalletId());
		BenificiaryDetails benificiaryDetail = benificiaryRepository.getByMobAndWal(targetMobileNo, walletSource);
		double sourceAmount = walletSource.getBalance().doubleValue();
		double targetAmount = walletTarget.getBalance().doubleValue();
		sourceAmount = sourceAmount - amount.doubleValue();
		targetAmount = targetAmount + amount.doubleValue();
		BigDecimal bigDecimalSource = new BigDecimal(sourceAmount);
		BigDecimal bigDecimalTarget = new BigDecimal(targetAmount);
		if (benificiaryDetail != null) {
			if (sourceAmount >= 0) {
				walletRepository.updateBal(bigDecimalSource, walletSource.getWalletId());
				walletRepository.updateBal(bigDecimalTarget, walletTarget.getWalletId());
				Transaction t1 = new Transaction("Transfer", LocalDate.now(), amount.doubleValue(),
						"Sent to " + targetMobileNo, walletSource);
				Transaction t2 = new Transaction("Transfer", LocalDate.now(), amount.doubleValue(),
						"Received from " + sourceMobileNo, walletTarget);
				transactionService.addTransaction(t1);
				transactionService.addTransaction(t2);
				return customerTarget;
			} else {
				throw new TransactionFailureException(
						"Transaction failed due to shortage of " + Math.abs(sourceAmount));
			}
		} else {
			throw new BenificiaryNotFoundException(targetMobileNo + " not added to your Benificiary details");
		}
	}

	@Override
	public List<Customer> getList() {
		List<Customer> customers = userRepository.findAll();
		return customers;
	}

	@Override
	public Customer updateAccount(Customer customer) {
		Customer customerFromDb = userRepository.getByMobileno(customer.getMobileNumber());
		if (customerFromDb == null) {
			throw new CustomerNotFoundException("Enter your registered mobile number correctly..");
		} else {
			userRepository.updatePassword(customer.getPassword(), customer.getMobileNumber());
			return customerFromDb;
		}
	}

	@Override
	public Customer addMoney(Wallet wallet, double amount) {
		int accountNo = wallet.getBankaccount().getAccountNo();
		BankAccount bankAccount = accountRepository.getByAccNo(accountNo);
		if (bankAccount == null) {
			throw new BankAccountNotFoundException("Bank Account " + accountNo + " not added to your wallet");
		}
		double walletAmount = wallet.getBalance().doubleValue();
		double bankAmount = bankAccount.getBalance();
		walletAmount = walletAmount + amount;
		bankAmount = bankAmount - amount;
		BigDecimal bigDecimalWalletAmount = new BigDecimal(walletAmount);
		if (bankAccount.getWallet().getWalletId() == wallet.getWalletId()) {
			if (bankAmount >= 0) {
				bankAccount.setBalance(bankAmount);
				wallet.setBalance(bigDecimalWalletAmount);
				accountRepository.updateBal(bankAmount, accountNo);
				walletRepository.updateBal(bigDecimalWalletAmount, wallet.getWalletId());
				Transaction transaction = new Transaction("Transfer", LocalDate.now(), amount,
						"Transfer from Bank Account " + bankAccount.getAccountNo(), wallet);
				transactionService.addTransaction(transaction);
				Customer customer = userRepository.getByWallet(wallet);
				return customer;
			} else {
				throw new TransactionFailureException("Transaction failed due to shortage of " + Math.abs(bankAmount));
			}
		} else {
			throw new BankAccountNotFoundException("Bank Account " + accountNo + " not added to your wallet");
		}
	}

	@Override
	public Wallet getById(int id) {
		Wallet wallet = walletRepository.getByWalId(id);
		if (wallet == null) {
			throw new CustomerNotFoundException("Wallet not found");
		} else {
			return wallet;
		}
	}

	@Override
	public String getMobileByWallet(Wallet wallet) {
		String mobile = userRepository.getCustomer(wallet);
		return mobile;
	}

	@Override
	public Customer depositAmount(Wallet wallet, int accountNo, BigDecimal amount) {
		BankAccount bankAccount = accountRepository.getByWalAndAcc(accountNo, wallet);
		if (bankAccount == null) {
			throw new TransactionFailureException(
					"Transaction failed. Bank account " + accountNo + " not linked with your wallet");
		}
		double walletAmount = bankAccount.getWallet().getBalance().doubleValue();
		double bankAmount = bankAccount.getBalance();
		walletAmount = walletAmount - amount.doubleValue();
		bankAmount = bankAmount + amount.doubleValue();
		BigDecimal bigDecimalWalletAmount = new BigDecimal(walletAmount);
		if (walletAmount >= 0) {
			walletRepository.updateBal(bigDecimalWalletAmount, bankAccount.getWallet().getWalletId());
			accountRepository.updateBal(bankAmount, accountNo);
			Transaction transaction = new Transaction("Transfer", LocalDate.now(), amount.doubleValue(),
					"Sent to Bank account " + accountNo, bankAccount.getWallet());
			transactionService.addTransaction(transaction);
			Customer customer = userRepository.getByWallet(bankAccount.getWallet());
			return customer;
		} else {
			throw new TransactionFailureException("Transaction failed due to shortage of " + Math.abs(walletAmount));
		}
	}

}

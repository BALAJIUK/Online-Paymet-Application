package com.cg.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BillPayment;
import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.exception.TransactionFailureException;
import com.cg.repositories.IBillPaymentRepository;
import com.cg.repositories.WalletRepository;

@Service
public class IBillPaymentServiceImplementation implements IBillPaymentService {

	@Autowired
	IBillPaymentRepository billPaymentRepository;

	@Autowired
	WalletRepository walletRepository;

	@Autowired
	ITransactionService transactionService;

	@Override
	public BillPayment addBillPayment(BillPayment payment) {
		Wallet wallet = payment.getWallet();
		double walletAmount = wallet.getBalance().doubleValue() - payment.getAmount();
		BigDecimal bigDecimalWalletAmount = new BigDecimal(walletAmount);
		if (walletAmount >= 0) {
			billPaymentRepository.save(payment);
			walletRepository.updateBal(bigDecimalWalletAmount, payment.getWallet().getWalletId());
			Transaction transaction = new Transaction("bill payment", LocalDate.now(), payment.getAmount(), payment.getBillType(),
					wallet);
			transactionService.addTransaction(transaction);
			return payment;
		}
		throw new TransactionFailureException("Transaction failed due to shortage of "+Math.abs(walletAmount));
	}

	@Override
	public List<BillPayment> viewAllBills(Wallet wallet) {
		
		List<BillPayment> bills = billPaymentRepository.viewBillPayment(wallet);

		return bills;
	}

}

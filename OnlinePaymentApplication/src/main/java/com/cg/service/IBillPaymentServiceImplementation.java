package com.cg.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BillPayment;
import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.repositories.IBillPaymentRepository;
import com.cg.repositories.WalletRepository;

@Service
public class IBillPaymentServiceImplementation implements IBillPaymentService {

	@Autowired
	IBillPaymentRepository billrepo;

	@Autowired
	WalletRepository wrepo;

	@Autowired
	ITransactionService service;

	@Override
	public BillPayment addBillPayment(BillPayment payment) {
		Wallet wallet = payment.getWallet();
		double wamount = wallet.getBalance().doubleValue() - payment.getAmount();
		BigDecimal bdwamont = new BigDecimal(wamount);
		if (wamount >= 0) {
			billrepo.save(payment);
			wrepo.updateBal(bdwamont, payment.getWallet().getWalletId());
			Transaction t = new Transaction("bill payment", LocalDate.now(), payment.getAmount(), payment.getBillType(),
					wallet);
			service.addTransaction(t);
			return payment;
		}
		return null;
	}

	@Override
	public BillPayment viewBillpayment(BillPayment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillPayment> viewAllBills(Wallet wallet) {
		List<BillPayment> bills = billrepo.viewBillPayment(wallet);

		return bills;
	}

}

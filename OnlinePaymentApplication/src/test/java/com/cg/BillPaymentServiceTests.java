package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.BillPayment;
import com.cg.entities.Wallet;
import com.cg.exception.TransactionFailureException;
import com.cg.repositories.IBillPaymentRepository;
import com.cg.repositories.WalletRepository;
import com.cg.service.IBillPaymentServiceImplementation;
import com.cg.service.ITransactionService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class BillPaymentServiceTests {

	@MockBean
	IBillPaymentRepository billrepo;

	@InjectMocks
	IBillPaymentServiceImplementation service;

	//if the wallet balance is lesser than bill payment
	@Test
	public void testAddBillFail() {
		Wallet w = new Wallet();
		w.setWalletId(76);
		w.setBalance(new BigDecimal("0.0"));
		BillPayment bp = new BillPayment();
		bp.setAmount(60.0);
		bp.setBillId(2);
		bp.setBillType("wifi");
		bp.setPaymentDate(LocalDate.now());
		bp.setWallet(w);
		assertThrows(TransactionFailureException.class, () -> service.addBillPayment(bp));
	}

	//Successfull bill payment
	@Test
	public void testViewAllBillsSuccess() {
		Wallet w = new Wallet();
		w.setWalletId(76);
		w.setBalance(new BigDecimal("0.0"));
		BillPayment bp = new BillPayment();
		bp.setAmount(60.0);
		bp.setBillId(2);
		bp.setBillType("wifi");
		bp.setPaymentDate(LocalDate.now());
		bp.setWallet(w);
		BillPayment bp1 = new BillPayment();
		bp.setAmount(600.0);
		bp.setBillId(3);
		bp.setBillType("wifi");
		bp.setPaymentDate(LocalDate.now());
		bp.setWallet(w);
		List<BillPayment> bills = new ArrayList();
		bills.add(bp1);
		bills.add(bp);
		Mockito.when(billrepo.viewBillPayment(w)).thenReturn(bills);
		assertEquals(bills, service.viewAllBills(w));
	}
}

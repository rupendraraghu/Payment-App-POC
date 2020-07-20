package com.wellsfargo.payment.app.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.payment.app.feign.proxy.WellsFargoPaymentProxy;
import com.wellsfargo.payment.app.model.proxy.AccountProxy;

@RestController
@RequestMapping("/rest/auth/user-management")
public class UserController {
	@Autowired
	private WellsFargoPaymentProxy wellsFargoPaymentProxy;

	@GetMapping("/process")
	public String process() {
		return "processing..";
	}

	@GetMapping("/getAllAccounts/{userId}")
	public List<AccountProxy> getAllAccounts(@PathVariable int userId) {
		return wellsFargoPaymentProxy.getAccountByUserId(userId);
	}

	@PutMapping("/depositAccount/{accountNumber}/amount/{amount}")
	public String depositAccount(@PathVariable int accountNumber, @PathVariable BigDecimal amount) {
		return wellsFargoPaymentProxy.depositAccount(accountNumber, amount);
	}

	@PutMapping("/withdrawAccount/{accountNumber}/amount/{amount}")
	public String withdrawAccount(@PathVariable int accountNumber, @PathVariable BigDecimal amount) {
		return wellsFargoPaymentProxy.withdrawAccount(accountNumber, amount);
	}
}

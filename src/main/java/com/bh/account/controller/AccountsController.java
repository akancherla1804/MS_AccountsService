package com.bh.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bh.account.constants.Constants;
import com.bh.account.dto.AddAccountRequest;
import com.bh.account.dto.Customers;
import com.bh.account.dto.TransactionRequest;
import com.bh.account.model.Customer;
import com.bh.account.service.AccountService;


@RestController
public class AccountsController {
	
	@Autowired
	AccountService accountService;

	@GetMapping(Constants.URI_GET_CUSTOMER_DETAILS)
	public Customer getCustomerDetails(@PathVariable String customerId) {
		return accountService.getCustomer(customerId);
	}
	
	@GetMapping(Constants.URI_GET_ALL_CUSTOMER_IDS)
	public Customers getCustomerIds(){
		return accountService.getCustomerIds();
	}
	
	@PostMapping(Constants.URI_CUSTOMER_ADD_ACCOUNT)
	public Customer addAccount(@RequestBody AddAccountRequest addAccountRequest) {
		return accountService.addAccount(addAccountRequest);
	}
	
	@PostMapping(Constants.URI_CUSTOMER_UPDTE_ACCOUNT)
	public Customer updateAccount(@RequestBody TransactionRequest transactionRequest) {
		return accountService.updateAccount(transactionRequest);
	}
	
}

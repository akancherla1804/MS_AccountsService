package com.bh.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bh.account.constants.Constants;
import com.bh.account.dto.AddAccountRequest;
import com.bh.account.dto.Customers;
import com.bh.account.dto.TransactionRequest;
import com.bh.account.model.Account;
import com.bh.account.model.Customer;

@Service
public class AccountService {
	private final Logger logger = LoggerFactory.getLogger(AccountService.class);

	public static Map<String, Customer> customersMap;
	static {
		customersMap = new HashMap<String, Customer>();
		customersMap.put("1", new Customer("1", "Aditya", "Kancherla", null, null));
		customersMap.put("2", new Customer("2", "Pavani", "Nalam", null, null));
		customersMap.put("3", new Customer("3", "Esha", "Kancherla", null, null));
		customersMap.put("4", new Customer("4", "John", "Doe", null, null));
		customersMap.put("5", new Customer("5", "Mike", "Lawrance", null, null));
		customersMap.put("6", new Customer("6", "Tom", "Cruise", null, null));

		Set<String> keys = customersMap.keySet();
		for (String key : keys) {
			Account account = new Account();
			account.setBalance(15000l);
			account.setAccountType(Constants.ACCOUNT_TYPE_PRIMARY);
			List<Account> accounts = new ArrayList<Account>();
			accounts.add(account);
			customersMap.get(key).setAccounts(accounts);
		}
	}

	public Customers getCustomerIds() {
		logger.info("Start AccountService.getCustomerIds() --> Get all Customer Ids in the system");
		Customers customers = new Customers();
		customers.setCustomerIds(customersMap.keySet());
		return customers;
	}

	public Customer getCustomer(String customerId) {
		logger.info("Start AccountService.getCustomer() --> Get Customer details for the selected customer Id");
		if (customersMap.containsKey(customerId)) {
			customersMap.get(customerId).setErrorMessage(null);
			return customersMap.get(customerId);
		} else {
			return null;
		}
	}

	public Customer addAccount(AddAccountRequest addAccountRequest) {
		logger.info("Start AccountService.addAccount() --> To Add Seconday Current Account for the existing Customer");
		if (customersMap.containsKey(addAccountRequest.getCustomerId())) {
			Customer customer = customersMap.get(addAccountRequest.getCustomerId());
			customer.setErrorMessage(null);
			if (customer.getAccounts() != null && customer.getAccounts().size() == 1) {
				Account account = new Account();
				double initCred = Double.parseDouble(addAccountRequest.getInitialCredit());
				account.setBalance(initCred);
				account.setAccountType(Constants.ACCOUNT_TYPE_SECONDARY);
				customer.getAccounts().add(account);
				return customer;
			} else if (customer.getAccounts() != null && customer.getAccounts().size() == 2) {
				customer.setErrorMessage(Constants.CUSTOMER_ACCOUNTS_PAS);
				return customer;
			} else {
				customer.setErrorMessage(Constants.CUSTOMER_ACCOUNTS_PNIL);
				return customer;
			}
		} else {
			Customer customer = new Customer();
			customer.setErrorMessage(Constants.CUSTOMER_INVALID);
			return customer;
		}
	}

	public Customer updateAccount(TransactionRequest transactionRequest) {
		logger.info("Start AccountService.addAccount() --> Update Account during transactions");
		boolean isTransCompleted = false;
		if (customersMap.containsKey(transactionRequest.getCustomerId())) {
			Customer customer = customersMap.get(transactionRequest.getCustomerId());
			customer.setErrorMessage(null);
			if (customer.getAccounts() != null && customer.getAccounts().size() > 0) {
				List<Account> accounts = customer.getAccounts();
				for (Account account : accounts) {
					if (account.getAccountType().equalsIgnoreCase(transactionRequest.getAccountType())) {
						isTransCompleted = true;
						double transactionAmount = Double.parseDouble(transactionRequest.getTransactionAmount());
						if (transactionRequest.getTransactionType().equalsIgnoreCase(Constants.CUSTOMER_TRANSACTION_DEBIT) && transactionAmount > 0
								&& transactionAmount < account.getBalance()) {
							account.setBalance(account.getBalance() - transactionAmount);
						} else if (transactionRequest.getTransactionType().equalsIgnoreCase(Constants.CUSTOMER_TRANSACTION_CREDIT)
								&& transactionAmount > 0) {
							account.setBalance(account.getBalance() + transactionAmount);
						}
						
					}
				}
				if (!isTransCompleted) {
					customer.setErrorMessage("Customer is not holding " + transactionRequest.getAccountType() + " Account");
					return customer;
				} else {
					return customer;
				}
			} else {
				customer.setErrorMessage(Constants.CUSTOMER_ACCOUNTS_NIL);
				return customer;
			}
		} else {
			Customer customer = new Customer();
			customer.setErrorMessage(Constants.CUSTOMER_INVALID);
			return customer;
		}
	}

}

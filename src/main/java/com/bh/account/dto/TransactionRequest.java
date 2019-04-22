package com.bh.account.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest implements Serializable {

	private static final long serialVersionUID = -8304174176911618222L;
	private String customerId;
	private String transactionAmount;
	private String transactionType;
	private String accountType;
}

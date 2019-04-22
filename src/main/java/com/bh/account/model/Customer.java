/**
 * 
 */
package com.bh.account.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

	private static final long serialVersionUID = -1183281095235708141L;

	public String customerId;
	public String name;
	public String surName;
	public List<Account> accounts;
	public String errorMessage;


}

package com.bh.account.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements Serializable {

	private static final long serialVersionUID = 9175598266409620208L;

	public double balance;
	public String accountType;

}

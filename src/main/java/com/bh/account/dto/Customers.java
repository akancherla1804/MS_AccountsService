package com.bh.account.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customers implements Serializable {
	private static final long serialVersionUID = 8714829729274487265L;
	private Set<String> customerIds;

}

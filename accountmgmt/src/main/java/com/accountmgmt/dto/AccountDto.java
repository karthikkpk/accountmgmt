package com.accountmgmt.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author Karthik P
 *
 */
@Data
public class AccountDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String accName;
	private Integer accNo;
	private String address;
	private String phone;
	private Integer accType;
	private Date openDate;
	private Double balanceAmount;
	private Date createdDate;
	private Date modifiedDate;

}

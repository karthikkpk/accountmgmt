package com.accountmgmt.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author Karthik P
 *
 */
@Data
public class ResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private AccountDto accountDto;
	private List<AccountDto> accountDtoList;

}

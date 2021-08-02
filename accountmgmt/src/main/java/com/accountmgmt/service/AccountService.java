package com.accountmgmt.service;

import java.util.List;

import com.accountmgmt.dto.AccountDto;

public interface AccountService {

	List<AccountDto> getAccounts() throws Exception;

	AccountDto getAccByAccountNo(final Integer accNo) throws Exception;

	AccountDto saveUpdateAccount(final AccountDto accountDto) throws Exception;

	void deleteAccByAccountNo(final Integer accNo) throws Exception;

	void deleteAll() throws Exception;

	void saveUpdateAccountBulk(final List<AccountDto> accountDtoList) throws Exception;
}

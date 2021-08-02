package com.accountmgmt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountmgmt.dto.AccountDto;
import com.accountmgmt.model.Account;
import com.accountmgmt.repository.AccountRepository;
import com.accountmgmt.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<AccountDto> getAccounts() throws Exception {

		final List<AccountDto> accountDtoList = new ArrayList<AccountDto>();
		final List<Account> accounts = accountRepository.findAll();

		if (accounts == null || accounts.size() <= 0) {
			throw new Exception("No Records!");
		}

		for (Account account : accounts) {
			final AccountDto accountDto = new AccountDto();
			BeanUtils.copyProperties(account, accountDto);
			accountDtoList.add(accountDto);
		}

		return accountDtoList;
	}

	@Override
	public AccountDto getAccByAccountNo(final Integer accNo) throws Exception {

		final Account account = accountRepository.findByAccNo(accNo);

		if (account == null) {
			throw new Exception("No record!");
		}

		final AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(account, accountDto);

		return accountDto;
	}

	@Override
	public AccountDto saveUpdateAccount(AccountDto accountDto) throws Exception {

		final Account account = new Account();
		BeanUtils.copyProperties(accountDto, account);

		if (accountDto.getBalanceAmount() == null) {
			account.setBalanceAmount(0.0);
		}
		account.setCreatedDate(new Date());
		account.setModifiedDate(new Date());

		Account acc = accountRepository.save(account);

		if (acc != null) {
			BeanUtils.copyProperties(account, accountDto);
		}

		return accountDto;

	}

	@Override
	public void deleteAccByAccountNo(Integer accNo) throws Exception {
		accountRepository.deleteByAccNo(accNo);
	}

	@Override
	public void deleteAll() throws Exception {
		accountRepository.deleteAll();

	}

	@Override
	public void saveUpdateAccountBulk(List<AccountDto> accountDtoList) throws Exception {

		final List<Account> accountList = new ArrayList<Account>();
		for (AccountDto accountDto : accountDtoList) {

			final Account account = new Account();
			BeanUtils.copyProperties(accountDto, account);

			if (accountDto.getBalanceAmount() == null) {
				account.setBalanceAmount(0.0);
			}
			account.setCreatedDate(new Date());
			account.setModifiedDate(new Date());

			accountList.add(account);
		}

		if (accountList.size() > 0) {
			accountRepository.saveAll(accountList);
		}
	}
}

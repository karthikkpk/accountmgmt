package com.accountmgmt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accountmgmt.dto.AccountDto;
import com.accountmgmt.dto.ResponseDto;

/**
 * @author Karthik P
 *
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountControllerTests {

	@Autowired
	AccountController accountController;

	static List<AccountDto> updateAccDtoList = new ArrayList<AccountDto>();

	@BeforeAll
	static void before() {
		updateAccDtoList = new ArrayList<AccountDto>();
	}

	@ParameterizedTest
	@DisplayName("Save Account : (index) ")
	@MethodSource("saveAccountList")
	@Order(1)
	void saveAccountBulk(final AccountDto accountDto) {

		accountController.deleteAll();
		final ResponseDto responseDto = accountController.saveUpdateAccount(accountDto);

		Assertions.assertEquals(true, responseDto.getMessage().contains("SUCCESS"));
		Assertions.assertEquals(accountDto.getAccNo(), responseDto.getAccountDto().getAccNo());

		updateAccDtoList.add(responseDto.getAccountDto());
	}

	@ParameterizedTest
	@DisplayName("Error : Save Account : (index) ")
	@MethodSource("errorAccountList")
	@Order(2)
	void saveAccountBulkError(final AccountDto accountDto) {

		final ResponseDto responseDto = accountController.saveUpdateAccount(accountDto);
		Assertions.assertEquals(true, responseDto.getMessage().contains("ERROR"));
	}

	@Test
	@DisplayName("Get All")
	@Order(3)
	void getAccounts() {
		ResponseDto responseDto = accountController.getAccounts();
		Assertions.assertEquals(true, responseDto.getMessage().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Update Account Bulk ")
	@Order(4)
	void updateAccountBulk() {
		String result = accountController.saveUpdateAccountBulk(updateAccountList());
		Assertions.assertEquals(true, result.contains("SUCCESS"));
	}

	@Test
	@DisplayName("Error : Update Account Bulk ")
	@Order(5)
	void updateAccountBulkError() {
		String result = accountController.saveUpdateAccountBulk(null);
		Assertions.assertEquals(true, result.contains("ERROR"));
	}

	@ParameterizedTest
	@DisplayName("Account By Num : (index) ")
	@MethodSource("saveAccountList")
	@Order(6)
	void getAccByAccountNo(AccountDto accountDto) {
		ResponseDto responseDto = accountController.getAccByAccountNo(accountDto.getAccNo());
		Assertions.assertEquals(true, responseDto.getMessage().contains("SUCCESS"));
		Assertions.assertEquals(responseDto.getAccountDto().getAccNo(), accountDto.getAccNo());
	}

	@ParameterizedTest
	@DisplayName("Error : Account By Num : (index)")
	@ValueSource(ints = { 123, 4322, 555, 0, })
	@Order(7)
	void getAccByAccountNoError(int accNo) {
		ResponseDto responseDto = accountController.getAccByAccountNo(accNo);
		Assertions.assertEquals(true, responseDto.getMessage().contains("ERROR"));
	}

	@ParameterizedTest
	@DisplayName("Delete Account By Nume : (index)")
	@ValueSource(ints = { 1234567893 })
	@Order(8)
	void DeleteAccByAccountNo(int accNo) {
		String result = accountController.deleteAccByAccountNo(accNo);
		Assertions.assertEquals(true, result.contains("SUCCESS"));
	}

	@ParameterizedTest
	@DisplayName("Error : Delete Account By Num : (index) ")
	@ValueSource(ints = { 231, 0, })
	@Order(9)
	void DeleteAccByAccountNoError(int accNo) {
		String result = accountController.deleteAccByAccountNo(accNo);
		Assertions.assertEquals(true, result.contains("ERROR"));
	}

	@Test
	@DisplayName("Error : Update Account Bulk ")
	@Order(10)
	void updateAccountBulkError2() {
		String result = accountController.saveUpdateAccountBulk(errorAccountList());
		Assertions.assertEquals(true, result.contains("ERROR"));
	}

	@Test
	@DisplayName("Error Get All")
	@Order(11)
	void getAccountsError() {
		accountController.deleteAll();
		ResponseDto responseDto = accountController.getAccounts();
		Assertions.assertEquals(200, responseDto.getMessage().contains("ERROR"));
	}

	/**
	 * 
	 * @return
	 */
	private static List<AccountDto> saveAccountList() {
		final List<AccountDto> accountDtoList = new ArrayList<AccountDto>();

		AccountDto accountDto = new AccountDto();
		accountDto.setAccName("Karthik P");
		accountDto.setAccNo(1234567890);
		accountDto.setAccType(1);
		accountDto.setAddress("Kovilambakkam, Chennai");
		accountDto.setBalanceAmount(1000.0);
		accountDto.setOpenDate(new Date());
		accountDto.setPhone("9629731586");
		accountDtoList.add(accountDto);

		accountDto = new AccountDto();
		accountDto.setAccName("Deva K");
		accountDto.setAccNo(1234567891);
		accountDto.setAccType(1);
		accountDto.setAddress("Medavakkam, Chennai");
		accountDto.setBalanceAmount(1500.0);
		accountDto.setOpenDate(new Date());
		accountDto.setPhone("9629731586");
		accountDtoList.add(accountDto);

		accountDto = new AccountDto();
		accountDto.setAccName("Kavin R");
		accountDto.setAccNo(1234567892);
		accountDto.setAccType(0);
		accountDto.setAddress("Tharamani, Chennai");
		accountDto.setBalanceAmount(1200.0);
		accountDto.setOpenDate(new Date());
		accountDto.setPhone("9629731586");
		accountDtoList.add(accountDto);

		accountDto = new AccountDto();
		accountDto.setAccName("Mugil T");
		accountDto.setAccNo(1234567893);
		accountDto.setAccType(0);
		accountDto.setAddress("KK Nagar, Chennai");
		accountDto.setBalanceAmount(1200.0);
		accountDto.setOpenDate(new Date());
		accountDto.setPhone("962973145");
		accountDtoList.add(accountDto);

		return accountDtoList;
	}

	/**
	 * 
	 * @return
	 */
	private static List<AccountDto> updateAccountList() {

		final List<AccountDto> updateList = new ArrayList<AccountDto>();
		for (AccountDto accountDto : updateAccDtoList) {
			accountDto.setPhone("1234567891");
			updateList.add(accountDto);
		}
		return updateAccDtoList;
	}

	/**
	 * 
	 * @return
	 */
	private static List<AccountDto> errorAccountList() {

		final List<AccountDto> accountDtoList = new ArrayList<AccountDto>();

		AccountDto accountDto = new AccountDto();
		accountDtoList.add(accountDto);

		accountDtoList.add(null);

		return accountDtoList;
	}

}

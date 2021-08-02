package com.accountmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountmgmt.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByAccNo(final Integer accNo);

	void deleteByAccNo(final Integer accNo);

}

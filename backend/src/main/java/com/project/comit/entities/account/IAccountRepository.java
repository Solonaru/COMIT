package com.project.comit.entities.account;

import org.springframework.data.repository.CrudRepository;

public interface IAccountRepository extends CrudRepository<Account, Long> {
	
	public Account findByPersonalData_Name(String name);
	
}

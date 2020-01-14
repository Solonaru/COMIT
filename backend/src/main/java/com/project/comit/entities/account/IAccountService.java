package com.project.comit.entities.account;

import com.project.comit.entities.IEntityService;

public interface IAccountService extends IEntityService<Account, Long> {

	public Account findByPersonalData_Name(String name);
	
}

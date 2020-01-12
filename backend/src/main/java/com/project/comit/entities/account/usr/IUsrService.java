package com.project.comit.entities.account.usr;

import com.project.comit.entities.IEntityService;

public interface IUsrService extends IEntityService<Usr, Long> {

	public long getCount();
	
}

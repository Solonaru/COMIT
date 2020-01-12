package com.project.comit.entities.event;

import com.project.comit.entities.IEntityService;

public interface IEventService extends IEntityService<Event, Long> {

	public long getCount();

}

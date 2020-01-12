package com.project.comit.entities.event;

import org.springframework.data.repository.CrudRepository;

public interface IEventRepository extends CrudRepository<Event, Long> {
	public long count();
}

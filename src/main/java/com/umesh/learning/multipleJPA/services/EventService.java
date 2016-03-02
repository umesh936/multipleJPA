package com.umesh.learning.multipleJPA.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umesh.learning.multipleJPA.model.event.Event;
import com.umesh.learning.multipleJPA.repository.event.EventRepository;

@Service
@Transactional(value = "eventTransactionManager")
public class EventService {

	@Resource
	EventRepository eventRepo;

	public Event getEventById(Integer eid) {
		/*
		 * Some how Below method will not work. AS it is calling method from SimpleJPARepository class.
		 * So any method from SimpleJPARepository will not be called. like findAll()
		 * Still looking into it. 
		 * Event event = eventRepo.findOne(eid);
		 */
		Event event = eventRepo.findByEid(eid);
		return event;
	}

	public Event saveEvent(Event event) {
		event = eventRepo.save(event);
		return event;
	}
}

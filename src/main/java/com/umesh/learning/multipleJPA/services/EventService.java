package com.umesh.learning.multipleJPA.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umesh.learning.multipleJPA.model.Event;
import com.umesh.learning.multipleJPA.repository.EventRepository;

@Service
@Transactional("eventTransactionManager")
public class EventService {

	@Resource
	EventRepository eventRepo;

	public Event getEventById(Integer eid) {
		Event event = eventRepo.getOne(eid);
		return event;
	}

	public Event saveEvent(Event event) {
		event = eventRepo.saveAndFlush(event);
		return event;
	}
}

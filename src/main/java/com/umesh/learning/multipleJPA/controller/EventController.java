package com.umesh.learning.multipleJPA.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.learning.multipleJPA.model.Event;
import com.umesh.learning.multipleJPA.services.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Resource
	EventService eventService;

	@Resource
	ObjectMapper objectMap;

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public Object saveEvent(Event event) {
		event = eventService.saveEvent(event);
		return event;
	}
	
	@RequestMapping(value = { "/event" }, method = RequestMethod.GET)
	public Object getLoginPage(@RequestParam("eid") String eid) {
		Event event = eventService.getEventById(Integer.parseInt(eid));
		return event;
	}

}

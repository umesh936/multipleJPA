package com.umesh.learning.multipleJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umesh.learning.multipleJPA.model.Event;


public interface EventRepository extends JpaRepository<Event, Integer> {

}

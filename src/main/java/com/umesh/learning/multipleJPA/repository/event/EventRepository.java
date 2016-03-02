package com.umesh.learning.multipleJPA.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.umesh.learning.multipleJPA.model.event.Event;

@Repository
@Transactional(value = "eventTransactionManager")
public interface EventRepository extends JpaRepository<Event, Integer> {
	Event findByEid(Integer eid);
}

package com.umesh.learning.multipleJPA.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6996073155613069564L;

	private Integer eid;
	private String eventName;
	private String eventPlace;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "eid", unique = true, nullable = false, length = 20)
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@Column(name = "eventName", nullable = false, length = 20)
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Column(name = "eventPlace", nullable = false, length = 50)
	public String getEventPlace() {
		return eventPlace;
	}
	
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	@Override
	public String toString() {
		return "Event [eid=" + eid + ", eventName=" + eventName + ", eventPlace="
				+ eventPlace + "]";
	}

}

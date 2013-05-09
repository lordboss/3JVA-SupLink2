package com.supinfo.suplink.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ClickDate {

	@Id
	@GeneratedValue
	private Long id;
	
	private String date;
	private Long nbclick;
	
	@ManyToOne
	@JoinColumn(name = "shortlink_id")
	private Shortlink shortlink;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public Long getNbclicks() {
		return nbclick;
	}

	public void setNbclicks(Long nbclick) {
		this.nbclick = nbclick;
	}
		
	public Shortlink getShortlink() {
		return shortlink;
	}
	
	public void setShortlink(Shortlink shortlink) {
		this.shortlink = shortlink;
	}
	
}

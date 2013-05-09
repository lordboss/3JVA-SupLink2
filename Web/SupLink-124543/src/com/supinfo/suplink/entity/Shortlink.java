package com.supinfo.suplink.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Shortlink {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String url;
	private String shorturl;
	private Integer nbclicks;
	private String creationdate;
	private Integer enabled;

	@OneToMany(mappedBy="shortlink")
	private List<Referrer> referrers;
	
	@OneToMany(mappedBy="shortlink")
	private List<Country> countries;

	@OneToMany(mappedBy="shortlink")
	private List<ClickDate> clickdates;


	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	
	public Integer getNbclicks() {
		return nbclicks;
	}

	public void setNbclicks(Integer nbclicks) {
		this.nbclicks = nbclicks;
	}
	
	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}

package com.supinfo.suplink.entity;

import java.util.List;

public class Statistics {
	private Shortlink shortlink;
	private List<ClickDate> clickdate;
	private List<Country> country;
	private List<Referrer> referrer;
	
	public List<Referrer> getReferrer() {
		return referrer;
	}
	public void setReferrer(List<Referrer> referrer) {
		this.referrer = referrer;
	}
	public List<Country> getCountry() {
		return country;
	}
	public void setCountry(List<Country> country) {
		this.country = country;
	}
	public List<ClickDate> getClickdate() {
		return clickdate;
	}
	public void setClickdate(List<ClickDate> clickdate) {
		this.clickdate = clickdate;
	}
	public Shortlink getShortlink() {
		return shortlink;
	}
	public void setShortlink(Shortlink shortlink) {
		this.shortlink = shortlink;
	}

}

package com.supinfo.suplink.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.supinfo.suplink.entity.Shortlink;
import com.supinfo.suplink.entity.Statistics;
import com.supinfo.suplink.dao.ShortlinkDao;

@Path("/users/{userid}/links")
public class ShortlinkResource {	
	
	public ShortlinkResource() {}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Shortlink> getAllShortlinks(@PathParam("userid") Long userid) {
		return ShortlinkDao.getAllShortlinks(userid);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/{shorturl}")
	public Shortlink getShortlink(@PathParam("shorturl") String shorturl) {
		Statistics statistics = ShortlinkDao.getShortlink(shorturl);
		return statistics.getShortlink();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/{shorturl}/go")
	public Shortlink goShortlink(@PathParam("shorturl") String shorturl, @Context HttpServletRequest request) {
		Shortlink shortlink = ShortlinkDao.goShortlink(shorturl, request);
		return shortlink;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Shortlink addShortlink(@PathParam("userid") Long userid, Shortlink shortlink) {
		return ShortlinkDao.addShortlink(userid, shortlink);
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/{shortlinkid}")
	public void modShortlink(@PathParam("userid") Long userid, @PathParam("shortlinkid") Long shortlinkid, Shortlink shortlink) {
		ShortlinkDao.modShortlink(userid, shortlinkid, shortlink);
		return;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/{shortlinkid}")
	public void delShortlink(@PathParam("userid") Long userid, @PathParam("shortlinkid") Long shortlinkid) {
		ShortlinkDao.delShortlink(userid, shortlinkid);
		return;
	}
}
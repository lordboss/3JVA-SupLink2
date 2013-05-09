package com.supinfo.suplink.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.supinfo.suplink.entity.User;
import com.supinfo.suplink.dao.UserDao;

@Path("/users")
public class UserResource {
	
	public UserResource() {}

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User getUserByAuth(User user) {
		return UserDao.getUserByAuth(user);
	}
	
}


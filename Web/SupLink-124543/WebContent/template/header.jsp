<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SupLink - Welcome</title>
</head>
<body>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container" style="width: 800px">
<!--           <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
 -->          <a class="brand" style="float: right" href="<%= application.getContextPath() %>/index"><b>SupLink</b> - Another URL Shortener</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="active">
                <a href="./index.html">Home</a>
              </li>
              <li class="">
                <a href="<%= application.getContextPath() %>/index">Shortlinks list</a>
              </li>
              <% if(session.getAttribute("userid") != null) { %>
              <li class="">
				<a href="<%= application.getContextPath() %>/logout">Logout</a>
			  </li>
			<% } else { %>
			 <li class="">
				<a href="<%= application.getContextPath() %>/login">Login</a>
			 </li>
			 <li class="">
			  	<a href="<%= application.getContextPath() %>/register">Register</a>
			  </li>
			<% } %>
			  </li>
            </ul>
          </div>
        </div>
        
        
<%-- 	  <div class="container">
	    <a class="brand" href="<%= application.getContextPath() %>"><b>SupLink</b> - Another URL Shortener</a>
 	    <div style="float: right">
 	    	<a class="brand" href="<%= application.getContextPath() %>/index">Shortlinks list</a> |

			<% if(session.getAttribute("userid") != null) { %>
				<a class="brand" href="<%= application.getContextPath() %>/logout">Logout</a>
			<% } else { %>
				<a class="brand" href="<%= application.getContextPath() %>/login">Login</a>
			<% } %>
	    </div>
	  </div>
 --%>	</div>
</div>

<%-- <h1>
	<a href="<%= application.getContextPath() %>"> SupLink - Another URL Shortener</a>
</h1> --%>

<!-- <p>
 -->	<%-- <a href="<%= application.getContextPath() %>/index">Shortlinks list</a> |

	<% if(session.getAttribute("userid") != null) { %>
		<a href="<%= application.getContextPath() %>/logout">Logout</a>
	<% } else { %>
		<a href="<%= application.getContextPath() %>/login">Login</a>
	<% } %> --%>
<!-- </p>
 -->
</nav>
<div id="content" style="margin-top: 100px; width:800px; margin-left: auto; margin-right: auto">
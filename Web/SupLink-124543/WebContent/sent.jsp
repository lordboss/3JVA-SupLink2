<%@ include file="/template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<h1>Mail sent</h1>

Your register confirmation email has been sent to <c:out value="${mail}" /> !<br/>
<c:out value="${mailmsg}" /><br/>
<a href="login">Click here to login</a>.<br/><br/>
	
<%@ include file="/template/footer.jsp" %>
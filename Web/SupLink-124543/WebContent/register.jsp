<%@ include file="/template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<h3>Register</h3>

<form action="register" method="post" class="form-horizontal">
  <div class="control-group">
    <label class="control-label" for="inputEmail">Email</label>
    <div class="controls">
      <input name="email" type="text" id="inputEmail" placeholder="Email">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">Password</label>
    <div class="controls">
      <input name="password" type="password" id="inputPassword" placeholder="Password">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputConfirmPassword">Confirm password</label>
    <div class="controls">
      <input name="confirmpassword" type="password" id="inputConfirmPassword" placeholder="Confirm password">
    </div>
  </div>
  <div class="form-actions">
  	<button type="submit" class="btn btn-primary">Register</button>
  </div>
</form>

	
<%@ include file="/template/footer.jsp" %>
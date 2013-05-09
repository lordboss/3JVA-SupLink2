<%@ include file="/template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<h3>Login</h3>

<form action="login" method="post" class="form-horizontal">
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
  <div class="form-actions">
  	<button type="submit" class="btn btn-primary">Login</button>
  </div>
</form>

<%@ include file="/template/footer.jsp" %>
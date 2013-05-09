<%@ include file="/template/header.jsp" %>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />


<h3>Make a shortlink</h3>

<form id="makeshortlink" action="index" method="post">
	<label for="inputName">Name</label>
	<input name="name" type="text" id="inputName" placeholder="Name"><br/>
    <label for="inputUrl">URL</label>
    <input name="url" type="text" id="inputUrl" value="http://">
  <div class="form-actions">
  	<button type="submit" class="btn btn-primary">Generate</button>
  </div>
</form>


<div id="shortlinkslist">
<h3>Shortlinks list</h3>

<c:if test="${!empty shortlinks}">

	<table class="table table-hover">
		<tr>
			<th>Name</th>
			<th>Original URL</th>
			<th>Shortened URL</th>
			<th>Clicks</th>
			<th>Creation date</th>
			<th> </th>
			<th> </th>
		</t>
		<c:forEach items="${shortlinks}" var="s">
		<tr id="${s.shorturl}">
			<td><c:out value="${s.name}" /> </td>
			<td><c:out value="${s.url}" /> </td>
			<td><a href="${pageContext.request.contextPath}/${s.shorturl}"><c:out value="http://suplink.com/${s.shorturl}" /></a></td>
			<td><c:out value="${s.nbclicks}" /> </td>
			<td><c:out value="${s.creationdate}" /> </td>
			<td>
				<form method="post" action="disableShortlink">
					<input type="hidden" name="shortlinkid" value="${s.id}" />
					
					<c:if test="${s.enabled == 1}">
						<input type="hidden" name="shortlinkaction" value="0" />
						<input class="btn btn-warning" type="submit" value="Disable" />
					</c:if>
					<c:if test="${s.enabled == 0}">
						<input type="hidden" name="shortlinkaction" value="1" />
						<input class="btn btn-success" type="submit" value="Enable" />
					</c:if>
				</form>
			</td>
		
			<td>
				<form method="post" action="removeShortlink">
					<input type="hidden" name="shortlinkid" value="${s.id}" />
					<input class="btn btn-danger" type="submit" value="Remove" />
				</form>
			</td>
			
		</tr>
		</c:forEach>
	</table>
	</div>
	
	<script type="text/javascript">
	$("tr").each(function() {
		var shorturl = $(this).attr("id");
		$(this).click(function(){
	        location.href = '${pageContext.request.contextPath}/link/'+shorturl;
		});
	 });
	</script>

</c:if>

<c:if test="${empty shortlinks}">
	No result.<br/>
</c:if>

<%@ include file="/template/footer.jsp" %>
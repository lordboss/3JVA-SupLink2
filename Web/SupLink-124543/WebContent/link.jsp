<%@ include file="/template/header.jsp" %>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/highcharts.js"></script>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css" />

<c:set var="baseURL" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

<div id="count"><b><c:out value="${shortlink.nbclicks}" /></b> <span>clicks</span></div>
<h2 id="linktitle"><c:out value="${shortlink.name}" /></h2>

<ul class="breadcrumb">
  <li><a href="${baseURL}/${shortlink.shorturl}"><c:out value="${shortlink.url}" /></a> <span class="divider">/</span></li>
  <li><a href="${baseURL}/${shortlink.shorturl}">http://suplink.com/${shortlink.shorturl}</a></li>
</ul>

<c:if test="${!empty clickdate}">

	<script type="text/javascript">
		$(document).ready(function() {
			var categories = [<c:forEach items="${clickdate}" var="cd">
								'<c:out value="${cd.date}" />',
							</c:forEach>];
			var data = [<c:forEach items="${clickdate}" var="cd">{
							y: <c:out value="${cd.nbclicks}" />,
						    color: "#123456"
						},
						</c:forEach>];
			ColumnChart(categories, data);
			
	
		});
	</script>
	<script type="text/javascript" src="../js/chartcolumns.js"></script>
	
	<script type="text/javascript" src="../js/chartpie.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var data = [<c:forEach items="${referrer}" var="r">
			['<c:out value="${r.referrer}" />', <c:out value="${r.nbclicks}" />],
		</c:forEach>];
		PieChart(data, 'pieref', 'Referrer');
	});
	</script>
	
		<script type="text/javascript">
	$(document).ready(function() {
		var data = [<c:forEach items="${country}" var="c">
			['<c:out value="${c.country}" />', <c:out value="${c.nbclicks}" />],
		</c:forEach>];
		PieChart(data, 'piecountry', 'Country');
	});
	</script>
	
	
	
	<div id="columns"></div>
	<div id="pies">
		<div id="pieref" ></div>		
		<div id="piecountry"></div>
		
		<div id="tables">
			<table class="table table1">
				<tr>
					<th>Referrer</th>
					<th>Clicks</th>
				</tr>
				<c:forEach items="${referrer}" var="r">
				<tr>
					<td><c:out value="${r.referrer}" /></td>
					<td><c:out value="${r.nbclicks}" /></td>
				</tr>
				</c:forEach>
			</table>
			
			<table class="table table2">
				<tr>
					<th>Country</th>
					<th>Clicks</th>
				</tr>
				<c:forEach items="${country}" var="r">
				<tr>
					<td><c:out value="${r.country}" /></td>
					<td><c:out value="${r.nbclicks}" /></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div id="clear"></div>
		
	</div>
</c:if>

<c:if test="${empty clickdate}">
	No statistic data yet.<br/>
</c:if>


<%@ include file="/template/footer.jsp" %>
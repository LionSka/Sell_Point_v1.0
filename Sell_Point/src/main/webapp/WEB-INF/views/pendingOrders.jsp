<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thoi ®</title>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="<c:url value="/resources/js/jquery/jquery.dataTables.js" />" charset="utf-8" type="text/javascript" ></script>
<script src="<c:url value="/resources/js/pendingOrders.js" />" charset="utf-8" type="text/javascript" ></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
 
 
<link href="<c:url value="/resources/styles/pendingOrders.css" />" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/styles/pendingOrders_table.css" />" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

</head>
<body>
	<table id="mainContentTable" width="100%">
		<tr>
			<td valign="top" style="background-color: yellow;">
				<input type="button" value="Refrescar" id="refreshButton"/>
			</td>
		</tr>
		<tr>
			<td valign="top" style="background-color: yellow;">
				<div id="demo" style="width: 500px; padding: 10px 10px 30px 10px; margin: 10px; border: 1px solid grey">
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="ordersListTable" width="100%">
						<thead>
							<tr>
								<th width="20%"># Orden</th>
								<th width="40%">Fecha/Hora</th>
								<th width="20%">Usuario</th>
								<th width="20%">Estado</th>
							</tr>
						</thead>
						<tbody> </tbody>
					</table>
				</div>
			</td>
		</tr>		
	</table>
</body>
</html>
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
<script src="<c:url value="/resources/js/CreateOrderJS.js" />" charset="utf-8" type="text/javascript" ></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
 
 
<link href="<c:url value="/resources/styles/createOrderStyle.css" />" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/styles/demo_table.css" />" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!--link href="<c:url value="/resources/styles/jquery-ui-1.8.22.custom.css" />" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/styles/jquery.datatables.css" />" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/styles/demo_table_jui.css" />" rel="stylesheet" type="text/css"/-->

<!--script src="<c:url value="/resources/js/CreateOrderJS.js" />" charset="utf-8" type="text/javascript" ></script>
<script src="<c:url value="/resources/js/jquery/jquery.js" />" charset="utf-8" type="text/javascript" ></script-->
<!-- script src="<c:url value="/resources/js/jquery/jquery.dataTables.min.js" />" charset="utf-8" type="text/javascript" ></script -->
<!--script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script-->


</head>
<body>
	<table id="mainContentTable">
		<tr>
			<td valign="top" style="background-color: yellow;">

				<div style="width: 700px; padding: 20px;">

					<table id="buttonsTable" width="100%">
						<c:set var="count" value="0" />
						
						<!-- 
						Toma la lista enviada desde el controlador y 
						crea un botón para cada elemento de la lista
						-->
						<c:forEach items="${productList}" var="item" varStatus="counter">

							<!--
			   				Inicia la fila si el contador es 1
							-->
							<c:set var="count" value="${count + 1}" />
							<c:if test="${count==1}">
								<tr>
							</c:if>

							<!-- 
			   				Inserta los productos como td's
							-->
							<td>
								<div class="productButtom">
									<table width="100%" height="60px">
										<tr>
											<td id="nom_Prod"><c:out value="${item.getNom_Prod()}" /></td>
										</tr>
										<tr>
											<td valign="bottom"><span id="precio"><c:out value="${item.getPrecio()}" /></span></td>
										</tr>
									</table>
								</div>
							</td>

							<!-- 
			   				Despues de agregar 3 td's cierra la fila e inicia una nueva
							-->
							<c:if test="${count==5}">
								</tr>
								<c:set var="count" value="0" />
							</c:if>
						</c:forEach>
					</table>
					<!-- Final de bottonsTable -->
				</div>

			</td>
			<td valign="top" align="center" style="background-color: yellow; ">
			
				<div style="margin: 10px;border:1px solid grey;padding: 10px 0px">
					<table width="100%" id="controlPanelForm">
						<tr>
							<td align="center"><div id="payButton" class="controlPanelButton">Pagar</div></td>
							<td align="center"><div class="controlPanelButton">Borrar</div></td>
							<td align="center"><div class="controlPanelButton">Borrar todo</div></td>								
						</tr>
					</table>
				</div>
				<!-- Fin del panel de control -->
				
				<div id="demo" style="width: 500px; padding: 10px 10px 30px 10px;margin: 10px;border:1px solid grey">
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="productsListTable" width="100%">
						<thead>
							<tr>
								<th>Cantidad</th>
								<th width="50%">Producto</th>
								<th>Unidad</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<!--tr class="odd gradeX">
								<td>2</td>
								<td>Pizza gr.</td>
								<td class="center">7500</td>
								<td class="center">15000</td>
							</tr -->
						</tbody>
						<!--tfoot>
							<tr>
								<th>Rendering engine</th>
								<th>Browser</th>
								<th>Platform(s)</th>
								<th>Engine version</th>
							</tr>
						</tfoot -->
					</table>
				</div>
				<!-- Fin de Tabla de productos ordenados -->
				
				<div style="font-size: 18px; width: 500px; padding: 5px 10px; border:1px solid grey; margin: 10px;">
					<table id="totalToPayInfo" width="100%">
						<tr>
							<td>
								<span>Sub Total</span>
							</td>
							<td align="right">
								<span id="subTotalLabel">0.00</span>
							</td>
						</tr>
						<tr>
							<td>
								<span>Descuento</span>
							</td>
							<td align="right">
								<span id="discountLabel">0.00</span>
							</td>
						</tr>
						<tr>
							<td>
								<span>Total</span>
							</td>
							<td align="right">
								<span id="totalLabel">0.00</span>
							</td>
						</tr>
					</table>				
				</div>
				<!-- Fin de información de total -->
			</td>
		</tr>
	</table>
	<!-- Final de mainContentTable -->
	
	<!-- Dialogs forms -->
	<div id="dialog-form" title="Cantidad">
		<p class="validateTips">Indique la cantidad de unidades.</p>
		<form>
			<fieldset>
				<label for="label_quantity">Cantidad:</label> 
				<input type="text" name="tbx_quantity" id="tbx_quantity" value="1" maxlength="10" class="text ui-widget-content ui-corner-all" style="margin-bottom: 10px;"/>
				<table>
					<tr>
						<td><div class="quantityDialogKey">1</div></td>
						<td><div class="quantityDialogKey">2</div></td>
						<td><div class="quantityDialogKey">3</div></td>
					</tr>
					<tr>
						<td><div class="quantityDialogKey">4</div></td>
						<td><div class="quantityDialogKey">5</div></td>
						<td><div class="quantityDialogKey">6</div></td>
					</tr>
					<tr>
						<td><div class="quantityDialogKey">7</div></td>
						<td><div class="quantityDialogKey">8</div></td>
						<td><div class="quantityDialogKey">9</div></td>
					</tr>
					<tr>
						<td><div id="quantityDialogKeyClean" class="quantityDialogKey">Limpiar</div></td>
						<td><div class="quantityDialogKey">0</div></td>
						<td><div id="quantityDialogKeyErase" class="quantityDialogKey">Borrar</div></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	
	<div id="paymentMethodForm" title="Pagar" style="font-size: 13px">
		<p class="validateTips"></p>
		<form>
			<fieldset>
				<table width="100%">
					<tr>
						<td align="left" style="height: 30px">
							<input id="inSiteRadio" name="orderType" class="orderTypeRadio" type="radio" value="S" checked="checked"> Normal
						</td>
						<td align="center">
							<input id="toCarryRadio" name="orderType" class="orderTypeRadio" type="radio" value="C"> Llevar
						</td>
						<td align="right">
							<input id="xpressRadio" name="orderType" class="orderTypeRadio" type="radio" value="E" > Express
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr style="background-color: rgb(204, 204, 204)">
						<td width="170px"><span style="font-size:18px;font-weight: bold">Total:</span></td>
						<td align="center"><span id="total" style="font-size:18px;font-weight: bold">0.00</span></td>
					</tr>
					<tr>
						<td style="height: 30px"><span>Forma de pago</span></td>
						<td>
							<input id="cashRadio" name="paymentMethod" type="radio" checked="checked"> Efectivo
							<input id="cardRadio" name="paymentMethod" type="radio"> Tarjeta
						</td>
					</tr>
					<tr>
						<td><span>Cancela con</span></td>
						<td><input type="text" name="tbx_cashPayment" id="tbx_cashPayment" style="margin-bottom: 10px;" readonly="readonly"/></td>
					</tr>
					<tr>
						<td><span>Cambio</span></td>
						<td><input type="text" name="change" id="change" style="margin-bottom: 10px;" readonly="readonly"/></td>
					</tr>
				</table>
				<table width="100%" id="paymentMethodKeyboard">
					<tr>
						<td align="center"><div class="paymentMethodDialogNumberKey">1</div></td>
						<td align="center"><div class="paymentMethodDialogNumberKey">2</div></td>
						<td align="center"><div class="paymentMethodDialogNumberKey">3</div></td>
					</tr>
					<tr>
						<td align="center"><div class="paymentMethodDialogNumberKey">4</div></td>
						<td align="center"><div class="paymentMethodDialogNumberKey">5</div></td>
						<td align="center"><div class="paymentMethodDialogNumberKey">6</div></td>
					</tr>
					<tr>
						<td align="center"><div class="paymentMethodDialogNumberKey">7</div></td>
						<td align="center"><div class="paymentMethodDialogNumberKey">8</div></td>
						<td align="center"><div class="paymentMethodDialogNumberKey">9</div></td>
					</tr>
					<tr>
						<td align="center"><div id="paymentMethodDialogCleanKey" class="paymentMethodDialogNumberKey">Limpiar</div></td>
						<td align="center"><div class="paymentMethodDialogNumberKey">0</div></td>
						<td align="center"><div id="paymentMethodDialogEraseKey" class="paymentMethodDialogNumberKey">Borrar</div></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	
</body>
</html>
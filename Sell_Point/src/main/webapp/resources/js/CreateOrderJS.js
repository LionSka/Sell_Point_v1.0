var oTable;
var selectedRow = 'null';
var products_quantity = 'null';
var pName='null';
var pPrice='null';
var totalToPay=0;

$(function() {
	
	/****************************************************************************/
	/************** Init jquery components **************************************/
	/****************************************************************************/
	//Init productsListTable
	oTable = $('#productsListTable').dataTable( {
		"bLengthChange": false,
		"iDisplayLength" : 15,
		"bFilter": false
    });	

	// Init dialog-form
	$("#dialog-form").dialog(
	{
		autoOpen : false,
		height : 530,
		width : 390,
		modal : true,
		buttons : {
			"Aceptar" : function() {
				acceptQuantityDialogEvent($(this));
			},
			Cancel : function() {
				products_quantity='null';
				pName='null';
				pPrice='null';

				$(this).dialog("close");
			}
		},
		close : function() {
			$("#tbx_quantity").val("1").removeClass("ui-state-error");
		}
	});
	
	// Init paymentMethodForm
	$("#paymentMethodForm").dialog(
	{
		autoOpen : false,
		height : 550,
		width : 400,
		modal : true,
		buttons : {
			"Aceptar" : function() {
				acceptPaymentMethodForm($(this));
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			$("#change").val("");
			$("#tbx_cashPayment").val("");
		}
	});
	
	
	/**********************************************************************/
	/************** Component events **************************************/
	/**********************************************************************/
	
	// Product buttons click event
	$(".productButtom").click(function(e) {
		pName=$(this).find("#nom_Prod").html();
		pPrice=$(this).find("#precio").html();
		
		$("#dialog-form").dialog("open");		
	});	
	// QuantityDialog buttons click event
	$(".quantityDialogKey").click(function(e) {
		
		if($(this).attr('id')=="quantityDialogKeyClean"){
			$("#tbx_quantity").val("");
			return;
		}
		if($(this).attr('id')=="quantityDialogKeyErase"){
			$("#tbx_quantity").val("");
			return;
		}		
		var quantityText=$("#tbx_quantity").val();
		if(quantityText.length==10){
			e.preventDefault();
			return;
		}		
		$("#tbx_quantity").val($("#tbx_quantity").val() +""+ $(this).html());		
	});
	
	
	// Open Payment Method button
	$("#payButton").click(function() {
		if((oTable.fnGetData().length)>0){
			$("#total").html(totalToPay);
			$("#paymentMethodForm").dialog("open");
		}else{
			alert("No existen productos agregados");
		}
	});	
	// Payment method dialog buttons click event
	$(".paymentMethodDialogNumberKey").click(function(e) {
		if($(this).attr('id')=="paymentMethodDialogCleanKey"){
			$("#tbx_cashPayment").val("");
			$("#change").val("");
			return;
		}
		if($(this).attr('id')=="paymentMethodDialogEraseKey"){
			$("#tbx_cashPayment").val("");
			$("#change").val("");
			return;
		}
		$("#tbx_cashPayment").val($("#tbx_cashPayment").val()+""+$(this).html());
		calculateChange($(this).html());
	});
	// Payment method Radios events
	$("#cashRadio").change(function() {
		//if($(this).attr("checked")){
			$('#tbx_cashPayment').removeAttr('disabled');
			$('#change').removeAttr('disabled');
			$("#paymentMethodKeyboard").show("500");
			
			$('#tbx_cashPayment').val('');
            $('#change').val('');
        //}	
	});
	$("#cardRadio").change(function() {
		//if($(this).attr("checked")){
			$('#tbx_cashPayment').attr('disabled', 'disabled');
            $('#change').attr('disabled', 'disabled');
            $("#paymentMethodKeyboard").hide("500");
            
            $('#tbx_cashPayment').val('Tarjeta');
            $('#change').val('0');
        //}	
	});
	
	// Table click event
	/*$('#productsListTable').find('tr').click(function(event) {

		$(oTable.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});

		$(event.target.parentNode).addClass('row_selected');
	});*/
	
});

/* Get the rows which are currently selected */
function fnGetSelected(oTableLocal) {
	var aReturn = new Array();
	var aTrs = oTableLocal.fnGetNodes();

	for ( var i = 0; i < aTrs.length; i++) {
		if ($(aTrs[i]).hasClass('row_selected')) {
			aReturn.push(aTrs[i]);
		}
	}
	alert(anSelected[0]);
	return aReturn;
}


/* Quantity dialog accept event */
function acceptQuantityDialogEvent(window) {
	
	var bValid = true;
	$("#tbx_quantity").removeClass("ui-state-error");
		
	bValid = bValid && checkLength($("#tbx_quantity"), "tbx_quantity", 1, 4 );
	bValid = bValid && checkRegexp($("#tbx_quantity"), /^([1-9])+$/, "Por favor indique una cantidad correcta");
	
	if (bValid) {
		products_quantity=$("#tbx_quantity").val();
		
		if(products_quantity!='null'){
			$('#productsListTable').dataTable().fnAddData( 
					[products_quantity, pName, pPrice, (pPrice*products_quantity) ] 
			);
			calculatesTotalToPay("+",(pPrice*products_quantity))
		}
		products_quantity='null';
		pName='null';
		pPrice='null';

		window.dialog("close");
	}
}

/* Event method for paymentMethodForm */
function acceptPaymentMethodForm(window){
	
	
	
}
function calculateChange(number){
	
	var cash=$("#tbx_cashPayment").val();
	var total=$("#total").html();
	
	if(parseInt(cash) >= parseInt(total)){
		$("#change").val((cash-total));
	}else{
		$("#change").val("Efectivo insuficiente");
	}
}

/* Validation methods */
function checkRegexp(o, regexp, n) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips("Cantidad invalida.");
		return false;
	} else {
		return true;
	}
}
function checkLength(o, n, min, max) {	
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips("El valor excede el límite soportado");
		return false;
	} else {
		return true;
	}	
}
function updateTips(t) {
	$(".validateTips").text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}

/**
 * Calculates and sets the amount to pay
 * @param operation must be "+" for sum up the amount or "-" for deduct the amount
 * @param amount the amount to +/
 */
function calculatesTotalToPay(operation, amount){
		
	if(operation=="+"){
		totalToPay=totalToPay + parseInt(amount);
	}
	else if(operation=="-"){		
		totalToPay=(parseInt(totalToPay - amount)<0)?0:(totalToPay - amount);
	}
	
	$("#subTotalLabel").html(totalToPay);
	$("#totalLabel").html(totalToPay);
}

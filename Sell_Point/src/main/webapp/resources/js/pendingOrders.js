var oTable;
var selectedRow = 'null';


$(function() {
	
	/****************************************************************************/
	/************** Init jquery components **************************************/
	/****************************************************************************/
	//Init productsListTable
	oTable = $('#ordersListTable').dataTable( {
		"bLengthChange": false,
		"iDisplayLength" : 15,
		"bFilter": false,
		"bDestroy": true
    });
	
	/**********************************************************************/
	/************** Component events **************************************/
	/**********************************************************************/
	
	
	// Table click event
	/*$('#productsListTable').find('tr').click(function(event) {

		$(oTable.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row_selected');
		});

		$(event.target.parentNode).addClass('row_selected');
	});*/
	
});

/**
 *  Get the rows which are currently selected 
 */
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


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Book Order</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />

<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<!-- <link rel="stylesheet" href="assets/css/popup.css">
<link rel="stylesheer" href="assets/dist/css/semantic.css">
<link rel="stylesheet" href="assets/dist/css/semantic.min.css"> -->
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<link href="custom/chosen/chosen.min.css" rel="stylesheet" />
<!-- <link href="custom/chosen/chosen-bootstrap.css" rel="stylesheet" /> -->


<style type="text/css">
.box {
	position: relative;
	border-radius: 3px;
	background: #ffffff;
	border-top: 3px solid #d2d6de;
	margin-bottom: 0px;
	width: 100%;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 0px;
}

.input-group-addon {
	padding: 3px;
}

.form-control {
	display: block;
	width: 100%;
	height: 25px;
	/* padding: 6px 12px; */
	padding: 0px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}
</style>
<style type="text/css">
/*
@media (max-width: 1200px) { 
    body    {
        font-size: 12px !important;
    }
    
*/
.sidebar-menu .treeview-menu>li>a {
	font-size: 13px;
}

.section {
	font-size: 12px !important;
}

}
.box-title {
	color: #5f9ea0;
}

.table {
	margin: 0px;
	letter-spacing: 0px;
	word-spacing: 0px;
}

.dropdown-menu>li>a {
	color: #f5f5f5;
	background-color: #656a6b;
}

/* .btn {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
	width: 100px;
	margin-top: 0px;
	background: #3d85c6;
	background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
	background: linear-gradient(#1770c1, #073763 50%, #1770c1);
	color: #fff;
	border: 1px solid;
}

.btn:hover {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
	width: 100px;
	margin-top: 0px;
	background: #3d85c6;
	background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
	background: linear-gradient(#1770c1, #073763 50%, #1770c1);
	color: #fff;
	border: 1px solid;
} */

/* label {
	font-weight: 500;
} */
input {
	padding-left: 5px;
}

.input {
	padding-left: 5px;
}

#parent {
	height: 300px;
}

input.wait {
	background: white url('ajax-loader.gif') no-repeat right center
}

.redborder {
	border: 1px solid red;
}

/* anchar tag disable 
.disable_a_href {
	pointer-events: none; *
	*color: #D3D3D3 !important;
	**
}*/

/* to make the upload slip as link */
.fileUpload {
	position: relative;
	overflow: hidden;
	margin: 2px;
}

.fileUpload input.upload {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity = 0);
}
</style>

<script type="text/javascript"
	src="assets/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jQueryUI/jquery-ui.js"></script>
<script type="text/javascript" src="assets/tableHeadFixer.js"></script>
<%-- <script type="text/javascript" src="assets/dist/js/semantic.js"></script>
<script type="text/javascript" src="assets/dist/js/semantic.min.js"></script> --%>

<!-- Bootstrap 3.3.6 -->
<script type="text/javascript" src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script type="text/javascript" src="Starter_files/app.js"></script>

<!-- Select2 -->
<%-- <script type="text/javascript" src="assets/plugins/select2/select2.full.min.js"></script>
<!-- InputMask -->
<script type="text/javascript" src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script type="text/javascript"
	src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script type="text/javascript" src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script type="text/javascript" src="assets/moment.min.js"></script>
<script type="text/javascript" src="assets/plugins/daterangepicker/daterangepicker.js"></script> --%>
<!-- bootstrap datepicker -->
<script type="text/javascript"
	src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="custom/format.js"></script>
<script src="custom/chosen/chosen.jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {

$('#tranDiscPerTxt').on('paste keyup', function(event){

 /*    var number = ($(this).val().split('.'));
   if (number[1] && number[1].length > 2)
   {
    var discPrcntg = parseFloat($("#tranDiscPerTxt").val());
   	var deciVal = number[0]+"."+number[1].substr(0,2);
    $("#tranDiscPerTxt").val(deciVal);
   } */
});
});
</script>
<script type="text/javascript">
$(document).ready(function() {
$('#editModalItemNewDiscPer').on('paste keyup', function(event){
	
   /*  var number = ($(this).val().split('.'));
   if (number[1] && number[1].length > 2)
   {
    var discPrcntg = parseFloat($("#editModalItemNewDiscPer").val());
    var deciVal = number[0]+"."+number[1].substr(0,2);
    $("#editModalItemNewDiscPer").val(deciVal);
   } */
});
});
</script>
<script type="text/javascript">




    
$.fn.datepicker.defaults.format ="<s:property value="getText('datepicker.defaults.format')"/>";
	//added by hanu Start
	
	//function to validate the Uploaded LPO Slip
	$(function(){
  $('#slip').change(function(){
	var name="";
    var url = $(this).val();
    if(url != "")
    {
	    var fileExtension = "";
	    var allowedfileTypes="<s:property value="getText('global.fileTypesAllowed')"/>";
	    if (url.lastIndexOf(".") > 0) {
	        fileExtension = url.substring(url.lastIndexOf(".") + 1, url.length);
	    }
	    if (allowedfileTypes.indexOf(fileExtension.toLowerCase()) > -1) {
	    	 name=url.replace(/C:\\fakepath\\/i, '')
	    	    var inner=$('#sliplink').html(name)
	        return true;
	    }
	    else {
	      /*  alert("You must select a GIF file for upload"); */
	        $('#lpoSlipModel').modal('show');
	      $("#slip").val('');
	       var inner=$('#sliplink').html('Upload LPO'); 
	        return false;
	    }
    }

  });

});
    var enableDoubleDiscounts = false;
    var anyItemLevelManualDiscuntsApplyed = false;
    var anyTranLevelManualDiscuntsApplyed = false;
	var compId = "";
    var isQuoteClicked ="false";
	var tranLevDiscAmt=0;
	var tranLevDiscPer=0;
	
	var discReasonCodes = ${itemEditDiscPerReasonCodes};
	var discReasonCodeOptions ='';
	var dateFormat= "<s:text name="bootstrap.date.format"/>";
	var currencyFormat = "<s:property value="getText('format.currency')"/>";
	 $.each( discReasonCodes, function(index,value){
	 discReasonCodeOptions+= '<option value="'+index+'" style="font-weight:bold">'+value+'</option>';
	});
	$(function() {
		$('#mainForm').submit(function () { $('[disabled]').removeAttr('disabled'); }) 
		$("#ef_datepicker").datepicker({
		    todayBtn: "linked",
		    autoclose: true,
		    todayHighlight: true,
		    format : dateFormat
		}); 
		
		
		$("#itemDetailsTable").tableHeadFixer();
		/* AJAX item Search */
		$("input#itemIDDesc").autocomplete({
			autoFocus: true,
		/*   Enabling the Qty textbox after selecting any item by firing autocomplete select event */
			select: function( event, ui ) {
		  				$('#qty').prop("disabled", false);
		  				$('#qty').focus();
		  				lookupPLUItem(ui.item.value);
						var qtyLen = $('#qty').val().length;
						if (qtyLen > 0) {
							$('#additembutton').prop("disabled", false);
						} else {
							$('#additembutton').prop("disabled", true);
						}
					},
			source : function(request, response) {

				//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

				if (request.term.length > 2) {
					/*  document.getElementById("loadText").style.display ="none"; */
					//document.getElementById("load").style.display = "block";  // show the loading image.
					//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
					$("#itemIDDesc").addClass("wait");

					$.ajax({
						url : "searchItemAction",
						type : "POST",
						data : {
							term : request.term
						},
						dataType : "json",
						success : function(jsonResponse) {
							response(jsonResponse.list);
							/*   document.getElementById("load").style.display = "none"; */
							$("#itemIDDesc").removeClass("wait");
						},
				        error: function(jqXHR, exception) {
				    	   $("#itemIDDesc").removeClass("wait");
		                   
		                   alert('An error occured while processing the request:\n' + exception);
		               	}

					});
				} else {
					/* document.getElementById("load").style.display = "none"; */
					$("#itemIDDesc").removeClass("wait");
				}
			},
			focus: function( event, ui ) { event.preventDefault(); }	
		});
		
		$("#itemIDDesc").autocomplete("widget").attr('style',
				'max-height: 300px; overflow-y: auto; overflow-x: hidden;')//to get a scroll bar

		/*   Enabling the Qty textbox based on itemIDDesc textbox input */
		$('#itemIDDesc').on('input', function(e) {
			var qtyLen = $('#qty').val().length;
			var itemIDDescLen = $('#itemIDDesc').val().length;
			$('#qty').prop("disabled", true);
			$('#additembutton').prop("disabled", true);
			});


		/*   Enabling the additembutton button based on Qty textbox input */
		$('#qty').on('input', function(e) {
			var qtyLen = $(this).val().length;
			var qty = parseInt($(this).val());
			
			if (qtyLen > 0 && qty > 0) {
				$('#additembutton').prop("disabled", false);
				
			} else {
				$('#additembutton').prop("disabled", true);
				$(this).val(""); // dont allow qty 0
			}
		});

		//to set site id when input event is raised on LPO number text box
		$('#lpoNum').on('input', function(e) {
			$('#deleveryAddr').val($('#siteId option:selected').text());
		});
			});
		
	//Krishna 
	$(document).ready(function() {
		
		$('#lpoNum').on('change', function(e) {
			//alert('Hi');
			lpoError.textContent = "";
			//$("#itemIDDesc").addClass("wait");
			$.ajax({
				url : "validateLPONumberDuplicate",
				type : "POST",
				data : {
					lpoNum : $('#lpoNum').val(),
				},
				dataType : "json",
				success : function(data) {
					//alert('Response: ' + data.LPODuplicate);
					if(data.LPODuplicate == true){
						document.getElementById('lpoNum').style.borderColor = "red";
						//alert('Entered LPO Number already exists in other order. Please enter unique LPO number.');
						lpoError.textContent = "LPO Number already exist." 
						lpoError.style.color = "red"
					}
					else{
						document.getElementById('lpoNum').style.borderColor = "";
					}						
					//response(jsonResponse.list);
					/*   document.getElementById("load").style.display = "none"; */
					//$("#itemIDDesc").removeClass("wait");
				},
		        error: function(jqXHR, exception) {
		    	 //  $("#itemIDDesc").removeClass("wait");
                   
                   alert('An error occured while processing the request:\n' + exception);
               	}

			});
		});
		/* AJAX item ADDING */
		$('#additembutton').click(function(event) {
			$('#additembutton').prop("disabled", true);
			/* document.getElementById("loadButtonClick").style.display = "block";  // show the loading message. */
			var itemid = $('#itemIDDesc').val();
			var qty = $('#qty').val();
			var vpn= $('#vpn').val();
			var checkInv = "true";
			
			 $('#avlQty').val("");
			 $('#prce').val("");
			var deliverType = $('input:radio[name="deliveryType"]:checked').val();
			if(deliverType=="1")//scheduled delivery
			{
				var deliveryDate = $("#delivery_datepicker").val();
				deliveryDate = new Date(deliveryDate);
				var currentDate = new Date();
				
				//the two date formats should be same and follow US format "YYYY/MM/DD" for date comparision in javascript
				deliveryDate = new Date(deliveryDate.getYear(),deliveryDate.getMonth(),deliveryDate.getDate());
				currentDate = new Date(currentDate.getYear(),currentDate.getMonth(),currentDate.getDate());
				
				var timeDiff = Math.abs(deliveryDate.getTime() - currentDate.getTime());
				var daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)); 

				/*var numberOfDaysToValidate = "<s:text name="scheduled.delivery.inventory.valid.noOf.days"/>";	*/
				var numberOfDaysToValidate = "<s:property value="schdldDlvryNofDys" />";
				if(daysDiff > numberOfDaysToValidate)
				{
					checkInv = "false";
				}
				
			}
			var tranType=1;
			$('#spinner').addClass('fa-spinner fa-spin');

			$.ajax({
				url : "addItemAction",
				type : "POST",
				data : {
					term : itemid,
					qty : qty,
					checkInv : checkInv,
					tranType : tranType,
					vpn:vpn
				},
				dataType : "json",
				success : function(jsonResponse) {
			if(jsonResponse.actionErrors != null && jsonResponse.actionErrors.length !== 0 
										&& jsonResponse.actionErrors[0].indexOf("Exceeds")!=-1)
			{
				  var error=jsonResponse.actionErrors[0];
				  $('#errorTitle').text(error);
				  //$('#errorModel').modal('show');
				  $('#noAvlCrdLmt').modal('show');
			      
				}else 
					if (jsonResponse.inventoryAvail)
					{
						UpdateTableContent(jsonResponse);
						moveScrollBar(jsonResponse);
					} else {
						//to show inventory validation dialog
						$('#modalItemID').text(jsonResponse.term);
						$('#reqInv').text(jsonResponse.qty);
						var availQty = jsonResponse.availQty;
						$('#availInv').text(availQty);
						if(availQty<=0)
						{
						  $('#invYes').prop("disabled",true);
						}
						else
						{
						 $('#invYes').prop("disabled",false);
						}

						$('#inventoryModal').modal('show');
					}
					$('#spinner').removeClass('fa-spinner fa-spin');
					//manually trigaring event
					$("#itemDetailsTable").trigger('update');

					$('#additembutton').prop("disabled", true);
					$('#itemIDDesc').val("");
					$('#qty').val("");
					

					$('#qty').prop("disabled", true)
				},
			    error: function(jqXHR, exception) {
			    	$('#spinner').removeClass('fa-spinner fa-spin');
	                
					alert('An error occured while processing the request:\n' + exception);
	           }


			});
			$('#quotebutton').prop("disabled", false);
			$('#BookOrder').prop("disabled", true);
			$('#itemIDDesc').focus();
		});

		//inventory restriction popup modal "Yes" button action
		//to add item with available inventory
		$('#invYes').click(function() {
			var itemid = $('#modalItemID').text();
			var qty = $('#availInv').text();
			var vpn = $('#vpn').val(); // did by bhavitha
			var checkInv = "false";
			$.ajax({
				url : "addInvItem",
				type : "POST",
				data : {
					term : itemid,
					qty : qty,
					checkInv : checkInv,
					vpn:vpn
				},
				dataType : "json",
				success : function(jsonResponse) {
					UpdateTableContent(jsonResponse);
					moveScrollBar(jsonResponse);
				},
				 error: function(jqXHR, exception) {
                     	alert('An error occured while processing the request:\n' + exception);
                   }
			});
			
		});

		//inventory restriction popup modal "No" button action
		//to add item with requested inventory
		$('#invNo').click(function() {
			var itemid = $('#modalItemID').text();
			var qty = $('#reqInv').text();
			var vpn = $('#vpn').val();
			var checkInv = "false";

			$.ajax({
				url : "addInvItem",
				type : "POST",
				data : {
					term : itemid,
					qty : qty,
					checkInv : checkInv,
					vpn:vpn
				},
				dataType : "json",
				success : function(jsonResponse) {
					UpdateTableContent(jsonResponse);
					moveScrollBar(jsonResponse);
				},
				 error: function(jqXHR, exception) {
					alert('An error occured while processing the request:\n' + exception);
		         }
			});
			
		});

		//inventory restriction popup modal "close" button action
		//to remove pluitem from session
		$('#invClose').click(function() {

			$.ajax({
				url : "clearPluItem",
				type : "POST",
				data : "",
				dataType : "json",
				success : function(jsonResponse) {
					 $('#itemIDDesc').focus();
				},
			 error: function(jqXHR, exception) {
                 	alert('An error occured while processing the request:\n' + exception);
               }
			});
          $('#vpn').val("");
         
		});
		
		
/* 		
 * Krishna : This code have been commented to stope Ajax request and update table on 
 *			Sucesfull Quote
 */
 
 //Quote button
 /*
		$("body").on("click", "[id='quotebutton']", function(event){
		
			$('#quotespinner').addClass('fa-spinner fa-spin');
			    
                var siteId = $("#siteId").val();
                var lpoNum =$("#lpoNum").val();
                var lpoDate =$("#lpo_datepicker").val();
                var deliveryDate = $("#delivery_datepicker").val();
                var deliveryTime = $("#deliveryTime").val();
                var deliveryNotes = $("#deliveryNotes").val();
                var effectiveDate= $("#ef_datepicker").val();
				$.ajax({
					url : "quoteAction",
					type : "POST",
					data : {siteId:siteId,deliveryType:deliverType,lpoNum:lpoNum,lpoDate:lpoDate,deliveryDate:deliveryDate,deliveryTime:deliveryTime,deliveryNotes:deliveryNotes,effectiveDate:effectiveDate},
					dataType : "json",
					success : function(jsonResponse) {
						UpdateTableContent(jsonResponse);

						$('#quotespinner').removeClass('fa-spinner fa-spin');

						$('#quotebutton').prop("disabled", true);
						$('#BookOrder').prop("disabled", false);

					}
				});
				//disabling all the fields on sales screen
				isQuoteClicked = "true";		
				$("#deliveryDetailsDev").find("input,textarea,select").prop("disabled", true);
				$("#itemSerachDiv :input").prop("disabled", true);
				$("#itemTableDiv :input").prop("disabled", true);
				$("#trnDiscPerAnchor").addClass("disable_a_href");
				$("#trnDiscAmtAnchor").addClass("disable_a_href");
				$("#modifyOrderAnchor").removeClass("disable_a_href");
								
				// $("#itemDetailsTable").find(".edit_href").addClass("disable_a_href"); 
				//$("#itemDetailsTable").find(".delete").addClass("disable_a_href");  
				
			   
			   
				
		});
		
		*/
		////Quote button End
	
		
		$("body").on("click", "[id='modifyOrderAnchor']", function(e) {
			//enabling all the fields on sales screen
			$("#deliveryDetailsDev").find("input,textarea,select").prop("disabled", false);
			$("#itemSerachDiv :input").prop("disabled", false);
			$("#itemTableDiv :input").prop("disabled", false);
			$("#trnDiscPerAnchor").removeClass("disable_a_href");
			$("#trnDiscAmtAnchor").removeClass("disable_a_href");
			if(tranLevDiscAmt>0)
			{
			$("#trnDiscPerAnchor").addClass("disable_a_href");
			}			
			if(tranLevDiscPer>0)
			{
			$("#trnDiscAmtAnchor").addClass("disable_a_href");
			}
			$("#modifyOrderAnchor").addClass("disable_a_href");
			
			/* $("#itemDetailsTable").find(".edit_href").removeClass("disable_a_href"); 
			$("#itemDetailsTable").find(".delete").removeClass("disable_a_href");  */
			isQuoteClicked = "false";
			
			$('#quotebutton').prop("disabled", false);
			$('#BookOrder').prop("disabled", true);
		});

		//to delete items from table
		$("body").on("click", "[id^='deleteItemfromTable']", function(e) {
			//alert("hello");
			if(isQuoteClicked=="false")
				{
			var id = this.getAttribute("data-id");
			  var siteId = $("#siteId").val();
              var lpoNum =$("#lpoNum").val();
              var lpoDate =$("#lpo_datepicker").val();
              var deliveryDate = $("#delivery_datepicker").val();
              var deliveryTime = $("#deliveryTime").val();
              var deliveryNotes = $("#deliveryNotes").val();
              var effectiveDate= $("#ef_datepicker").val();
            
              var deliverType = $('input:radio[name="deliveryType"]:checked').val();
            
			$.ajax({
				url : "deleteItem",
				type : "POST",
				data : {
					ItemIndex : id,siteId:siteId,deliveryType:deliverType,lpoNum:lpoNum,lpoDate:lpoDate,deliveryDate:deliveryDate,deliveryTime:deliveryTime,deliveryNotes:deliveryNotes,effectiveDate:effectiveDate
				},
				dataType : "json",
				success : function(jsonResponse) {
					UpdateTableContent(jsonResponse);
					$("#anyTranLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyTranLevelManualDiscuntsApplyed);
                    $("#anyItemLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyItemLevelManualDiscuntsApplyed);

				},
				 error: function(jqXHR, exception) {
	                 	alert('An error occured while processing the request:\n' + exception);
	               }
			});
		}
			$('#itemIDDesc').focus();
		});
		$("body").on("click", "[id^='nodeleteItemfromTable']", function(e) {
			$('#itemIDDesc').focus();
		});
		$("body").on("click", "[id^='cancelEditItemBtn']", function(e) {
			$('#itemIDDesc').focus();
		});

		$("#itemDetailsTable").on('update', function() {
			
			/*  $( ".netPriceClass" ).css( "border", "3px solid red" );
			 alert($("#itemDetailsTable").find(".netPriceClass").text); */
			//alert($( ".netPriceClass" ).text);
		});
		/*  $('.netPriceClass').on("click", "#itemDetailsTable tr", function(e) {
		alert("this.id");
		}); */
		
//when the edit button is clicked
$("body").on("click","[id^='editHref_']",function(e)
{
	MOflag = false;
	if(isQuoteClicked=="false")
	{
		var anchar = $(this);
		var elementId = anchar.attr('id');
		var elementIdArr = elementId.split("_");
		var itemRow = elementIdArr[1];
		var originalPrice = anchar.attr('data-item-price');
		var itemLevelDisAmt = anchar.attr('data-item-disamt');
		var itemLevelDisPer = anchar.attr('data-item-disper');
		var itemType= anchar.attr('data-item-type');

		var itemID = $("#itemId"+itemRow).html();
		var itemIdDes =  $("#itemdesc"+itemRow).html();


		 $("#editModalItemId").html(itemID);
		$("#editModalItemDesc").html(itemIdDes);
		 var itemQty = $("#itemQty"+itemRow).html();
		 $("#editModalItemQty").html(itemQty);
		 $("#editModalItemNewQty").val("");
		  var availableInventory= $("#AvlQty"+itemRow).html();
		 $("#editModelAvlQty").html(availableInventory);
		 $('#editModelAvlQty').css("background-color", "");
		 
		 $("#editModalItemNewQty").prop("disabled",false);
		  
		 $("#editModalItemPrice").html(originalPrice);
		 $("#editModalItemOvrrPrice").html($("#itemPrice"+itemRow).html());

		$("#editModalItemNewPrice").val("");
		$("#editModalItemNewPrice").prop("disabled",false);
		 
		 //$("#editModalItemDiscPer").html($("#itemDiscPer"+itemRow).html());
		  if(itemLevelDisPer=="" || itemLevelDisPer=="0")
		  {
			  $("#editModalItemNewDiscAmt").prop("disabled",false);
		  }
		  else
		  {
			  $("#editModalItemNewDiscAmt").prop("disabled",true);
			 
		  }
		  if(itemLevelDisAmt=="" || itemLevelDisAmt=="0")
		  {
			  $("#editModalItemNewDiscPer").prop("disabled",false);
			  $("#editModalItemNewQty").prop("disabled",false);
			  $("#editModalItemNewPrice").prop("disabled",false);
		  }
		  else
		  {
			  $("#editModalItemNewDiscPer").prop("disabled",true);
			  /* $("#editModalItemNewQty").prop("disabled",true);
			  $("#editModalItemNewPrice").prop("disabled",true); */
		  }


		 
		 $("#editModalItemDiscPer").html(itemLevelDisPer);
		 
		 $("#editModalItemNewDiscPer").val(itemLevelDisPer);

		 
		 //$("#editModalItemDiscAmt").html($("#itemDiscAmt"+itemRow).html());
		 $("#editModalItemDiscAmt").html(itemLevelDisAmt);
		 
		 
		 $("#editModalItemNewDiscAmt").val(itemLevelDisAmt);

		 
		// $("#editModalItemNewSalesAgent").val("");
		  $("#editModalItemSalesAgent").html($("#itemSlAgentId"+itemRow).html());
		  
		 $("#editItemIndex").html(itemRow);
		$("#editItemBtn").prop("disabled",true);

		//price override reason codes
		var priceOvvrLength = $('#priceReasonCode').find("option").length;
		if(priceOvvrLength<=0)
		{
		var priceOvvrReasonCodes = ${itemEditPricOvrrReasonCodes};
		 $.each( priceOvvrReasonCodes, function(index,value){
		  $('#priceReasonCode').append( '<option value="'+index+'" style="font-weight:bold">'+value+'</option>' );
		});	
		}
		//discount % reason codes
		var discPerLength = $('#discPerReasonCode').find("option").length;
		if(discPerLength<=0)
		{
		 $('#discPerReasonCode').html(discReasonCodeOptions);
		}

		//discount Amt reason codes
		var discAmtLength = $('#discAmtReasonCode').find("option").length;
		if(discAmtLength<=0)
		{
		 $('#discAmtReasonCode').html(discReasonCodeOptions);
		}
		
		//added to allow only price override for service items
		if(itemType!=null && itemType=="Service"){
			 $("#editModalItemNewDiscPer").prop("disabled",true);
			  $("#editModalItemNewQty").prop("disabled",true);
			  $("#editModalItemNewDiscAmt").prop("disabled",true);
			  $("#editModalItemNewPrice").prop("disabled",false);
		} 
		
	 $('#editItemModal').modal('show');	
	}
}
);


//new qty not allow zero
$("body").on("input","[id='editModalItemNewQty']",function(e)
{
	var avlQty=$('#editModelAvlQty').html();
	var qty=this.value;
	var checkInv = true;
	var validateInventory = <s:property value="validateInventory" />;
	
	var deliverType = $('input:radio[name="deliveryType"]:checked').val();
	if(deliverType=="1")//scheduled delivery
	{
		var deliveryDate = $("#delivery_datepicker").val();
		deliveryDate = new Date(deliveryDate);
		var currentDate = new Date();
		
		//the two date formats should be same and follow US format "YYYY/MM/DD" for date comparision in javascript
		deliveryDate = new Date(deliveryDate.getYear(),deliveryDate.getMonth(),deliveryDate.getDate());
		currentDate = new Date(currentDate.getYear(),currentDate.getMonth(),currentDate.getDate());
		
		var timeDiff = Math.abs(deliveryDate.getTime() - currentDate.getTime());
		var daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)); 

		/*var numberOfDaysToValidate = "<s:text name="scheduled.delivery.inventory.valid.noOf.days"/>";	*/	
		var numberOfDaysToValidate = "<s:property value="schdldDlvryNofDys" />";
		if(daysDiff > numberOfDaysToValidate)
		{
			checkInv = false;
		}
		
	}
    
	if(checkInv && Number(qty)>Number(avlQty) && validateInventory)
	{
		$(this).val("");
		$('#editModelAvlQty').css("background-color", "red");
	}
	else
		{
		 $('#editModelAvlQty').css("background-color", "");
		}
	 //enabling edit item modal submit button if any text box have new value
	 enableEditItemBtn();
	

}
);

//new price not allow zere
$("body").on("input","[id='editModalItemNewPrice']",function(e)
{
	if(this.value<1)
	 {
	  $(this).val("");
	  
	 }
	
	  //enabling edit item modal submit button if any text box have new value
	  enableEditItemBtn();
	

}
);

//display popup of enable double discount popup
$("body").on("click","[id='editModalItemNewDiscAmt']",function(e)
{
  enbDblDiscOnItem()
});

//display popup of enable double discount popup
$("body").on("click","[id='editModalItemNewDiscPer']",function(e)
{
   enbDblDiscOnItem()
});

//disabling  editModalItemNewDiscPer based on the input of editModalItemNewDiscAmt
$("body").on("input","[id='editModalItemNewDiscAmt']",function(e)
{
var itemOrgOrOvrrPrice = $("#editModalItemNewPrice").val()!="" ? $("#editModalItemNewPrice").val() : $("#editModalItemOvrrPrice").html();//item original or override price
var itemQty =  $("#editModalItemNewQty").val()!="" ? $("#editModalItemNewQty").val() : $("#editModalItemQty").html();
var extPrice = itemOrgOrOvrrPrice * itemQty;
	
var amtVal = $(this).val();
	if(amtVal<0 || amtVal > extPrice || amtVal=="")
	 {
	  $(this).val("");
	  $("#editModalItemNewDiscPer").prop("disabled",false);
	  $("#editModalItemNewQty").prop("disabled",false);
	  $("#editModalItemNewPrice").prop("disabled",false);
	 }
	 else
	 {
	  $("#editModalItemNewDiscPer").prop("disabled",true);
	  /* $("#editModalItemNewQty").prop("disabled",true);
	  $("#editModalItemNewPrice").prop("disabled",true); */
	 }
	  //enabling edit item modal submit button if any text box have new value
	  enableEditItemBtn();

}
);
//disabling  editModalItemNewDiscAmt based on the input of editModalItemNewDiscPer
$("body").on("input","[id='editModalItemNewDiscPer']",function(e)
{
	
	var maxDisPer = $("#editModalItemNewDiscPer").attr("max");
	var perVal = $(this).val();
	//if(+perVal<0 || +perVal>+maxDisPer || perVal=="" || perVal.indexOf(".") > 0)
	 if((parseFloat(perVal) < parseFloat(0)) || (parseFloat(perVal) > parseFloat(maxDisPer)) || perVal=="")
	 {
		if((parseFloat(perVal) > parseFloat(maxDisPer)) && +maxDisPer>0)
		 {
			$(this).val(maxDisPer);
			$("#editModalItemNewDiscAmt").prop("disabled",true);
		 }else
		 {
			 $(this).val("");
			 $("#editModalItemNewDiscAmt").prop("disabled",false);
		 } 
	       
	  
	 }
	 else
	 {
	  $("#editModalItemNewDiscAmt").prop("disabled",true);
	  
	 }
	 //enabling edit item modal submit button if any text box have new value
	  enableEditItemBtn();

}
);
//sales agent
$("body").on("input","[id='editModalItemNewSalesAgent']",function(e)
{
	//enabling edit item modal submit button if any text box have new value
	  enableEditItemBtn();
}
);
//enabling edit item modal submit button if any text box have new value
function enableEditItemBtn()
{
      var ItemNewQtyLength = $("#editModalItemNewQty").val().length;
	  var ItemNewPriceLength =$("#editModalItemNewPrice").val().length;
	  var ItemNewDiscPerLength =$("#editModalItemNewDiscPer").val().length;
	  var ItemNewDiscAmtLength =$("#editModalItemNewDiscAmt").val().length;
	  var ItemNewSalesAgentLength =$("#editModalItemNewSalesAgent").val().length;
	  
	  if(ItemNewQtyLength>0 ||ItemNewPriceLength>0 || ItemNewDiscPerLength>0 || ItemNewDiscAmtLength>0 ||ItemNewSalesAgentLength>0)
	  {
	    $("#editItemBtn").prop("disabled",false);
	  }
	  else
	  {
	   $("#editItemBtn").prop("disabled",true);
	  }
}

// enable OK button when reason code change
$('#discAmtReasonCode').change(function(){
	var ItemNewDiscAmtLength =$("#editModalItemNewDiscAmt").val().length;
	if(ItemNewDiscAmtLength != 0) enableEditItemBtn();
});
$('#discPerReasonCode').change(function(){
	var ItemNewDiscPerLength =$("#editModalItemNewDiscPer").val().length;
	if(ItemNewDiscPerLength != 0) enableEditItemBtn();
});
$('#priceReasonCode').change(function(){
	var ItemNewPriceLength =$("#editModalItemNewPrice").val().length;
	if(ItemNewPriceLength != 0) enableEditItemBtn();
});

//editItemBtn on click event to update the item details -arjun
$("#creditLimitOverride").keypress(function(e) {
	if(e.which==13){
		if ($('#managerOverrideSuccessDiv').is(":visible"))
		{
     		$("#movrsucbut").trigger('click');
		}
		if ($('#managerOverrideSuccessDiv1').is(":visible"))
		{
     		$("#override_button").trigger('click');
		}
	}
});

$("#editItemModal").keypress(function(e) {
	if(e.which==13){
		$("#editItemBtn").trigger('click');
	}
});
$("body").on("click","[id='editItemBtn']",function(){

	//var itemNewQty=$("#editModalItemNewQty").val();


/* AJAX item ADDING */
	var itemid = $("#editModalItemId").text();
	var qty = $("#editModalItemNewQty").val();
	var vpn= $('#vpn').val();
	var checkInv = "true";
	var tranType=1;
	
/* 	$.ajax({
		url : "addItemAction",
		type : "POST",
		data : {
			term : itemid,
			qty : qty,
			checkInv : checkInv,
			tranType : tranType,
			vpn:vpn,
		},
		dataType : "json",
		success : function(jsonResponse) {
			if (!jsonResponse.inventoryAvail) {
				//to show inventory validation dialog
				$('#editModalItemID').text(jsonResponse.term);
				$('#editReqInv').text(jsonResponse.qty);
				var availQty = jsonResponse.availQty;
				$('#editAvailInv').text(availQty);
				if(availQty<=0)
				{
				  $('#editInvYes').prop("disabled",true);
				}
				else
				{
				 $('#editInvYes').prop("disabled",false);
				}

				$('#noInventoryAvlModal').modal('show');
			}
		}

	});
 */

var itemNewPrice =$("#editModalItemNewPrice").val();
var itemNewDiscPer =$("#editModalItemNewDiscPer").val();
var itemNewDiscAmt =$("#editModalItemNewDiscAmt").val();
if($("#editModalItemNewSalesAgent").val()!="-1"){
var itemNewSalesAgent =$("#editModalItemNewSalesAgent").val();
}
var itemIndex =$("#editItemModal").find("#editItemIndex").html();
var itemDiscAmtReasonCode =$("#discAmtReasonCode").val();
var itemDiscPerReasonCode =$("#discPerReasonCode").val();
var priceOverrdReasonCode =$("#priceReasonCode").val();

var reasonCode = itemNewDiscAmt ? itemDiscAmtReasonCode : itemDiscPerReasonCode;
$.ajax({
url:"updateItemDetails",
type:"POST",
data:{ itemNewQty:qty, 
itemNewPrice:itemNewPrice,
itemNewDiscPer:itemNewDiscPer,
itemNewDiscAmt:itemNewDiscAmt,
itemNewSalesAgent:itemNewSalesAgent,
discReasonCode:reasonCode,
priceOverrdReasonCode:priceOverrdReasonCode,
itemIndex :itemIndex
},
dataType:"json",
success :function(jsonResponse)
{
UpdateTableContent(jsonResponse);

 $("#enableDoubleDiscounts").val(jsonResponse.enableDoubleDiscounts);
 $("#anyTranLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyTranLevelManualDiscuntsApplyed);
 $("#anyItemLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyItemLevelManualDiscuntsApplyed);
 
},
error: function(jqXHR, exception) {
    	alert('An error occured while processing the request:\n' + exception);
  }


});
$('#itemIDDesc').focus();
});

//to add item with available inventory through edit model
$('#editInvYes').click(function() {
	var itemNewQty = parseInt($('#editAvailInv').text());
	var itemIndex =$("#editItemModal").find("#editItemIndex").html();
	$.ajax({
		url:"updateItemDetails",
		type:"POST",
		data:{itemNewQty :itemNewQty,
			itemIndex :itemIndex
		},
		dataType:"json",
		success :function(jsonResponse)
		{
		UpdateTableContent(jsonResponse);

		},
		 error: function(jqXHR, exception) {
             	alert('An error occured while processing the request:\n' + exception);
           }
		});
});

//inventory restriction popup modal "No" button action
//to add item with requested inventory through through edit model
$('#editInvNo').click(function() {
	var itemNewQty = parseInt($('#editReqInv').text());
	var itemIndex =$("#editItemModal").find("#editItemIndex").html();
	$.ajax({
		url:"updateItemDetails",
		type:"POST",
		data:{itemNewQty:itemNewQty,
			itemIndex :itemIndex
		},
		dataType:"json",
		success :function(jsonResponse)
		{
		UpdateTableContent(jsonResponse);

		},
		 error: function(jqXHR, exception) {
             	alert('An error occured while processing the request:\n' + exception);
           }
		});
});
//To clear double discounts
$("body").on("click","[id='disableDoubleDiscBtn']",function(){
	$.ajax({
		url:"disableDoubleDiscs",
		type:"POST",
		data:"",
		dataType:"json",
		success :function(jsonResponse)
		{			
		 UpdateTableContent(jsonResponse);
		 $("#enableDoubleDiscounts").val(jsonResponse.enableDoubleDiscounts);
         $("#anyTranLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyTranLevelManualDiscuntsApplyed);
         $("#anyItemLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyItemLevelManualDiscuntsApplyed);
		 if(compId != "")
		 {
			$('#'+compId).modal('show');
		 }
		},	 
		error: function(jqXHR, exception) {
            	alert('An error occured while processing the request:\n' + exception);
          }
	});
});
	 

$('#trnDiscPerAnchor').click(function()
{
 var tranDiscPerModal = "tranDiscPerModal";
 enbDblDisc(tranDiscPerModal);
});
//triggered when tranDiscPerModal is shown before
$('#tranDiscPerModal').on("show.bs.modal",function(e){
	$('#tranDiscPerReasonCode').html(discReasonCodeOptions);
});
$("body").on("input","[id='tranDiscPerTxt']",function(){
	
	var maxDisPer = $("#tranDiscPerTxt").attr("max");
	var perVal = $(this).val();
	if((parseFloat(perVal) > parseFloat(maxDisPer)))
	 {
	  		$(this).val(maxDisPer);
	}
});
//tranDiscPerBtn on click event to update the item details
$("body").on("click","[id='tranDiscPerBtn']",function(){
	var tranDiscPer = $('#tranDiscPerTxt').val();
	var tranDiscPerReasonCode =$("#tranDiscPerReasonCode").val();
	if(tranDiscPer == ""){ // SEND ZERO IF FIELD EMPTY
		tranDiscPer = parseFloat('0.0');
	}
	$.ajax({
		url:"updateTranLevelDiscountByPer",
		type:"POST",
		data:{discReasonCode:tranDiscPerReasonCode,
			  tranDiscPer:tranDiscPer
			   },
		dataType:"json",
		success : function(jsonResponse)
		{
			var trnDiscPer = jsonResponse.tranDiscPer;
			 $("#enableDoubleDiscounts").val(jsonResponse.enableDoubleDiscounts);
			 $("#anyTranLevelManualDiscuntsApplyed").val(jsonResponse.anyTranLevelManualDiscuntsApplyed);
			 $("#anyItemLevelManualDiscuntsApplyed").val(jsonResponse.anyItemLevelManualDiscuntsApplyed);
			
		/* 	if(trnDiscPer!=null && trnDiscPer != "0" )
			{
				$('#trnDiscAmtAnchor').addClass('disable_a_href');
			}
			else
			{
				$('#trnDiscAmtAnchor').removeClass('disable_a_href');
			} */
		 UpdateTableContent(jsonResponse);
		},
		error: function(jqXHR, exception) {
             	alert('An error occured while processing the request:\n' + exception);
           }
	});

});

$('#trnDiscAmtAnchor').click(function()
{
 var tranDiscAmtModal = "tranDiscAmtModal";
 enbDblDisc(tranDiscAmtModal);
});

//triggered when tranDiscAmtModal is shown before
$('#tranDiscAmtModal').on("show.bs.modal",function(e){
	$('#tranDiscAmtReasonCode').html(discReasonCodeOptions);
	
});
$("body").on("input","[id='tranDiscAmtTxt']",function(){
	
	var totalExcludingServicePrice = Number($('#totalExcludingServicePrice').html());
	totalExcludingServicePrice =totalExcludingServicePrice + tranLevDiscAmt; 
	var tranDiscAmt =  $('#tranDiscAmtTxt').val();
	if(Number(tranDiscAmt) > totalExcludingServicePrice)
	{
		$(this).val("");
		$('#tranDiscAmtBtn').prop('disabled',true);
	}
	else
	{
		$('#tranDiscAmtBtn').prop('disabled',false);
		
	}
	
});
//tranDiscAmtBtn on click event to update the item details
$("body").on("click","[id='tranDiscAmtBtn']",function(){
	var tranDiscAmt = $('#tranDiscAmtTxt').val();
	var tranDiscAmtReasonCode =$("#tranDiscAmtReasonCode").val();
	// SEND ZERO IF FIELD EMPTY
	if(tranDiscAmt == ""){
		tranDiscAmt = parseFloat('0.0');
	}
	$.ajax({
		url:"updateTranLevelDiscountByAmt",
		type:"POST",
		data:{discReasonCode:tranDiscAmtReasonCode,
			  tranDiscAmt:tranDiscAmt
			   },
		dataType:"json",
		success : function(jsonResponse)
		{
			 var trnDiscAmt = jsonResponse.tranDiscAmt;
			 $("#enableDoubleDiscounts").val(jsonResponse.enableDoubleDiscounts);
			 $("#anyTranLevelManualDiscuntsApplyed").val(jsonResponse.anyTranLevelManualDiscuntsApplyed);
			 $("#anyItemLevelManualDiscuntsApplyed").val(jsonResponse.anyItemLevelManualDiscuntsApplyed);
			 
			/* if(trnDiscAmt!=null && trnDiscAmt != "0" )
			{
				$('#trnDiscPerAnchor').addClass('disable_a_href');
			}
			else
			{
				$('#trnDiscPerAnchor').removeClass('disable_a_href');
			}  */
		 UpdateTableContent(jsonResponse);
		},
		 error: function(jqXHR, exception) {
             	alert('An error occured while processing the request:\n' + exception);
           }
	});

});
	});

function moveScrollBar(jsonResponse) {
	try{
			var rowpos = $('#tr'+jsonResponse.itemActiveIndex).position();
			$('#parent').scrollTop(rowpos.top);
		}catch(err){
			console.log('#tr'+jsonResponse.itemActiveIndex +'Do not exixt in table view');
		}
}
	//to updateOrders table content TODO
	function UpdateTableContent(jsonResponse) {
		$("#itemDetailsTable > tbody").children('tr').remove();//removing existing table rows
		var disableHref = 'class="disable_a_href"';
		<s:if test="%{allowItemDelete}">
			disableHref = '';
		</s:if>
		var row;
		var itemQty=0;
		var i = 1;
		if(jsonResponse.actionErrors != null && jsonResponse.actionErrors.length !== 0 && jsonResponse.actionErrors[0].indexOf("Exceeds")!=-1){
			$('#noAvlCrdLmt').modal('show');
			}
		//loading the discount reason codes and description to display
		var resncodes=jsonResponse.orderTran.reasonCodes['Discount'];
		$.each(jsonResponse.orderTran.ordTranLineItems,
						function(key, value) {

												
								itemQty=itemQty+value.lineQnt;
								//Discount amt,percent function
												var discountAmt = 0, discountPer = 0,itemPrmName= "",itemLevDiscAmt="",itemLevDiscPer="";
												var itemPromoDivDisplayStyle="none";
												
												var TransDiscDivDisplayStyle="none";
												var tranlevelDiscId = "";
												var tranDiscRsnCde = "";
												
												var itemlevelDiscId = "";
												var itemDiscRsnCde = "";
												var itemDiscDivDisplayStyle ="none";
												discountAmt = value.totalApplyedDiscountAmtOnItem==null?"":value.totalApplyedDiscountAmtOnItem;
												var currencyFormat = "<s:property value="getText('format.currency')"/>";
												$.each(value.ordTranDscItms, function(key, disc) {
													if(disc.dscAmt!=null)
													{
													//discountAmt += disc.dscAmt;
													if(disc.tyDsc=="0" && disc.prmId==null && (disc.dscPer==""||disc.dscPer=="0"))
													{
														itemLevDiscAmt = format(currencyFormat,disc.dscAmt);
														itemlevelDiscId = itemLevDiscAmt +"(Amt)";
														itemDiscRsnCde = "I_"+resncodes[disc.discReasonCode];
														itemDiscDivDisplayStyle ="block";
													}
													else if(disc.tyDsc=="1" && disc.prmId==null && (disc.dscPer==""||disc.dscPer=="0"))
													{
														tranLevDiscAmt = format(currencyFormat,disc.dscAmt);
														tranlevelDiscId = tranLevDiscAmt +"(Amt)";
														tranDiscRsnCde ="T_"+resncodes[disc.discReasonCode];
														TransDiscDivDisplayStyle ="block";
													}
													}
													if(disc.dscPer!=null && disc.dscPer>0)
													{
													//discountPer += disc.dscPer;
													if(disc.tyDsc=="0" && disc.prmId==null)
													{
														itemLevDiscPer =disc.dscPer;
														itemlevelDiscId = disc.dscPer+"(%)";
														itemDiscRsnCde = "I_"+resncodes[disc.discReasonCode];
														itemDiscDivDisplayStyle ="block";
													}
													else if(disc.tyDsc=="1" && disc.prmId==null)
													{
														tranLevDiscPer =disc.dscPer;
														tranlevelDiscId = disc.dscPer+"(%)";
														tranDiscRsnCde = "T_"+resncodes[disc.discReasonCode];
														TransDiscDivDisplayStyle ="block";
													}
													}
													//krish- 
													if(disc.prmId!=null)
													{
													 itemPrmName += disc.prmDesc;
													 itemPromoDivDisplayStyle = "block";
													}
												});
												var price = value.itmPrnPrc;
												if(value.priceOverRideFlag == "1" )
												{
												 price =  value.overRidePrice;
												}
												var salAgentDivDisplayStyle="none";
												var salAgentId = "";
												if(value.empId!=null && value.empId!="")
												{
												 salAgentId = value.empId;
												 salAgentDivDisplayStyle ="block";
												}		  
												
												var rowNum = i;
												rowNum--;
												console.log(jsonResponse.itemActiveIndex)
												if(value.registryId == null)
												{
													value.registryId="";
												}
												if(rowNum==(jsonResponse.itemActiveIndex)){
													row += '<tr id=tr'+rowNum+' style="background-color:#ffff84;">'
													}else{
														row += '<tr id=tr'+rowNum+'>'
													}
												
												row += '<td style="min-width: 10px">'
														+ i++
														+ '</td><td style="min-width: 20px"><a href="javascript:void(0)"'+ disableHref + 'id="delete_item_'+rowNum+'" data-id='+rowNum+'><i class="fa fa-trash"></i></a></td><td><a href="javascript:void(0)" id="editHref_'+rowNum+'" data-item-disamt="'+itemLevDiscAmt+'" data-item-disper="'+itemLevDiscPer+'" data-item-price= "'+value.itmPrnPrc+'"data-item-type= "'+value.pluItem.item.itmTyCd+'" data-emp-role-id="<s:property value="empRoleID"/>"><i class="fa fa-edit"></i></a></td><td><span id="itemId'+rowNum+'">'
														+ value.itemId
														+ '</span><div id="itemdesc'+rowNum+'">'
														+  value.pluItem.item.itmDesc
														+ '</div><div id="itemPromName'+rowNum+'" style="display:'+itemPromoDivDisplayStyle+'">'
														+itemPrmName
														+'</div></td><td style="text-align:right;"><span id="itemQty'+rowNum+'">'
														+ value.lineQnt
														+ '</span><div id="itemSlAgentDiv'+rowNum+'" style="display:'+salAgentDivDisplayStyle+'" ><s:text name="itemlevel.salesAgent"/> <span id="itemSlAgentId'+rowNum+'">'
														+salAgentId
														+'</span> </div></td><td  style="text-align:right;" id="AvlQty'+rowNum+'">'
														+ value.pluItem.inventory
														+ '</td><td style="min-width: 40px;text-align:right;" id="itemVpn'+rowNum+'">'
														+ value.registryId
														+ '</td><td style="min-width: 40px;text-align:right;" id="itemPrice'+rowNum+'">'
														+ price
														/* + '</td><td style="min-width: 40px;text-align:right;" id="itemDiscPer'+rowNum+'">'
														+ discountPer
														+ '(%) */
														+'</td><td style="min-width: 40px;text-align:right;">'
														+ value.extnLnItmRtn
														+'</td><td style="min-width: 40px;text-align:right;" id="itemDiscAmt'+rowNum+'">'
														+ discountAmt
														+ '<div id="tranDiscDiv'+rowNum+'" style="display:'+TransDiscDivDisplayStyle+'" >' +tranDiscRsnCde+' : <span id="tranlevelDisc'+rowNum+'">'
														+tranlevelDiscId+'</span> </div><div id="itemDiscDiv'+rowNum+'" style="display:'+itemDiscDivDisplayStyle+'" >' +itemDiscRsnCde+' : <span id="itemlevelDisc'+rowNum+'">'
														+itemlevelDiscId+'</span> </div></td><td style="min-width: 40px;font-weight: 500;text-align:right;">0</td><td style="min-width: 40px;text-align:right;" class="netPriceClass">'
														+ value.extnDscLnItm + '</td></tr>';
											
												

											});
							
		$('#itemDetailsTable > tbody').append(row);

		if(jsonResponse.orderTran.ordTranSum) {
			var ordTranSum = jsonResponse.orderTran.ordTranSum;
			var currencyFormat = "<s:property value="getText('format.currency')"/>";
			var subtotal=format(currencyFormat,ordTranSum.dkartSlsTot);
			var discount=format(currencyFormat,ordTranSum.dkartDscTot);
			var totalTax=format(currencyFormat,ordTranSum.dkartTaxTot);
			var nettotl=format(currencyFormat,ordTranSum.dkartNetTot);
		 	$('#subtotal').html(subtotal); 
			$('#discount').html(discount);
			$('#totalTax').html(totalTax);
			$('#total').html(nettotl);
			$('#qtyTot').html("<b>"+itemQty+"</b>");
			$('#itemCount').html("<b>"+(i-1)+"</b>");
			$('#totalExcludingServicePrice').html(ordTranSum.totalExcludingServicePrice);

		}
		//To handle double discounts warning dialog
		$("#anyTranLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyTranLevelManualDiscuntsApplyed);
        $("#anyItemLevelManualDiscuntsApplyed").val(jsonResponse.orderTran.anyItemLevelManualDiscuntsApplyed);
		
		// Update credit limit
		if(jsonResponse.orderTran.availCrdtLimit>=0){
			var avCrdtLimit = format(currencyFormat, jsonResponse.orderTran.availCrdtLimit);
			$('#avlCrdLmt').html(avCrdtLimit);
		}
		
		// handle quote button 
		updateButtons(jsonResponse.orderTran);
		$('#vpn').val("");
		$('#itemIDDesc').focus();		
	}
	
	function updateButtons(orderTran)
	{
		if(orderTran!= null && orderTran.ordTranLineItems!=null)
		{
		//added for updating credit limit @mallikarjun
			/* if(orderTran.availCrdtLimit>=0){
				var avCrdtLimit = format(currencyFormat, orderTran.availCrdtLimit);
				$('#avlCrdLmt').html(avCrdtLimit);
			}  */
			
			 
			var noOfItems = orderTran.ordTranLineItems.length;
			if(noOfItems<1)
			{
				$('#quotebutton').prop("disabled", true);
				$('#BookOrder').prop("disabled", true);
				
			}else if(noOfItems==1 && orderTran.ordTranLineItems[0].itmTy==2){
				$('#quotebutton').prop("disabled", true);
			}
		}
	}
	// A jQuery event , which is triggered after "onbeforeunload"
	window.thisPage = window.thisPage || {};
	//window.thisPage.isDirty = false;
	window.thisPage.isDirty = false;

	window.thisPage.closeEditorWarning = function(event) {
		if (window.thisPage.isDirty) {

			return 'It looks like you have been editing something'
					+ ' - if you leave before saving, then your changes will be lost.';
		} else
			return undefined;
	};

	/* $("form").on('keyup', 'textarea', // You can use input[type=text] here as well.
	 function () { 
	 window.thisPage.isDirty = true; 
	 });

	 $("form").submit(function () {
	 QC.thisPage.isDirty = false;
	 }); */
	window.onbeforeunload = window.thisPage.closeEditorWarning;

	//to manage multiple buttons
	/*  function Quote() 
	{
	alert("this.id");
	document.getElementById("Quote").disabled = true;
	//document.getElementById("BookOrder").disabled = false;
	document.forms[0].action ="viewannualtargetincaction.do";
	document.forms[0].submit();  
	}  */
     function enbDblDisc(componentId)
	 {	 
	    enableDoubleDiscounts = $('#enableDoubleDiscounts').val();
	    anyItemLevelManualDiscuntsApplyed = $('#anyItemLevelManualDiscuntsApplyed').val();
	    anyTranLevelManualDiscuntsApplyed = $('#anyTranLevelManualDiscuntsApplyed').val();
		 if(enableDoubleDiscounts == "false"//enableDoubleDiscounts = No
		    && (anyItemLevelManualDiscuntsApplyed == "true"))
		 {
		  $('#enableDounleDiscWarn').modal('show');
		  compId = componentId;
		 }
		 else
		 {
		  $('#'+componentId).modal('show');
		 }
	 
	 
	  }
	  function enbDblDiscOnItem()
	 {
	    enableDoubleDiscounts = $('#enableDoubleDiscounts').val();
	    anyItemLevelManualDiscuntsApplyed = $('#anyItemLevelManualDiscuntsApplyed').val();
	    anyTranLevelManualDiscuntsApplyed = $('#anyTranLevelManualDiscuntsApplyed').val();
		 if(enableDoubleDiscounts == "false"//enableDoubleDiscounts = No
		    && (anyTranLevelManualDiscuntsApplyed == "true" ))
		 {
		  compId = "";
		  $('#enableDounleDiscWarn').modal('show');
		 }
		 
      }
	//added by hanu End
	
	$(function() {
		//Initialize Select2 Elements
		//$(".select2").select2();

		//Datemask dd/mm/yyyy
		/* $("#datemask").inputmask("dd/mm/yyyy", {
			"placeholder" : "dd/mm/yyyy"
		});
		//Datemask2 mm/dd/yyyy
		$("#datemask2").inputmask("mm/dd/yyyy", {
			"placeholder" : "mm/dd/yyyy"
		});
		//Money Euro
		$("[data-mask]").inputmask();

		//Date range picker
		$('#reservation').daterangepicker();
		//Date range picker with time picker
		$('#reservationtime').daterangepicker({
			timePicker : true,
			timePickerIncrement : 30,
			format : 'MM/DD/YYYY h:mm A'
		});
		
		//$('#shipment_Method').prop("disabled", true);//by default disabled
		//$('#sales_Agent').prop("disabled", true);
		//Date range as a button
		$('#daterange-btn').daterangepicker(
				{
					ranges : {
						'Today' : [ moment(), moment() ],
						'Yesterday' : [ moment().subtract(1, 'days'),
								moment().subtract(1, 'days') ],
						'Last 7 Days' : [ moment().subtract(6, 'days'),
								moment() ],
						'Last 30 Days' : [ moment().subtract(29, 'days'),
								moment() ],
						'This Month' : [ moment().startOf('month'),
								moment().endOf('month') ],
						'Last Month' : [
								moment().subtract(1, 'month').startOf('month'),
								moment().subtract(1, 'month').endOf('month') ]
					},
					startDate : moment().subtract(29, 'days'),
					endDate : moment()
				},
				function(start, end) {
					$('#daterange-btn span').html(
							start.format('MMMM D, YYYY') + ' - '
									+ end.format('MMMM D, YYYY'));
				}); */
		
		var delvStartDate= new Date();//for enabling the from dates  from the current date in calender
		var afterCurrentDate = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
		var dateFormat= "<s:text name="bootstrap.date.format"/>";
		//Date picker
		$('#lpo_datepicker').datepicker({
			    todayBtn: "linked",
    			autoclose: true,
    			todayHighlight: true,
    			format : dateFormat
		});
		$('#ef_datepicker').datepicker({
			    todayBtn: "linked",
    			autoclose: true,
    			todayHighlight: true,
    			format : dateFormat
		});
		$('#delivery_datepicker').datepicker({
			    todayBtn: "linked",
			    startDate: afterCurrentDate,
				autoclose: true,
				todayHighlight: true,
				format : dateFormat
		});
		/*  //iCheck for checkbox and radio inputs
				    startDate: delvStartDate,
	 $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
		   checkboxClass: 'icheckbox_minimal-blue',
		   radioClass: 'iradio_minimal-blue'
		 });
		 //Red color scheme for iCheck
		 $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
		   checkboxClass: 'icheckbox_minimal-red',
		   radioClass: 'iradio_minimal-red'
		 });
		 //Flat red color scheme for iCheck
		 $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		   checkboxClass: 'icheckbox_flat-green',
		   radioClass: 'iradio_flat-green'
		 });
		 */
		/*  //Colorpicker
		 $(".my-colorpicker1").colorpicker();
		 //color picker with addon
		 $(".my-colorpicker2").colorpicker();
		 */
		/*  //Timepicker
		 $(".timepicker").timepicker({
		   showInputs: false
		 }); */
		$(document).on("change", function() {
			var itemIDDescEnDis = "<s:property value="allowItemSearch"/>";
			if(itemIDDescEnDis=='false'){
				itemIDDescEnDis = true;
			}else{
				itemIDDescEnDis=false;
			}
			var deliverType = $('input:radio[name="deliveryType"]:checked').val();
			if ($("#siteId").val()!=null && $("#siteId").val()!="") {
 				$('#shipment_Method').prop("disabled", false);
				$('#sales_Agent').prop("disabled", false);
				
			}else {
				$('#additembutton').prop("disabled", true);
				$('#qty').prop("disabled", true);
				$('#shipment_Method').prop("disabled", true);	
				$('#sales_Agent').prop("disabled", true);
			}
			
			if(deliverType == "0" && $("#ef_datepicker").val()!=null && $("#ef_datepicker").val()!=""){
				$("#itemIDDesc").prop("disabled", itemIDDescEnDis);
			}else if(deliverType == "1" && $("#delivery_datepicker").val()!=null && $("#delivery_datepicker").val()!="" && $("#ef_datepicker").val()!=null && $("#ef_datepicker").val()!=""){
				$("#itemIDDesc").prop("disabled", itemIDDescEnDis);
			}else{
				$("#itemIDDesc").prop("disabled", true);
			}
			
		});

$('input:radio[name="deliveryType"]').change(function() {
deliverType = $(this).val();
if(deliverType=="0") // Immediate Delivery
{
	$('#delivery_datepicker').prop("disabled", true);
	$('#delivery_datepicker').val("");
	$('#timeSelecter').prop("disabled", true);
	$("#ef_datepicker").datepicker().datepicker("setDate",new Date()); 
}
else if(deliverType =="1") // Scheduled Delivery
{
	$('#delivery_datepicker').prop("disabled", false);
	
	
	$('#timeSelecter').prop("disabled", false);
	$('#delivery_datepicker').focus();
}
/* if ($('#del_radio').val()) {
	$('#delivery_datepicker').prop("disabled", false);
	$('#timeSelecter').prop("disabled", false);
	$('#delivery_datepicker').removeAttr("disabled").focus();
} */

});

//checking if lpo date is before the delivery date
$("#lpo_datepicker").on("change blur paste cut", function() {
var deliverType = $('input:radio[name="deliveryType"]:checked').val();
var x, lpo_date, delivery_date,str_Del_Date="";
if(deliverType =="0")//immediate delivery
	{
	delivery_date =  new Date();
	str_Del_Date="immediate";
	}
else//scheduled delivery
	{
	str_Del_Date = $('#delivery_datepicker').val();
	delivery_date = new Date(str_Del_Date);
	}


x = document.getElementById("lpo_datepicker").value;
lpo_date = new Date(x);


//document.getElementById("lpo_datepicker").value=d1.getMonth();
if (str_Del_Date =="" ||lpo_date > delivery_date) {

document.getElementById("lpo_datepicker").placeholder = "Invalid";
//  $("#lpo_datepicker").addClass("redborder");
document.getElementById("lpo_datepicker").value = "";
//$("").tooltip();
$("#lpo_datepicker").popover({
	animation : "fade",
	delay : "20",
	trigger : "focus",
	placement : "top"
});
} else {
$("#lpo_datepicker").popover({
	animation : "fade",
	delay : "20",
	trigger : "blur",
	placement : "top",
	disabled : true
});

}

});

//check if scheduled delivery date is after the current date
$("#delivery_datepicker").on("change blur paste cut",function()
{
	var deliveryDate = $("#delivery_datepicker").val();
	deliveryDate = new Date(deliveryDate);
	var currentDate = new Date();
	
	//the two date formats should be same and follow US format "YYYY/MM/DD" for date comparision in javascript
	deliveryDate = new Date(deliveryDate.getFullYear(),deliveryDate.getMonth(),deliveryDate.getDate());
	currentDate = new Date(currentDate.getFullYear(),currentDate.getMonth(),currentDate.getDate());
	if(deliveryDate <= currentDate)
	{
		document.getElementById("delivery_datepicker").placeholder = "Invalid";
	 	$("#delivery_datepicker").val("");
	 $("#delivery_datepicker").popover({
			animation : "fade",
			delay : "20",
			trigger : "focus",
			placement : "top"
		});
	}
	else {
		$("#delivery_datepicker").popover({
			animation : "fade",
			delay : "20",
			trigger : "blur",
			placement : "top",
			disabled : true
		});
		//populating effectiveDate based in deliveryDate
		 var effDate;   
		 var deliverType = $('input:radio[name="deliveryType"]:checked').val();
		 if(deliverType=="1")//scheduled delivery
		{		
			
			var timeDiff = Math.abs(deliveryDate.getTime() - currentDate.getTime());
			var daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)); 

			/*var numberOfDaysToValidate = "<s:text name="scheduled.delivery.inventory.valid.noOf.days"/>";*/	
			var numberOfDaysToValidate = "<s:property value="schdldDlvryNofDys" />";
			if(daysDiff > numberOfDaysToValidate)
			{
				deliveryDate.setDate(deliveryDate.getDate() - numberOfDaysToValidate);
				effDate = deliveryDate;
			}
			else
			{
				effDate = currentDate;
			}
			
		}
		else//immediate
		{
		 effDate = currentDate;
		}
		 $("#ef_datepicker").datepicker().datepicker("setDate",effDate); 
		}
});

//check if effective date is after the current date
$("#ef_datepicker").on("change blur paste cut",function()
{
	var deliverType = $('input:radio[name="deliveryType"]:checked').val();
	var effectiveDate =  $(this).val();
	effectiveDate = new Date(effectiveDate);
	var currentDate = new Date();
	var deliveryDate = $("#delivery_datepicker").val();
	deliveryDate = new Date(deliveryDate);
    var dateFormat= "<s:text name="bootstrap.date.format"/>";
	if(deliverType=="0")//immediate delivery
	{		
		deliveryDate = currentDate;
	}
	
	
	//the three date formats should be same and follow US format "YYYY/MM/DD" for date comparision in javascript
	deliveryDate = new Date(deliveryDate.getYear(),deliveryDate.getMonth(),deliveryDate.getDate());
	effectiveDate = new Date(effectiveDate.getYear(),effectiveDate.getMonth(),effectiveDate.getDate());
	currentDate = new Date(currentDate.getYear(),currentDate.getMonth(),currentDate.getDate());
	if(effectiveDate < currentDate || effectiveDate > deliveryDate)
	{
	document.getElementById("ef_datepicker").placeholder = "Invalid";
	document.getElementById("ef_datepicker").value = "";
	$(this).blur();
    $("#ef_datepicker").popover({
			animation : "fade",
			delay : "20",
			trigger : "focus",
			placement : "top",
			format : dateFormat
		}); 
	}
	else {
		$("#ef_datepicker").popover({
			animation : "fade",
			delay : "20",
			trigger : "blur",
			placement : "top",
			disabled : true,
			format : dateFormat
		});
		}
	
});
/* $(document).ready(function() {
$("#itemDetailsTable").tableHeadFixer();
}); */

/* 	$("#lpo_datepicker").on("change blur paste cut", function() {

		var x, lpo_date, delivery_date;
		var $this = $(this);

		// Get the value of the input field with id="numb"
		x = document.getElementById("lpo_datepicker").value;
		lpo_date = new Date(x);
		x = document.getElementById("delivery_datepicker").value;
		delivery_date = new Date(x);
		//document.getElementById("lpo_datepicker").value=d1.getMonth();
		if (lpo_date > delivery_date) {

			document.getElementById("lpo_datepicker").placeholder = "Invalid";
			$("#lpo_datepicker").addClass("redborder");
			document.getElementById("lpo_datepicker").value = "";
			//$("").tooltip();
			$("#lpo_datepicker").popover({
				animation : "fade",
				delay : "20",
				trigger : "focus",
				placement : "top"
			});
		} else {
			$this.removeClass("redborder");
		}

	}); */
	});
	
	function addServiceItem(){

		var e = document.getElementById("shipment_Method");
		var index = document.getElementById("shipment_Method").selectedIndex;
		var selctValue = e.value;
		if(index>0 && selctValue.length>0){
		$.ajax({
			url : "addServiceItemAction",
			type : "POST",
			data : {
				term : selctValue
			},
			dataType : "json",
			success : function(jsonResponse) {
	if(jsonResponse.actionErrors != null && jsonResponse.actionErrors.length !== 0 ){
			$('#noAvlCrdLmt').modal('show');
			}else 
				if (jsonResponse.inventoryAvail) {
					UpdateTableContent(jsonResponse);
				}
		
	},
	 error: function(jqXHR, exception) {
         	alert('An error occured while processing the request:\n' + exception);
       }
		});
		}
	}	
	
	
	function modifyTranSalesAgnt(){
		var agent = document.getElementById("sales_Agent").value;
		if(agent!=null){
		$.ajax({
			url : "modifyTranlvlSlsAgnt",
			type : "POST",
			data : {
				agentId : agent
			},
			dataType : "json",
			error: function(jqXHR, exception) {
	             	alert('An error occured while processing the request:\n' + exception);
	           }
/* 			success : function(jsonResponse) {
	if(jsonResponse.actionErrors != null && jsonResponse.actionErrors.length !== 0 ){
			$('#noAvlCrdLmt').modal('show');
			}else 
				if (jsonResponse.inventoryAvail) {
					UpdateTableContent(jsonResponse);
				}
		
	} */
		});
		}
	}
	
</script>
<script type="text/javascript">
$(function(){
	$("#quotebutton").click(function(e)
		    {
		     $("#quoting").modal('show');
		    });
});

$(function(){
	$("#cancelbutton").click(function(e)
		    {
		     $("#cancel").modal('show');
		    });
});
 $(function(){
	 $("body").on('click', "[id^='delete_item_']", function(e) { 
	$("#deleteItemModaltxt").val("");
		     $("#deleteItemModal").modal('show');
		     var id = this.getAttribute("data-id");
		     $("#deleteItemfromTable").attr("data-id",id);
		    });
}); 
</script>

<script type="text/javascript">

	var NewOrder = (function () {
  		
  		
  		var _addGiftVoucher = function() {
  			var request = $.ajax({
			  url: "addGiftVoucher",
			  method: "POST",
			  timeout: 30 //TODO set global timeout
			});
			 
			request.done(function( msg ) {
				alert( msg );
			});
			 
			request.fail(function( jqXHR, textStatus ) {
				alert("Request failed: " + textStatus);
			});
  		
  			return false;
  		}
  		
  		return {
  			addGiftVoucher: _addGiftVoucher
  		};



	})();


</script>

<script type="text/javascript">
  $(function(){
  	$('#sales_Agent').chosen();
  	$('#editModalItemNewSalesAgent').chosen({width: "100%"});
  });
</script>


<script type="text/javascript">
	
  $(document).ready(function() {
    $('#order').addClass('active');
    $('#bookorder').addClass('active');
  });
</script>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<s:include value="topbarNewOrder.jsp" />
		<s:include value="menubar.jsp">
			<!-- store name has to display in bookorder screen @sharanya start -->
			<s:param name="Store">- <s:property
					value="storeName.substring(6)" />
			</s:param>
			<!-- sharanya end -->
		</s:include>


		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">


			<!-- Main content -->
			<section class="content">
				<form id="mainForm" action="quoteOrderAction" method="POST"
					enctype="multipart/form-data">
					<!-- Your Page Content Here -->
					<div class="row">
						<div class="col-md-12">
							<div class="box box-primary">

								<div class="box-body" style="background-color: #e4e4e4;"
									id="deliveryDetailsDev">
									<div class="col-md-6 description-block border-right">
										<div class="form-group">
											<table style="width: 100%;">
												<tr>
													<td style="width: 30%; text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('delivery.address')" /><span
															style="color: red;">*</span></label></td>
													<td><select id="siteId" name="siteId"
														class="form-control" style="margin: 1px;" autofocus>
															<s:iterator value="customer.customerSite">
																<s:iterator value="customerSiteAddressList">

																	<s:if test="%{tyAds=='1'}">
																		<option
																			value=<s:property value="[1].customerSitePK.custSiteId"/>
																			<s:if test ="%{orderTran.getIdBtchInvResv()== [1].customerSitePK.custSiteId}">selected</s:if>>
																			<s:property value="a1Cnct" />
																			<s:if test="%{a2Cnct!=null}">, <s:property
																					value="a2Cnct" />
																			</s:if>
																			<%-- <s:if test="%{a3Cnct!=null}">, <s:property value="a3Cnct" />
																			</s:if>
																			<s:if test="%{a4Cnct!=null}">, <s:property value="a4Cnct" />
																			</s:if> --%>
																			<s:if test="%{ciCnct!=null}">, <s:property
																					value="ciCnct" />
																			</s:if>
																			<s:if test="%{coCnct!=null}">, <s:property
																					value="coCnct" />
																			</s:if>
																			<s:if test="%{pcCnct!=null}">, <s:property
																					value="pcCnct" />
																			</s:if>
																		</option>
																	</s:if>
																</s:iterator>
															</s:iterator>
													</select> <input type="hidden" name="deleveryAddr" id="deleveryAddr"
														value=""></td>
												</tr>
												<tr>
													<td style="text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('delivery.type')" /><span
															style="color: red;">*</span></label></td>

													<td
														style="min-width: 300px; background-color: white; border-radius: 4px;">
														<table
															style="width: 100%; border-color: #c5c5c5; border-width: thin;">
															<tbody>
																<tr>
																	<td style="min-width: 100px; padding-left: 6px;">
																		<div class="" style="margin: 1px;">
																			<label class="label-normal"> <s:set
																					var="delTyp" value="1" /> <s:set var="immDelType"
																					value="%{'checked'}" /> <s:set var="scheDelType"
																					value="%{''}" /> <s:set var="scheDelDateFocus"
																					value="%{'disabled'}" /> <s:set
																					var="allowItemSearch" value="%{'disabled'}" /> <s:if
																					test="%{allowItemSearch}">
																					<s:set var="allowItemSearch" value="%{''}" />
																				</s:if> <s:set var="scheDeleveryDate" value="%{''}" /> <s:if
																					test='%{orderTran.ordTranSum.ordLvlDvr!=null && orderTran.ordTranSum.ordLvlDvr.equalsIgnoreCase("1")}'>
																					<s:set var="effctDeleveryDate">
																						<s:date name='orderTran.ordTranSum.ordEfDate'
																							format="%{getText('format.date')}" />
																					</s:set>

																					<s:set var="immDelType" value="%{''}" />
																					<s:set var="scheDelType" value="%{'checked'}" />
																					<s:set var="scheDelDateFocus" value="%{''}" />
																					<s:set var="scheDeleveryDate">
																						<s:date name='orderTran.ordTranSum.ordDlvrDate'
																							format="%{getText('format.date')}" />
																					</s:set>
																				</s:if> <s:elseif test="%{deliveryType == #delTyp}">
																					<s:set var="immDelType" value="%{''}" />
																					<s:set var="scheDelType" value="%{'checked'}" />
																					<s:set var="scheDelDateFocus" value="%{''}" />
																					<s:set var="scheDeleveryDate"
																						value="%{deliveryDate}" />
																					<s:set var="effctDeleveryDate"
																						value="%{effectiveDate}" />
																				</s:elseif> <s:else>
																					<s:set var="effctDeleveryDate"
																						value="%{getText('format.date.args',{orderTran.getOrdTranSum().getOrdEfDate()})}" />
																				</s:else> <input type="hidden" name="unitTest1"
																				id="unitTest1"
																				value="<s:property value="#immDelType"/>"> <input
																				type="hidden" name="unitTest2" id="unitTest2"
																				value="<s:property value="orderTran.ordTranSum.ordLvlDvr"/>">
																				<input type="radio" name="deliveryType" value="0"
																				<s:property value="#immDelType"/>> <s:property
																					value="getText('immediate.label')" />
																			</label>
																		</div>
																	</td>
																	<td>
																		<div class="input-group date">

																			<div class="input-group-addon">
																				<input id="del_radio" type="radio"
																					name="deliveryType" value="1"
																					<s:property value="#scheDelType"/>>

																			</div>
																			<input type="text" id="delivery_datepicker"
																				value="<s:property value="#scheDeleveryDate"/>"
																				data-toggle="tooltip"
																				data-content="Should be after current date"
																				name="deliveryDate" placeholder="Schedule"
																				class="form-control pull-right"
																				<s:property value="#scheDelDateFocus"/>>

																		</div> <s:select class="form-control" style="margin: 1px;"
																			id="timeSelecter" name="deliveryTime"
																			list="sheduledDeliverTimePeriodMap"
																			value="orderTran.ordTranSum.ordDlvrTimePeriod"
																			theme="bootstrap"></s:select> <!-- /.form group -->


																	</td>
																</tr>
															</tbody>
														</table>



													</td>
												</tr>
												<tr>
													<td style="text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('delivery.notes')" /></label></td>
													<td><s:textarea id="deliveryNotes"
															name="deliveryNotes" class="form-control"
															style="margin: 1px; overflow: auto;"
															placeholder="Delivery Notes" maxlength="30"
															data-toggle="tooltip"
															data-content="Should not be grater than 30 characters"
															theme="simple" value="%{orderTran.getCtDvrInfoIns}">
														</s:textarea></td>
												</tr>
											</table>

										</div>
									</div>
									<div class="col-md-6 description-block ">
										<div class="form-group">

											<table style="width: 100%; text-align: right;">

												<tr>
													<td style="width: 30%; text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('lpo.number')" />:</label></td>
													<td>
														<table style="width: 100%;">
															<td><s:textfield id="lpoNum" maxlength="20"
																	autocomplete="off" name="lpoNum"
																	placeholder="LPO Number" class="form-control"
																	theme="simple" key="LPO Number"
																	value="%{orderTran.getOrdTranSum().getCustLpoNum()}"></s:textfield>
																	<span id="lpoError"></span>
															</td>
															<td><div class="fileUpload btn-link">
																	<i class="fa fa-paperclip" id="sliplink"> <s:if
																			test="%{orderTran.getOrdTranLpo()!=null && orderTran.getOrdTranLpo().getLpoSlipName()!=null}">
																			<s:property
																				value="%{orderTran.getOrdTranLpo().getLpoSlipName()}" />
																		</s:if> <s:else>
																			<s:property value="getText('upload.lpo')" />
																		</s:else>
																	</i><input id="slip" type="file" class="upload"
																		name="lpoSlip" accept=".png,.jpeg,.jpg,.pdf,.bmp" />
																</div></td>
															</td>
														</table>
												</tr>
												<tr>
													<td style="text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('lpo.date')" />:</label></td>
													<td><div class="input-group date" style="margin: 1px;">
															<div class="input-group-addon">
																<i class="fa fa-calendar"></i>
															</div>
															<!-- <input type="text" data-toggle="tooltip"
																data-content="Should not be after delivery date"
																placeholder="LPO Date" id="lpo_datepicker"
																name="lpoDate" class="form-control pull-right"> -->

															<s:textfield data-toggle="tooltip"
																data-content="Should not be after delivery date"
																placeholder="LPO Date" id="lpo_datepicker"
																name="lpoDate" class="form-control pull-right"
																theme="simple"
																value="%{getText('format.date.args',{orderTran.getOrdTranSum().getCustLpoDate()})}"></s:textfield>

														</div></td>
												</tr>

												<tr>
													<td style="text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('order.effective.date')" />:<span
															style="color: red;">*</span></label></td>
													<td><div class="input-group date" style="margin: 1px;">
															<div class="input-group-addon">
																<i class="fa fa-calendar"></i>
															</div>
															<!-- <input type="text" data-toggle="tooltip"
																data-content="Should be between current date and delivery date"
																placeholder="Effective Date" id="ef_datepicker" 
																name="effectiveDate"
																class="form-control pull-right"> -->

															<!--<s:textfield data-toggle="tooltip"
																data-content="Should be between current date and delivery date"
																placeholder="Effective Date" id="ef_datepicker"
																name="effectiveDate" class="form-control pull-right"
																theme="simple"
																value="%{getText('format.date.args',{orderTran.getOrdTranSum().getOrdEfDate()})}">
															</s:textfield>-->
															<s:textfield data-toggle="tooltip"
																data-content="Should be between current date and delivery date"
																placeholder="Effective Date" id="ef_datepicker"
																name="effectiveDate" class="form-control pull-right"
																theme="simple" value="%{#effctDeleveryDate}">
															</s:textfield>

														</div></td>
												</tr>
												<tr>
													<td style="width: 30%; text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('shipment.method')" />:</label></td>
													<td><s:select theme="bootstrap" id="shipment_Method"
															name="shipmentMethod"
															list="%{(shipmentMethods!=null?shipmentMethods:{})}"
															class="form-control" style="margin: 1px;"
															onchange="addServiceItem()" headerKey="0"
															headerValue="-------------"
															value="orderTran.getShipmentMethod()">
														</s:select></td>
												</tr>
												<tr>
													<td style="width: 30%; text-align: right;"><label
														class="label-normal" style="margin-right: 5px;"><s:property
																value="getText('sales.agent')" />:</label></td>
													<td style="text-align: left;"><s:select
															theme="bootstrap" id="sales_Agent"
															list="%{(SalesAgentList!=null?SalesAgentList:{})}"
															class="form-control" style="margin: 1px;"
															onchange="modifyTranSalesAgnt()"
															value="orderTran.getEmId()">
														</s:select></td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>

					<div class="container-fluid bg-blue" id="itemSerachDiv">
						<div class="row">
							<div class="col-sm-12 col-md-6 col-lg-7"
								style="padding-top: 4px; margin-bottom: 2px;">
								<div class="row">
									<div class="col-xs-6">
										<input class="form-control" id="itemIDDesc"
											placeholder="Enter Item/Scan Item" type="text"
											pattern="[a-zA-Z][a-zA-Z0-9\s]*"
											<s:property value="#allowItemSearch"/>>
									</div>
									<div class="col-xs-2" style="padding-left: 0px;">
										<input class="form-control " placeholder="QTY" type="number"
											id="qty" name="qty" min="1" max="99999" disabled="true"
											step="1" onkeypress='return isNumberKey(event);'
											onInput="checkLength(9,this)">
									</div>
									<div class="col-xs-2"
										style="padding-left: 0px; padding-right: 0px;">
										<input id="vpn" class="form-control " type="text"
											maxlength="14" placeholder="Ref No">
									</div>
									<shiro:hasPermission name="addItemAction">
										<div class="col-sm-2 col-xs-1" style="margin-top: -3px;">
											<button type="submit" class="btn btn-sm " id="additembutton"
												disabled="true"
												style="width: 62px; margin: 2px; background: #666; font: normal 100 15px/1 sans-serif; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); background: linear-gradient(#717171, #262626 20%, #717171);">
												<s:property value="getText('add.button')" />
												&nbsp;<span id="spinner" class="fa" aria-hidden="true"></span>
											</button>
										</div>
									</shiro:hasPermission>
									<shiro:lacksPermission name="addItemAction">
										<div class="col-sm-2 col-xs-1" style="margin-top: -3px;">
											<button type="button" class="btn btn-sm " disabled="true"
												title="please enable the 'addItemAction' permission"
												style="width: 62px; margin: 2px; background: #666; font: normal 100 15px/1 sans-serif; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); background: linear-gradient(#717171, #262626 20%, #717171);">
												<s:property value="getText('add.button')" />
												&nbsp;<span id="spinner" class="fa" aria-hidden="true"></span>
											</button>
										</div>
									</shiro:lacksPermission>
								</div>
							</div>
							<div class="col-sm-12 col-md-6 col-lg-5"
								style="padding-top: 4px; margin-bottom: 2px;">
								<div class="row">
									<div class="col-xs-4">
										<label for="avlQty" class="pull-left label-normal"
											style="padding-top: 2px;"><s:property
												value="getText('avlqty.head')" />&nbsp;&nbsp;</label> <input
											style="background-color: #e4e4e4; text-align: center; font-weight: bold; max-width: 50%"
											class="form-control" type="text" value="" id="avlQty"
											disabled>
									</div>
									<div class="col-xs-4 " style="padding-right: 0px;">
										<label for="prce" class="pull-left label-normal"
											style="padding-top: 2px;"><s:property
												value="getText('price.head')" />&nbsp;&nbsp;</label> <input
											style="background-color: #e4e4e4; text-align: center; font-weight: bold; max-width: 50%"
											" class="form-control" type="text" value="" id="prce"
											disabled>
									</div>
									<div class="col-xs-4 text-right">
										<div class="dropdown" style="padding-top: 2px;">
											<label class="label-normal"><s:property
													value="getText('order.options.head')" /></label> <i
												id="optionsDropdown"
												class="fa  fa-bars fa-lg dropdown-toggle" type="button"
												data-toggle="dropdown"></i>
											<ul style="margin-left: -90px; background-color: #efefef;"
												class="dropdown-menu ">
												<li><s:if test="%{disable_discount}">
														<a href="javascript:void(0)" id="trnDiscPerAnchor"> <s:property
																value="getText('order.options.head1')" />
														</a>
													</s:if> <s:else>
														<a href="javascript:void(0)" class="disable_a_href">
															<s:property value="getText('order.options.head1')" />
														</a>
													</s:else></li>
												<li><s:if test="%{disable_amt}">
														<a href="javascript:void(0)" id="trnDiscAmtAnchor"> <s:property
																value="getText('order.options.head2')" />
														</a>
													</s:if> <s:else>
														<a href="javascript:void(0)" class="disable_a_href"> <s:property
																value="getText('order.options.head2')" />
														</a>
													</s:else></li>
												<li><a href="javascript:void(0)" class="disable_a_href">
														<s:property value="getText('order.options.head3')" />
												</a></li>


												<!--<li><a href="javascript:void(0)" data-toggle="modal" data-target="#giftVoucherCode">
													Add Gift Voucher <%--  --%>	
												</a></li>-->
											</ul>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>




					<%-- <div class="row" id="itemSerachDiv">
						<div class="col-md-12">
							<div class="box bg-blue"
								style="margin-bottom: 5px; margin-bottom: 5px;">
								<table style="width: 100%; margin: 1px;">
									<tbody>
										<tr>
											<td style="width: 1%;"></td>
											<td style="width: 30%;"><input style="margin-left: 5px;"
												class="form-control" id="itemIDDesc"
												placeholder="Enter Item/Scan Item" type="text"
											    pattern="[a-zA-Z][a-zA-Z0-9\s]*"> <!--  <div id="load" style="display:none"><img src="ajax-loader.gif"/></div> -->
											</td>
											<td style="width: 1%;"></td>
											<td style="width: 10px;"><input style="width: 100px;"
												class="form-control " placeholder="QTY" type="number"
												id="qty" name="qty" min="1" disabled="true"></td>
											<td style="width: 1%;"></td>
											<td style="width: 10%;"><input id="vpn"
												style="width: 100px;" class="form-control " type="text"
												placeholder="Ref No"></td>
											<td style="">
												<button type="submit"
													style="width: 100px; margin: 2px; background: #666; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); border: 1px solid rgba(255, 255, 255, 0.77); font: normal 100 15px/1 sans-serif; background: linear-gradient(#717171, #262626 20%, #717171); color: #fff; display: inline-block;"
													class="btn  " id="additembutton" disabled="true">
													<s:property value="getText('add.button')"/> &nbsp;<span id="spinner" class="fa" aria-hidden="true"></span>
												</button>
											</td>
											<td style="width: 1%;"></td>
											<td style="width: 4%;"><s:property
													value="getText('avlqty.head')" /></td>
											<td style="width: 7%;"><input
												style="background-color: #e4e4e4; text-align:center;font-weight: bold;" class="form-control"
												type="text" value="" id="avlQty" disabled></td>


											<td style="width: 1%;"></td>
											<td style="width: 3%;"><s:property
													value="getText('price.head')" /></td>
											<td style="width: 7%;"><input
												style="background-color: #e4e4e4; text-align:center;font-weight: bold;" class="form-control"
												type="text" value="" id="prce" disabled></td>

											<td>
												<div class="dropdown pull-right" style="margin-right: 20px;">

													<label><s:property
															value="getText('order.options.head')" /></label> <i
														class="fa  fa-bars fa-lg dropdown-toggle" type="button"
														data-toggle="dropdown"></i>
													<ul style="margin-left: -90px; background-color: #efefef;"
														class="dropdown-menu ">
														<li><a href="javascript:void(0)"  id="trnDiscPerAnchor">
																<s:property value="getText('order.options.head1')" />
														</a></li>
														<li><a href="javascript:void(0)" id="trnDiscAmtAnchor">
																<s:property value="getText('order.options.head2')" />
														</a></li>
														<li><a href="javascript:void(0)" class="disable_a_href">
																<s:property value="getText('order.options.head3')" />
														</a></li>

														<!-- <li><a href="#"  class="disable_a_href" id="modifyOrderAnchor">Modify Order</a></li> -->
													</ul>

												</div>

											</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div> --%>

					<div class="row" id="itemTableDiv">

						<!-- Item table -->
						<div class="col-xs-12">
							<div class="box box-default" style="margin-top: 0px;">

								<!-- /.box-header -->
								<div id="parent" class="box-body no-padding">
									<table id="itemDetailsTable" name="itemDetailsTable"
										class="table table-striped table-hover">
										<thead>
											<tr style="background-color: #ADC2EE;">
												<th><s:property value="getText('table.head.SNo')" /></th>
												<th><s:property value="getText('table.head.delete')" /></th>
												<th><s:property value="getText('table.head.edit')" /></th>
												<th style="min-width: 100px"><s:property
														value="getText('table.head.item')" /></th>
												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('table.head.qty')" /></th>
												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('avlqty.head')" /></th>
												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('table.head.vpn')" /></th>

												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('table.head.unitprice')" /></th>
												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('table.head.subtotal')" /></th>
												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('table.head.disc')" /></th>
												</div>
												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('table.head.tax')" /></th>
												<th style="min-width: 50px; text-align: right;"><s:property
														value="getText('table.head.total')" />(<s:property
														value="getText('global.currency')" />)</th>

											</tr>
										</thead>

										<tbody>
											<s:set var="totalItemQty" value="0" />
											<s:iterator value="orderTran.getOrdTranLineItems()"
												status="itStatus">
												<s:set var="totalItemQty" value="%{#totalItemQty+lineQnt}" />
												<tr>
													<td style="min-width: 10px"><s:property
															value="#itStatus.count" /> <s:property
															value="#itmPrnPrc" /></td>
													<s:if test="%{disable_delItm}">
														<td style="min-width: 20px"><a
															href="javascript:void(0)"
															id="delete_item_<s:property value="#itStatus.index" />"
															data-id="<s:property value="#itStatus.count-1" />"><i
																class="fa fa-trash-o"></i></a></td>
													</s:if>
													<s:else>
														<td style="min-width: 20px"><a
															href="javascript:void(0)" class="disable_a_href"
															id="delete_item_<s:property value="#itStatus.index" />"
															data-id="<s:property value="#itStatus.count-1" />"><i
																class="fa fa-trash-o"></i></a></td>
													</s:else>

													<td><a href="javascript:void(0)"
														id="editHref_<s:property value="#itStatus.count-1" />"
														data-item-disamt="<s:property value="itemLevDiscAmt" />"
														data-item-disper="<s:property value="itemLevDiscPer" />"
														data-item-price="<s:property value="itmPrnPrc" />"
														data-item-type="<s:property value="pluItem.item.itmTyCd" />"
														data-emp-role-id="<s:property value="empRoleID"/>"><i
															class="fa fa-edit"></i></a></td>


													<td><span
														id="itemId<s:property value="#itStatus.count-1" />">
															<s:property value="itemId" />
													</span>
														<div id="itemdesc<s:property value="#itStatus.count-1" />">
															<s:property value="pluItem.item.itmDesc" />
														</div> <s:iterator value="ordTranDscItms" status="lineId">
															<div
																id="itemPromName_<s:property value="#lineId.count-1" />_<s:property value="#itStatus.count-1" />"
																style="display: <s:property value="%{(prmDesc!=null?'block':'none')}" />">
																<s:property value="prmDesc" />
															</div>
															<%--<div
																id="discReasonCode_<s:property value="#lineId.count-1" />_<s:property value="#itStatus.count-1" />"
																style="display: <s:property value="%{(discReasonCode!=null?'block':'none')}" />">
																<s:property value="discReasonCode" />
															</div> --%>
														</s:iterator></td>
													<%-- 	<td><span
														id="itemQty<s:property value="#itStatus.count-1" />">
															<s:property value="lineQnt" />
													</span>
														<div
															id="itemSlAgentDiv<s:property value="#itStatus.count-1" />"
															style="display: <s:property value="%{((empId==null)?'none':'block')}" />">
															<s:text name="itemlevel.salesAgent" />
															<span
																id="itemSlAgentId<s:property value="#itStatus.count-1" />">
																<s:property value="empId" />
															</span>
														</div></td>
													<td id="AvlQty<s:property value="#itStatus.count-1" />"><s:property
															value="pluItem.inventory" /></td>
													<td style="min-width: 40px"
														id="itemVpn<s:property value="#itStatus.count-1" />">
														<s:property value="registryId" />
													</td>
													<td style="min-width: 40px; text-align: right;"
														id="itemPrice<s:property value="#itStatus.count-1" />">
														<s:property
															value="%{(priceOverRideFlag == 1?overRidePrice:itmPrnPrc)}" />
													</td>
													<td style="min-width: 40px; text-align: right;"
														id="itemDiscAmt<s:property value="#itStatus.count-1" />">
														<s:property
															value="%{ordTranDscItms==null?'0':ordTranDscItms.get(0).getDscAmt()}" />
													</td> --%>

													<td style="text-align: right;"><span
														id="itemQty<s:property value="#itStatus.count-1" />">
															<s:property value="lineQnt" />
													</span>
														<div
															id="itemSlAgentDiv<s:property value="#itStatus.count-1" />"
															style="display: <s:property value="%{((empId==null)?'none':'block')}" />">
															<s:text name="itemlevel.salesAgent" />
															<span
																id="itemSlAgentId<s:property value="#itStatus.count-1" />">
																<s:property value="empId" />
															</span>
														</div></td>
													<td style="text-align: right;"
														id="AvlQty<s:property value="#itStatus.count-1" />"><s:property
															value="pluItem.inventory" /></td>
													<td style="min-width: 40px; text-align: right;"
														id="itemVpn<s:property value="#itStatus.count-1" />">
														<s:property value="registryId" />
													</td>
													<td style="min-width: 40px; text-align: right;"
														id="itemPrice<s:property value="#itStatus.count-1" />">
														<s:property
															value="%{getText('format.currency.args',{priceOverRideFlag == 1?overRidePrice:itmPrnPrc})}" />
													</td>
													<td style="min-width: 40px; text-align: right;"><s:property
															value="%{getText('format.currency.args',{extnLnItmRtn})}" />
													</td>
													<td style="min-width: 40px; text-align: right;"
														id="itemDiscAmt<s:property value="#itStatus.count-1" />">
														<s:property
															value="%{getText('format.currency.args',{totalApplyedDiscountAmtOnItem})}" />
														<!-- arjun --> <s:iterator value="ordTranDscItms"
															status="lineId">
															<s:set var="resncds"
																value="orderTran.getReasonCodes()['Discount']"></s:set>

															<s:if test="prmId==null && tyDsc==0 && dscPer>0">
																<div
																	style="display: <s:property value="%{((dscPer==null)?'none':'block')}" />">
																	I_
																	<s:property value="%{#resncds[discReasonCode]}" />
																	:
																	<s:property value="dscPer" />
																	<s:text name="discount.percentage" />
																</div>
															</s:if>
															<s:elseif test="prmId==null && tyDsc==0 && dscAmt>0">
																<div
																	style="display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
																	I_
																	<s:property value="%{#resncds[discReasonCode]}" />
																	:
																	<s:property value="dscAmt" />
																	<s:text name="discount.amount" />
																</div>
															</s:elseif>
															<s:elseif test="prmId==null && tyDsc==1 && dscPer>0">
																<div
																	style="display: <s:property value="%{((dscPer==null)?'none':'block')}" />">
																	T_
																	<s:property value="%{#resncds[discReasonCode]}" />
																	:
																	<s:property value="dscPer" />
																	<s:text name="discount.percentage" />
																</div>
															</s:elseif>
															<s:elseif test="prmId==null && tyDsc==1 && dscAmt>0">
																<div
																	style="display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
																	T_
																	<s:property value="%{#resncds[discReasonCode]}" />
																	:
																	<s:property value="dscAmt" />
																	<s:text name="discount.amount" />
																</div>
															</s:elseif>
														</s:iterator> <!-- tyDsc=="0" dscAmt -itemlevel -->
													</td>
													<td style="min-width: 40px; text-align: right;">0</td>
													<td style="min-width: 40px; text-align: right;"
														class="netPriceClass"><s:property
															value="%{getText('format.currency.args',{extnDscLnItm})}" /></td>
												</tr>



											</s:iterator>


										</tbody>
									</table>
								</div>
								<!-- /.box-body -->
							</div>
						</div>
					</div>
					<!--ORIGINAL TABLES ORIGINAL TABLES ORIGINAL TABLES ORIGINAL TABLES ORIGINAL TABLES ORIGINAL TABLES-->
					<div class="row" style="margin: 5px; height: 3px;">
						<div class="progress" style="height: 2px;">
							<div class="progress-bar"
								style="width: 100%; background-color: #ADC2EE;"></div>
						</div>
					</div>
					<!--Customer Table -->
					<div class="row">
						<div class="col-md-4">
							<div class="box ">
								<div class="box-header">
									<h3 class="box-title" style="color: #226e71;">
										<b><s:property value="getText('credit.limit.head')" /></b>
									</h3>
									<s:if test="%{creditLimitOverride}">
										<a href="javascript:void(0)" class="pull-right"
											data-toggle="modal" data-target="#creditLimitOverride">
											Override&nbsp;<span class="glyphicon glyphicon-cog"
											style="top: 2px;" aria-hidden="true"></span>
										</a>
									</s:if>
									<s:else>
										<a href="javascript:void(0)" class="pull-right"
											style="color: #D3D3D3 !important;"
											title="Use parameter 'EnableExceedingCustomerAvailableLimit' to enable this feature">
											Override&nbsp;<span class="glyphicon glyphicon-cog"
											style="top: 2px;" aria-hidden="true"></span>
										</a>
									</s:else>
								</div>
								<div class="box-body no-padding">
									<table class="table table-bordered table-totals"
										style="border: 1px solid;">
										<tbody>
											<tr style="border: 2px solid #e4e4e4;">
												<td
													style="width: 40%; font-weight: 500; border: 2px solid #e4e4e4;">
													<s:property value="getText('available.credit.limit')" />
												</td>
												<td style="border: 2px solid #e4e4e4;"><s:text
														name="global.currency" />&nbsp; <b id="avlCrdLmt"><s:property
															value="%{getText('format.currency.args',{orderTran.availCrdtLimit})}" />
												</b></td>
											</tr>

											<tr style="border: 2px solid #e4e4e4;">
												<td style="font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('due.amount')" /></td>
												<td style="border: 2px solid #e4e4e4;"><s:text
														name="global.currency" /><b> <s:property
															value="%{getText('format.currency.args',{customer.customerLimits.pendDue})}" /></b></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!--//// Customer Table -->


						<!--Loyalty Table -->
						<div class="col-md-4">
							<div class="box ">
								<div class="box-header">
									<h3 class="box-title" style="color: #226e71;">
										<s:property value="getText('loyalty.rewards')" />
										</b>
									</h3>
								</div>
								<div class="box-body no-padding">
									<table class="table table-bordered table-totals"
										style="border: 1px solid;">
										<tbody>
											<tr style="border: 2px solid #e4e4e4;">
												<td
													style="width: 40%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('available.points')" /></td>
												<td style="font-weight: 900; border: 2px solid #e4e4e4;">0</td>

											</tr>
											<tr style="border: 2px solid #e4e4e4;">
												<td style="font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('earned.points')" /></td>
												<td style="font-weight: 900; border: 2px solid #e4e4e4;">0</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!--//// Loyalty Table -->

						<!--Loyalty Table -->
						<div class="col-md-4">
							<div class="box ">
								<div class="box-header">
									<h3 class="box-title" style="color: #226e71;">
										<s:property value="getText('table.head.transactions_details')" />
									</h3>
								</div>
								<div class="box-body no-padding">
									<table class="table ">
										<tbody>
											<tr style="border: 2px solid #e4e4e4;">
												<td
													style="width: 40%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('no.of.items')" /></td>
												<td
													style="color: #000; border: 2px solid #e4e4e4; text-align: right;"
													id="itemCount"><b> <s:if
															test="%{orderTran.getOrdTranLineItems()==null}">0</s:if>
														<s:else>
															<s:property
																value="orderTran.getOrdTranLineItems().size()" />
														</s:else>
												</b></td>
											</tr>
											<tr style="border: 2px solid #e4e4e4;">
												<td
													style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('total.qty')" /></td>
												<td
													style="color: #000; border: 2px solid #e4e4e4; text-align: right;"
													id="qtyTot"><b><s:text name="%{#totalItemQty}"></s:text></b></td>
											</tr>
											<tr style="border: 2px solid #e4e4e4;">
												<td
													style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('subtotal.label')" /></td>
												<td
													style="color: #000; border: 2px solid #e4e4e4; text-align: right;"><s:text
														name="global.currency" />&nbsp<b id="subtotal"> <s:property
															value="%{getText('format.currency.args',{orderTran.getOrdTranSum().dkartSlsTot})}" /></b></td>
											</tr>
											<tr style="border: 2px solid #e4e4e4;">
												<td
													style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('total.discount')" /></td>
												<td
													style="color: #000; border: 2px solid #e4e4e4; text-align: right;"><s:text
														name="global.currency" />&nbsp<b id="discount"> <s:property
															value="%{getText('format.currency.args',{orderTran.getOrdTranSum().dkartDscTot})}" /></b>
												</th>
											</tr>
											<tr style="border: 2px solid #e4e4e4;">
												<td
													style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property
														value="getText('table.head.tax')" /></td>
												<td
													style="color: #000; border: 2px solid #e4e4e4; text-align: right;"><s:text
														name="global.currency" />&nbsp<b id="totalTax"> <s:property
															value="%{getText('format.currency.args',{orderTran.getOrdTranSum().dkartTaxTot})}" /></b></td>
											</tr>
											<tr
												style="border: 2px solid #e4e4e4; background-color: #6e7071; color: white;">
												<td
													style="width: 30%; font-weight: 900; border: 2px solid #e4e4e4; font-size: 17px;"><s:property
														value="getText('net.total')" /></td>
												<td
													style="border: 2px solid #e4e4e4; font-size: 20px; text-align: right;"><s:text
														name="global.currency" />&nbsp<b id="total"> <s:property
															value="%{getText('format.currency.args',{orderTran.getOrdTranSum().dkartNetTot})}" /></b></td>
												<span id="totalExcludingServicePrice" style="display: none">
													<s:property
														value="orderTran.getOrdTranSum().totalExcludingServicePrice" />
												</span>


												<input type="text" id="enableDoubleDiscounts"
													value="<s:property
															value="enableDoubleDiscounts" />"
													hidden="true" />
												<input type="text" id="anyItemLevelManualDiscuntsApplyed"
													value="<s:property
												value="orderTran.anyItemLevelManualDiscuntsApplyed" />"
													hidden="true" />
												<input type="text" id="anyTranLevelManualDiscuntsApplyed"
													value="<s:property
												value="orderTran.anyTranLevelManualDiscuntsApplyed" />"
													hidden="true" />
											</tr>

										</tbody>
									</table>
								</div>
							</div>
							<div>
								<input type="submit" value="Book Order" id="BookOrder" disabled
									hidden="true">
								<!-- <a href="#cancel"> -->
								<button class="btn btn-primary" type="button" id="cancelbutton">
									<s:property value="getText('cancel.button')" />
								</button>
								</a>
								<s:if
									test="%{orderTran.getOrdTranLineItems()!=null && orderTran.getOrdTranLineItems().size()>0}">
									<!-- <a href="#quoting"> -->
									<button class="btn btn-primary pull-right" type="button"
										id="quotebutton">
										<s:property value="getText('quote.button')" />
										&nbsp;<span id="quotespinner" class="fa" aria-hidden="true"></span>
									</button>
									</a>
								</s:if>
								<s:else>

									<!-- <a href="#quoting"> -->
									<button class="btn btn-primary pull-right" type="button"
										id="quotebutton" disabled>
										<s:property value="getText('quote.button')" />
										&nbsp;<span id="quotespinner" class="fa" aria-hidden="true"></span>
									</button>
									</a>

								</s:else>
							</div>
						</div>
					</div>




				</form>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

	</div>

	<!-- ./wrapper -->


	<!-- inventory Modal -->
	<div class="modal fade bs-example-modal-sm" id="inventoryModal"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">


		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<!--  <button type="button" class="close" data-dismiss="modal">&times;</button> -->
					<h4 class="modal-title">
						<s:property value="getText('_promptmsg.validateinventory')" />
					</h4>
				</div>
				<div class="modal-body">
					<s:property value="getText('_promptmsg.validateinventory1')" />
					<br>
					<br>
					<div class="row">
						<div class="col-xs-4">
							Item Id: <b><span id="modalItemID"></span></b>
						</div>
						<div class="col-xs-4">
							<s:property value="getText('_promptmsg.validateinventory2')" />
							: <b><span id="reqInv"></span></b>
						</div>
						<div class="col-xs-4">
							<s:property value="getText('_promptmsg.validateinventory3')" />
							: <b><span id="availInv"></span></b>
						</div>
					</div>
					<%-- <span id="modalItemID" class="badge"></span><br/><br/>
						<s:property value="getText('_promptmsg.validateinventory2')" />
						<span id="reqInv" class="badge"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						<s:property value="getText('_promptmsg.validateinventory3')" />
						<span id="availInv" class="badge"></span>
						<br /> <br /> Press <span class="badge">Add Avail Qty</span> to add item with available
						inventory.<br /> <br /> Press <span class="badge">Override</span>
						to add item with requested inventory. --%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="invYes">
						<s:property value="getText('add.avail.qty.button')" />
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="invNo">
						<s:property value="getText('override.button')" />
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="invClose">
						<s:property value="getText('close.button')" />
					</button>
				</div>
			</div>

		</div>
	</div>
	<!-- No inventory available when quantity given through item edit model @mallikarjun-->
	<div class="modal fade bs-example-modal-sm" id="noInventoryAvlModal"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">


		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<!--  <button type="button" class="close" data-dismiss="modal">&times;</button> -->
					<h4 class="modal-title">
						<s:property value="getText('_promptmsg.validateinventory')" />
					</h4>
				</div>
				<div class="modal-body">
					<s:property value="getText('_promptmsg.validateinventory1')" />
					<span id="modalItemID" class="badge"></span><br />
					<br />
					<s:property value="getText('_promptmsg.validateinventory2')" />
					<span id="reqInv" class="badge"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					<s:property value="getText('_promptmsg.validateinventory3')" />
					<span id="availInv" class="badge"></span>
					<%-- <br /> <br />
						Press <span class="badge">Add Avail Qty</span> to add item with
						available inventory.<br /> <br /> Press <span class="badge">Override</span>
						to add item with requested inventory. --%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="editInvYes">
						<s:property value="getText('add.avail.qty.button')" />
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="editInvNo">
						<s:property value="getText('override.button')" />
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="editinvClose">
						<s:property value="getText('close.button')" />
					</button>
				</div>
			</div>

		</div>
	</div>

	<!-- when exceeds the credit limit total @mallikarjun-->
	<div class="modal fade" id="noAvlCrdLmt" data-backdrop="static"
		data-keyboard="true" tabindex="-1" role="dialog"
		aria-labelledby="noAvlCrdLmtLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" aria-label="Close"
						data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="noAvlCrdLmtLabel">Available Credit
						Limit Exceeded</h4>
				</div>
				<div class="modal-body">
					<p>
						<s:property value="getText('_promptmsg.transaction.total.exceed')" />
					</p>
					<p style="padding-left: 20px;">
						Press <span class="badge badge-info">Override</span> to allow a
						manager to override available credit limit of the customer
					</p>
					<p style="padding-left: 20px;">
						Press <span class="badge badge-info">Ok</span> to continue without
						adding the item
					</p>
				</div>
				<div class="modal-footer">
					<s:if test="%{creditLimitOverride}">
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							data-toggle="modal" data-target="#creditLimitOverride">
							Override</button>
					</s:if>
					<s:else>
						<button type="button" class="btn btn-primary disabled"
							title="Use parameter 'EnableExceedingCustomerAvailableLimit' to enable this feature">
							Override</button>
					</s:else>
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						<s:property value="getText('ok.button')" />
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="creditLimitOverride" tabindex="-1"
		role="dialog" data-backdrop="static"
		aria-labelledby="creditLimitOverrideLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" aria-label="Close"
						data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="creditLimitOverrideLabel">Credit
						Limit Override</h4>
				</div>
				<div class="modal-body">
					<form action="overrideCreditLimit" method="post"
						id="creditLimitOverrideForm" class="form-horizontal" role="form">
						<div class="form-group">
							<label class="control-label col-xs-6" for=""
								style="padding-top: 1px"> Credit Limit</label>
							<div class="col-xs-6" style="padding-left: 0px">
								<small><s:text name="global.currency" />&nbsp;</small> <b
									class="form-control-static" id="avlCrdLmt1"><s:property
										value="%{getText('format.currency.args',{orderTran.transCrdtLimit})}" /></b>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-6" for="newCreditLimit"
								style="padding-top: 4px"> New Credit Limit</label>
							<div class="col-xs-6" style="padding-left: 0px">
								<input type="number" class="form-control" id="newCreditLimit"
									name="newCreditLimit" placeholder="Enter New Credit Limit"
									step="1"
									onkeypress="return event.charCode >= 48 && event.charCode <= 57"
									required="required" min="0"
									max="<s:property value="%{orderTran.customer.maxCrdtLmtOvrrdAllwd}" />"
									autocomplete="off">

							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-6" style="padding-top: 4px"></label>
							<div class="col-xs-6" style="padding-left: 0px">
								<p style="font-size: 10px; font-weight: bold">
									(
									<s:text name="NewOrder.CrdtLmtOvrrd.MaxAllwd" />
									<s:property
										value="%{getText('format.currency.args',{orderTran.customer.maxCrdtLmtOvrrdAllwd})}" />
									)
								</p>
							</div>
						</div>
						<hr>
						<div class="form-group">
							<label class="control-label col-xs-4" for="loginid"
								style="padding-top: 4px"> Login ID</label>
							<div class="col-xs-8" style="padding-left: 0px">
								<input type="text" class="form-control" id="loginid"
									name="loginid" placeholder="Enter Login ID" required="required"
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4" for="password"
								style="padding-top: 4px"> Password</label>
							<div class="col-xs-8" style="padding-left: 0px">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Enter password"
									required="required">
							</div>
						</div>
						<div class="form-group" style="margin-top: 10px">
							<button type="submit" class="btn btn-primary center-block">
								Submit<i class="hidden">&nbsp;<i
									class="fa fa-spin fa-spinner" aria-hidden="true"></i></i>
							</button>
						</div>
						<div class="alert alert-danger hidden"
							id="managerOverrideErrorResponse"
							style="padding: 4px; margin-top: 10px; margin-bottom: 0px">
						</div>

					</form>
					<div class="form-group hidden" id="managerOverrideSuccessDiv">
						<div class="alert alert-success"
							id="managerOverrideSuccessResponse"
							style="padding: 4px; margin-top: 0px; margin-bottom: 10px">
						</div>
						<button type="submit" id="movrsucbut"
							class="btn btn-primary center-block" data-dismiss="modal">Ok</button>
					</div>
				</div>
				<%-- <div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							Override</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<s:property value="getText('ok.button')" /></button>
					</div>  --%>
			</div>
		</div>
		<script type="text/javascript">
			<!--laxmikanth start [for new credit limit field validation] -->
				$("#newCreditLimit").keypress(function(e) {
  					var newCreditLimitVal = $(this).val();
					var key = e.which;
					if(newCreditLimitVal){
						var newVal = newCreditLimitVal+String.fromCharCode(e.keyCode);
						var maxAllwd = $("#newCreditLimit").attr("max");
						if(parseFloat(newVal) > parseFloat(maxAllwd)){
							e.preventDefault();
						}
					}
					if((key < 48) || (key > 57) || newCreditLimitVal.length > 13 ){
						if(key == '46'){
							if((newCreditLimitVal.indexOf('.') != -1) ){
								e.preventDefault();
							}
							return;
						}
						e.preventDefault();
					}
				});
				<!--laxmikanth end -->
				function managerOverrideErrorResponse(response){
					$('#managerOverrideErrorResponse').html('Override could not be processed:<br><strong>'+response+'</strong>');
					$('#managerOverrideErrorResponse').removeClass('hidden');
				}
				function hideManagerOverrideResponse(){
					$('#managerOverrideErrorResponse').addClass('hidden');
				}
				function managerOverrideSuccessResponse(response){
					var currency = "<s:property value="getText('global.currency')"/>";
					$('#managerOverrideSuccessResponse').html("New Credit Limit has been successfully set to "+currency+" "+response);
					$('#creditLimitOverrideForm').addClass('hidden');
					$('#managerOverrideSuccessDiv').removeClass('hidden');
				}
				function loading(){
					$("#creditLimitOverrideForm :input").attr("readonly", true);
					$("#creditLimitOverrideForm button i").removeClass('hidden');
				}
				function doneLoading(){
					$("#creditLimitOverrideForm :input").attr('readonly',false);
					$("#creditLimitOverrideForm button i").addClass('hidden');
				}
				function resetOverrideModal(){
					$("#creditLimitOverrideForm").get(0).reset();
					$("#managerOverrideSuccessResponse").html('');
					$("#managerOverrideErrorResponse").addClass('hidden');
					$("#creditLimitOverrideForm").removeClass('hidden');
					$("#managerOverrideSuccessDiv").addClass('hidden');
				}
				
				$('#creditLimitOverride').on('show.bs.modal', function (e) {
				  resetOverrideModal();
				})
				
				$('#creditLimitOverrideForm').submit(function(e){
					e.preventDefault();
					
					hideManagerOverrideResponse();
					var data = $(this).serialize();
					loading();
					$.ajax({
						type: $(this).attr('method'),
						url: $(this).attr('action'),
						data: data,
						timeout: 10000
					}).done(function(data, textStatus, jqXHR) {
						doneLoading();
						if(!data.validCredentials){
							managerOverrideErrorResponse('Invalid Credentials');
						}
						else{
							if(!data.managerOverride){
								managerOverrideErrorResponse('Not Authorized');
							}
							else if(!data.validCreditLimit){
								managerOverrideErrorResponse('Invalid Credit Limit');
							}
							else{
								$.ajax({
									type: 'post',
									url: 'reloadOrdTran',
									data: '',
									timeout: 10000
								}).done(function(data, textStatus, jqXHR) {
									$("#avlCrdLmt1").html(format(currencyFormat,data.orderTran.transCrdtLimit));
									UpdateTableContent(data);
									managerOverrideSuccessResponse(format(currencyFormat,data.orderTran.transCrdtLimit));
								}).fail(function(jqXHR, textStatus, errorThrown) {
									//TODO
								});	
							}
						}
						
					}).fail(function(jqXHR, textStatus, errorThrown) {
						doneLoading();
						managerOverrideErrorResponse(textStatus);
						
			            alert('An error occured while processing the request:\n' + errorThrown);
			           	
					}).always(function(){
						
					});
					
				});
				
			</script>
	</div>

	<!-- when Error Model @ Krishna-->
	<div class="modal fade bs-example-modal-sm" id="errorModel"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="errorTitle" class="modal-title">
						<s:property value="getText('_exception')" />
					</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="button" class="btn btn-primary center-block"
						data-dismiss="modal">
						<s:property value="getText('ok.button')" />
						&nbsp;
					</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal to show when the UploadLPO file type is not matched -->
	<div class="modal fade bs-example-modal-sm" id="lpoSlipModel"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="errorTitle" class="modal-title">
						<s:property value="getText('_promptmsg.uploadfile')" />
					</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="button" class="btn btn-primary center-block"
						data-dismiss="modal">
						<s:property value="getText('ok.button')" />
						&nbsp;
					</button>
				</div>
			</div>

		</div>
	</div>
	<!-- End of modal -->

	<!-- EditItem Modal-->
	<div class="modal fade bs-example-modal-sm" id="editItemModal"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header bg-blue">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="text-align: center">
						<s:text name="edit.item" />
					</h4>
				</div>
				<div class="modal-body table-responsive" style="overflow: visible;">
					<table class="table table-striped ">
						<tr>
							<td><s:text name="edit.item.id" /></td>
							<td id="editModalItemId"></td>
							<td><s:text name="edit.item.desc" /></td>
							<td colspan="3" id="editModalItemDesc"></td>

						</tr>
						<tr>
							<td><s:text name="edit.item.qty" /></td>
							<td id="editModalItemQty"></td>
							<td><s:text name="edit.item.newQty" /></td>
							<td colspan="1"><input type="number" min="1"
								id="editModalItemNewQty" style="width: 100px;" step="1"
								onkeypress='return isNumberKey(event);'
								onInput="checkLength(9,this)"></td>
							<td><s:text name="NewOrder.TableHead.AvlQty" />:</td>
							<td id="editModelAvlQty"></td>
						</tr>
						<tr>
							<td><s:text name="edit.item.price" /></td>
							<td id="editModalItemPrice"></td>
							<td><s:text name="edit.item.newPrice" /></td>
							<td><input type="number" min="1" id="editModalItemNewPrice"
								style="width: 100px;" name="newCreditLimit" step="1"
								onkeypress='return isNumberKey(event);'
								onInput="checkLength(14,this)"></td>
							<td><s:text name="edit.item.reasonCode" /></td>
							<td><select id="priceReasonCode" name="priceReasonCode"
								class="form-control"
								style="font-weight: bold; font-size: 12px; color: black">
							</select></td>
						</tr>
						<tr>
							<td><s:text name="edit.item.discountPer" /></td>
							<td id="editModalItemDiscPer"></td>
							<td><s:text name="edit.item.newDiscountPer" /><br />
							<p style="font-size: 10px; font-weight: bold">
									(
									<s:text name="edit.item.maxDisPer" />
									<s:property value="maxDisPerc" />
									)
								</p></td>
							<td><input type="number" min="0"
								max="<s:property value="maxDisPerc" />"
								id="editModalItemNewDiscPer" step="1"
								onkeypress='return isNumberKey(event);' data-toggle="tooltip"
								data-content="" style="width: 100px;"></td>
							<td><s:text name="edit.item.reasonCode" /></td>
							<td><select id="discPerReasonCode" name="discPerReasonCode"
								class="form-control"
								style="font-weight: bold; font-size: 12px; color: black">
							</select></td>
						</tr>
						<tr>
							<td><s:text name="edit.item.discountAmt" /></td>
							<td id="editModalItemDiscAmt"></td>
							<td><s:text name="edit.item.newDiscountAmt" /></td>
							<td><input type="number" min="0" step="1"
								onkeypress='return isNumberKey(event);'
								id="editModalItemNewDiscAmt" style="width: 100px;"></td>
							<td><s:text name="edit.item.reasonCode" /></td>
							<td><select id="discAmtReasonCode" name="discAmtReasonCode"
								class="form-control"
								style="font-weight: bold; font-size: 12px; color: black">
							</select></td>
						</tr>
						<tr>
							<td><s:text name="edit.item.salesAgent" /></td>
							<td id="editModalItemSalesAgent"></td>
							<td><s:text name="edit.item.newSalesAgent" /></td>
							<td colspan="3"><s:select theme="bootstrap"
									id="editModalItemNewSalesAgent"
									list="%{(SalesAgentList!=null?SalesAgentList:{})}"
									class="form-control" style="margin: 1px;" headerKey="-1"
									headerValue="----- Select Agent -----"></s:select> <!-- <input type="text" id="editModalItemNewSalesAgent" style="width:100px;"> --></td>
						</tr>
					</table>
					<span id="editItemIndex" style="display: none"></span> <span
						id="editModalItemOvrrPrice" style="display: none"></span>

				</div>
				<div class="modal-footer text-center">
					<button type="submit" class="btn btn-primary" data-dismiss="modal"
						id="editItemBtn" disabled>
						<s:property value="getText('ok.button')" />
						&nbsp;<span id="editItemSpinner" class="fa" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="cancelEditItemBtn">
						<s:property value="getText('cancel.button')" />
					</button>
				</div>
				<!-- <div class="modal-footer text-center">
						
					</div> -->
			</div>


		</div>
	</div>

	<!-- transaction level discount% Modal-->
	<div class="modal fade bs-example-modal-sm" id="tranDiscPerModal"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="text-align: center">
						<s:text name="tran.disc.percent" />
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td><s:text name="edit.item.discountPer" /><br />
							<p style="font-size: 10px; font-weight: bold">
									(
									<s:text name="edit.item.maxDisPer" />
									<s:property value="maxDisPerc" />
									)
								</p></td>
							<td><input type="number" min="0"
								max="<s:property value="maxDisPerc" />" id="tranDiscPerTxt"
								step="1" onkeypress="return isNumberKey(event)"
								style="width: 100px;"></td>
						</tr>
						<tr>
							<td><s:text name="edit.item.reasonCode" /></td>
							<td><select id="tranDiscPerReasonCode"
								name="tranDiscPerReasonCode" class="form-control"
								style="font-weight: bold; font-size: 12px; color: black; width: 100px;">
							</select></td>
						</tr>
					</table>

				</div>
				<div class="modal-footer text-center">
					<button type="submit" class="btn btn-primary center-block"
						data-dismiss="modal" id="tranDiscPerBtn">
						<s:property value="getText('ok.button')" />
						&nbsp;<span id="tranDiscPerSpinner" class="fa" aria-hidden="true"></span>
					</button>

				</div>
			</div>

		</div>
	</div>

	<!-- transaction level discountAmt Modal-->
	<div class="modal fade bs-example-modal-sm" id="tranDiscAmtModal"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="text-align: center">
						<s:text name="tran.disc.amount" />
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td><s:text name="edit.item.discountAmt" /></td>
							<td><input type="number" min="0" step="0.01"
								id="tranDiscAmtTxt" onkeypress="return isNumberKey(event)"
								style="width: 100px;"></td>
						</tr>
						<tr>
							<td><s:text name="edit.item.reasonCode" /></td>
							<td><select id="tranDiscAmtReasonCode"
								name="tranDiscAmtReasonCode" class="form-control"
								style="font-weight: bold; font-size: 12px; color: black; width: 100px;">
							</select></td>
						</tr>
					</table>

				</div>
				<div class="modal-footer text-center">
					<button type="submit" class="btn btn-primary center-block"
						data-dismiss="modal" id="tranDiscAmtBtn">
						<s:property value="getText('ok.button')" />
						&nbsp;<span id="tranDiscAmtSpinner" class="fa" aria-hidden="true"></span>
					</button>

				</div>
			</div>

		</div>
	</div>
	<!--modal to show when to click on quote button @sharanya  -->
	<div class="modal fade bs-example-modal-sm" id="quoting"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<s:property value="getText('_promptmsg.quote')" />
					</h4>
				</div>
				<div class="modal-footer text-center">
					<button class="btn btn-primary center-block"
						onclick="quoteOrderAction()">
						<s:property value="getText('ok.button')" />
					</button>
				</div>
			</div>
		</div>
		<script type="text/javascript">
				function quoteOrderAction(){
					preventBack = false;
					$("#mainForm").submit();
				}
			</script>
	</div>

	<!--modal to show when to click on delete button @sharanya  -->
	<div class="modal fade bs-example-modal-sm" id="deleteItemModal"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="margin: auto; max-width: 250px;">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>Are you sure you want to delete this item ?</h4>
				</div>
				<div class="modal-footer" style="margin: auto; max-width: 300px;">
					<input type="number" hidden="true" id="deleteItemModaltxt" />
					<button type="submit"
						class="pull-left btn btn-primary center-block"
						data-dismiss="modal" id="deleteItemfromTable">
						<s:property value="getText('yes.button')" />
						&nbsp;
					</button>

					<button type="submit"
						class="pull-right btn btn-primary center-block"
						data-dismiss="modal" id="nodeleteItemfromTable">
						<s:property value="getText('no.button')" />
						&nbsp;
					</button>

				</div>
			</div>

		</div>
	</div>





	<!--Display  double discount WARN popup-->


	<div class="modal fade bs-example-modal-sm" id="enableDounleDiscWarn"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header bg-blue">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="text-align: center">
						<s:text name="disc.already.appy.notice.header" />
					</h4>
				</div>
				<div class="modal-body text-center">

					<p>
						<s:text name="disc.already.appy.notice.message" />
					</p>
				</div>
				<div class="modal-footer text-center">
					<button type="submit" class="btn btn-primary" data-dismiss="modal"
						id="disableDoubleDiscBtn">
						<s:property value="getText('ok.button')" />
						&nbsp;<span id="editItemSpinner" class="fa" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						<s:property value="getText('cancel.button')" />
					</button>
				</div>
			</div>

		</div>
	</div>


	<!-- modal to show when you click on cancel button @sharanya -->
	<div class="modal fade bs-example-modal-sm" id="cancel"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="errorTitle" class="modal-title">
						<s:property value="getText('message_4')" />
					</h4>
				</div>
				<div class="modal-footer" style="margin: auto; max-width: 300px;">
					<button type="submit"
						class="pull-right btn btn-primary center-block"
						data-dismiss="modal">
						<s:property value="getText('no.button')" />
						&nbsp;
					</button>
					<form action="CancelSalesOrder">
						<button type="submit"
							class="pull-left btn btn-primary center-block" id="cancelorder"
							onclick="preventBack=false;">
							<s:property value="getText('yes.button')" />
							&nbsp;
						</button>
					</form>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="giftVoucherCode" data-backdrop="static"
		data-keyboard="true" tabindex="-1" role="dialog"
		aria-labelledby="giftVoucherCodeLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" aria-label="Close"
						data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="giftVoucherCodeLabel">Enter Gift
						Voucher details</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form"
						onsubmit="return NewOrder.addGiftVoucher()">
						<div class="form-group">
							<label class="col-sm-6 control-label" for="giftVouchCode">Gift
								Voucher Code</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="giftVouchCode"
									placeholder="Gift Voucher Code" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-6 control-label" for="giftVouchValue">Gift
								Voucher Value</label>
							<div class="col-sm-6">
								<input type="number" class="form-control" id="giftVouchValue"
									placeholder="Gift Voucher Value" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<s:if test="%{creditLimitOverride}">
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							data-toggle="modal" data-target="#creditLimitOverride">
							Override</button>
					</s:if>
					<s:else>
						<button type="button" class="btn btn-primary disabled"
							title="Use parameter 'EnableExceedingCustomerAvailableLimit' to enable this feature">
							Override</button>
					</s:else>
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						<s:property value="getText('ok.button')" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- modal to display the manager override @laxmikanth -->
	<div class="modal fade" id="managerOverride" tabindex="-1"
		role="dialog" data-backdrop="static" data-keyboard="false"
		aria-labelledby="managerOverrideLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" aria-label="Close"
						data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="managerOverrideLabel">
						<s:property value="getText('manager.override')" />
					</h4>
				</div>
				<div class="modal-body">
					<form action="managerOverride" method="post"
						id="managerOverrideForm" class="form-horizontal" role="form">
						<div class="form-group">
							<label class="control-label col-xs-4" for="loginid"
								style="padding-top: 4px"> Login ID</label>
							<div class="col-xs-8" style="padding-left: 0px">
								<input type="text" class="form-control" id="loginid"
									name="loginid" placeholder="Enter Login ID" required="required"
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4" for="password"
								style="padding-top: 6px"> Password</label>
							<div class="col-xs-8"
								style="padding-left: 0px; padding-top: 8px;">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Enter password"
									required="required">
							</div>
						</div>
						<div class="form-group"
							style="margin-top: 10px; padding-top: 8px;">
							<button type="submit" class="btn btn-primary center-block">
								Submit<i class="hidden">&nbsp;<i
									class="fa fa-spin fa-spinner" aria-hidden="true"></i></i>
							</button>
						</div>
						<div class="alert alert-danger hidden"
							id="managerOverrideErrorResponse1"
							style="padding: 4px; margin-top: 10px; margin-bottom: 0px">
						</div>

					</form>
					<div class="form-group hidden" id="managerOverrideSuccessDiv1">
						<div class="alert alert-success"
							id="managerOverrideSuccessResponse1"
							style="padding: 4px; margin-top: 0px; margin-bottom: 10px">
						</div>
						<button type="submit" id="override_button"
							class="btn btn-primary center-block" data-dismiss="modal">Ok</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	<!-- sharanya -->
			<!--  parameter "itemid" is added by hanu to reduce ajax calls -->
function lookupPLUItem(itemid){		
				$.ajax({
					url : "pricelookup",
					type : "POST",
					data : {
						term : itemid,
			
					},
					dataType : "json",
					success : function(jsonResponse2)
					
					{
						//alert("jsonResponse=====1"+JSON.stringify(jsonResponse2)); 
						//alert("jsonResponse=====2"+JSON.stringify(jsonResponse2.availQty)); 
						//alert("jsonResponse=====3"+JSON.stringify(jsonResponse2.unitprice)); 
						
						if(jsonResponse2.inventoryAvail){
							var availQty=jsonResponse2.availQty;
							
							$('#avlQty').val(availQty);
							}else{
								$('#avlQty').val("0");
							    } 
						if(jsonResponse2.unitprice!=null){
								  $('#prce').val(jsonResponse2.unitprice);
							      }else{
								  $('#prce').val("0");
							}
					},
					 error: function(jqXHR, exception) {
			         	alert('An error occured while processing the request:\n' + exception);
			         }
			 
	});
}

<!-- laxmikanth: manager override popup for unauthorized roles(salesAgent and Data entry operator), -->
											<!--not to modify amount and discount at item level edit  -->
$("body").on("input paste","#editModalItemNewPrice,#editModalItemNewDiscPer,#editModalItemNewDiscAmt,#tranDiscAmtTxt,#tranDiscPerTxt",function(e){
	var managerOverride = <s:property value="managerOverride" />;
	if(managerOverride)
		checkAccess(this.id);
});
var MOflag = false;
$("#editModalItemNewPrice,#editModalItemNewDiscPer,#editModalItemNewDiscAmt").blur(function(){
	MOflag = false;
});
$("#optionsDropdown").click(function(){
	MOflag = false;
});
function checkAccess(id){
	var empRoleId = $("[id^='editHref_']").attr('data-emp-role-id');
	var access = (empRoleId == 5 || empRoleId == 6);
	if((access && MOflag == false)){
		$('#'+id).val("");
		$('#managerOverride').modal('show');
	}
}
function hideManagerOverrideResponse1(){
	$('#managerOverrideErrorResponse1').addClass('hidden');
}
function responseLoading1(){
	$("#managerOverrideForm :input").attr("readonly", true);
	$("#managerOverrideForm button i").removeClass('hidden');
}
function responseDoneLoading1(){
	$("#managerOverrideForm :input").attr('readonly',false);
	$("#managerOverrideForm button i").addClass('hidden');
}
function managerOverrideErrorResponse1(response){
	$('#managerOverrideErrorResponse1').html('Override could not be processed:<br><strong>'+response+'</strong>');
	$('#managerOverrideErrorResponse1').removeClass('hidden');
}
$('#managerOverride').on('show.bs.modal', function (e) {
	resetManagerOverrideModal1();
})
function resetManagerOverrideModal1(){
	$("#managerOverrideForm").get(0).reset();
	$("#managerOverrideSuccessResponse1").html('');
	$("#managerOverrideErrorResponse1").addClass('hidden');
	$("#managerOverrideForm").removeClass('hidden');
	$("#managerOverrideSuccessDiv1").addClass('hidden');
}
$('#managerOverrideForm').submit(function(e){
	e.preventDefault();
	
	hideManagerOverrideResponse1();
	var data = $(this).serialize();
	responseLoading1();
	$.ajax({
		type: $(this).attr('method'),
		url: $(this).attr('action'),
		data: data,
		timeout: 10000
	}).done(function(data, textStatus, jqXHR) {
		responseDoneLoading1();
		if(!data.validCredentials){
			managerOverrideErrorResponse1('Invalid Credentials');
		}
		else{
			if(!data.managerOverride){
				managerOverrideErrorResponse1('Not Authorized');
			}
			else{
				$('#managerOverrideSuccessResponse1').html('Manager override done successfully, Now you will be able to override it');
				$('#managerOverrideForm').addClass('hidden');
				$('#managerOverrideSuccessDiv1').removeClass('hidden');
				MOflag = true;
			}
		}
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		responseDoneLoading1();
		managerOverrideErrorResponse1(textStatus);
		
        alert('An error occured while processing the request:\n' + errorThrown);
       	
	}).always(function(){
		
	});
});
// to get focus first fiels on all popup
$('.modal').on('shown.bs.modal', function () {
	  $(this).find('input:first').focus();
});
<!--laxmikanth end -->

	</script>

	<script type="text/javascript">
    window.onbeforeunload = confirmExit;
    var preventBack=<s:property value="preventBack" />;
    function confirmExit() {
    	if(preventBack)
        return "You have attempted to leave this page. Are you sure?";
    }
</script>

	<script type="text/Javascript">
function checkLength(len,ele){
	  var fieldLength = ele.value.length;
	  if(fieldLength <= len){
	    return true;
	  }
	  else
	  {
	    var str = ele.value;
	    str = str.substring(0, str.length - 1);
	    ele.value = str;
	  }
	}

// modified the logic to enter the decimals @laxmikanth
function isNumberKey(evt)
{
	var currencyFormat = "<s:property value="getText('format.currency')"/>";
	var digit = format(currencyFormat,parseFloat('0.0'));
	var allowedDecimals = digit.split('.')[1].length;
	var charCode = (evt.which) ? evt.which : event.keyCode
	var targettedId = evt.target.id;
	var targettedIdCaseInSensitive = targettedId.toLowerCase();
	if(targettedIdCaseInSensitive.includes("qty")){
		if(charCode == 46)evt.preventDefault();
	}
	var val = $('#'+targettedId).val();
	if(charCode == 48 && (val == 0 || val == "")){
		$('#'+targettedId).val(0); // NOT TO ALLOW NO.OF ZEROS
		return false;
	}
	var dot = val.indexOf('.');
	var noOfDecimals = parseFloat('0.0');
	if(val != undefined && dot >= 1){
		noOfDecimals = val.split('.')[1].length;
	}
	
	if ( charCode !== 8){
		if(allowedDecimals == noOfDecimals){
			return false;
		}}
	
   if (charCode > 31 && (charCode < 46  || charCode > 57))
      return false;
	
	if(dot >= 1 && charCode == 46){ // NOT TO ALLOW MORE NO.OF DOTS
		return false;
	}
   return true;
}
	
</script>

</body>
</html>
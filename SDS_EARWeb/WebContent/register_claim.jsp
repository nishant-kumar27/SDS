<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Register Claim</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
	<link rel="stylesheet" href="assets/css/animate.css">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />

<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<link rel="stylesheet" href="assets/css/popup.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

  <![endif]-->

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

		<s:include value="topbar.jsp"></s:include>
		<s:include value="menubar.jsp"></s:include>

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<!-- Main content -->
			<section class="content">
				<small class="pull-right"
					style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property value="getText('newclaim.help_text')"/></b></small>
				<!-- Your Page Content Here -->
				<div class="row" style="margin: 0%;">
					<div class="nav-tabs-custom">
						<ul class="nav nav-pills">

							<li class="active"><a data-toggle="tab" href="#tab_1"
								aria-expanded="true"><s:property value="getText('with.invoice')"/></a></li>
							<li class=""><a id="without_Invoice" data-toggle="tab"
								href="#tab_2" aria-expanded="false"><s:property value="getText('without.invoice')"/></a></li>

						</ul>
						<div class="tab-content ">
							<div id="tab_1" class="tab-pane active">
								<div class="row">
									<form>
										<div class="col-md-2">&nbsp;</div>
										<div class="col-md-6 col-xs-12">

											<div class="form-group form-group-sm">
												<input type="text" placeholder=" Invoice No/Order ID" id="invoiceId" autocomplete="off" autofocus="autofocus"
													class="form-control" style="font-size: 14px;">

											</div>

										</div>
										<div class=" col-md-2 ">
											<button class="btn btn-block btn-sm pull-right " id="searchWithInvoice"
												style="color: #ffffff; width: 100px; margin-bottom: 10px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
												type="submit"><s:property value="getText('search.button')"/></button>
										</div>
									</form>
								</div>
								
								<div class="row">
									<div class="col-sm-4 invoice-col">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.id')"/>:</td>
												<td><b id="custID"></b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('status.label')"/>:</td>
												<td><b id="status"></b></td>
											</tr>

										</table>
									</div>
									<!-- /.col -->
									<div class="col-sm-4 invoice-col">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.name')"/>:
												</td>
												<td><b id="custName"></b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.segment')"/>:
												</td>
												<td><b id="custClass"></b></td>
											</tr>
										</table>
									</div>
									<!-- /.col -->
									<div class="col-sm-4 invoice-col">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('originalinv.no')"/>:
													
												</td>
												<td><b id="invoiceID"></b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('reason.code')"/>:
												</td>
												<td><select class="form-control input-xs">
														<option>option 1</option>
														<option>option 2</option>
														<option>option 3</option>
														<option>option 4</option>
														<option>option 5</option>
												</select></td>
											</tr>

										</table>
									</div>
								</div>
								<div class="row ">
									<div class="col-md-12 ">
										<div class="table-responsive">
											<table class="table table-striped" id="returnItems">
												<thead>
													<tr style="background-color: #ADC2EE;">
														<th style="min-width: 10px"><s:property value="getText('table.head.SNo')"/></th>
														<th style="min-width: 110px"><s:property value="getText('table.head.item')"/></th>
														<th style="min-width: 90px"><s:property value="getText('sold.qty')"/></th>
														<th style="min-width: 90px"><s:property value="getText('sold.at')"/></th>
														<th style="min-width: 100px;"><s:property value="getText('net.price')"/></th>
														<th style="min-width: 50px;"><s:property value="getText('qty.return')"/></th>
														<th style="min-width: 120px"><s:property value="getText('price.head')"/></th>
														<th style="min-width: 120px"><s:property value="getText('reason.code')"/></th>
													</tr>
												</thead>
												<tbody>

													

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- /.tab-pane -->
							<div id="tab_2" class="tab-pane ">
								<div class="row">
									<div class="col-md-2  "></div>
									<div class="col-md-6 col-xs-12">

										<div class="form-group">
											<table class="table">
												<tr>
													<td><input type="text" placeholder=" Customer Id/Name"
														class="form-control"></td>
													<td><input type="text" placeholder=" Item ID"
														class="form-control"></td>
												</tr>

											</table>


										</div>

									</div>
									<div class=" col-md-2 ">
										<button class="btn btn-block  pull-right "
											style="color: #ffffff; width: 100px; margin-bottom: 10px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
											type="button"><s:property value="getText('search.button')"/></button>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4 invoice-col">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.id')"/>:
													</b>
												</td>
												<td><b>10024339</b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('status.label')"/>:</b></td>
												<td><b><s:property value="getText('new_status')"/></b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('available.credit.limit')"/>
													:</b>
												</td>
												<td><b>QAR 235,236.00</b></td>
											</tr>

										</table>
									</div>
									<!-- /.col -->
									<div class="col-sm-4 invoice-col">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.name')"/>
													:</b>
												</td>
												<td><b>Carrefour</b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.segment')"/>
													:</b>
												</td>
												<td><b>Class A</b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('balance.due')"/>:</b></td>
												<td><b>QAR 2,345.00</b></td>
											</tr>
										</table>
									</div>
									<!-- /.col -->
									<div class="col-sm-4 invoice-col">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('reason.code')"/>
													:</b>
												</td>
												<td><select class="form-control">
														<option>option 1</option>
														<option>option 2</option>
														<option>option 3</option>
														<option>option 4</option>
														<option>option 5</option>
												</select></td>
											</tr>

										</table>
									</div>

								</div>
								<div class="row ">
									<div class="col-md-12 ">
										<table style="width: 100%; margin: 0px;" class="bg-blue">
											<tbody>
												<tr>
													<td style="width: 40%;"><input
														style="margin-left: 5px; padding-left: 5px;"
														class="form-control" placeholder="Enter Item/Scan Item"
														type="text"></td>
													<td style="width: 1%;"></td>
													<td style="width: 80px;"><input
														style="width: 80px; padding-left: 5px;"
														class="form-control " placeholder="QTY" type="text">
													</td>
													<td style="width: 1%;"></td>
													<td style="">
														<button type="submit"
															style="width: 100px; margin: 2px; background: #666; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); border: 1px solid rgba(255, 255, 255, 0.77); font: normal 100 15px/1 sans-serif; background: linear-gradient(#717171, #262626 20%, #717171); color: #fff; display: inline-block;"
															class="btn  "><s:property value="getText('add.button')"/></button>
													</td>

													<td></td>
												</tr>

											</tbody>
										</table>
										<div class="table-responsive">
											<table class="table table-striped">
												<thead>
													<tr style="width: 100%;">

													</tr>
													<tr style="background-color: #ADC2EE;">
														<th style="min-width: 10px"><s:property value="getText('table.head.SNo')"/></th>
														<th style="min-width: 100px"><s:property value="getText('table.head.item')"/></th>
														<th style="min-width: 80px"><s:property value="getText('table.head.unitprice')"/></th>
														<th style="max-width: 90px;"><s:property value="getText('return.Qty')"/></th>
														<th style="min-width: 120px"><s:property value="getText('return.price')"/></th>
														<th style="min-width: 120px;"><s:property value="getText('net.price')"/></th>
														<th style="min-width: 100px"><s:property value="getText('reason.code')"/></th>


													</tr>
												</thead>
												<tbody>
													<tr>
														<td>1</td>
														<td>10000007<br>
														<small>Red Bull</small></td>
														<td>7.00</td>

														<td style="max-width: 50px;"><div class="form-group">
																<input type="text" style="width: 90px;">
															</div></td>
														<td style=""><div class="form-group">
																<a href="#manager_override"><input type="text"
																	style="width: 130px; background-color: #f7ffb7;"></a>
															</div></td>
														<td style="">QAR 49,000</td>
														<td><select class="form-control">
																<option>option 1</option>
																<option>option 2</option>
																<option>option 3</option>
																<option>option 4</option>
																<option>option 5</option>
														</select></td>


													</tr>
													<tr>
														<td>2</td>
														<td>10000123<br>
														<small>Pacific Ice Toilette</small></td>
														<td>160.00</td>
														<td style=""><div class="form-group">
																<input type="text" style="width: 90px;">
															</div></td>
														<td style=""><div class="form-group">
																<a href="#manager_override"><input type="text"
																	style="width: 130px; background-color: #f7ffb7;"></a>
															</div></td>
														<td style="">QAR 49,000</td>
														<td><select class="form-control">
																<option>option 1</option>
																<option>option 2</option>
																<option>option 3</option>
																<option>option 4</option>
																<option>option 5</option>
														</select></td>


													</tr>
													<tr>
														<td>3</td>
														<td>10000156<br>
														<small>EAU De Perfume</small></td>

														<td>190.00</td>
														<td><div class="form-group">
																<input type="text" style="width: 90px;">
															</div></td>
														<td><div class="form-group">
																<a href="#manager_override"><input type="text"
																	style="width: 130px; background-color: #f7ffb7;"></a>
															</div></td>
														<td style="">QAR 49,000</td>
														<td><select class="form-control">
																<option>option 1</option>
																<option>option 2</option>
																<option>option 3</option>
																<option>option 4</option>
																<option>option 5</option>
														</select></td>


													</tr>
												</tbody>
											</table>
										</div>

									</div>
								</div>


							</div>
						</div>
						<!-- /.tab-content -->
					</div>
				</div>
				<div class="row">

					<div class="col-md-4 pull-right" style="margin-top: -10px;">
						<div class="box ">

							<div class="box-body no-padding">
								<table class="table table-bordered">
									<tbody>
										<tr style="border: 2px solid #e4e4e4;">
											<th
												style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('subtotal.label')"/></th>
											<td style="color: #000; border: 2px solid #e4e4e4;"><s:property value="getText('global.currency')"/>
												<b id="subTotal">0.00</b>
											<div class="dropdown pull-right" style="margin-right: 10px;">


													<i class="fa fa-plus dropdown-toggle" type="button"
														data-toggle="dropdown"></i>
													<ul style="margin-left: 0px; background-color: #656a6b;"
														class="dropdown-menu ">
														<li><a href="#popup"><s:property value="getText('claimoptions.head1')"/></a></li>
														<li><a href="#popup1"><s:property value="getText('claimoptions.head2')"/></a></li>

													</ul>

												</div>
											</td>
										</tr>
										<tr style="border: 2px solid #e4e4e4;">
											<th
												style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('expenses.label')"/></th>
											<td style="color: #000; border: 2px solid #e4e4e4;"><s:property value="getText('global.currency')"/>
												<span id="expenses">0.00</span></td>
										</tr>

										<tr
											style="border: 2px solid #e4e4e4; background-color: #6e7071; color: white;">
											<th
												style="width: 40%; font-weight: 900; border: 2px solid #e4e4e4; font-size: 17px;"><s:property value="getText('net.total')"/>
												</th>
											<td style="border: 2px solid #e4e4e4; font-size: 20px;"><s:property value="getText('global.currency')"/>
												<b id="netTotal">0.00</b>
											</td>
										</tr>
										<tr>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-md-3 col-xs-12 pull-right">
						<a href="#" style="color: #ffffff;">
							<button class="btn btn-block " id="registerClaim"
								style="color: #ffffff; width: 200px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); border: 1px solid; color: #fff; display: inline-block;"
								type="button" disabled><s:property value="getText('register.claim.button')"/></button>
						</a>

					</div>
				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- ./wrapper -->
	<div id="manager_override" class="overlay1">
		<div class="popup" style="min-height: 120px; width: 300px;">
			<form action="#" method="get">
				<h5 style="text-align: center;">
					<b><s:property value="getText('_promptmsg.override.price')"/></b>
				</h5>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="User Id">
					<br> <input type="password" class="form-control"
						placeholder="Password"> <br> <select
						class="form-control">
						<option><s:property value="getText('reason.code')"/></option>
						<option>option 1</option>
						<option>option 2</option>
						<option>option 3</option>
						<option>option 4</option>
						<option>option 5</option>
					</select> <br>
					<button style="width: 100px; margin-left: 29%;" class="btn "><s:property value="getText('override.button')"/>
					</button>
				</div>

			</form>
			<a class="close" style="margin-top: -10px;" href="#">&times;</a>
		</div>
	</div>
	<div id="manager_override2" class="overlay1">
		<div class="popup" style="min-height: 120px; width: 300px;">
			<form action="#" method="get">
				<h5 style="text-align: center;">
					<b><s:property value="getText('_promptmsg.register.claim')"/></b>
				</h5>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="User Id">

					<br>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="Password">
					<br>


					<button style="width: 100px; margin-left: 29%;" type="submit"
						class="btn "><s:property value="getText('override.button')"/></button>
				</div>

			</form>
			<a class="close" style="margin-top: -10px;" href="#">&times;</a>
		</div>
	</div>
	<!-- REQUIRED JS SCRIPTS -->
	<div id="popup" class="overlay1">
		<div class="popup" style="min-height: 120px;">
			<p class="login-box-msg" style="color: black;">
				<b><s:property value="getText('message_11')"/>:</b>
			</p>

			<p class="login-box-msg">
				<input type="text" style="width: 100px;">
			</p>
			<a href=""><button class="btn btn-block btn-info"
					style="max-width: 80px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff; margin: auto;"
					type="submit"><s:property value="getText('confirm.button')"/></button></a> <a class="close" href="">&times;</a>

		</div>
	</div>
	<div id="popup1" class="overlay1">
		<div class="popup" style="min-height: 120px;">
			<p class="login-box-msg" style="color: black;">
				<b><s:property value="getText('message_16)"/>:</b>
			</p>

			<p class="login-box-msg">
				<input type="text" style="width: 100px;">
			</p>
			<a href="#Generate_Claim.html"><button
					class="btn btn-block btn-info"
					style="max-width: 80px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff; margin: auto;"
					type="submit"><s:property value="getText('confirm.button')"/></button></a> <a class="close"
				href="#Generate_Claim.html">&times;</a>

		</div>
	</div>
	<a href="#manager_override2" id="test"></a>

	<s:hidden id=""></s:hidden>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
	<script>
    $("#without_Invoice").click(function() {
      console.log("test");
      document.getElementById("test").click();

    });
  </script>
	<!-- ChartJS 1.0.1 -->

	<script type="text/javascript">
    var returnItems = [];

    
    function refreshLineItems() {
      $('#returnItems > tbody').empty();
      if (returnItems.length > 0) {
        var lineItem = "";
        for (var i = 0; i < returnItems.length; i++) {
          lineItem = '<tr><td>'
                  + (i + 1)
                  + '</td><td>'
                  + returnItems[i].itemId
                  + '<br><small>'
                  + returnItems[i].deItmShrtRcpt
                  + '</small></td><td>'
                  + returnItems[i].lineQnt
                  + '</td><td style="">'
                  + returnItems[i].itmPrnPrc
                  + '</td> <td style="">'
                  + (returnItems[i].lineQnt * returnItems[i].itmPrnPrc)
                  + '</td> <td style="max-width: 50px;"><div class="form-group"> <input type="number" id="returnQty'+i+'" min="0" max="'+returnItems[i].lineQnt+'" value="0" style="width: 90px;"> </div></td> <td style=""><div class="form-group"> <input type="text" id="priceOverride'+i+'" style="width:130px;background-color:#f7ffb7;" data-toggle="popover" data-trigger="focus" data-content="Will require manager override"> </div> </td> <td><select class="form-control input-xs"> <option>option 1</option> <option>option 2</option> <option>option 3</option> <option>option 4</option> <option>option 5</option> </select></td> </tr>';

          $('#returnItems > tbody').append(lineItem);
$('#returnItems > tbody').animateCss('fadeIn pulse');
        }
      }
      refreshTotals();
    }
    
    function refreshTotals() {
      subTotal = 0, expenses = 0, netTotal = 0;
      $.each(returnItems, function(i, val) {
        var qty = parseInt($('#returnQty' + i).val());
        var price = parseInt(returnItems[i].itmPrnPrc);
        subTotal += qty * price;
      });
      netTotal = subTotal + expenses;
      $('#subTotal').html(subTotal);
      $('#expenses').html(expenses);
      $('#netTotal').html(netTotal);
      
      $('#subTotal').closest('table').animateCss('fadeIn');
    }
  </script>

	<script type="text/javascript">
    
    $('#searchWithInvoice').click(function(e) {
      e.preventDefault();
      var invoiceId = $('#invoiceId');
      invoiceId.parent().removeClass('has-error');
      if (invoiceId.val().length < 1) {
        invoiceId.parent().addClass('has-error');
        invoiceId.focus();
        return;
      } else {
        //ajaxcall
        var ajaxData = 'invoiceID=' + $('#invoiceId').val();
        var ajaxUrl = 'orderSearchByOrder';

        $.ajax({
          url: ajaxUrl,
          data: ajaxData,
          type: 'post',
          //timeout : 15000,
          success: function(result, status, xhr) {
          	returnItems = result.lineItems;
          	
          	if(result.actionErrors.length>0)
          	{
          		$('#custID').html("");
          		$('#custName').html("");
          		$('#custClass').html("");
          		$('#invoiceID').html("");
          		$('#status').html("");
          		setTimeout(alert(result.actionErrors[0]), 500);
          		refreshLineItems();
          		return;
          	}
          	$('#invoiceId').val('');
   			$('#custID').html(result.customerID);
            $('#custName').html(result.customerName);
            $('#custClass').html(result.customerSegment);
            $('#invoiceID').html(result.invoiceID);
            $('#status').html("New");
            refreshLineItems();
          },
          error: function(jqXHR, exception) {
            alert(exception);
          }
        });
      }

    });

    $(document).ready(function() {
      $(function() {
        $('[data-toggle="popover"]').popover();
      });
    });

    $(document).on("change", "[id^=priceOverride]", function() {
      alert('manager override');
    });

    $(document).on("change", "[id^=returnQty]", function() {
      var max = parseInt($(this).attr('max'));
      var value = parseInt($(this).val());
      if (value > max) $(this).val(max);
      if (value < 1) $(this).val('0');
      refreshTotals();
    });

    //     $('[id^=priceOverride]').change(function() {
    //       alert('manager override');
    //     });
  </script>

	<script type="text/javascript">
    $('#claim').addClass('active');
    $('#registerclaim').addClass('active');

    $.fn.extend({
      animateCss: function(animationName) {
        var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
        $(this).addClass('animated ' + animationName).one(animationEnd, function() {
          $(this).removeClass('animated ' + animationName);
        });
      }
    });
  </script>
</body>
</html>
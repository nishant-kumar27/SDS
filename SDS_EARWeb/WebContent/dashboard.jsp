<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>SDS | Dashboard</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>
<script src="assets/chart/Chart.min.js"></script>
<script type="text/javascript" src="custom/format.js"></script>
<%-- 
<style>
.btn {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.4);
}

.btn:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.20), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
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

.btn {
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
}

.dropdown-menu>li>a {
	color: #f5f5f5;
	background-color: #656a6b;
}

@media ( max-width : 900px) {
	body {
		font-size: 12px !important;
	}
	.section {
		font-size: 12px !important;
	}
}

.sidebar-menu .treeview-menu>li>a {
	font-size: 13px;
}

body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}
/*
.small-box:before {
    position: absolute;
    display:inline-block;
    height: 100%;
    width: 100%;
    opacity: 0.5;
    background: url("https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif");
    background-size: contain;
    pointer-events: none;
}*/
</style> --%>
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

		<!-- Main Header -->
		<s:include value="topbar.jsp" />
		<s:set var="userId" scope="session" value="employee.employeeId" />

		<!-- Left side column. contains the logo and sidebar -->
		<s:include value="menubar.jsp" />



		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<!-- Main content -->
			<section class="content">
				
				<!-- Your Page Content Here -->
				<div class="row">
					<div class="col-lg-3 col-md-3 col-xs-6">
						<!-- small box -->
						<a href="<s:url action="invoices_dashboard"/>">
							<div class="small-box bg-blue-gradient ">

								<div class="inner">
									<h4 id="receivables_total">
										<s:property value="getText('receivables.label')"/>
									</h4>
									<br>
									<p id="receivables_total_label">&nbsp</p>
								</div>
								<div class="icon">
									<i class="fa fa-money"></i>
								</div>

							</div>
						</a>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-md-3 col-xs-6">
						<!-- small box -->
						<a href="<s:url action="orders_dashboard"/>">
							<div class="small-box bg-blue-gradient">
								<div class="inner">
									<h4 class="pull-right" id="return_order_count">
									</h4>
									<h4 id="sale_order_count">
										 <s:property value="getText('orders.label')"/>
									</h4>
									
									<br>
									<p class="pull-right" id="return_order_label">&nbsp</p>
									<p id="sale_order_label">&nbsp</p>
									
								</div>
								

							</div>
						</a>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-md-3 col-xs-6">
						<!-- small box -->
						<a href="claims_dashboard">
							<div class="small-box bg-blue-gradient">
								<div class="inner">
									<h4 id="claims_total">
										<s:property value="getText('claims.label')"/>
									</h4>
									<br>
									<p id="claims_total_label">&nbsp</p>
								</div>
								<div class="icon">
									<i class="fa fa-mail-reply-all"></i>
								</div>

							</div>
						</a>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-md-3 col-xs-6" >
						<!-- small box -->
						<a href="collections_dashboard">
							<div class="small-box bg-blue-gradient" >
								<div class="inner">
									<h4 id="collections_total">
										<s:property value="getText('collections.label')"/>
									</h4>
									<br>
									<p id="collections_total_label">&nbsp</p>
								</div>
								<div class="icon">
									<i class="fa fa-balance-scale"></i>
								</div>

							</div>
						</a>
					</div>
					<!-- ./col -->
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="box" style="min-height: 300px;">
							<div class="box-header with-border">
								<h3 class="box-title">
									<s:property value="getText('receivables.label')"/> -&nbsp;<i id="Receivables_range_tag"
										class="pull-right"><s:property value="getText('range.loading')"/></i>
								</h3>
								<div class="box-tools">
									<div class="dropdown " style="margin-right: 5px;">

										<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
											data-toggle="dropdown"></i>
										<ul id="range_receivables"
											style="margin-left: -150px; background-color: #656a6b;"
											class="dropdown-menu ">
											<li><a id="week"> <s:property value="getText('current.week')"/> </a></li>
											<li><a id="month"><s:property value="getText('current.month')"/></a></li>
											<li><a id="quarter"><s:property value="getText('current.quarter')"/></a></li>
											<li><a id="year"><s:property value="getText('current.year')"/></a></li>
											<li><a id="NONE"><s:property value="getText('none')"/></a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="box-body">
								<div id="receivables_data_spinner" class="chart">
									<canvas id="barChart_receivables"
										style="height: 230px; width: 510px;" width="510" height="230"></canvas>


								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /..claims -->
						<div class="box " style="min-height: 300px;">
							<div class="box-header with-border">
								<h3 class="box-title">
									<s:property value="getText('claims.label')"/> -&nbsp;<i id="Claims_range_tag"><s:property value="getText('range.loading')"/></i>
								</h3>
								<div class="box-tools">
									<div class="dropdown " style="margin-right: 5px;">

										<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
											data-toggle="dropdown"></i>
										<ul id="range_claims"
											style="margin-left: -150px; background-color: #656a6b;"
											class="dropdown-menu ">
											<li><a id="week"> <s:property value="getText('current.week')"/> </a></li>
											<li><a id="month"><s:property value="getText('current.month')"/></a></li>
											<li><a id="quarter"><s:property value="getText('current.quarter')"/></a></li>
											<li><a id="year"><s:property value="getText('current.year')"/></a></li>
											<li><a id="NONE"><s:property value="getText('none')"/></a></li>
										</ul>
									</div>
								</div>

							</div>
							<div class="box-body">
								<div id="claim_data_spinner" class="chart">
									<canvas id="barChart_claims"
										style="height: 230px; width: 510px;" width="510" height="230"></canvas>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!--- / End Claims -->
					</div>
					<div class="col-md-6">
						<!-- /.. Sales-->
						<div class="box box-default" style="min-height: 300px;">
							<div class="box-header with-border">
								<h3 class="box-title">
									<s:property value="getText('orders.label')"/> -&nbsp;<i id="Order_range_tag"><s:property value="getText('range.loading')"/></i>
								</h3>
								<div class="box-tools">
									<div class="dropdown " style="margin-right: 5px;">

										<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
											data-toggle="dropdown"></i>
										<ul id="range_orders"
											style="margin-left: -150px; background-color: #656a6b;"
											class="dropdown-menu ">
											<li><a id="week"> <s:property value="getText('current.week')"/> </a></li>
											<li><a id="month"><s:property value="getText('current.month')"/></a></li>
											<li><a id="quarter"><s:property value="getText('current.quarter')"/></a></li>
											<li><a id="year"><s:property value="getText('current.year')"/></a></li>
											<li><a id="NONE"><s:property value="getText('none')"/></a></li>
										</ul>
									</div>
								</div>

							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-8">
										<div id="order_data_spinner" class="chart-responsive" >
											<canvas id="pieChart" style="height: 350px; width: 530px;"
												width="530" height="265"></canvas>
										</div>
										<!-- ./chart-responsive -->
									</div>
									<!-- /.col -->
									<div class="col-md-4">
										<ul class="chart-legend clearfix">
											<li><i class="fa fa-square " style="color: #807ec7;"></i>
												<s:property value="getText('dashboard.quoted.orders')"/> </li>
											<li><i class="fa fa-square " style="color: #8064A1;"></i>
												<s:property value="getText('dashboard.new.order')"/></li>
											<li><i class="fa fa-square " style="color: #badb73;"></i>
												<s:property value="getText('open.orders')"/></li>
											<li><i class="fa fa-square " style="color: #9f6a80;"></i>
												<s:property value="getText('dashboard.inprogress.orders')"/></li>
											<li><i class="fa fa-square " style="color: #b97070;"></i>
												<s:property value="getText('dashboard.completed.orders')"/></li>
											<li><i class="fa fa-square " style="color: #6da164;"></i>
												<s:property value="getText('delivered.orders')"/></li>
											<li><i class="fa fa-square " style="color: #649fa1;"></i>
												<s:property value="getText('cancel.order')"/></li>
											<li><i class="fa fa-square " style="color: #a18764;"></i>
												<s:property value="getText('returned.orders')"/></li>
										</ul>
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
						</div>
						<!-- /.. End Sales -->
						<div class="box" style="min-height: 300px;">
							<div class="box-header with-border">
								<h3 class="box-title">
									<s:property value="getText('collections.label')" /> -&nbsp;<i id="Collections_range_tag"><s:property value="getText('range.loading')"/></i>
								</h3>
								<div class="box-tools">
									<div class="dropdown " style="margin-right: 5px;">

										<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
											data-toggle="dropdown"></i>

										 <ul id="range_collections"
											style="margin-left: -150px; background-color: #656a6b;"
											class="dropdown-menu ">
											<li><a id="week"> <s:property value="getText('current.week')"/> </a></li>
											<li><a id="month"><s:property value="getText('current.month')"/></a></li>
											<li><a id="quarter"><s:property value="getText('current.quarter')"/></a></li>
											<li><a id="year"><s:property value="getText('current.year')"/></a></li>
											<li><a id="NONE"><s:property value="getText('none')"/></a></li>
										</ul>
									</div>
								</div>
								
							</div>
							<div class="box-body">
								<div id="collection_data_spinner" class="chart">
									<canvas id="barChart_collections"
										style="height: 230px; width: 510px;" width="510" height="230"></canvas>
								</div>
							</div>
							<!-- /.box-body -->
						</div>


					</div>
				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->


		<!-- Control Sidebar -->

		<!-- Hidden form data -->
		<form action="orders_dashboard" name="hidden_order_data" method="post">
			<input type="hidden" name="empId" /> <input type="hidden"
				name="range" /> <input type="hidden" name="status" />
		</form>
		<form action="invoices_dashboard" name="hidden_receivables_data"
			method="post">
			<input type="hidden" name="range" /> <input type="hidden"
				name="status" />
		</form>
		<form action="claims_dashboard" name="hidden_claims_data"
			method="post">
			<input type="hidden" name="range" /> <input type="hidden"
				name="status">
		</form>
		
		<form action="collections_dashboard" name="hidden_collections_data"
			method="post">
			<input type="hidden" name="range" /> 
			<input type="hidden" name="x_axis_field">
			<input type="hidden" value="0" name="ischart">	
		</form>
	</div>


	<script>
	   var order_range 		= "<s:property value="default_range"/>", 
				claim_range 		= "<s:property value="default_range"/>", 
				receivables_range 	= "<s:property value="default_range"/>", 
				collections_range 	= "<s:property value="default_range"/>",
				refresh_interval 	= "<s:property value="refresh_interval"/>";
		$(function() {
			//setting the default range
			
            
            function getRangeLabel(range){
                if(range==="week"){
                    return "<s:property value="getText('current.week')" />";
                }
                else if(range==="month"){
                    return "<s:property value="getText('current.month')" />";
                }
                else if(range==="quarter"){
                    return "<s:property value="getText('current.quarter')" />";
                }
                else if(range==="year"){
                    return "<s:property value="getText('current.year')" />";
                }
                else if(range==="NONE"){
                    return "<s:property value="getText('none')" />";
                }
                
            }
				
			//receivables chart
			var recbarChartCanvas = $("#barChart_receivables").get(0)
					.getContext("2d");
			var gradient = recbarChartCanvas.createLinearGradient(0, 0, 0, 400);
			gradient.addColorStop(0, '#aa0000');
			gradient.addColorStop(1, '#380b0b');
			var gradient2 = recbarChartCanvas
					.createLinearGradient(0, 0, 0, 300);
			gradient2.addColorStop(0, '#22ac09');
			gradient2.addColorStop(1, '#14380e');
			var ctx = document.getElementById("barChart_receivables");
			options_receivables = {
				type : 'bar',
				data : {
					labels : [ "Current", "Overdue", ],
					datasets : [ {
						label : "Receivables",
						data : [ 0, 0 ],
						backgroundColor : [ gradient2, gradient ],
						borderColor : [ gradient2, gradient ],
						borderWidth : 1,
						hoverBackgroundColor : [ '#22ac09', '#aa0000' ],
						hoverBorderColor : [ gradient2, gradient, ]
					} ]
				},
				options : {

					legend : {
						display : false,
						labels : {
							fontColor : 'rgb(255, 99, 132)'
						}
					},
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				}
			};
           
            
			var recmyChart = new Chart(ctx, options_receivables);
			var currencyFormat = "<s:property value="getText('format.currency')"/>";
			function displayReceivables(){
				$.getJSON(
						'receivables_dashboard_barchart_action',
						{
							empId : "<s:property value="#session['userId']"/>",
							range : receivables_range
						},
						function(jsonResponse) {
							  console.log("Receivables JSON data returned for range:"+receivables_range+" at:"+new Date());
							document
									.getElementById("Receivables_range_tag").innerHTML = getRangeLabel(receivables_range);
							var sum =format(currencyFormat, jsonResponse.amounts[0]
									+ jsonResponse.amounts[1]);
							document.getElementById("receivables_total").innerHTML = "<s:property value="getText('global.currency')" /> "
									+ sum;
							document
									.getElementById("receivables_total_label").innerHTML = "<s:property value="getText('receivables.label')"/>";
							console.log(jsonResponse.amounts[0]);
							options_receivables.data.datasets[0].data = [
									jsonResponse.amounts[0],
									jsonResponse.amounts[1] ];
							recmyChart.update();
						});
			}
			function refreshReceivables(){
				$.getJSON(
						'receivables_dashboard_barchart_action',
						{
							empId : "<s:property value="#session['userId']"/>",
							range : receivables_range
						},
						function(jsonResponse) {
							  console.log("Receivables JSON data returned for range:"+receivables_range+" at:"+new Date());
							document
									.getElementById("Receivables_range_tag").innerHTML = getRangeLabel(receivables_range);
							var sum = jsonResponse.amounts[0]
									+ jsonResponse.amounts[1];
							
							options_receivables.data.datasets[0].data = [
									jsonResponse.amounts[0],
									jsonResponse.amounts[1] ];
							recmyChart.update();
						});
			}
			displayReceivables();
			

			//Claims Chart
			var clabarChartCanvas = $("#barChart_claims").get(0).getContext(
					"2d");
			var gradient = clabarChartCanvas.createLinearGradient(0, 0, 0, 400);
			gradient.addColorStop(0, '#848484');
			gradient.addColorStop(1, '#121212');

			var ctx = document.getElementById("barChart_claims");
			options_claims = {
				type : 'bar',
				data : {
					labels : [ "Registered", "Approved","In-Progress", "Accepted" ],
					datasets : [ {
						label : "Claims",
						data : [ 0, 0, 0 ],
						backgroundColor : gradient,
						borderColor : gradient,
						borderWidth : 1,
						hoverBackgroundColor : '#848484',
						hoverBorderColor : '#848484',
					} ]
				},
				options : {
					legend : {
						display : false,
						labels : {
							fontColor : 'rgb(255, 99, 132)'
						}
					},
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				}
			};
			var clamyChart = new Chart(ctx, options_claims);
			function displayClaims(){
                $.getJSON(
							'claims_dashboard_barchart_action',
							{
								range : claim_range
							},
							function(jsonResponse) {
								document.getElementById("Claims_range_tag").innerHTML = getRangeLabel(claim_range);
								console.log(jsonResponse.count);
								document.getElementById("claims_total").innerHTML = jsonResponse.claim_total_count;
								document.getElementById("claims_total_label").innerHTML = "<s:property value="getText('claims.label')"/>";
								options_claims.data.datasets[0].data = jsonResponse.count;
								options_claims.data.labels = jsonResponse.dept;
								clamyChart.update();
							});
            }
            
            function refreshClaims(){
                 $.getJSON(
							'claims_dashboard_barchart_action',
							{
								range : claim_range
							},
							function(jsonResponse) {
								document.getElementById("Claims_range_tag").innerHTML = getRangeLabel(claim_range);
								options_claims.data.datasets[0].data = jsonResponse.count;
								options_claims.data.labels = jsonResponse.dept;
								clamyChart.update();
							});
            }
            displayClaims();
       
            
            
			//collections chart    
			var barChartCanvas = $("#barChart_collections").get(0).getContext(
					"2d");
			var gradient = barChartCanvas.createLinearGradient(0, 0, 0, 300);
			gradient.addColorStop(0, '#63c429');
			gradient.addColorStop(1, '#1a330c');

			var ctx_collections = document
					.getElementById("barChart_collections");
			options_collections = {
				type : 'bar',
				data : {
					labels : [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
							"Aug", "Sep", "Oct", "Nov", "Dec" ],
					datasets : [ {
						label : "Collections",
						data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
						backgroundColor : gradient,
						borderColor : gradient,
						borderWidth : 1,
						hoverBackgroundColor : '#63c429',
						hoverBorderColor : '#63c429',
					} ]
				},
				options : {
					legend : {
						display : false,
						labels : {
							fontColor : 'rgb(255, 99, 132)'
						}
					},
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				}
			};
			var colmyChart = new Chart(ctx_collections, options_collections);
            function displayCollections(){
                $.getJSON(
							'collections_dashboard_piechart_action',
							{
								range : collections_range
							},
							function(jsonResponse) {
								
								document
										.getElementById("Collections_range_tag").innerHTML = getRangeLabel(collections_range);
								var collectns_totl=format(currencyFormat,jsonResponse.collection_total_amount);
								document.getElementById("collections_total").innerHTML = "<s:property value="getText('global.currency')" /> "+collectns_totl;
								document
										.getElementById("collections_total_label").innerHTML = "<s:property value="getText('collections.label')"/>";
								 options_collections.data.datasets[0].data=jsonResponse.collections;
								options_collections.data.labels=jsonResponse.labels;
								colmyChart.update();

							});
            } 
            
            function refreshCollections(){
                 $.getJSON(
							'collections_dashboard_piechart_action',
							{
								range : collections_range
							},
							function(jsonResponse) {
								
								document.getElementById("Collections_range_tag").innerHTML = getRangeLabel(collections_range);
								options_collections.data.datasets[0].data=jsonResponse.collections;
								options_collections.data.labels=jsonResponse.labels;
								colmyChart.update();

							});
            }
            displayCollections();
        
			

			//piechart
			var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
			var ctx = document.getElementById("pieChart");
			 var gradient = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient.addColorStop(0, '#807ec7');   
		        gradient.addColorStop(1, '#03383c');
		     var gradient2 = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient2.addColorStop(0, '#8064A1');   
		        gradient2.addColorStop(1, '#2a1541');
		    var gradient3 = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient3.addColorStop(0, '#badb73');   
		        gradient3.addColorStop(1, '#2c3b0b');
		    var gradient4 = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient4.addColorStop(0, '#9f6a80');   
		        gradient4.addColorStop(1, '#3c0a1f');
		    var gradient5 = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient5.addColorStop(0, '#b97070');   
		        gradient5.addColorStop(1, '#380a0a');
		     var gradient6 = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient6.addColorStop(0, '#6da164');   
		        gradient6.addColorStop(1, '#1c5512');
		     var gradient7 = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient7.addColorStop(0, '#649fa1');   
		        gradient7.addColorStop(1, '#125543');
		     var gradient8 = pieChartCanvas.createLinearGradient(0, 0, 0, 300);
		        gradient8.addColorStop(0, '#a18764');   
		        gradient8.addColorStop(1, '#554812');
			options_orders={
				type : 'pie',
				data: {
					labels : [ "<s:property value="getText('quoted_status')"/>", "<s:property value="getText('new_status')"/>", 
								"<s:property value="getText('open_status')"/>", "<s:property value="getText('inprogress_status')"/>",
								"<s:property value="getText('completed_status')"/>", "<s:property value="getText('delivered_status')"/>",
								"<s:property value="getText('cancelled_status')"/>", "<s:property value="getText('returned_status')"/>"
							],
					datasets : [ {
						data : [ 0, 0, 0, 0, 0, 0, 0, 0 ],
						backgroundColor : [ gradient, gradient2, gradient3,
								gradient4, gradient5, gradient6, gradient7,
								gradient8 ],
						hoverBackgroundColor : ['#807ec7','#8064A1','#badb73','#9f6a80','#b97070','#6da164','#649fa1','#a18764'],
						borderWidth :  [1,1,1,1,1,1,1,1]
					} ]
				},
				options : {
					legend : {
						display : false,
						labels : {
							fontColor : 'rgb(255, 99, 132)'
						}
					}
				}
			};

			var myPieChart = new Chart(ctx, options_orders);
            function displayOrders(){
                $.getJSON(
							'orders_dashboard_piechart_action',
							{
								empId : "<s:property value="#session['userId']"/>",
								range : order_range
							},
							function(jsonResponse) {
// 								$("#order_data_loader").removeClass('loader');
								document.getElementById("Order_range_tag").innerHTML = getRangeLabel(order_range);
								document.getElementById("sale_order_count").innerHTML = jsonResponse.sale_order_count;
								document.getElementById("sale_order_label").innerHTML = "<s:property value="getText('dashboard.sale.orders')"/>";
								document.getElementById("return_order_count").innerHTML = jsonResponse.returned_order_count;
								document.getElementById("return_order_label").innerHTML = "<s:property value="getText('returned.orders')"/>";
								options_orders.data.datasets[0].data = [
										jsonResponse.orders_count[0].pendingOrders,
										jsonResponse.orders_count[0].newOrders,
										jsonResponse.orders_count[0].openOrders,
										jsonResponse.orders_count[0].inProgress,
										jsonResponse.orders_count[0].completed,
										jsonResponse.orders_count[0].deliveredOrders,
										jsonResponse.orders_count[0].cancelledOrders,
										jsonResponse.orders_count[0].returnedOrders ];
								myPieChart.update();
							});
            }
            function refreshOrders(){
                $.getJSON(
							'orders_dashboard_piechart_action',
							{
								empId : "<s:property value="#session['userId']"/>",
								range : order_range
							},
							function(jsonResponse) {
// 								$("#order_data_loader").removeClass('loader');
								document.getElementById("Order_range_tag").innerHTML =getRangeLabel(order_range);
								options_orders.data.datasets[0].data = [
										jsonResponse.orders_count[0].pendingOrders,
										jsonResponse.orders_count[0].newOrders,
										jsonResponse.orders_count[0].openOrders,
										jsonResponse.orders_count[0].inProgress,
										jsonResponse.orders_count[0].completed,
										jsonResponse.orders_count[0].deliveredOrders,
										jsonResponse.orders_count[0].cancelledOrders,
										jsonResponse.orders_count[0].returnedOrders ];
								myPieChart.update();
							});
            
            }
            displayOrders();
            
			

			//Click events on chart data for detailed info
			pieChartCanvas.canvas.onclick = function(evt) {
				var pt = myPieChart.getElementsAtEvent(evt);
				if (pt[0]._model.label) {
					var stat = pt[0]._model.label;
					document.forms["hidden_order_data"].empId.value = "<s:property value="#session['userId']"/>";
					document.forms["hidden_order_data"].range.value = order_range;
					document.forms["hidden_order_data"].status.value = stat;
					document.forms["hidden_order_data"].submit();
					//window.location.href="DashBoard_Sales_Agent_Pending_Orders.html";
				}
			}
            
            
            
			recbarChartCanvas.canvas.onclick = function(evt) {
				var pt = recmyChart.getElementsAtEvent(evt);
				if (pt[0]._model.label) {
					var stat = pt[0]._model.label;
					document.forms["hidden_receivables_data"].range.value = receivables_range;
					document.forms["hidden_receivables_data"].status.value = stat;
					document.forms["hidden_receivables_data"].submit();
				}
			}
			clabarChartCanvas.canvas.onclick = function(evt) {
				var pt = clamyChart.getElementsAtEvent(evt);
				if (pt[0]._model.label) {
					document.forms["hidden_claims_data"].status.value = pt[0]._model.label;
					document.forms["hidden_claims_data"].range.value = claim_range;
					document.forms["hidden_claims_data"].submit();
				}

			}
			barChartCanvas.canvas.onclick = function(evt) {
				var pt = colmyChart.getElementsAtEvent(evt);
				if (pt[0]._model.label) {
					document.forms["hidden_collections_data"].range.value = collections_range;
					document.forms["hidden_collections_data"].x_axis_field.value = pt[0]._model.label;
					document.forms["hidden_collections_data"].ischart.value=1;
					document.forms["hidden_collections_data"].submit();
					
				}
			}
			setInterval(function () {
				refreshReceivables();
				refreshOrders();
                refreshCollections();
                refreshClaims();
			}, refresh_interval);
			
			//click events on range options for each chart
			$("#range_collections li")
					.click(
							function(evt) {
								
								$("#Collections_range_tag").addClass('loading-pulse');
								if (evt.target.id == "week") {
									$
											.getJSON(
													'collections_dashboard_piechart_action',
													{
														range : "week"
													},
													function(jsonResponse) {
                                                        collections_range="week";
														

														document
																.getElementById("Collections_range_tag").innerHTML = "<s:property value="getText('current.week')" />";
														options_collections.data.datasets[0].data = jsonResponse.collections;
														options_collections.data.labels = jsonResponse.labels;
														colmyChart.update();

													});

								} else if (evt.target.id == "month") {
									$
											.getJSON(
													'collections_dashboard_piechart_action',
													{
														range : "month"
													},
													function(jsonResponse) {

														collections_range="month";
														document
																.getElementById("Collections_range_tag").innerHTML ="<s:property value="getText('current.month')" />";
														options_collections.data.datasets[0].data = jsonResponse.collections;
														options_collections.data.labels = jsonResponse.labels;
														colmyChart.update();

													});

								} else if (evt.target.id == "quarter") {
									$
											.getJSON(
													'collections_dashboard_piechart_action',
													{
														range : "quarter"
													},
													function(jsonResponse) {
                                                        
														collections_range="quarter";
														document
																.getElementById("Collections_range_tag").innerHTML = "<s:property value="getText('current.quarter')" />";
														options_collections.data.datasets[0].data = jsonResponse.collections;
														options_collections.data.labels = jsonResponse.labels;
														colmyChart.update();

													});

								} else if (evt.target.id == "year") {
									$
											.getJSON(
													'collections_dashboard_piechart_action',
													{
														range : "year"
													},
													function(jsonResponse) {

														  
                                                        collections_range="year";
														document
																.getElementById("Collections_range_tag").innerHTML = "<s:property value="getText('current.year')" />";
														options_collections.data.datasets[0].data = jsonResponse.collections;
														options_collections.data.labels = jsonResponse.labels;
														colmyChart.update();

													});

								} else if (evt.target.id == "NONE") {
									$
									.getJSON(
											'collections_dashboard_piechart_action',
											{
												range : "NONE"
											},
											function(jsonResponse) {

												collections_range="NONE";
												document
														.getElementById("Collections_range_tag").innerHTML ="<s:property value="getText('none')" />";
												options_collections.data.datasets[0].data = jsonResponse.collections;
												options_collections.data.labels = jsonResponse.labels;
												colmyChart.update();

											});

						}

							});

			$("#range_claims li")
					.click(
							function(evt) {

								if (evt.target.id == "week") {
									//data to be pulled
									
									$
											.getJSON(
													'claims_dashboard_barchart_action',
													{
														range : "week"
													},
													function(jsonResponse) {
                                                        claim_range = "week";
														document
																.getElementById("Claims_range_tag").innerHTML = "<s:property value="getText('current.week')" />";
														options_claims.data.datasets[0].data = jsonResponse.count;
														options_claims.data.labels = jsonResponse.dept;
														clamyChart.update();
													});
								} else if (evt.target.id == "month") {
									//data to be pulled
									
									$
											.getJSON(
													'claims_dashboard_barchart_action',
													{
														range : "month"
													},
													function(jsonResponse) {
                                                        claim_range = "month";
														document
																.getElementById("Claims_range_tag").innerHTML = "<s:property value="getText('current.month')" />";
														options_claims.data.datasets[0].data = jsonResponse.count;
														options_claims.data.labels = jsonResponse.dept;
														clamyChart.update();

													});
								} else if (evt.target.id == "quarter") {
									//data to be pulled
									
									$
											.getJSON(
													'claims_dashboard_barchart_action',
													{
														range : "quarter"
													},
													function(jsonResponse) {
                                                        claim_range = "quarter";
														document
																.getElementById("Claims_range_tag").innerHTML = "<s:property value="getText('current.quarter')" />";
														options_claims.data.datasets[0].data = jsonResponse.count;
														options_claims.data.labels = jsonResponse.dept;
														clamyChart.update();
													});
								} else if (evt.target.id == "year") {

									//data to be pulled
									
									$
											.getJSON(
													'claims_dashboard_barchart_action',
													{
														range : "year"
													},
													function(jsonResponse) {
                                                        claim_range = "year"
														document
																.getElementById("Claims_range_tag").innerHTML = "<s:property value="getText('current.year')" />";
														options_claims.data.datasets[0].data = jsonResponse.count;
														options_claims.data.labels = jsonResponse.dept;
														clamyChart.update();
													});
								}else if (evt.target.id == "NONE") {

									//data to be pulled
									
									$
											.getJSON(
													'claims_dashboard_barchart_action',
													{
														range : "NONE"
													},
													function(jsonResponse) {
                                                        claim_range = "NONE"
														document
																.getElementById("Claims_range_tag").innerHTML = "<s:property value="getText('none')" />";
														options_claims.data.datasets[0].data = jsonResponse.count;
														options_claims.data.labels = jsonResponse.dept;
														clamyChart.update();
													});
								}
								

							});

			$("#range_receivables li")
					.click(
							function(evt) {

								if (evt.target.id == "week") {
									//data to be pulled
									//options_receivables.data.datasets[0].data=[0,0];
									//document.getElementById("Receivables_range_tag").innerHTML="Current Week";
									
									$
											.getJSON(
													'receivables_dashboard_barchart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "week"
													},
													function(jsonResponse) {
                                                        receivables_range = "week";
														document
																.getElementById("Receivables_range_tag").innerHTML ="<s:property value="getText('current.week')" />";
														options_receivables.data.datasets[0].data = [
																jsonResponse.amounts[0],
																jsonResponse.amounts[1] ];
														recmyChart.update();
													});
								} else if (evt.target.id == "month") {
									//data to be pulled
									
									$
											.getJSON(
													'receivables_dashboard_barchart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "month"
													},
													function(jsonResponse) {
                                                        receivables_range = "month";
														document
																.getElementById("Receivables_range_tag").innerHTML = "<s:property value="getText('current.month')" />";
														options_receivables.data.datasets[0].data = [
																jsonResponse.amounts[0],
																jsonResponse.amounts[1] ];
														recmyChart.update();
													});
								}

								else if (evt.target.id == "year") {
									
									//data to be pulled
									$
											.getJSON(
													'receivables_dashboard_barchart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "year"
													},
													function(jsonResponse) {
                                                        receivables_range = "year";
														document
																.getElementById("Receivables_range_tag").innerHTML = "<s:property value="getText('current.year')" />";
														options_receivables.data.datasets[0].data = [
																jsonResponse.amounts[0],
																jsonResponse.amounts[1] ];
														recmyChart.update();
													});
								} else if (evt.target.id == "NONE"){
									
									//data to be pulled
									$
											.getJSON(
													'receivables_dashboard_barchart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "NONE"
													},
													function(jsonResponse) {
                                                        receivables_range = "NONE";
														document
																.getElementById("Receivables_range_tag").innerHTML = "<s:property value="getText('none')" />";
														options_receivables.data.datasets[0].data = [
																jsonResponse.amounts[0],
																jsonResponse.amounts[1] ];
														recmyChart.update();
														console
																.log(options_receivables.data)
													});
								}else {
									
									//data to be pulled
									$
											.getJSON(
													'receivables_dashboard_barchart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "quarter"
													},
													function(jsonResponse) {
                                                        receivables_range = "quarter";
														document
																.getElementById("Receivables_range_tag").innerHTML = "<s:property value="getText('current.quarter')" />";
														options_receivables.data.datasets[0].data = [
																jsonResponse.amounts[0],
																jsonResponse.amounts[1] ];
														recmyChart.update();
														console
																.log(options_receivables.data)
													});
								}

								//update chart
								//recmyChart.update();
							});

			$("#range_orders li")
					.click(
							function(evt) {

								if (evt.target.id == "week") {
									//data to be pulled
									
									$
											.getJSON(
													'orders_dashboard_piechart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "week"
													},
													function(jsonResponse) {
                                                        order_range = "week";
														//document.getElementById("Order_range_tag").innerHTML=jsonResponse.orders_count[0].newOrders;
														options_orders.data.datasets[0].data = [
																jsonResponse.orders_count[0].pendingOrders,
																jsonResponse.orders_count[0].newOrders,
																jsonResponse.orders_count[0].openOrders,
																jsonResponse.orders_count[0].inProgress,
																jsonResponse.orders_count[0].completed,
																jsonResponse.orders_count[0].deliveredOrders,
																jsonResponse.orders_count[0].cancelledOrders,
																jsonResponse.orders_count[0].returnedOrders ];
														document
																.getElementById("Order_range_tag").innerHTML = "<s:property value="getText('current.week')" />";
														myPieChart.update();

													});
									//options_orders.data.datasets[0].data=[7,6,3,4,5,4];
									//document.getElementById("Order_range_tag").innerHTML="Current Week";

								} else if (evt.target.id == "month") {
									//data to be pulled
									
									$
											.getJSON(
													'orders_dashboard_piechart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "month"
													},
													function(jsonResponse) {
                                                        order_range = "month";
														document
																.getElementById("Order_range_tag").innerHTML = "<s:property value="getText('current.month')" />";
														options_orders.data.datasets[0].data = [
																jsonResponse.orders_count[0].pendingOrders,
																jsonResponse.orders_count[0].newOrders,
																jsonResponse.orders_count[0].openOrders,
																jsonResponse.orders_count[0].inProgress,
																jsonResponse.orders_count[0].completed,
																jsonResponse.orders_count[0].deliveredOrders,
																jsonResponse.orders_count[0].cancelledOrders,
																jsonResponse.orders_count[0].returnedOrders ];
														myPieChart.update();

													});
								} else if (evt.target.id == "quarter") {
									//data to be pulled
									
									$
											.getJSON(
													'orders_dashboard_piechart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "quarter"
													},
													function(jsonResponse) {
                                                        order_range = "quarter";
														document
																.getElementById("Order_range_tag").innerHTML = "<s:property value="getText('current.quarter')" />";
														options_orders.data.datasets[0].data = [
																jsonResponse.orders_count[0].pendingOrders,
																jsonResponse.orders_count[0].newOrders,
																jsonResponse.orders_count[0].openOrders,
																jsonResponse.orders_count[0].inProgress,
																jsonResponse.orders_count[0].completed,
																jsonResponse.orders_count[0].deliveredOrders,
																jsonResponse.orders_count[0].cancelledOrders,
																jsonResponse.orders_count[0].returnedOrders ];
														myPieChart.update();
													});
								} else if (evt.target.id == "year") {

									//data to be pulled
									
									$
											.getJSON(
													'orders_dashboard_piechart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "year"
													},
													function(jsonResponse) {
                                                        order_range = "year";
														document
																.getElementById("Order_range_tag").innerHTML = "<s:property value="getText('current.year')" />";
														options_orders.data.datasets[0].data = [
																jsonResponse.orders_count[0].pendingOrders,
																jsonResponse.orders_count[0].newOrders,
																jsonResponse.orders_count[0].openOrders,
																jsonResponse.orders_count[0].inProgress,
																jsonResponse.orders_count[0].completed,
																jsonResponse.orders_count[0].deliveredOrders,
																jsonResponse.orders_count[0].cancelledOrders,
																jsonResponse.orders_count[0].returnedOrders ];
														myPieChart.update();
													});
								}else if (evt.target.id == "NONE") {

									//data to be pulled
									
									$
											.getJSON(
													'orders_dashboard_piechart_action',
													{
														empId : "<s:property value="#session['userId']"/>",
														range : "NONE"
													},
													function(jsonResponse) {
                                                        order_range = "NONE";
														document
																.getElementById("Order_range_tag").innerHTML = "<s:property value="getText('none')" />";
														options_orders.data.datasets[0].data = [
																jsonResponse.orders_count[0].pendingOrders,
																jsonResponse.orders_count[0].newOrders,
																jsonResponse.orders_count[0].openOrders,
																jsonResponse.orders_count[0].inProgress,
																jsonResponse.orders_count[0].completed,
																jsonResponse.orders_count[0].deliveredOrders,
																jsonResponse.orders_count[0].cancelledOrders,
																jsonResponse.orders_count[0].returnedOrders ];
														myPieChart.update();
													});
								}

								//update chart
								myPieChart.update();
							});

		});
        
        

	</script>
	<script type="text/javascript">
		$('#dashboard').addClass('active');
	</script>
</body>
</html>


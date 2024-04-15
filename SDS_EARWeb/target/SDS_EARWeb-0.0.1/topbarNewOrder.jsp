<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<header class="main-header">

	<a href="homePage" class="logo"> <span class="logo-lg"><img
			src="assets/SDSlogo.png"></span> <span class="logo-mini"><b></b>SDS</span>

	</a>

	<nav class="navbar navbar-static-top" role="navigation">

		<a href="javascript:void(0)" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
			<!-- commented by sharanya -->
				<%-- <li class="dropdown notifications-menu"><a aria-expanded="true"
					href="#" class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-bell-o"></i> <span class="label label-warning">1</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">You have 1 notifications</li>
						<li>
							<!-- inner menu: contains the actual data -->
							<div
								style="position: relative; overflow: hidden; width: auto; height: 200px;"
								class="slimScrollDiv">
								<ul style="overflow: hidden; width: 100%; height: 200px;"
									class="menu">
									<li><a href="#"> <i class="fa fa-truck text-aqua"></i>
											10 new orders today
									</a></li>
								</ul>
								<div
									style="background: none repeat scroll 0% 0% rgb(0, 0, 0); width: 3px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 200px;"
									class="slimScrollBar"></div>
								<div
									style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: none repeat scroll 0% 0% rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"
									class="slimScrollRail"></div>
							</div>
						</li>
						<li class="footer"><a href="#">View all</a></li>
					</ul></li> --%>



				<!-- User Account Menu -->
				<li class="dropdown user user-menu text-center">
					<a href="javascript:void(0)" data-toggle="control-sidebar" style="padding: 5px 10px;">
						<s:property value="employee.employeeName" /><br>
						<small><s:property value="employee.roleName" /></small>
					</a>
				</li>
				<!-- Control Sidebar Toggle Button -->
				<li class="dropdown user user-menu text-center">
					<a href="javascript:void(0)" data-toggle="control-sidebar" style="padding: 5px 10px;">
						<i class="fa fa-user "></i><br>
						<i class="fa fa-angle-down fa-lg"></i>
					</a>
				</li>
			</ul>
		</div>

		<div class="navbar-custom-menu pull-left hidden-xs">
			<ul class="nav navbar-nav text-center">
				<li class="dropdown user user-menu">
					<a href="javascript:void(0)" style="cursor:default; padding: 5px 15px;">
						Order ID:<br><small><b><s:property value="orderID" /></b></small>
					</a>
				</li>
				<li class="dropdown user user-menu">
					<a href="javascript:void(0)" style="cursor:default; padding: 5px 15px;">
						Customer Name:<br><small><b><s:property value="customer.customerHeader.ctNm" /></b></small>
					</a>
				</li>
				<li class="dropdown user user-menu">
					<a href="javascript:void(0)" style="cursor:default; padding: 5px 15px;">
						Customer ID:<br><small><b><s:property value="customer.customerHeader.customerHeaderPK.custId" /></b></small>
					</a>
				</li>
				<li class="dropdown user user-menu">
					<a href="javascript:void(0)" style="cursor:default; padding: 5px 15px;">
						Segment:<br><small><b><s:property value="customer.getCustomerSegmentID()" /></b></small>
					</a>
				</li>
				<li class="dropdown user user-menu">
					<a href="javascript:void(0)" style="cursor:default; padding: 5px 15px;">
						Store:<br><small><b><s:property value="orderID.substring(0,5)" /></b></small>
					</a>
				</li>
			</ul>
		</div>
	</nav>
</header>



<aside class="control-sidebar control-sidebar-dark"
	style="position: fixed; height: auto;">

	<div class="tab-content">
		<!-- Home tab content -->
		<div class="tab-pane active" id="control-sidebar-home-tab">
			<h3 style="text-align: center;" class="control-sidebar-heading">
				<s:property value="employee.employeeName" />
			</h3>
					<small>
			<div class="row">
				<div class="col-xs-6 text-right">Emp ID:</div>
				<div class="col-xs-6" style="color:white; padding-left: 0px;""><s:property value="employee.employeeId" /></div>
			</div>
			<div class="row">
				<div class="col-xs-6 text-right">Emp Role:</div>
				<div class="col-xs-6" style="color:white; padding-left: 0px;""><s:property value="employee.roleName" /></div>
			</div>
			<br>
			<s:if test="%{employee.merchAssoc!=null && employee.merchAssoc.size()>0}">
				<s:iterator value="employee.merchAssoc" status="stat">
					<s:set var="merchLevel" value="%{merchId.charAt(0)}"></s:set>
					<s:if test="%{#merchLevel=='0' && merchName!=null}">
						<div class="row">
							<div class="col-xs-6 text-right">Company:</div>
							<div class="col-xs-6" style="color:white; padding-left: 0px;"><s:property value="merchName" /></div>
						</div>
					</s:if>
					<s:elseif test="%{#merchLevel=='1' && merchName!=null}">
						<div class="row">
							<div class="col-xs-6 text-right">Division:</div>
							<div class="col-xs-6" style="color:white; padding-left: 0px;"><s:property value="merchName" /></div>
						</div>
					</s:elseif>
					<%-- <s:elseif test="%{#merchLevel=='2' && merchName!=null}">
						<div class="row">
							<div class="col-xs-6 text-right">Group:</div>
							<div class="col-xs-6" style="color:white; padding-left: 0px;"><s:property value="merchName" /></div>
						</div>
					</s:elseif>
					<s:elseif test="%{#merchLevel=='3' && merchName!=null}">
						<div class="row">
							<div class="col-xs-6 text-right">Department:</div>
							<div class="col-xs-6" style="color:white; padding-left: 0px;"><s:property value="merchName" /></div>
						</div>
					</s:elseif>
					<s:elseif test="%{#merchLevel=='4' && merchName!=null}">
						<div class="row">
							<div class="col-xs-6 text-right">Class:</div>
							<div class="col-xs-6" style="color:white; padding-left: 0px;"><s:property value="merchName" /></div>
						</div>
					</s:elseif>
					<s:elseif test="%{#merchLevel=='5' && merchName!=null}">
						<div class="row">
							<div class="col-xs-6 text-right">Subclass:</div>
							<div class="col-xs-6" style="color:white; padding-left: 0px;"><s:property value="merchName" /></div>
						</div>
					</s:elseif> --%>
				</s:iterator>

			</s:if>
			</small>
			<br>
			<div class="progress" style="height: 3px;">
				<div class="progress-bar" style="width: 100%;"></div>
			</div>

			<ul class="control-sidebar-menu">
				<li><a href="javascript:void(0)"> <i
						class="menu-icon fa fa-key bg-blue"></i>

						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Change Password</h4>


						</div>
				</a></li>
				<!-- commented by sharanya -->
			<!-- 	<li><a href="javascript:void(0)"> <i
						class="menu-icon fa fa-language bg-blue"></i>

						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Change Language</h4>


						</div>
				</a></li> -->
				<div class="row" style="margin: 5px; height: 3px;">
					<div class="progress" style="height: 3px;">
						<div class="progress-bar" style="width: 100%;"></div>
					</div>
				</div>
				<li><a href="logout" style="color: #ffffff;"><button
							class="btn btn-block btn-primary" style="margin: 0 auto;"
							type="button">Logout</button></a></li>

			</ul>
		</div>
	</div>
</aside>
<!-- /.control-sidebar -->
<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
<div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>

<script type="text/javascript">
	$(function(){
		//Disable cut copy paste
	    /* $("body").bind("cut copy paste", function (e) {
	    	console.log(e.type+" is disabled");
	        e.preventDefault();
	    }); 
	   
	    //Disable mouse right click
	    $("body").on("contextmenu", function(e){
	    	console.log("Right Click is disabled");
	        return false;
	    });
	    
	    //Disable double click
	    $("body").on("dblclick", function(e){
	    	console.log("Double Click is disabled");
    		e.preventDefault();
  		}); */
  		
  		//Saideep:
  		//Global Ajax Error for handling session timeout, use global:false in your ajax calls to skip this global function
  		$(document).bind("ajaxError", function(event, jqXHR, ajaxSettings, thrownError){
			if(jqXHR.status!=null && jqXHR.status==401)
			{
				preventBack = false;
				alert('Session has timed out. Please re-login.');
	        	//location.reload(true);
	        	window.location.href="<s:url action="logout"/>";
			}
		});
	});
</script>


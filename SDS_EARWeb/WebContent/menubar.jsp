<%@page import="rispl.ds.DSAction"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%!
	// local vars
	String noAccess="javascript:void(0)";
	String lacksPermission="disabled hidden";
	String adminRoleId="1";
	
	// --------------------- FORMAT --------------------------------------
	// String action_tag_mapping[] = 				{"action_name", "html_tag_id"}
	// -------------------------------------------------------------------
	// Dashboard or main
	String dashboardAction[] =		 	{"dashboard","dashboard"};
	// Orders Related
	String orderSearchAction[] =		{"order_Search","ordersearch"};
	String newOrderAction[] = 			{"newOrder","bookorder"};//{"customerSearch","bookorder"};
	String openOrdersAction[] = 		{"openorders","openorders"};
	String deliveredOrdersAction[] =	{"deliveredOrders","deliveredorders"};
	String cancelledOrdersAction[] = 	{"cancelledOrders","cancelledorders"};
	String returnedOrdersAction[] = 	{"returnedOrders","returnedorders"};
	String pendingOrdersAction[] = 		{"savedOrder","pendingorders"};
	// Invoice Related
	String invoiceSearchAction[] = 		{"invoices","invoicesearch"};
	String openInvoicesAction[] = 		{"OpenInvoices","openinvoices"};
	String postPaymentAction[] = 		{"noAccess","postPaymentSearch"};
	// Claim Related
	String claimSearchAction[] = 		{"claim_search","claimsearch"};
	String newClaimAction[] = 			{"generateClaim","registerclaim"};
	String approveClaimAction[] = 		{"approveClaim","approveclaim"};
	String acceptClaimAction[] = 		{"claimAccept","acceptclaim"};
	String rejectedClaimsAction[] = 	{"rejectClaim","rejectedclaims"};
	// Customer Related
	String customerSearchAction[] = 	{"customerSearch","customersearch"};
	// Inventory Related
	String inventorySearchAction[] = 	{"Inventory_Lookup","inventorylookupitem"};
	// Admin Related
	String rolesAction[] = 				{"rolesConfig","roles"};
	String parametersAction[] = 		{"paramterConfig","parameters"};
	String customerSegmentsAction[] = 	{"customerConfig","customersegments"};
	String resetPasswordAction[] = 		{"resetPasswordPage","resetpassword"};
	
%>
<style>
	.padding-left-4px{
		padding-left:4px;
	}
	.nav>li.disabled>a{
		color: #777;
	}
</style>
<aside class="main-sidebar">
	<section class="sidebar">
		<ul class="sidebar-menu nav">
			<li class="header" style="color:#01ffdd;">
				<%-- <h4><b><s:property value="getText('menu.label')"/></b></h4> --%>
				<h4><b><s:property value="getText('country.name')"/>
				<!-- store name has to display in the book order screen @sharanya start -->
				<span style="white-space: normal; font-size:18px"><b>${param.Store}</b></span>
				<!-- sharanya end -->
				 </b></h4>
			</li>
			<!-- DASHBOARD PERMISSION START -->
			<shiro:hasPermission name="<%=dashboardAction[0]%>">
				<li class="treeview" id="<%=dashboardAction[1]%>">
					<a href="<%=dashboardAction[0]%>"> 
			</shiro:hasPermission>
			<shiro:lacksPermission name="<%=dashboardAction[0]%>">
				<li class="treeview <%=lacksPermission%>" id="<%=dashboardAction[1]%>">
					<a href="<%=noAccess%>"> 
			</shiro:lacksPermission>
				<i class="fa fa-line-chart"></i> 
					<span><s:text name="dashboard.label"/></span> 
				</a>
			</li>
			<!-- END -->
			
			<li class="treeview" id="order">
				<a href="javascript:void(0)">
					<i class="fa fa-truck"></i>
					<span><s:text name="order.label"/></span> 
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- ORDER SEARCH PERMISSION START -->
					<shiro:hasPermission name="<%=orderSearchAction[0]%>">
						<li id="<%=orderSearchAction[1]%>">
							<a href="<%=orderSearchAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=orderSearchAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=orderSearchAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="order.search"/>
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- NEW ORDER PERMISSION START -->
					<shiro:hasPermission name="<%=newOrderAction[0]%>">
						<li id="<%=newOrderAction[1]%>">
							<a href="<%=newOrderAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=newOrderAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=newOrderAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="neworder.button"/>
							</span>
							<s:if test="%{#session['OrderTransaction']!=null}">
								<span class="badge pull-right" style="margin-top: -9px" 
									 title="1 Order is in progress...">
									<span class="glyphicon glyphicon-hourglass"></span>
								</span>	
							</s:if>
						</a>
					</li>
					<!-- END -->
					
					<!-- OPEN ORDERS PERMISSION START -->
					<shiro:hasPermission name="<%=openOrdersAction[0]%>">
						<li id="<%=openOrdersAction[1]%>">
							<a href="<%=openOrdersAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=openOrdersAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=openOrdersAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="open.orders"/>
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- DELIVERED ORDERS PERMISSION START -->
					<shiro:hasPermission name="<%=deliveredOrdersAction[0]%>">
						<li id="<%=deliveredOrdersAction[1]%>">
							<a href="<%=deliveredOrdersAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=deliveredOrdersAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=deliveredOrdersAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="delivered.orders"/>
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- CANCELLED ORDERS PERMISSION START -->
					<shiro:hasPermission name="<%=cancelledOrdersAction[0]%>">
						<li id="<%=cancelledOrdersAction[1]%>">
							<a href="<%=cancelledOrdersAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=cancelledOrdersAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=cancelledOrdersAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="cancel.order"/>
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- RETURNED ORDERS PERMISSION START -->
					<shiro:hasPermission name="<%=returnedOrdersAction[0]%>">
						<li id="<%=returnedOrdersAction[1]%>">
							<a href="<%=returnedOrdersAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=returnedOrdersAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=returnedOrdersAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="returned.orders"/>
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- PENDING ORDERS PERMISSION START -->
					<shiro:hasPermission name="<%=pendingOrdersAction[0]%>">
						<li id="<%=pendingOrdersAction[1]%>">
							<a href="<%=pendingOrdersAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=pendingOrdersAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=pendingOrdersAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="saved.orders"/>
							</span>
						</a>
					</li>
					<!-- END -->
				</ul>
			</li>	
			<li class="treeview" id="invoice">
				<a href="javascript:void(0)">
					<i class="fa fa-file-text"></i>
					<span><s:text name="invoice.label"/></span>
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- INVOICE SEARCH PERMISSION START -->
					<shiro:hasPermission name="<%=invoiceSearchAction[0]%>">
						<li id="<%=invoiceSearchAction[1]%>">
							<a href="<%=invoiceSearchAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=invoiceSearchAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=invoiceSearchAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="invoice.search"/>
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- OPEN INVOICES PERMISSION START -->
					<shiro:hasPermission name="<%=openInvoicesAction[0]%>">
						<li id="<%=openInvoicesAction[1]%>">
							<a href="<%=openInvoicesAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=openInvoicesAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=openInvoicesAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="open_invoices"/>
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- POST PAYMENT PERMISSION START -->
					<shiro:hasPermission name="<%=postPaymentAction[0]%>">
						<li id="<%=postPaymentAction[1]%>">
							<a href="<%=postPaymentAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=postPaymentAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=postPaymentAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="post.payment.button"/>
							</span>
						</a>
					</li>
					<!-- END -->
				</ul>		
			</li>
			<li class="treeview" id="claim">
				<a href="javascript:void(0)">
					<i class="fa fa-dashboard"></i>
					<span><s:text name="claim.label"/></span>
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- CLAIM SEARCH PERMISSION START -->
					<shiro:hasPermission name="<%=claimSearchAction[0]%>">
						<li id="<%=claimSearchAction[1]%>">
							<a href="<%=claimSearchAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=claimSearchAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=claimSearchAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="claim.search"/>
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- REGISTER CLAIM PERMISSION START -->
					<shiro:hasPermission name="<%=newClaimAction[0]%>">
						<li id="<%=newClaimAction[1]%>">
							<a href="<%=newClaimAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=newClaimAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=newClaimAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="new.claim"/>
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- APPROVE CLAIM PERMISSION START -->
					<shiro:hasPermission name="<%=approveClaimAction[0]%>">
						<li id="<%=approveClaimAction[1]%>">
							<a href="<%=approveClaimAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=approveClaimAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=approveClaimAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="menu.approve.claim"/>
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- ACCEPT CLAIM PERMISSION START -->
					<shiro:hasPermission name="<%=acceptClaimAction[0]%>">
						<li id="<%=acceptClaimAction[1]%>">
							<a href="<%=acceptClaimAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=acceptClaimAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=acceptClaimAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="menu.accept.claim"/>
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- REJECT CLAIM PERMISSION START -->
					<shiro:hasPermission name="<%=rejectedClaimsAction[0]%>">
						<li id="<%=rejectedClaimsAction[1]%>">
							<a href="<%=rejectedClaimsAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=rejectedClaimsAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=rejectedClaimsAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="rejected.claim"/>
							</span>
						</a>
					</li>
					<!-- END -->
				</ul>
			</li>		
			<li class="treeview" id="customer">
				<a href="javascript:void(0)">
					<i class="fa fa-user"></i>
					<span><s:text name="customer.label"/></span>
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- CUSTOMER SEARCH PERMISSION START -->
					<shiro:hasPermission name="<%=customerSearchAction[0]%>">
						<li id="<%=customerSearchAction[1]%>">
							<a href="<%=customerSearchAction[0]%>">
					</shiro:hasPermission>
					<shiro:lacksPermission name="<%=customerSearchAction[0]%>">
						<li class="<%=lacksPermission%>" id="<%=customerSearchAction[1]%>">
							<a href="<%=noAccess%>">
					</shiro:lacksPermission>
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								<s:text name="menu.customersearch"/>
							</span>
						</a>
					</li>
					<!-- END -->		
				</ul>
			</li>
			<!-- INVENTORY PERMISSION START -->
			<shiro:hasPermission name="<%=inventorySearchAction[0]%>">
				<li class="treeview" id="<%=inventorySearchAction[1]%>">
					<a href="<%=inventorySearchAction[0]%>"> 
			</shiro:hasPermission>
			<shiro:lacksPermission name="<%=inventorySearchAction[0]%>">
				<li class="treeview <%=lacksPermission%>" id="<%=inventorySearchAction[1]%>">
					<a href="<%=noAccess%>"> 
			</shiro:lacksPermission>
				<i class="fa fa-cubes"></i> 
					<span><s:text name="inventory.lookup"/></span> 
				</a>
			</li>
			<!-- END -->	
			
			<shiro:hasAnyRoles name="<%=adminRoleId%>">
				<!-- Chiranjibee Comments To Add Admin Panel To Menubar page -->
				<li class="treeview" id="admin">
					<a href="javascript:void(0)">
						<i class="glyphicon glyphicon-cog"></i>
						<span><s:text name="admin.config"/></span>
						<i class="fa fa-angle-down pull-right"></i>
					</a>
					<ul class="treeview-menu nav">
						<!-- ROLES PERMISSION START -->
						<shiro:hasPermission name="<%=rolesAction[0]%>">
							<li id="<%=rolesAction[1]%>">
								<a href="<%=rolesAction[0]%>">
						</shiro:hasPermission>
						<shiro:lacksPermission name="<%=rolesAction[0]%>">
							<li class="<%=lacksPermission%>" id="<%=rolesAction[1]%>">
								<a href="<%=noAccess%>">
						</shiro:lacksPermission>
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									<s:text name="roles.config"/>
								</span>
							</a>
						</li>
						<!-- END -->
						
						<!-- PARAMETER PERMISSION START -->
						<shiro:hasPermission name="<%=parametersAction[0]%>">
							<li id="<%=parametersAction[1]%>">
								<a href="<%=parametersAction[0]%>">
						</shiro:hasPermission>
						<shiro:lacksPermission name="<%=parametersAction[0]%>">
							<li class="<%=lacksPermission%>" id="<%=parametersAction[1]%>">
								<a href="<%=noAccess%>">
						</shiro:lacksPermission>
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									<s:text name="parameter.config"/>
								</span>
							</a>
						</li>
						<!-- END -->
						
						<!-- CUSTOMER SEGMENTS PERMISSION START -->
						<shiro:hasPermission name="<%=customerSegmentsAction[0]%>">
							<li id="<%=customerSegmentsAction[1]%>">
								<a href="<%=customerSegmentsAction[0]%>">
						</shiro:hasPermission>
						<shiro:lacksPermission name="<%=customerSegmentsAction[0]%>">
							<li class="<%=lacksPermission%>" id="<%=customerSegmentsAction[1]%>">
								<a href="<%=noAccess%>">
						</shiro:lacksPermission>
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									<s:text name="customer.config"/>
								</span>
							</a>
						</li>
						<!-- END -->
						<!-- Chiranjibee Comments To Add Admin Panel To Menubar page -->
						
						<!-- RESET PASSWORD PERMISSION START -->
						<shiro:hasPermission name="<%=resetPasswordAction[0]%>">
							<li id="<%=resetPasswordAction[1]%>">
								<a href="<%=resetPasswordAction[0]%>">
						</shiro:hasPermission>
						<shiro:lacksPermission name="<%=resetPasswordAction[0]%>">
							<li class="<%=lacksPermission%>" id="<%=resetPasswordAction[1]%>">
								<a href="<%=noAccess%>">
						</shiro:lacksPermission>
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									<s:text name="menu.resetpassword"/>
								</span>
							</a>
						</li>
						<!-- END -->
					</ul>
				</li>
				
			</shiro:hasAnyRoles>
				
		</ul>
		
		
			
		<%-- <ul class="sidebar-menu nav">
			<li class="header">
				<h4><b>MENU</b></h4>
			</li>	
			<li class="treeview" id="dashboard"><a href="main"> <i
					class="fa fa-line-chart"></i> <span>DashBoard</span>
			</a></li>
			<li class="treeview" id="order"><a href="javascript:void(0)">
					<i class="fa fa-truck"></i> <span>Order</span> <i
					class="fa fa-angle-down pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li id="ordersearch"><a href="order_Search"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Order Search</a></li>
					<li id="bookorder"><a href="newOrder"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;New Order</a></li>
					<li id="openorders"><a href="javascript:void(0)"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Open Orders</a></li>
					<li id="deliveredorders"><a href="deliveredOrders"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Delivered Orders</a></li>
					<li id="cancelledorders"><a href="cancelledOrders"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Cancelled Orders</a></li>
					<li id="returnedorders"><a href="returnedOrders"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Returned Orders</a></li>
					<li id="pendingorders"><a href="pendingOrder"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Pending Orders</a></li>
				</ul></li>
			<li class="treeview" id="invoice"><a href="javascript:void(0)">
					<i class="fa fa-file-text"></i> <span>Invoice</span> <i
					class="fa fa-angle-down pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li id="invoicesearch"><a href="invoices"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Invoice Search</a></li>
					<li id="openinvoices"><a href="OpenInvoices"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Open Invoices</a></li>
					<li id="postPaymentSearch"><a href="postPaymentSearch"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Post Payment</a></li>
				</ul></li>
			<li class="treeview" id="claim"><a href="javascript:void(0)">
					<i class="fa fa-dashboard"></i> <span>Claim</span> <i
					class="fa fa-angle-down pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li id="claimsearch"><a href="claim_search"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Claim Search</a></li>
					<li id="registerclaim"><a href="generateClaim"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;New Claim</a></li>
					<li id="approveclaim"><a href="approveClaim"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Approve Claim</a></li>
					<li id="acceptclaim"><a href="claimAccept"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Accept Claim</a></li>
					<li id="rejectedclaims"><a href="rejectClaim"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Rejected Claims</a></li>
				</ul></li>
			<li class="treeview" id="customer"><a href="javascript:void(0)">
					<i class="fa fa-user"></i> <span>Customer</span> <i
					class="fa fa-angle-down pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li id="customersearch"><a href="customerSearch"><i
							class="fa fa-angle-right"></i>&nbsp;&nbsp;Customer Search</a></li>
				</ul></li>

			<li class="treeview" id="inventorylookupitem"><a
				href="Inventory_Lookup"> <i class="fa fa-cubes"></i> <span>Inventory</span>
			</a></li>
		</ul> --%>
	</section>
	<div style="position: absolute; bottom: 2%; left: 8%; color: gray; word-break: break-word;">
		<small><s:property value="getText('build_no')"/>:&nbsp; <s:property value="getText('build.no')" /></small>
	</div>
</aside>


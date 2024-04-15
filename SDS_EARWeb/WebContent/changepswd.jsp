<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Change Password</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" >
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
	<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>
<%-- <script src="assets/plugins/jQueryUI/jquery-ui.js"></script>
<script src="assets/plugins/select2/select2.full.min.js"></script> --%>

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
		<s:include value="menubar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">
       
			<!-- Main content -->
			<section class="content">
			
				<div class="row">
					<div class="col-md-8">
						<label style="font-size:25px; color: #226e71;">Change Password</label>
					</div>
					<div class="col-md-8 nav-tabs-custom" style="margin:0px 15px;">
						
						<form style="padding:10px 0px;" class="form-horizontal" id="newpswd" action="NewPswd" onsubmit="return validatePassword()" method="post">
							
							<div class="form-group" style="margin-bottom: 1px">
			      				<label class="col-xs-5 control-label" for="employeeId" style="padding-top: 1px; font-weight: normal;">
			      					Employee ID: </label>
			      				<div class="col-xs-5">
			      					<p><b><s:property value="employee.employeeId" id="employeeId" /></b></p>
			      				</div>
			    			</div>
			    			<div class="form-group" style="margin-bottom: 1px">
			      				<label class="col-xs-5 control-label" for="employeeName" style="padding-top: 1px; font-weight: normal;">
			      					Employee Name:</label>
			      				<div class="col-xs-5">
			      					<p><b><s:property value="employee.employeeName" id="employeeName" /></b></p>
			      				</div>
			    			</div>
							<div class="form-group" style="margin-bottom: 1px">
			      				<label class="col-xs-5 control-label" for="Password" style="padding-top: 1px; font-weight: normal;">
			      					Current Password:</label>
			      				<div class="col-xs-5">
			      					<input type="password" name="CurrentPassword" class="form-control" id="Password" required="required">
			      				</div>
			    			</div>
			   				<div class="form-group" style="margin-bottom: 1px">
			      				<label class="col-xs-5 control-label" for="NewPassword" style="padding-top: 1px; font-weight: normal;">
			      					New Password:</label>
			     	 			<div class="col-xs-5">
			      					<input type="password" name="NewPassword" class="form-control" id="NewPassword" required="required">
								</div>
							</div>
			      			<div class="form-group">
			     		 		<label class="col-xs-5 control-label" for="ReEnterNewPassword" style="padding-top: 1px; font-weight: normal;">
			     		 			Re-enter New Password:</label>
				      			<div class="col-xs-5">
				      				<input type="password" name="ReEnterNewPassword" class="form-control"  id="ReEnterNewPassword" required="required">
				      			</div>
				      		</div>
							<div class="form-group"> 
								<div class="col-xs-12">
									<a class="btn btn-primary" href="homePage">Cancel</a>
									<button type="submit" class="btn btn-primary pull-right">Submit</button>
									
								</div>
	                     	</div>
	    					<div class="col-xs-12">
								<s:actionerror id="actionerror" theme="bootstrap" cssStyle="background-image:none;" 
									cssClass="alert-dismissible" onclick="dismiss" />
								<s:actionmessage id="actionmessage" theme="bootstrap" cssStyle="background-image:none;" 
									cssClass="alert-dismissible" onclick="dismiss" />
	                     	</div>
						</form>
					</div>
				</div>										
  			</section>
		</div>
	</div>


	<div class="modal fade bs-example-modal-sm" id="mismatchPassword"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>Password does not match the confirm password.</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="submit" id="submit1"
						class="btn btn-primary center-block" data-dismiss="modal">
						Ok &nbsp;</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade bs-example-modal-sm" id="alertcharlength"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>Your password must be between 8-15 characters.</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="submit" id="submit1"
						class="btn btn-primary center-block" data-dismiss="modal">
						Ok &nbsp;</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade bs-example-modal-sm" id="alert2"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>Password should contain atleast one special character and one Number</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="submit" id="submit2"
						class="btn btn-primary center-block" data-dismiss="modal">
						Ok &nbsp;</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade bs-example-modal-sm" id="alertpasswordmatch"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>New Password should not be same as Old Password.</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="submit" id="submit2"
						class="btn btn-primary center-block" data-dismiss="modal">
						Ok &nbsp;</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade bs-example-modal-sm" id="success"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>
						Your password has been changed Successfully.<br>
						<!-- Use this password the next time you log in<br>
							Press Ok to continue. -->
					</h4>
				</div>
				<div class="modal-footer text-center">

					<button type="button" class="btn btn-primary center-block"
						onclick="redirect()" id="redirectbtn">Ok &nbsp;</button>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade bs-example-modal-sm" id="error"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>Failed to update your password</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="button" class="btn btn-primary center-block"
						data-dismiss="modal">Ok &nbsp;</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade bs-example-modal-sm" id="validation"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>Please fill out the Fields.</h4>
				</div>
				<div class="modal-footer text-center">
					<button type="submit" id="submit1"
						class="btn btn-primary center-block" data-dismiss="modal">
						Ok &nbsp;</button>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript"> 
    var msg= "${message}";
    if(msg=='Updated'){
     $("#success").modal('show');
    }
    if(msg=='notupdated'){
   $("#error").modal('show'); 
    }
   function redirect(){
   location.href = "homePage";
   }
    function validatePassword() {
    var newPassword = document.getElementById('newpswd').NewPassword.value;
    var confirmPassword=document.getElementById('newpswd').ReEnterNewPassword.value;
    var oldPassword=document.getElementById('newpswd').Password.value;
    var minNumberofChars = 8;
    var maxNumberofChars = 15;
    var regularExpression= /^((?=.*\d)(?=.*[ !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]).{8,15})$/;
	
	 if(newPassword=="" || oldPassword=="" || confirmPassword==""){
    $("#validation").modal('show');
        return false;
    }
    if(oldPassword==newPassword)
    {
     $("#alertpasswordmatch").modal('show');
        return false;
    }
    if(newPassword.length < minNumberofChars || newPassword.length > maxNumberofChars){
    $("#alertcharlength").modal('show');
        return false;
    }
    if(newPassword!=confirmPassword){
    $("#mismatchPassword").modal('show');
        return false;
    }
	if(!regularExpression.test(newPassword)) {
     $("#alert2").modal('show');
        return false;
    } 
    else{ 
      
     return true;
    }
}
</script>
</body>
</html>
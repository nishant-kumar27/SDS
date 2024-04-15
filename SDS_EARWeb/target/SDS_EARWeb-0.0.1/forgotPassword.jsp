<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
	
<title>SDS | Forgot Password</title>

<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<!-- Ionicons -->
<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<%-- <script src="assets/plugins/jQuery/jQuery-2.2.0.min.js"></script> --%>
<script type="text/javascript" src="jquery/jquery-1.12.4.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>

<!--[if lt IE 9]>
    <script type="text/javascript" src="assets/ie8support/html5shiv.min.js"></script>
	<script type="text/javascript" src="assets/ie8support/html5shiv-printshiv.min.js"></script>
	<script type="text/javascript" src="assets/ie8support/respond.min.js"></script>
<![endif]-->

<style>
.login-page {
	background: #e6e6ea;
}

</style>
</head>
<body class="hold-transition login-page">
	<div class="login-box well well-sm">
		<div class="login-logo text-center">
			<img alt="logo" src="assets/SDSlogo.png" class="image"
				style="image-resolution: snap;"><br>
			<h2 style="margin-bottom:0px;">Salam Distribution System</h2>
			<h4 style="color:gray"><s:property value="getText('country.name')" /></h4>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<h3 class="login-box-msg text-center">Forgot Password</h3>
			<p class="login-box-msg text-center">Please enter the below details</p>

			<form action="forgotPassword" method="post">
				<div class="form-group has-feedback" style="margin-bottom: 15px;">
					<input type="text" name="loginid" id="loginid" class="form-control"
						placeholder="Login Id" autocomplete="off" style="height:34px;"
						required> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback" style="margin-bottom: 15px;">
					<input type="email" name="email" id="email"
						class="form-control" placeholder="Email Address" style="height:34px;"
						required> <span
						class="fa fa-at form-control-feedback"></span>
				</div>
				<div class="form-group" style="margin-bottom: 15px; ">
					<a type="button" id="back"
						class="btn btn-primary" href="<s:url value="/"/>">Back</a>
					<button type="submit" id="submit"
						class="btn btn-primary pull-right">Next</button>
				</div>
			</form>
			<div class="row">
				
			</div>
			<div class="row">
				<s:actionerror id="actionerror" theme="bootstrap" cssStyle="background-image:none;" 
						cssClass="alert-dismissible" onclick="dismiss" />
			</div>
			
		</div>
		<!-- /.login-box-body -->
	</div>
	<div class="text-center" style="color: gray;">
		<small>Build:&nbsp; <s:property value="getText('build.no')" /></small>
	</div>
	<!-- /.login-box -->
	<script type="text/javascript">
		$('#loginid').focus();
		
		/* $('#submit').click(function(e)
		{
			$('#actionerror').empty();
			var loginid = $('#loginid');
			var password = $('#password');
			if (loginid.val().length <= 0)
			{
				loginid.parent().addClass('has-error');
			} else
			{
				loginid.parent().removeClass('has-error');
			}
			if (password.val().length <= 0)
			{
				password.parent().addClass('has-error');
			} else
			{
				password.parent().removeClass('has-error');
			}
			if (loginid.val().length <= 0)
			{
				loginid.focus();
				e.preventDefault();
				return;
			}
			if (password.val().length <= 0)
			{
				password.focus();
				e.preventDefault();
				return;
			}
		}); */
	</script>


</body>
</html>

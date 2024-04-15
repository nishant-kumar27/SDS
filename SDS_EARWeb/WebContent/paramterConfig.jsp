<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Parameters</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<!-- <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/css/popup.css"> -->
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/css/Customizations.css"
	type="text/css" />
<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>
<%-- <script src="assets/plugins/select2/select2.full.min.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script
	src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script> --%>

<script type="text/javascript">
	function fetchSimpleParamterElemnts() {
		document.getElementById("paramGroupSelect").submit();
	}

	var specialKeys = new Array();
	specialKeys.push(8); //Backspace
	function IsNumeric(e) {
		var keyCode = e.which ? e.which : e.keyCode
		var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys
				.indexOf(keyCode) != -1);
		document.getElementById("error").style.display = ret ? "none"
				: "inline";
		if (ret) {
			document.getElementById("save").disabled = false;
		} else {
			document.getElementById("save").disabled = true;
		}
		return ret;
	}

	function checkRange(inputNumber) {
		var inputID = inputNumber.id;
		var input = document.getElementById(inputID).value;
		var inputRange = parseInt(input);
		if (inputRange >= 0 && inputRange <= 100) {
			var inputRange1 = inputRange.toString().replace (/0/g, "");
			if (inputRange1 ==="") 
			{
			inputRange1 = "0";
			document.getElementById(inputID).value = inputRange1;
			}
			document.getElementById("save").disabled = false;
			document.getElementById("error").style.display = "none";
			return true;
		} else {
			document.getElementById("save").disabled = true;
			document.getElementById(inputID).focus();
			document.getElementById("error").style.display = "inline";
			return false;
		}
	}
</script>
<script type="text/javascript">
	$(function(){
		$('#admin').addClass('active');
		$('#parameters').addClass('active');
	});
</script>
</head>

<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<section class="content">
				<div class="row">
					<div class="col-md-4">
						<h3 style="color: #226e71;"><s:text name="parameter.config"/></h3>
					</div>
				</div>
				<div class="row">
				<s:set var="parameter" value="parameterError" />
				<form id="paramGroupSelect" action="groupParamElement"
									method="POST">
					<div class="col-md-12">
						<div class="box box-primary">
						<s:if test="%{#parameter=='TRUE'}">
							<s:actionerror theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
						</s:if>
						<s:if test="%{#parameter=='FALSE'}">
							<s:actionmessage theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
						</s:if>
							<div class="box-body">
									<label><s:text name="parameter.config.select.lebel"></s:text></label>&nbsp;&nbsp;
									<s:select headerKey="0"
										headerValue="%{getText('parameter.config.select.parameterGroupLevel')}"
										list="parameterList" id="parameterGroup" name="parameterGroup"
										onchange="fetchSimpleParamterElemnts()"></s:select>
								
							</div>

						</div>

					</div>
					</form>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="box box-primary table-responsive">
							<div class="box-header"></div>
							<form id="updateXML" name="updateXML" action="updateXMLParameter"
								method="POST">
								<div classs="box-body no-padding">
									<table id="simpleElementTable" class="table table-striped">
										<thead>
											<tr style="background-color: #ADC2EE;">
												<th style="width: 10px"><s:text name="index"/></th>
												<th><s:text name="parameter.config.group"/></th>
												<th><s:text name="parameter.config.name"/></th>
												<th><s:text name="parameter.config.description"/></th>
												<th><s:text name="parameter.config.currentValue"/></th>
												<th><s:text name="parameter.config.newValue"/></th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="simpleElementList" var="list"
												status="stat">
												<tr>
													<td><s:property value="%{#stat.count}" />.</td>
													<td><s:property value="groupParameter" /></td>
													<s:hidden
														name="updateParameterlist[%{#stat.index}].parameterGrpElement"
														value="%{groupParameter}" theme="bootstrap"></s:hidden>
													<td><s:property value="simpleTypeElement" /></td>
													<s:hidden
														name="updateParameterlist[%{#stat.index}].simpleElement"
														value="%{simpleTypeElement}" theme="bootstrap"></s:hidden>
													<td><s:property value="simpleTypeElementDescription" /></td>
													<td><s:property value="simpleTypeElementCurrentValue" /></td>
													<s:if test="%{dataType.size>0}">
														<s:if test="%{dataType[0].equalsIgnoreCase('Text')}">
															<td><s:textarea name="updateParameterlist[%{#stat.index}].newValue"
																id="%{simpleTypeElement}" value="%{simpleTypeElementCurrentValue}"
																theme="bootstrap" onblur="checkEmail(this)"></s:textarea></td>
																
														</s:if>
														<s:else>
															<td><s:select list="dataType" var="newDropDownValue"
																	value="simpleTypeElementCurrentValue" id="paramterValue"
																	name="updateParameterlist[%{#stat.index}].newValue"
																	theme="simple" cssStyle="width:150px;"></s:select>
															</td>
														</s:else>
													</s:if>
													<s:else>
														<td><s:textfield
																name="updateParameterlist[%{#stat.index}].newValue"
																id="%{simpleTypeElement}"
																value="%{simpleTypeElementCurrentValue}" width="150"
																size="22" theme="simple" onkeypress="return IsNumeric(event)"
																ondrop="return false;" onpaste="return false;"
																onkeyup="return checkRange(%{simpleTypeElement})"
																onblur="return checkRange(%{simpleTypeElement})">
															</s:textfield></td>

													</s:else>
												</tr>
											</s:iterator>
										</tbody>
									</table>

								</div>
								<span id="error"
									style="color: Red; display: none; float: right;" ><s:text name="parameter.config.numberValidation"/></span>
								<s:submit type="button" class="btn btn-default pull-right"
									id="save" theme="bootstrap"><s:text name="parameter.config.save"/>
								</s:submit>
								<br>
								
								<div class="box-footer clearfix"></div>
							</form>
						</div>
					</div>

				</div>


			</section>
			<!-- /.content -->
		</div>

	</div>
<script type="text/javascript">
	function checkEmail(ref){
		ref.value = validateEmail(ref.value); // accept only valid email patters
		ref.value = ref.value.replace(/,/g,', '); // add space after comma
		if(ref.value.length<=0) ref.value="null"
	}
	
	function validateEmail(email) {
		var re = /([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)/ig;
		return email.match(re);
	}
	
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SDS</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
  
  <!-- Font Awesome -->
        <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />

  <!-- Theme style -->
  <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="assets/css/Customizations.css">
    <!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
  <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
  
  <style type="text/css">
  .mbg-blue {
    background-color: #00c0ef !important;
     }
  </style>
  <style type="text/css">
.errorMessage{
	list-style: none;
}
.alert {
  margin-bottom: 1px;
  height: 30px;
  line-height:30px;
  padding:0px 15px;
}
</style>
<script type="text/javascript">
$(function(){
	  $('#ediOrder').change(function(){
		var name="";
	    var url = $(this).val();
	    /*alert(url);*/
	    if(url != "")
	    {
		    var fileExtension = "";
		    var allowedfileTypes="<s:property value="getText('edi.order.fileTypesAllowed')"/>";
		    if (url.lastIndexOf(".") > 0) {
		        fileExtension = url.substring(url.lastIndexOf(".") + 1, url.length);
		    }
		   /* alert(fileExtension);*/
		    if (!(allowedfileTypes.indexOf(fileExtension.toLowerCase()) > -1)) {
		    	/*  alert("You must select a GIF file for upload"); */
		         $('#ediOrderModel').modal('show');
				 $("#ediOrder").val('');
		        /*alert('invalid type');*/
		        return false;
		    }
	    }
	  });

	});
</script>
<script type="text/javascript">
$(document).ready(function() {
    $('#uploadEdiFile').bind("click",function() 
    { 
        var imgVal = $('#ediOrder').val(); 
        if(imgVal=='') 
        { 
             $('#ediNoFile').modal('show');
            return false; 
        } 
    }); 
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

  <!-- Main Header -->
 
 <s:include value="topbar.jsp" />
  <s:include value="menubar.jsp" />

  <!-- Content Wrapper. Contains page content -->
  <div style="min-height: 595px;" class="content-wrapper">
   
    

    <!-- Main content -->
    <section class="content">
    
      <!-- Your Page Content Here -->
    <section class="invoice">
      <!-- title row -->
        <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('edi.help_text')"/>  </b></small>
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('edi.header.details')"/>
          </h2>
        </div>
        <!-- /.col -->
      </div>
       <hr/>
       <div class="row">
        <div class="col-md-6">
        	<s:if test="hasActionErrors()">
   			<div class="row">
      			<div
            class="alert alert-danger"  style="margin-left:14px; margin-bottom:0px;" role="alert" >
             <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>            
             <p><s:actionerror/></p>
			</div>
			<br/>
   			</div>
			</s:if>   	
			
        	<table class="table table-bordered" width="50%">
        	<form action="uploadEDIFile" method="post" enctype="multipart/form-data">
			<tr><td><s:property value="getText('customer.id')"/></td><td><b><s:property value="customerID"/></b></td></tr>
        	<tr><td><s:property value="getText('edi.selectFile')"/></td><td><input id="ediOrder" type="file" name="ediFile" accept=".csv"/></td></tr>
        	<tr><td colspan="3" align="right">
        	<input type="hidden" name = "customerID" value="<s:property value="customerID"/>"/>
        	<button type="submit" id="uploadEdiFile" class="btn"><s:property value="getText('edi.button.Upload')"/></button>
        	<button  onClick="history.go(-1);return true;" type="button" class="pull-right btn btn-secoundry" style="color:#ffffff; width:100px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
            <s:property value="getText('back.button')"/>
          	</button>
        	
        	</td></tr>
        	</form>
			</table>
			
        	
        </div>
        
        </div>
         <div class="row">
        <!-- /.col -->
        <!-- /.col -->
      </div>
      <!-- /.box-header -->
    </section>
     
    
    </section>
    <!-- /.content -->
    
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  

  
  <!-- /.control-sidebar -->
  
</div>

<!-- To show confirm cancel model -->
	
	<!-- End of model -->

<!-- ./wrapper -->

<!-- ChartJS 1.0.1 -->


	<script>
		function windowPrint() {
			window.print();
		}
	</script>
		<!-- Modal to show when the EDI file type is not matched -->
		<div class="modal fade bs-example-modal-sm" id="ediOrderModel"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="errorTitle" class="modal-title">
							<s:property value="getText('edi.prompt.invalid.file.type')" />
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
		<!-- Modal to show when no file is selected -->
		<div class="modal fade bs-example-modal-sm" id="ediNoFile"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="errorTitle" class="modal-title">
							<s:property value="getText('edi.order.select.file.mandatory')" />
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
</body></html>
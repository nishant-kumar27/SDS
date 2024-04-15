<html>
<head><title>FreeMarker Template Test</title>
<style>
#container {
   width: 500px;
   height: 37px;
}
#container1 {
   width: 250px;
   height: 37px;
}

#container img {
   width: 50%;
}

#info {
background-color :#35b0e9;
height: 60px;
line-height: 50px;
text-align: center;
}
#info1 {
background-color :#FFFFFF;
height: 60px;
line-height: 50px;
text-align: center;
}
</style>
<body bgcolor="#FFFFFF">
<div id="container">
<table>
<tr>
<h3>Thank you for your interest!</h3>
</tr>
<tr><p>
Here's a summary of your test email. </p>
</tr>
</table>
<div>
<table>
<tr>
<td align="left">Book Name:</td><td align="left"><strong>${bookName} </strong></td>
</tr>
<tr>
<td align="left">Book Details:</td><td align="left"><strong>${bookDetail} </strong></td>
</tr>
<tr>
<td align="left">Writers:</td><td align="left">
<ul>
<#list writers as writer>
	<li>${writer}</li>
</#list>
</ul>

</td>
</tr>
</table>
</div>
</div>
</body>
</html>
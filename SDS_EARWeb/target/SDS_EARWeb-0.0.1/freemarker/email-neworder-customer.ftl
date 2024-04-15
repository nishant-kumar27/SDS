<html>
   <head>
      <title>New Order Email For Customer</title>
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
         table {
    		border-collapse: collapse;
		}
		.table td, .table th {
		    border: 1px solid lightgray;
		}
      </style>
   <body bgcolor="#FFFFFF">
      <div id="containe">
         <table>
            <tr>
               <h3>Thank you for your order!</h3>
            </tr>
            <tr>
               <p>
                  Here's a summary of your purchase. 
               </p>
            </tr>
         </table>
         <div>
            <table>
               <tr>
                  <td align="left">Order Id :</td>
                  <td align="left"><strong>${orderId} </strong></td>
               </tr>
               <tr>
                  <td align="left">Order Date :</td>
                  <td align="left"><strong>${orderDate} </strong></td>
               </tr>
               <tr>
                  <td align="left">Order Delivery Date :</td>
                  <td align="left"><strong>${deliveryDate} </strong></td>
               </tr>
                <tr>
                  <td align="left">Customer Id:</td>
                  <td align="left"><strong>${customerId} </strong></td>
               </tr>
               <tr>
                  <td align="left">Customer Name:</td>
                  <td align="left"><strong>${customerName} </strong></td>
               </tr>
               <tr>
                  <td align="left">Lpo No :</td>
                  <td align="left"><strong>${LpoNo} </strong></td>
               </tr>
               <tr>
                  <td align="left">Lpo Date :</td>
                  <td align="left"><strong>${LpoDate} </strong></td>
               </tr>
               <tr>
                  <td align="left">Order Total:</td>
                  <td align="left">QAR <strong>${netTotal} </strong></td>
               </tr>
            </table>
            <div>
               <h4>
               Items Details :
               <h4>
            </div>
            <table class="table">
               <thead>
                  <tr bgcolor="#ADC2EE">
                     <th>#</th>
                     <th>Item Id</th>
                     <th>Item Desc</th>
                     <th>Order Qty</th>
                     <th>Unit Price</th>
                     <th>Discount</th>
                     <th>Item Total</th>
                  </tr>
               </thead>
               <tbody>
                  <#list OrderTranLineItemsList as OrderTranLineItem>
                  <tr>
                     <td>${OrderTranLineItem?counter}.</td>
                     <td>${OrderTranLineItem.getItemId()}</td>
                     <td>${OrderTranLineItem.getDeItmShrtRcpt()}</td>
                     <td align="right">${OrderTranLineItem.getLineQnt()}</td>
                     <td align="right">
                        <#if OrderTranLineItem.getPriceOverRideFlag()?? >
                        <#if OrderTranLineItem.getPriceOverRideFlag() == "1" >
                        ${OrderTranLineItem.getOverRidePrice()}
                        </#if>
                        <#else>
                        ${OrderTranLineItem.getItmPrnPrc()}
                        </#if>
                     </td>
                     <#-- 
                     <td align="right">${OrderTranLineItem.getItmPrnPrc()}</td>
                     -->
                     <td align="right">${OrderTranLineItem.getExtnLnItmRtn() - OrderTranLineItem.getExtnDscLnItm()}</td>
                     <td align="right">${OrderTranLineItem.getExtnDscLnItm()}</td>
                  </tr>
                  </#list>
               </tbody>
            </table>
         </div>
      </div>
   </body>
</html>


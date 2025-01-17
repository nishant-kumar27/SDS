
package com.rsi.dk.wms.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "CustomerOrderException", targetNamespace = "http://customerDetails.rsi.dk.wms")
public class CustomerOrderException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private wms.dk.rsi.customerdetails.CustomerOrderException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public CustomerOrderException(String message, wms.dk.rsi.customerdetails.CustomerOrderException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public CustomerOrderException(String message, wms.dk.rsi.customerdetails.CustomerOrderException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: wms.dk.rsi.customerdetails.CustomerOrderException
     */
    public wms.dk.rsi.customerdetails.CustomerOrderException getFaultInfo() {
        return faultInfo;
    }

}

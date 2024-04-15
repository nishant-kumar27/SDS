//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.07 at 08:14:08 PM IST 
//


package com.rispl.sds.parameter.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Holds all information regarding SDS Register Claim
 * 				Parameter.
 * 			
 * 
 * <p>Java class for RegisterClaim_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegisterClaim_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnableClaimWithOutInvoice" type="{}Boolean_Type"/>
 *         &lt;element name="EnableRegisterClaimManagerOveride" type="{}Boolean_Type"/>
 *         &lt;element name="EnableSendingMailToCustomer" type="{}Boolean_Type"/>
 *         &lt;element name="EnableSendingMailToDeptHead" type="{}Boolean_Type"/>
 *         &lt;element name="EnableSendingMailToSalesAgent" type="{}Boolean_Type"/>
 *         &lt;element name="EnableSendingMailToDataEntryOperator" type="{}Boolean_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterClaim_Type", propOrder = {
    "enableClaimWithOutInvoice",
    "enableRegisterClaimManagerOveride",
    "enableSendingMailToCustomer",
    "enableSendingMailToDeptHead",
    "enableSendingMailToSalesAgent",
    "enableSendingMailToDataEntryOperator"
})
public class RegisterClaimType {

    @XmlElement(name = "EnableClaimWithOutInvoice", required = true, defaultValue = "No")
    protected BooleanType enableClaimWithOutInvoice;
    @XmlElement(name = "EnableRegisterClaimManagerOveride", required = true, defaultValue = "Yes")
    protected BooleanType enableRegisterClaimManagerOveride;
    @XmlElement(name = "EnableSendingMailToCustomer", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToCustomer;
    @XmlElement(name = "EnableSendingMailToDeptHead", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToDeptHead;
    @XmlElement(name = "EnableSendingMailToSalesAgent", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToSalesAgent;
    @XmlElement(name = "EnableSendingMailToDataEntryOperator", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToDataEntryOperator;

    /**
     * Gets the value of the enableClaimWithOutInvoice property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableClaimWithOutInvoice() {
        return enableClaimWithOutInvoice;
    }

    /**
     * Sets the value of the enableClaimWithOutInvoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableClaimWithOutInvoice(BooleanType value) {
        this.enableClaimWithOutInvoice = value;
    }

    /**
     * Gets the value of the enableRegisterClaimManagerOveride property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableRegisterClaimManagerOveride() {
        return enableRegisterClaimManagerOveride;
    }

    /**
     * Sets the value of the enableRegisterClaimManagerOveride property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableRegisterClaimManagerOveride(BooleanType value) {
        this.enableRegisterClaimManagerOveride = value;
    }

    /**
     * Gets the value of the enableSendingMailToCustomer property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableSendingMailToCustomer() {
        return enableSendingMailToCustomer;
    }

    /**
     * Sets the value of the enableSendingMailToCustomer property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableSendingMailToCustomer(BooleanType value) {
        this.enableSendingMailToCustomer = value;
    }

    /**
     * Gets the value of the enableSendingMailToDeptHead property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableSendingMailToDeptHead() {
        return enableSendingMailToDeptHead;
    }

    /**
     * Sets the value of the enableSendingMailToDeptHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableSendingMailToDeptHead(BooleanType value) {
        this.enableSendingMailToDeptHead = value;
    }

    /**
     * Gets the value of the enableSendingMailToSalesAgent property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableSendingMailToSalesAgent() {
        return enableSendingMailToSalesAgent;
    }

    /**
     * Sets the value of the enableSendingMailToSalesAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableSendingMailToSalesAgent(BooleanType value) {
        this.enableSendingMailToSalesAgent = value;
    }

    /**
     * Gets the value of the enableSendingMailToDataEntryOperator property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableSendingMailToDataEntryOperator() {
        return enableSendingMailToDataEntryOperator;
    }

    /**
     * Sets the value of the enableSendingMailToDataEntryOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableSendingMailToDataEntryOperator(BooleanType value) {
        this.enableSendingMailToDataEntryOperator = value;
    }

}

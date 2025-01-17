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
 * 				Holds all information regarding SDS BOOK Order
 * 				Parameter.
 * 			
 * 
 * <p>Java class for BookOrder_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BookOrder_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CheckForAvailableInventory" type="{}Boolean_Type"/>
 *         &lt;element name="EnableBookOrderManagerOverride" type="{}Boolean_Type"/>
 *         &lt;element name="EnableRebateCustomerForExpiryItems" type="{}Boolean_Type"/>
 *         &lt;element name="DiscountPercentageLimit" type="{}IntgerLimit_Type"/>
 *         &lt;element name="EnableDoubleDiscounts" type="{}Boolean_Type"/>
 *         &lt;element name="EnableExceedingCustomerAvailableLimit" type="{}Boolean_Type"/>
 *         &lt;element name="OrderExpiryDays" type="{}IntgerLimit_Type"/>
 *         &lt;element name="ScheduledDeliveryOrderBeforeNoOfDays" type="{}IntgerLimit_Type"/>
 *         &lt;element name="EnableSendingMailToCustomer" type="{}Boolean_Type"/>
 *         &lt;element name="EnableSendingMailToDeptHead" type="{}Boolean_Type"/>
 *         &lt;element name="EnableSendingMailToSalesAgent" type="{}Boolean_Type"/>
 *         &lt;element name="EnableSendingMailToDataEntryOperator" type="{}Boolean_Type"/>
 *         &lt;element name="EnableGroupingDiscountedLineItems" type="{}Boolean_Type"/>
 *         &lt;element name="InceaseAvailLimitPercenatageForSegmentA" type="{}IntgerLimit_Type"/>
 *         &lt;element name="InceaseAvailLimitPercenatageForSegmentB" type="{}IntgerLimit_Type"/>
 *         &lt;element name="InceaseAvailLimitPercenatageForSegmentC" type="{}IntgerLimit_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookOrder_Type", propOrder = {
    "checkForAvailableInventory",
    "enableBookOrderManagerOverride",
    "enableRebateCustomerForExpiryItems",
    "discountPercentageLimit",
    "enableDoubleDiscounts",
    "enableExceedingCustomerAvailableLimit",
    "orderExpiryDays",
    "scheduledDeliveryOrderBeforeNoOfDays",
    "enableSendingMailToCustomer",
    "enableSendingMailToDeptHead",
    "enableSendingMailToSalesAgent",
    "enableSendingMailToDataEntryOperator",
    "enableGroupingDiscountedLineItems",
    "inceaseAvailLimitPercenatageForSegmentA",
    "inceaseAvailLimitPercenatageForSegmentB",
    "inceaseAvailLimitPercenatageForSegmentC"
})
public class BookOrderType {

    @XmlElement(name = "CheckForAvailableInventory", required = true, defaultValue = "Yes")
    protected BooleanType checkForAvailableInventory;
    @XmlElement(name = "EnableBookOrderManagerOverride", required = true, defaultValue = "Yes")
    protected BooleanType enableBookOrderManagerOverride;
    @XmlElement(name = "EnableRebateCustomerForExpiryItems", required = true, defaultValue = "No")
    protected BooleanType enableRebateCustomerForExpiryItems;
    @XmlElement(name = "DiscountPercentageLimit", defaultValue = "0")
    protected int discountPercentageLimit;
    @XmlElement(name = "EnableDoubleDiscounts", required = true, defaultValue = "Yes")
    protected BooleanType enableDoubleDiscounts;
    @XmlElement(name = "EnableExceedingCustomerAvailableLimit", required = true, defaultValue = "No")
    protected BooleanType enableExceedingCustomerAvailableLimit;
    @XmlElement(name = "OrderExpiryDays", defaultValue = "30")
    protected int orderExpiryDays;
    @XmlElement(name = "ScheduledDeliveryOrderBeforeNoOfDays", defaultValue = "7")
    protected int scheduledDeliveryOrderBeforeNoOfDays;
    @XmlElement(name = "EnableSendingMailToCustomer", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToCustomer;
    @XmlElement(name = "EnableSendingMailToDeptHead", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToDeptHead;
    @XmlElement(name = "EnableSendingMailToSalesAgent", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToSalesAgent;
    @XmlElement(name = "EnableSendingMailToDataEntryOperator", required = true, defaultValue = "Yes")
    protected BooleanType enableSendingMailToDataEntryOperator;
    @XmlElement(name = "EnableGroupingDiscountedLineItems", required = true, defaultValue = "Yes")
    protected BooleanType enableGroupingDiscountedLineItems;
    @XmlElement(name = "InceaseAvailLimitPercenatageForSegmentA", defaultValue = "0")
    protected int inceaseAvailLimitPercenatageForSegmentA;
    @XmlElement(name = "InceaseAvailLimitPercenatageForSegmentB", defaultValue = "0")
    protected int inceaseAvailLimitPercenatageForSegmentB;
    @XmlElement(name = "InceaseAvailLimitPercenatageForSegmentC", defaultValue = "0")
    protected int inceaseAvailLimitPercenatageForSegmentC;

    /**
     * Gets the value of the checkForAvailableInventory property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getCheckForAvailableInventory() {
        return checkForAvailableInventory;
    }

    /**
     * Sets the value of the checkForAvailableInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setCheckForAvailableInventory(BooleanType value) {
        this.checkForAvailableInventory = value;
    }

    /**
     * Gets the value of the enableBookOrderManagerOverride property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableBookOrderManagerOverride() {
        return enableBookOrderManagerOverride;
    }

    /**
     * Sets the value of the enableBookOrderManagerOverride property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableBookOrderManagerOverride(BooleanType value) {
        this.enableBookOrderManagerOverride = value;
    }

    /**
     * Gets the value of the enableRebateCustomerForExpiryItems property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableRebateCustomerForExpiryItems() {
        return enableRebateCustomerForExpiryItems;
    }

    /**
     * Sets the value of the enableRebateCustomerForExpiryItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableRebateCustomerForExpiryItems(BooleanType value) {
        this.enableRebateCustomerForExpiryItems = value;
    }

    /**
     * Gets the value of the discountPercentageLimit property.
     * 
     */
    public int getDiscountPercentageLimit() {
        return discountPercentageLimit;
    }

    /**
     * Sets the value of the discountPercentageLimit property.
     * 
     */
    public void setDiscountPercentageLimit(int value) {
        this.discountPercentageLimit = value;
    }

    /**
     * Gets the value of the enableDoubleDiscounts property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableDoubleDiscounts() {
        return enableDoubleDiscounts;
    }

    /**
     * Sets the value of the enableDoubleDiscounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableDoubleDiscounts(BooleanType value) {
        this.enableDoubleDiscounts = value;
    }

    /**
     * Gets the value of the enableExceedingCustomerAvailableLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableExceedingCustomerAvailableLimit() {
        return enableExceedingCustomerAvailableLimit;
    }

    /**
     * Sets the value of the enableExceedingCustomerAvailableLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableExceedingCustomerAvailableLimit(BooleanType value) {
        this.enableExceedingCustomerAvailableLimit = value;
    }

    /**
     * Gets the value of the orderExpiryDays property.
     * 
     */
    public int getOrderExpiryDays() {
        return orderExpiryDays;
    }

    /**
     * Sets the value of the orderExpiryDays property.
     * 
     */
    public void setOrderExpiryDays(int value) {
        this.orderExpiryDays = value;
    }

    /**
     * Gets the value of the scheduledDeliveryOrderBeforeNoOfDays property.
     * 
     */
    public int getScheduledDeliveryOrderBeforeNoOfDays() {
        return scheduledDeliveryOrderBeforeNoOfDays;
    }

    /**
     * Sets the value of the scheduledDeliveryOrderBeforeNoOfDays property.
     * 
     */
    public void setScheduledDeliveryOrderBeforeNoOfDays(int value) {
        this.scheduledDeliveryOrderBeforeNoOfDays = value;
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

    /**
     * Gets the value of the enableGroupingDiscountedLineItems property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanType }
     *     
     */
    public BooleanType getEnableGroupingDiscountedLineItems() {
        return enableGroupingDiscountedLineItems;
    }

    /**
     * Sets the value of the enableGroupingDiscountedLineItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanType }
     *     
     */
    public void setEnableGroupingDiscountedLineItems(BooleanType value) {
        this.enableGroupingDiscountedLineItems = value;
    }

    /**
     * Gets the value of the inceaseAvailLimitPercenatageForSegmentA property.
     * 
     */
    public int getInceaseAvailLimitPercenatageForSegmentA() {
        return inceaseAvailLimitPercenatageForSegmentA;
    }

    /**
     * Sets the value of the inceaseAvailLimitPercenatageForSegmentA property.
     * 
     */
    public void setInceaseAvailLimitPercenatageForSegmentA(int value) {
        this.inceaseAvailLimitPercenatageForSegmentA = value;
    }

    /**
     * Gets the value of the inceaseAvailLimitPercenatageForSegmentB property.
     * 
     */
    public int getInceaseAvailLimitPercenatageForSegmentB() {
        return inceaseAvailLimitPercenatageForSegmentB;
    }

    /**
     * Sets the value of the inceaseAvailLimitPercenatageForSegmentB property.
     * 
     */
    public void setInceaseAvailLimitPercenatageForSegmentB(int value) {
        this.inceaseAvailLimitPercenatageForSegmentB = value;
    }

    /**
     * Gets the value of the inceaseAvailLimitPercenatageForSegmentC property.
     * 
     */
    public int getInceaseAvailLimitPercenatageForSegmentC() {
        return inceaseAvailLimitPercenatageForSegmentC;
    }

    /**
     * Sets the value of the inceaseAvailLimitPercenatageForSegmentC property.
     * 
     */
    public void setInceaseAvailLimitPercenatageForSegmentC(int value) {
        this.inceaseAvailLimitPercenatageForSegmentC = value;
    }

}

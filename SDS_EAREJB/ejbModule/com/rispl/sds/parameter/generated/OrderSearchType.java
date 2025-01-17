//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.07 at 08:14:08 PM IST 
//


package com.rispl.sds.parameter.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Holds all information regarding SDS Order Search
 * 				Parameter.
 * 			
 * 
 * <p>Java class for OrderSearch_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderSearch_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="OpenOrderSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="DeliveredOrderSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="CancelledOrderSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="ReturnedOrderSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="SaveOrderSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="DataEntryRoleID" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="SalesAgentRoleID" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="DivisionHeadRoleID" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderSearch_Type", propOrder = {
    "orderSearchRange",
    "openOrderSearchRange",
    "deliveredOrderSearchRange",
    "cancelledOrderSearchRange",
    "returnedOrderSearchRange",
    "saveOrderSearchRange",
    "dataEntryRoleID",
    "salesAgentRoleID",
    "divisionHeadRoleID"
})
public class OrderSearchType {

    @XmlElement(name = "OrderSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType orderSearchRange;
    @XmlElement(name = "OpenOrderSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType openOrderSearchRange;
    @XmlElement(name = "DeliveredOrderSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType deliveredOrderSearchRange;
    @XmlElement(name = "CancelledOrderSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType cancelledOrderSearchRange;
    @XmlElement(name = "ReturnedOrderSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType returnedOrderSearchRange;
    @XmlElement(name = "SaveOrderSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType saveOrderSearchRange;
    @XmlElement(name = "DataEntryRoleID", required = true, defaultValue = "6")
    protected BigInteger dataEntryRoleID;
    @XmlElement(name = "SalesAgentRoleID", required = true, defaultValue = "5")
    protected BigInteger salesAgentRoleID;
    @XmlElement(name = "DivisionHeadRoleID", required = true, defaultValue = "7")
    protected BigInteger divisionHeadRoleID;

    /**
     * Gets the value of the orderSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getOrderSearchRange() {
        return orderSearchRange;
    }

    /**
     * Sets the value of the orderSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setOrderSearchRange(DashboardRangeType value) {
        this.orderSearchRange = value;
    }

    /**
     * Gets the value of the openOrderSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getOpenOrderSearchRange() {
        return openOrderSearchRange;
    }

    /**
     * Sets the value of the openOrderSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setOpenOrderSearchRange(DashboardRangeType value) {
        this.openOrderSearchRange = value;
    }

    /**
     * Gets the value of the deliveredOrderSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getDeliveredOrderSearchRange() {
        return deliveredOrderSearchRange;
    }

    /**
     * Sets the value of the deliveredOrderSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setDeliveredOrderSearchRange(DashboardRangeType value) {
        this.deliveredOrderSearchRange = value;
    }

    /**
     * Gets the value of the cancelledOrderSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getCancelledOrderSearchRange() {
        return cancelledOrderSearchRange;
    }

    /**
     * Sets the value of the cancelledOrderSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setCancelledOrderSearchRange(DashboardRangeType value) {
        this.cancelledOrderSearchRange = value;
    }

    /**
     * Gets the value of the returnedOrderSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getReturnedOrderSearchRange() {
        return returnedOrderSearchRange;
    }

    /**
     * Sets the value of the returnedOrderSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setReturnedOrderSearchRange(DashboardRangeType value) {
        this.returnedOrderSearchRange = value;
    }

    /**
     * Gets the value of the saveOrderSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getSaveOrderSearchRange() {
        return saveOrderSearchRange;
    }

    /**
     * Sets the value of the saveOrderSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setSaveOrderSearchRange(DashboardRangeType value) {
        this.saveOrderSearchRange = value;
    }

    /**
     * Gets the value of the dataEntryRoleID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDataEntryRoleID() {
        return dataEntryRoleID;
    }

    /**
     * Sets the value of the dataEntryRoleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDataEntryRoleID(BigInteger value) {
        this.dataEntryRoleID = value;
    }

    /**
     * Gets the value of the salesAgentRoleID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSalesAgentRoleID() {
        return salesAgentRoleID;
    }

    /**
     * Sets the value of the salesAgentRoleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSalesAgentRoleID(BigInteger value) {
        this.salesAgentRoleID = value;
    }

    /**
     * Gets the value of the divisionHeadRoleID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDivisionHeadRoleID() {
        return divisionHeadRoleID;
    }

    /**
     * Sets the value of the divisionHeadRoleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDivisionHeadRoleID(BigInteger value) {
        this.divisionHeadRoleID = value;
    }

}

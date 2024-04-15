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
 * 				Holds all information regarding SDS Claim Search
 * 				Parameter.
 * 			
 * 
 * <p>Java class for ClaimSearch_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClaimSearch_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClaimSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="ApproveClaimSearchRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="ClaimNeedToBeAcceptedRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="ClaimAutoAcceptedRange" type="{}DashboardRange_Type"/>
 *         &lt;element name="RejectedClaimRange" type="{}DashboardRange_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClaimSearch_Type", propOrder = {
    "claimSearchRange",
    "approveClaimSearchRange",
    "claimNeedToBeAcceptedRange",
    "claimAutoAcceptedRange",
    "rejectedClaimRange"
})
public class ClaimSearchType {

    @XmlElement(name = "ClaimSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType claimSearchRange;
    @XmlElement(name = "ApproveClaimSearchRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType approveClaimSearchRange;
    @XmlElement(name = "ClaimNeedToBeAcceptedRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType claimNeedToBeAcceptedRange;
    @XmlElement(name = "ClaimAutoAcceptedRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType claimAutoAcceptedRange;
    @XmlElement(name = "RejectedClaimRange", required = true, defaultValue = "CURRENT_QUARTER")
    protected DashboardRangeType rejectedClaimRange;

    /**
     * Gets the value of the claimSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getClaimSearchRange() {
        return claimSearchRange;
    }

    /**
     * Sets the value of the claimSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setClaimSearchRange(DashboardRangeType value) {
        this.claimSearchRange = value;
    }

    /**
     * Gets the value of the approveClaimSearchRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getApproveClaimSearchRange() {
        return approveClaimSearchRange;
    }

    /**
     * Sets the value of the approveClaimSearchRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setApproveClaimSearchRange(DashboardRangeType value) {
        this.approveClaimSearchRange = value;
    }

    /**
     * Gets the value of the claimNeedToBeAcceptedRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getClaimNeedToBeAcceptedRange() {
        return claimNeedToBeAcceptedRange;
    }

    /**
     * Sets the value of the claimNeedToBeAcceptedRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setClaimNeedToBeAcceptedRange(DashboardRangeType value) {
        this.claimNeedToBeAcceptedRange = value;
    }

    /**
     * Gets the value of the claimAutoAcceptedRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getClaimAutoAcceptedRange() {
        return claimAutoAcceptedRange;
    }

    /**
     * Sets the value of the claimAutoAcceptedRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setClaimAutoAcceptedRange(DashboardRangeType value) {
        this.claimAutoAcceptedRange = value;
    }

    /**
     * Gets the value of the rejectedClaimRange property.
     * 
     * @return
     *     possible object is
     *     {@link DashboardRangeType }
     *     
     */
    public DashboardRangeType getRejectedClaimRange() {
        return rejectedClaimRange;
    }

    /**
     * Sets the value of the rejectedClaimRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DashboardRangeType }
     *     
     */
    public void setRejectedClaimRange(DashboardRangeType value) {
        this.rejectedClaimRange = value;
    }

}
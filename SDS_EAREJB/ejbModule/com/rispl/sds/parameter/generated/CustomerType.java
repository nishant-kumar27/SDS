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
 * 				Holds all information regarding SDS Customer
 * 				Parameter.
 * 			
 * 
 * <p>Java class for Customer_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerSearch" type="{}CustomerSearch_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer_Type", propOrder = {
    "customerSearch"
})
public class CustomerType {

    @XmlElement(name = "CustomerSearch", required = true)
    protected CustomerSearchType customerSearch;

    /**
     * Gets the value of the customerSearch property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerSearchType }
     *     
     */
    public CustomerSearchType getCustomerSearch() {
        return customerSearch;
    }

    /**
     * Sets the value of the customerSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerSearchType }
     *     
     */
    public void setCustomerSearch(CustomerSearchType value) {
        this.customerSearch = value;
    }

}

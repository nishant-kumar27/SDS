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
 * 				Holds all information regarding SDS Invoices
 * 				Parameter.
 * 			
 * 
 * <p>Java class for Invoice_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Invoice_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvoiceSearch" type="{}InvoiceSearch_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Invoice_Type", propOrder = {
    "invoiceSearch"
})
public class InvoiceType {

    @XmlElement(name = "InvoiceSearch", required = true)
    protected InvoiceSearchType invoiceSearch;

    /**
     * Gets the value of the invoiceSearch property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceSearchType }
     *     
     */
    public InvoiceSearchType getInvoiceSearch() {
        return invoiceSearch;
    }

    /**
     * Sets the value of the invoiceSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceSearchType }
     *     
     */
    public void setInvoiceSearch(InvoiceSearchType value) {
        this.invoiceSearch = value;
    }

}

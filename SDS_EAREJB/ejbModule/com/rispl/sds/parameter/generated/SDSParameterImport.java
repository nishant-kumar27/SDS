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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDSParameter" type="{}SDSParameter_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sdsParameter"
})
@XmlRootElement(name = "SDSParameterImport")
public class SDSParameterImport {

    @XmlElement(name = "SDSParameter", required = true)
    protected SDSParameterType sdsParameter;

    /**
     * Gets the value of the sdsParameter property.
     * 
     * @return
     *     possible object is
     *     {@link SDSParameterType }
     *     
     */
    public SDSParameterType getSDSParameter() {
        return sdsParameter;
    }

    /**
     * Sets the value of the sdsParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SDSParameterType }
     *     
     */
    public void setSDSParameter(SDSParameterType value) {
        this.sdsParameter = value;
    }

}

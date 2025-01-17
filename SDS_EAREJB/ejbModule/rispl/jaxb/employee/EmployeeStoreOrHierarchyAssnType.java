//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.12 at 05:37:53 PM IST 
//


package rispl.jaxb.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 			Holds an employee association to a store and/or a hierarchy node. Generally, only one of the
 * 			enclosed elements is provided; however, there may be cases where an employee needs both a store
 * 			association and a hierarchy association, so a sequence with optional elements is used instead of
 * 			a choice.
 * 		
 * 
 * <p>Java class for EmployeeStoreOrHierarchyAssn_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmployeeStoreOrHierarchyAssn_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EmployeeStoreID" type="{}RetailStoreId_type" minOccurs="0"/>
 *         &lt;element name="EmployeeHierarchyAssn" type="{}EmployeeHierarchyAssn_type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeStoreOrHierarchyAssn_type", propOrder = {
    "employeeStoreID",
    "employeeHierarchyAssn"
})
public class EmployeeStoreOrHierarchyAssnType {

    @XmlElement(name = "EmployeeStoreID")
    protected String employeeStoreID;
    @XmlElement(name = "EmployeeHierarchyAssn")
    protected EmployeeHierarchyAssnType employeeHierarchyAssn;

    /**
     * Gets the value of the employeeStoreID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeStoreID() {
        return employeeStoreID;
    }

    /**
     * Sets the value of the employeeStoreID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeStoreID(String value) {
        this.employeeStoreID = value;
    }

    /**
     * Gets the value of the employeeHierarchyAssn property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeHierarchyAssnType }
     *     
     */
    public EmployeeHierarchyAssnType getEmployeeHierarchyAssn() {
        return employeeHierarchyAssn;
    }

    /**
     * Sets the value of the employeeHierarchyAssn property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeHierarchyAssnType }
     *     
     */
    public void setEmployeeHierarchyAssn(EmployeeHierarchyAssnType value) {
        this.employeeHierarchyAssn = value;
    }

}

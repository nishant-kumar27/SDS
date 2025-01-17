//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.12 at 02:47:07 PM IST 
//


package rispl.jaxb.merchandisehierarchy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetailStorePOSDepartment_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetailStorePOSDepartment_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChangeType" type="{}ChangeType_subtype"/>
 *         &lt;element name="RetailStoreId" type="{}RetailStoreId_type"/>
 *         &lt;element name="DefaultEntryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnabledFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ListSortIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetailStorePOSDepartment_type", propOrder = {
    "changeType",
    "retailStoreId",
    "defaultEntryCode",
    "enabledFlag",
    "listSortIndex"
})
public class RetailStorePOSDepartmentType {

    @XmlElement(name = "ChangeType", required = true)
    protected ChangeTypeSubtype changeType;
    @XmlElement(name = "RetailStoreId", required = true)
    protected String retailStoreId;
    @XmlElement(name = "DefaultEntryCode", required = true)
    protected String defaultEntryCode;
    @XmlElement(name = "EnabledFlag")
    protected boolean enabledFlag;
    @XmlElement(name = "ListSortIndex")
    protected int listSortIndex;

    /**
     * Gets the value of the changeType property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeTypeSubtype }
     *     
     */
    public ChangeTypeSubtype getChangeType() {
        return changeType;
    }

    /**
     * Sets the value of the changeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeTypeSubtype }
     *     
     */
    public void setChangeType(ChangeTypeSubtype value) {
        this.changeType = value;
    }

    /**
     * Gets the value of the retailStoreId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetailStoreId() {
        return retailStoreId;
    }

    /**
     * Sets the value of the retailStoreId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetailStoreId(String value) {
        this.retailStoreId = value;
    }

    /**
     * Gets the value of the defaultEntryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultEntryCode() {
        return defaultEntryCode;
    }

    /**
     * Sets the value of the defaultEntryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultEntryCode(String value) {
        this.defaultEntryCode = value;
    }

    /**
     * Gets the value of the enabledFlag property.
     * 
     */
    public boolean isEnabledFlag() {
        return enabledFlag;
    }

    /**
     * Sets the value of the enabledFlag property.
     * 
     */
    public void setEnabledFlag(boolean value) {
        this.enabledFlag = value;
    }

    /**
     * Gets the value of the listSortIndex property.
     * 
     */
    public int getListSortIndex() {
        return listSortIndex;
    }

    /**
     * Sets the value of the listSortIndex property.
     * 
     */
    public void setListSortIndex(int value) {
        this.listSortIndex = value;
    }

}

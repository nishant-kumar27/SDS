//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.03 at 02:52:33 PM IST 
//


package rispl.jaxb.item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 			This is used to define a size description for a certain locale.
 * 		
 * 
 * <p>Java class for LocalizedItemSizeDescription_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocalizedItemSizeDescription_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="TableName" type="{}Name_type" />
 *       &lt;attribute name="TableDesc" type="{}Description_type" />
 *       &lt;attribute name="TypeDesc" type="{}Name_type" />
 *       &lt;attribute name="ProportionDesc" type="{}Description_type" />
 *       &lt;attribute name="Language" type="{}Language_type" />
 *       &lt;attribute name="Country" type="{}Country_type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocalizedItemSizeDescription_type")
public class LocalizedItemSizeDescriptionType {

    @XmlAttribute(name = "TableName")
    protected String tableName;
    @XmlAttribute(name = "TableDesc")
    protected String tableDesc;
    @XmlAttribute(name = "TypeDesc")
    protected String typeDesc;
    @XmlAttribute(name = "ProportionDesc")
    protected String proportionDesc;
    @XmlAttribute(name = "Language")
    protected LanguageType language;
    @XmlAttribute(name = "Country")
    protected String country;

    /**
     * Gets the value of the tableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    /**
     * Gets the value of the tableDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableDesc() {
        return tableDesc;
    }

    /**
     * Sets the value of the tableDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableDesc(String value) {
        this.tableDesc = value;
    }

    /**
     * Gets the value of the typeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * Sets the value of the typeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeDesc(String value) {
        this.typeDesc = value;
    }

    /**
     * Gets the value of the proportionDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProportionDesc() {
        return proportionDesc;
    }

    /**
     * Sets the value of the proportionDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProportionDesc(String value) {
        this.proportionDesc = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link LanguageType }
     *     
     */
    public LanguageType getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link LanguageType }
     *     
     */
    public void setLanguage(LanguageType value) {
        this.language = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

}

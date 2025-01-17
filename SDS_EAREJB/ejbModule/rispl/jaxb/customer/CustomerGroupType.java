//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.24 at 03:36:53 PM IST 
//


package rispl.jaxb.customer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 			Represents a group of customers that can be marketed to,
 * 			e.g. seniors, teachers, etc. These groups are typically used
 * 			to trigger transaction-level discounts.
 * 		
 * 
 * <p>Java class for CustomerGroup_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerGroup_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocalizedNameDescription" type="{}LocalizedNameDescription_type" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ChangeType" use="required" type="{}ChangeType_type" />
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerGroup_type", propOrder = {
    "localizedNameDescription"
})
public class CustomerGroupType {

    @XmlElement(name = "LocalizedNameDescription", required = true)
    protected List<LocalizedNameDescriptionType> localizedNameDescription;
    @XmlAttribute(name = "ChangeType", required = true)
    protected ChangeTypeType changeType;
    @XmlAttribute(name = "ID", required = true)
    protected int id;

    /**
     * Gets the value of the localizedNameDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localizedNameDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalizedNameDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedNameDescriptionType }
     * 
     * 
     */
    public List<LocalizedNameDescriptionType> getLocalizedNameDescription() {
        if (localizedNameDescription == null) {
            localizedNameDescription = new ArrayList<LocalizedNameDescriptionType>();
        }
        return this.localizedNameDescription;
    }

    /**
     * Gets the value of the changeType property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeTypeType }
     *     
     */
    public ChangeTypeType getChangeType() {
        return changeType;
    }

    /**
     * Sets the value of the changeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeTypeType }
     *     
     */
    public void setChangeType(ChangeTypeType value) {
        this.changeType = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

}

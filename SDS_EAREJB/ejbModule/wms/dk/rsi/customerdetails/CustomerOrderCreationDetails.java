
package wms.dk.rsi.customerdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customerOrderCreationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerOrderCreationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="headerDetails" type="{http://customerDetails.rsi.dk.wms}createHeaderDetails" minOccurs="0"/>
 *         &lt;element name="lineDetails" type="{http://customerDetails.rsi.dk.wms}createLineDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerOrderCreationDetails", propOrder = {
    "headerDetails",
    "lineDetails"
})
public class CustomerOrderCreationDetails {

    protected CreateHeaderDetails headerDetails;
    @XmlElement(nillable = true)
    protected List<CreateLineDetails> lineDetails;

    /**
     * Gets the value of the headerDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CreateHeaderDetails }
     *     
     */
    public CreateHeaderDetails getHeaderDetails() {
        return headerDetails;
    }

    /**
     * Sets the value of the headerDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateHeaderDetails }
     *     
     */
    public void setHeaderDetails(CreateHeaderDetails value) {
        this.headerDetails = value;
    }

    /**
     * Gets the value of the lineDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreateLineDetails }
     * 
     * 
     */
    public List<CreateLineDetails> getLineDetails() {
        if (lineDetails == null) {
            lineDetails = new ArrayList<CreateLineDetails>();
        }
        return this.lineDetails;
    }

}

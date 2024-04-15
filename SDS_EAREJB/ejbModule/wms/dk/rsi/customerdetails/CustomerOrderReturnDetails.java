
package wms.dk.rsi.customerdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customerOrderReturnDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerOrderReturnDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnHeaderDetails" type="{http://customerDetails.rsi.dk.wms}returnHeaderDetails" minOccurs="0"/>
 *         &lt;element name="returnLineDetails" type="{http://customerDetails.rsi.dk.wms}returnLineDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerOrderReturnDetails", propOrder = {
    "returnHeaderDetails",
    "returnLineDetails"
})
public class CustomerOrderReturnDetails {

    protected ReturnHeaderDetails returnHeaderDetails;
    @XmlElement(nillable = true)
    protected List<ReturnLineDetails> returnLineDetails;

    /**
     * Gets the value of the returnHeaderDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnHeaderDetails }
     *     
     */
    public ReturnHeaderDetails getReturnHeaderDetails() {
        return returnHeaderDetails;
    }

    /**
     * Sets the value of the returnHeaderDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnHeaderDetails }
     *     
     */
    public void setReturnHeaderDetails(ReturnHeaderDetails value) {
        this.returnHeaderDetails = value;
    }

    /**
     * Gets the value of the returnLineDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnLineDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnLineDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReturnLineDetails }
     * 
     * 
     */
    public List<ReturnLineDetails> getReturnLineDetails() {
        if (returnLineDetails == null) {
            returnLineDetails = new ArrayList<ReturnLineDetails>();
        }
        return this.returnLineDetails;
    }

}


package wms.dk.rsi.customerdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for returnCustomerOrderResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="returnCustomerOrderResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnOrderStatus" type="{http://customerDetails.rsi.dk.wms}customerOrderReturnStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnCustomerOrderResponse", propOrder = {
    "returnOrderStatus"
})
public class ReturnCustomerOrderResponse {

    protected List<CustomerOrderReturnStatus> returnOrderStatus;

    /**
     * Gets the value of the returnOrderStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnOrderStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnOrderStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerOrderReturnStatus }
     * 
     * 
     */
    public List<CustomerOrderReturnStatus> getReturnOrderStatus() {
        if (returnOrderStatus == null) {
            returnOrderStatus = new ArrayList<CustomerOrderReturnStatus>();
        }
        return this.returnOrderStatus;
    }

}

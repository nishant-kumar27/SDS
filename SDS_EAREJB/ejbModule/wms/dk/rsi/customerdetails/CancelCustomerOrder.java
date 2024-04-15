
package wms.dk.rsi.customerdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cancelCustomerOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancelCustomerOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancelCustomerOrderList" type="{http://customerDetails.rsi.dk.wms}customerOrderCancelDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelCustomerOrder", propOrder = {
    "cancelCustomerOrderList"
})
public class CancelCustomerOrder {

    protected List<CustomerOrderCancelDetails> cancelCustomerOrderList;

    /**
     * Gets the value of the cancelCustomerOrderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancelCustomerOrderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancelCustomerOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerOrderCancelDetails }
     * 
     * 
     */
    public List<CustomerOrderCancelDetails> getCancelCustomerOrderList() {
        if (cancelCustomerOrderList == null) {
            cancelCustomerOrderList = new ArrayList<CustomerOrderCancelDetails>();
        }
        return this.cancelCustomerOrderList;
    }

}

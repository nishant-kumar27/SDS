
package wms.dk.rsi.customerdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for returnCustomerOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="returnCustomerOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnCustomerOrderList" type="{http://customerDetails.rsi.dk.wms}customerOrderReturnDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnCustomerOrder", propOrder = {
    "returnCustomerOrderList"
})
public class ReturnCustomerOrder {

    protected List<CustomerOrderReturnDetails> returnCustomerOrderList;

    /**
     * Gets the value of the returnCustomerOrderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnCustomerOrderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnCustomerOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerOrderReturnDetails }
     * 
     * 
     */
    public List<CustomerOrderReturnDetails> getReturnCustomerOrderList() {
        if (returnCustomerOrderList == null) {
            returnCustomerOrderList = new ArrayList<CustomerOrderReturnDetails>();
        }
        return this.returnCustomerOrderList;
    }

}


package wms.dk.rsi.customerdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for returnHeaderDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="returnHeaderDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customerOrderNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uniqueSeqNoForReturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnHeaderDetails", propOrder = {
    "businessDate",
    "customerOrderNO",
    "returnReasonCode",
    "uniqueSeqNoForReturn"
})
public class ReturnHeaderDetails {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar businessDate;
    protected String customerOrderNO;
    protected String returnReasonCode;
    protected String uniqueSeqNoForReturn;

    /**
     * Gets the value of the businessDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBusinessDate() {
        return businessDate;
    }

    /**
     * Sets the value of the businessDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBusinessDate(XMLGregorianCalendar value) {
        this.businessDate = value;
    }

    /**
     * Gets the value of the customerOrderNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerOrderNO() {
        return customerOrderNO;
    }

    /**
     * Sets the value of the customerOrderNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerOrderNO(String value) {
        this.customerOrderNO = value;
    }

    /**
     * Gets the value of the returnReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnReasonCode() {
        return returnReasonCode;
    }

    /**
     * Sets the value of the returnReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnReasonCode(String value) {
        this.returnReasonCode = value;
    }

    /**
     * Gets the value of the uniqueSeqNoForReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqueSeqNoForReturn() {
        return uniqueSeqNoForReturn;
    }

    /**
     * Sets the value of the uniqueSeqNoForReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqueSeqNoForReturn(String value) {
        this.uniqueSeqNoForReturn = value;
    }

}

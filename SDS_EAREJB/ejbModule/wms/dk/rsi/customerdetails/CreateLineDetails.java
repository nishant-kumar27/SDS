
package wms.dk.rsi.customerdetails;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createLineDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createLineDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerOrderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itmID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestedUnitQunatity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="sellingUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tranNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createLineDetails", propOrder = {
    "customerOrderNo",
    "destinationID",
    "itmID",
    "requestedUnitQunatity",
    "sellingUOM",
    "tranNo"
})
public class CreateLineDetails {

    protected String customerOrderNo;
    protected String destinationID;
    protected String itmID;
    protected BigDecimal requestedUnitQunatity;
    protected String sellingUOM;
    protected String tranNo;

    /**
     * Gets the value of the customerOrderNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    /**
     * Sets the value of the customerOrderNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerOrderNo(String value) {
        this.customerOrderNo = value;
    }

    /**
     * Gets the value of the destinationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationID() {
        return destinationID;
    }

    /**
     * Sets the value of the destinationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationID(String value) {
        this.destinationID = value;
    }

    /**
     * Gets the value of the itmID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItmID() {
        return itmID;
    }

    /**
     * Sets the value of the itmID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItmID(String value) {
        this.itmID = value;
    }

    /**
     * Gets the value of the requestedUnitQunatity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRequestedUnitQunatity() {
        return requestedUnitQunatity;
    }

    /**
     * Sets the value of the requestedUnitQunatity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRequestedUnitQunatity(BigDecimal value) {
        this.requestedUnitQunatity = value;
    }

    /**
     * Gets the value of the sellingUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingUOM() {
        return sellingUOM;
    }

    /**
     * Sets the value of the sellingUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingUOM(String value) {
        this.sellingUOM = value;
    }

    /**
     * Gets the value of the tranNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranNo() {
        return tranNo;
    }

    /**
     * Sets the value of the tranNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranNo(String value) {
        this.tranNo = value;
    }

}

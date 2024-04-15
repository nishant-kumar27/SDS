
package wms.dk.rsi.customerdetails;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for createHeaderDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createHeaderDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="additionalField1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalField2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalField3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billAddress4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billAddress5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billAddressDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billPreferedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bllCompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carrierCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carrierServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerOrderNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="deliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deliveryTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dlComments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="earliestPickDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="extraCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="giftCostTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="lastPickedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lpoNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packingCharge" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="promotionalTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="route" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipAddress4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipAddress5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipAddressDescriptions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="shipFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipPreferedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shippingTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="shpCompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="taxTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
@XmlType(name = "createHeaderDetails", propOrder = {
    "additionalField1",
    "additionalField2",
    "additionalField3",
    "billAddress1",
    "billAddress2",
    "billAddress3",
    "billAddress4",
    "billAddress5",
    "billAddressDescription",
    "billCity",
    "billCountry",
    "billCountryCode",
    "billFirstName",
    "billLastName",
    "billPhone",
    "billPreferedName",
    "billState",
    "billZip",
    "bllCompanyName",
    "carrierCode",
    "carrierServiceCode",
    "customerOrderNO",
    "deliveryCharge",
    "deliveryDate",
    "deliveryTime",
    "dlComments",
    "earliestPickDate",
    "extraCharge",
    "giftCostTotal",
    "lastPickedDate",
    "lpoNumber",
    "packingCharge",
    "priority",
    "promotionalTotal",
    "route",
    "shipAddress1",
    "shipAddress2",
    "shipAddress3",
    "shipAddress4",
    "shipAddress5",
    "shipAddressDescriptions",
    "shipCity",
    "shipCountry",
    "shipCountryCode",
    "shipDate",
    "shipFirstName",
    "shipLastName",
    "shipPhone",
    "shipPreferedName",
    "shipState",
    "shipZip",
    "shippingTotal",
    "shpCompanyName",
    "subTotal",
    "taxTotal",
    "total",
    "tranNo"
})
public class CreateHeaderDetails {

    protected String additionalField1;
    protected String additionalField2;
    protected String additionalField3;
    protected String billAddress1;
    protected String billAddress2;
    protected String billAddress3;
    protected String billAddress4;
    protected String billAddress5;
    protected String billAddressDescription;
    protected String billCity;
    protected String billCountry;
    protected String billCountryCode;
    protected String billFirstName;
    protected String billLastName;
    protected String billPhone;
    protected String billPreferedName;
    protected String billState;
    protected String billZip;
    protected String bllCompanyName;
    protected String carrierCode;
    protected String carrierServiceCode;
    protected String customerOrderNO;
    protected BigDecimal deliveryCharge;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deliveryDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deliveryTime;
    protected String dlComments;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar earliestPickDate;
    protected BigDecimal extraCharge;
    protected BigDecimal giftCostTotal;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastPickedDate;
    protected String lpoNumber;
    protected BigDecimal packingCharge;
    protected BigDecimal priority;
    protected BigDecimal promotionalTotal;
    protected String route;
    protected String shipAddress1;
    protected String shipAddress2;
    protected String shipAddress3;
    protected String shipAddress4;
    protected String shipAddress5;
    protected String shipAddressDescriptions;
    protected String shipCity;
    protected String shipCountry;
    protected String shipCountryCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar shipDate;
    protected String shipFirstName;
    protected String shipLastName;
    protected String shipPhone;
    protected String shipPreferedName;
    protected String shipState;
    protected String shipZip;
    protected BigDecimal shippingTotal;
    protected String shpCompanyName;
    protected BigDecimal subTotal;
    protected BigDecimal taxTotal;
    protected BigDecimal total;
    protected String tranNo;

    /**
     * Gets the value of the additionalField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalField1() {
        return additionalField1;
    }

    /**
     * Sets the value of the additionalField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalField1(String value) {
        this.additionalField1 = value;
    }

    /**
     * Gets the value of the additionalField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalField2() {
        return additionalField2;
    }

    /**
     * Sets the value of the additionalField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalField2(String value) {
        this.additionalField2 = value;
    }

    /**
     * Gets the value of the additionalField3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalField3() {
        return additionalField3;
    }

    /**
     * Sets the value of the additionalField3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalField3(String value) {
        this.additionalField3 = value;
    }

    /**
     * Gets the value of the billAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddress1() {
        return billAddress1;
    }

    /**
     * Sets the value of the billAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddress1(String value) {
        this.billAddress1 = value;
    }

    /**
     * Gets the value of the billAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddress2() {
        return billAddress2;
    }

    /**
     * Sets the value of the billAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddress2(String value) {
        this.billAddress2 = value;
    }

    /**
     * Gets the value of the billAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddress3() {
        return billAddress3;
    }

    /**
     * Sets the value of the billAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddress3(String value) {
        this.billAddress3 = value;
    }

    /**
     * Gets the value of the billAddress4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddress4() {
        return billAddress4;
    }

    /**
     * Sets the value of the billAddress4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddress4(String value) {
        this.billAddress4 = value;
    }

    /**
     * Gets the value of the billAddress5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddress5() {
        return billAddress5;
    }

    /**
     * Sets the value of the billAddress5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddress5(String value) {
        this.billAddress5 = value;
    }

    /**
     * Gets the value of the billAddressDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAddressDescription() {
        return billAddressDescription;
    }

    /**
     * Sets the value of the billAddressDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAddressDescription(String value) {
        this.billAddressDescription = value;
    }

    /**
     * Gets the value of the billCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCity() {
        return billCity;
    }

    /**
     * Sets the value of the billCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCity(String value) {
        this.billCity = value;
    }

    /**
     * Gets the value of the billCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCountry() {
        return billCountry;
    }

    /**
     * Sets the value of the billCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCountry(String value) {
        this.billCountry = value;
    }

    /**
     * Gets the value of the billCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCountryCode() {
        return billCountryCode;
    }

    /**
     * Sets the value of the billCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCountryCode(String value) {
        this.billCountryCode = value;
    }

    /**
     * Gets the value of the billFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillFirstName() {
        return billFirstName;
    }

    /**
     * Sets the value of the billFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillFirstName(String value) {
        this.billFirstName = value;
    }

    /**
     * Gets the value of the billLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillLastName() {
        return billLastName;
    }

    /**
     * Sets the value of the billLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillLastName(String value) {
        this.billLastName = value;
    }

    /**
     * Gets the value of the billPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillPhone() {
        return billPhone;
    }

    /**
     * Sets the value of the billPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillPhone(String value) {
        this.billPhone = value;
    }

    /**
     * Gets the value of the billPreferedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillPreferedName() {
        return billPreferedName;
    }

    /**
     * Sets the value of the billPreferedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillPreferedName(String value) {
        this.billPreferedName = value;
    }

    /**
     * Gets the value of the billState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillState() {
        return billState;
    }

    /**
     * Sets the value of the billState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillState(String value) {
        this.billState = value;
    }

    /**
     * Gets the value of the billZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillZip() {
        return billZip;
    }

    /**
     * Sets the value of the billZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillZip(String value) {
        this.billZip = value;
    }

    /**
     * Gets the value of the bllCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBllCompanyName() {
        return bllCompanyName;
    }

    /**
     * Sets the value of the bllCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBllCompanyName(String value) {
        this.bllCompanyName = value;
    }

    /**
     * Gets the value of the carrierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierCode() {
        return carrierCode;
    }

    /**
     * Sets the value of the carrierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierCode(String value) {
        this.carrierCode = value;
    }

    /**
     * Gets the value of the carrierServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierServiceCode() {
        return carrierServiceCode;
    }

    /**
     * Sets the value of the carrierServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierServiceCode(String value) {
        this.carrierServiceCode = value;
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
     * Gets the value of the deliveryCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeliveryCharge() {
        return deliveryCharge;
    }

    /**
     * Sets the value of the deliveryCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeliveryCharge(BigDecimal value) {
        this.deliveryCharge = value;
    }

    /**
     * Gets the value of the deliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Sets the value of the deliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeliveryDate(XMLGregorianCalendar value) {
        this.deliveryDate = value;
    }

    /**
     * Gets the value of the deliveryTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * Sets the value of the deliveryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeliveryTime(XMLGregorianCalendar value) {
        this.deliveryTime = value;
    }

    /**
     * Gets the value of the dlComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlComments() {
        return dlComments;
    }

    /**
     * Sets the value of the dlComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlComments(String value) {
        this.dlComments = value;
    }

    /**
     * Gets the value of the earliestPickDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestPickDate() {
        return earliestPickDate;
    }

    /**
     * Sets the value of the earliestPickDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestPickDate(XMLGregorianCalendar value) {
        this.earliestPickDate = value;
    }

    /**
     * Gets the value of the extraCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExtraCharge() {
        return extraCharge;
    }

    /**
     * Sets the value of the extraCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExtraCharge(BigDecimal value) {
        this.extraCharge = value;
    }

    /**
     * Gets the value of the giftCostTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGiftCostTotal() {
        return giftCostTotal;
    }

    /**
     * Sets the value of the giftCostTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGiftCostTotal(BigDecimal value) {
        this.giftCostTotal = value;
    }

    /**
     * Gets the value of the lastPickedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastPickedDate() {
        return lastPickedDate;
    }

    /**
     * Sets the value of the lastPickedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastPickedDate(XMLGregorianCalendar value) {
        this.lastPickedDate = value;
    }

    /**
     * Gets the value of the lpoNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLpoNumber() {
        return lpoNumber;
    }

    /**
     * Sets the value of the lpoNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLpoNumber(String value) {
        this.lpoNumber = value;
    }

    /**
     * Gets the value of the packingCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPackingCharge() {
        return packingCharge;
    }

    /**
     * Sets the value of the packingCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPackingCharge(BigDecimal value) {
        this.packingCharge = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriority(BigDecimal value) {
        this.priority = value;
    }

    /**
     * Gets the value of the promotionalTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPromotionalTotal() {
        return promotionalTotal;
    }

    /**
     * Sets the value of the promotionalTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPromotionalTotal(BigDecimal value) {
        this.promotionalTotal = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoute(String value) {
        this.route = value;
    }

    /**
     * Gets the value of the shipAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAddress1() {
        return shipAddress1;
    }

    /**
     * Sets the value of the shipAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAddress1(String value) {
        this.shipAddress1 = value;
    }

    /**
     * Gets the value of the shipAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAddress2() {
        return shipAddress2;
    }

    /**
     * Sets the value of the shipAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAddress2(String value) {
        this.shipAddress2 = value;
    }

    /**
     * Gets the value of the shipAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAddress3() {
        return shipAddress3;
    }

    /**
     * Sets the value of the shipAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAddress3(String value) {
        this.shipAddress3 = value;
    }

    /**
     * Gets the value of the shipAddress4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAddress4() {
        return shipAddress4;
    }

    /**
     * Sets the value of the shipAddress4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAddress4(String value) {
        this.shipAddress4 = value;
    }

    /**
     * Gets the value of the shipAddress5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAddress5() {
        return shipAddress5;
    }

    /**
     * Sets the value of the shipAddress5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAddress5(String value) {
        this.shipAddress5 = value;
    }

    /**
     * Gets the value of the shipAddressDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipAddressDescriptions() {
        return shipAddressDescriptions;
    }

    /**
     * Sets the value of the shipAddressDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipAddressDescriptions(String value) {
        this.shipAddressDescriptions = value;
    }

    /**
     * Gets the value of the shipCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCity() {
        return shipCity;
    }

    /**
     * Sets the value of the shipCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCity(String value) {
        this.shipCity = value;
    }

    /**
     * Gets the value of the shipCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCountry() {
        return shipCountry;
    }

    /**
     * Sets the value of the shipCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCountry(String value) {
        this.shipCountry = value;
    }

    /**
     * Gets the value of the shipCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCountryCode() {
        return shipCountryCode;
    }

    /**
     * Sets the value of the shipCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCountryCode(String value) {
        this.shipCountryCode = value;
    }

    /**
     * Gets the value of the shipDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getShipDate() {
        return shipDate;
    }

    /**
     * Sets the value of the shipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setShipDate(XMLGregorianCalendar value) {
        this.shipDate = value;
    }

    /**
     * Gets the value of the shipFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipFirstName() {
        return shipFirstName;
    }

    /**
     * Sets the value of the shipFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipFirstName(String value) {
        this.shipFirstName = value;
    }

    /**
     * Gets the value of the shipLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipLastName() {
        return shipLastName;
    }

    /**
     * Sets the value of the shipLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipLastName(String value) {
        this.shipLastName = value;
    }

    /**
     * Gets the value of the shipPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipPhone() {
        return shipPhone;
    }

    /**
     * Sets the value of the shipPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipPhone(String value) {
        this.shipPhone = value;
    }

    /**
     * Gets the value of the shipPreferedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipPreferedName() {
        return shipPreferedName;
    }

    /**
     * Sets the value of the shipPreferedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipPreferedName(String value) {
        this.shipPreferedName = value;
    }

    /**
     * Gets the value of the shipState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipState() {
        return shipState;
    }

    /**
     * Sets the value of the shipState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipState(String value) {
        this.shipState = value;
    }

    /**
     * Gets the value of the shipZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipZip() {
        return shipZip;
    }

    /**
     * Sets the value of the shipZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipZip(String value) {
        this.shipZip = value;
    }

    /**
     * Gets the value of the shippingTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getShippingTotal() {
        return shippingTotal;
    }

    /**
     * Sets the value of the shippingTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setShippingTotal(BigDecimal value) {
        this.shippingTotal = value;
    }

    /**
     * Gets the value of the shpCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShpCompanyName() {
        return shpCompanyName;
    }

    /**
     * Sets the value of the shpCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShpCompanyName(String value) {
        this.shpCompanyName = value;
    }

    /**
     * Gets the value of the subTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * Sets the value of the subTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSubTotal(BigDecimal value) {
        this.subTotal = value;
    }

    /**
     * Gets the value of the taxTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    /**
     * Sets the value of the taxTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxTotal(BigDecimal value) {
        this.taxTotal = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotal(BigDecimal value) {
        this.total = value;
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

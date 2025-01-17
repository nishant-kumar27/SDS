//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.03 at 02:52:33 PM IST 
//


package rispl.jaxb.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             Upper level item information. This element requires a child element
 *             to specify which store it belongs to. This element can be repeated
 *             if this item should belong to multiple stores. The LocalizedNameDescription
 *             elements may also be repeated with the intention that each
 *             specifies a different language or country.
 *         
 * 
 * <p>Java class for Item_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Item_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShortName" type="{}LocalizedName_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LongDescription" type="{}LocalizedDescription_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LocalizedNameDescription" type="{}LocalizedNameDescription_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MerchandiseHierarchy" type="{}MerchandiseHierarchy_type" minOccurs="0"/>
 *         &lt;element name="RetailStoreItem" type="{}RetailStoreItem_type" maxOccurs="unbounded"/>
 *         &lt;element name="DisplayMessage" type="{}ItemLevelMessages_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UINLabel" type="{}UINLabel_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Classification" type="{}Classification_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RelatedItemAssociation" type="{}RelatedItemAssociation_type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ChangeType" type="{}ChangeType_subtype" default="ADD" />
 *       &lt;attribute name="ID" use="required" type="{}ID_type" />
 *       &lt;attribute name="Type">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Stock"/>
 *             &lt;enumeration value="Service"/>
 *             &lt;enumeration value="Coupon"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="POSDepartmentID" type="{}Class_type" />
 *       &lt;attribute name="ItemCost" type="{}Amount_type" />
 *       &lt;attribute name="KitSetCode" type="{}Code_type" default="0" />
 *       &lt;attribute name="UOMCode" type="{}Code_type" />
 *       &lt;attribute name="PackItemWeightCount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Size">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Color" type="{}Code_type" />
 *       &lt;attribute name="Style">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="4"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="TaxGroup" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Taxable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="Discountable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="DamageDiscountable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="RegistryEligible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AuthorizedForSale" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RestockingFee" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="WillCall" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SerializedItem" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="UINType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UINCaptureTime">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="20"/>
 *             &lt;enumeration value="Sale"/>
 *             &lt;enumeration value="StoreReceiving"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="ExternalSystemCreateUIN" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="SizeRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ActivationRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ImageFileName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ImageLocation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item_type", propOrder = {
    "shortName",
    "longDescription",
    "localizedNameDescription",
    "merchandiseHierarchy",
    "retailStoreItem",
    "displayMessage",
    "uinLabel",
    "classification",
    "relatedItemAssociation"
})
public class ItemType {

    @XmlElement(name = "ShortName")
    protected List<LocalizedNameType> shortName;
    @XmlElement(name = "LongDescription")
    protected List<LocalizedDescriptionType> longDescription;
    @XmlElement(name = "LocalizedNameDescription")
    protected List<LocalizedNameDescriptionType> localizedNameDescription;
    @XmlElement(name = "MerchandiseHierarchy")
    protected MerchandiseHierarchyType merchandiseHierarchy;
    @XmlElement(name = "RetailStoreItem", required = true)
    protected List<RetailStoreItemType> retailStoreItem;
    @XmlElement(name = "DisplayMessage")
    protected List<ItemLevelMessagesType> displayMessage;
    @XmlElement(name = "UINLabel")
    protected List<UINLabelType> uinLabel;
    @XmlElement(name = "Classification")
    protected List<ClassificationType> classification;
    @XmlElement(name = "RelatedItemAssociation")
    protected List<RelatedItemAssociationType> relatedItemAssociation;
    @XmlAttribute(name = "ChangeType")
    protected ChangeTypeSubtype changeType;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "POSDepartmentID")
    protected String posDepartmentID;
    @XmlAttribute(name = "ItemCost")
    protected BigDecimal itemCost;
    @XmlAttribute(name = "KitSetCode")
    protected String kitSetCode;
    @XmlAttribute(name = "UOMCode")
    protected String uomCode;
    @XmlAttribute(name = "PackItemWeightCount")
    protected BigDecimal packItemWeightCount;
    @XmlAttribute(name = "Size")
    protected String size;
    @XmlAttribute(name = "Color")
    protected String color;
    @XmlAttribute(name = "Style")
    protected String style;
    @XmlAttribute(name = "TaxGroup")
    protected Integer taxGroup;
    @XmlAttribute(name = "Taxable")
    protected Boolean taxable;
    @XmlAttribute(name = "Discountable")
    protected Boolean discountable;
    @XmlAttribute(name = "DamageDiscountable")
    protected Boolean damageDiscountable;
    @XmlAttribute(name = "RegistryEligible")
    protected Boolean registryEligible;
    @XmlAttribute(name = "AuthorizedForSale")
    protected Boolean authorizedForSale;
    @XmlAttribute(name = "RestockingFee")
    protected Boolean restockingFee;
    @XmlAttribute(name = "WillCall")
    protected Boolean willCall;
    @XmlAttribute(name = "SerializedItem")
    protected Boolean serializedItem;
    @XmlAttribute(name = "UINType")
    protected String uinType;
    @XmlAttribute(name = "UINCaptureTime")
    protected String uinCaptureTime;
    @XmlAttribute(name = "ExternalSystemCreateUIN")
    protected Boolean externalSystemCreateUIN;
    @XmlAttribute(name = "SizeRequired")
    protected Boolean sizeRequired;
    @XmlAttribute(name = "ActivationRequired")
    protected Boolean activationRequired;
    @XmlAttribute(name = "ImageFileName")
    protected String imageFileName;
    @XmlAttribute(name = "ImageLocation")
    protected String imageLocation;

    /**
     * Gets the value of the shortName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shortName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShortName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedNameType }
     * 
     * 
     */
    public List<LocalizedNameType> getShortName() {
        if (shortName == null) {
            shortName = new ArrayList<LocalizedNameType>();
        }
        return this.shortName;
    }

    /**
     * Gets the value of the longDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the longDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLongDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedDescriptionType }
     * 
     * 
     */
    public List<LocalizedDescriptionType> getLongDescription() {
        if (longDescription == null) {
            longDescription = new ArrayList<LocalizedDescriptionType>();
        }
        return this.longDescription;
    }

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
     * Gets the value of the merchandiseHierarchy property.
     * 
     * @return
     *     possible object is
     *     {@link MerchandiseHierarchyType }
     *     
     */
    public MerchandiseHierarchyType getMerchandiseHierarchy() {
        return merchandiseHierarchy;
    }

    /**
     * Sets the value of the merchandiseHierarchy property.
     * 
     * @param value
     *     allowed object is
     *     {@link MerchandiseHierarchyType }
     *     
     */
    public void setMerchandiseHierarchy(MerchandiseHierarchyType value) {
        this.merchandiseHierarchy = value;
    }

    /**
     * Gets the value of the retailStoreItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the retailStoreItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRetailStoreItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RetailStoreItemType }
     * 
     * 
     */
    public List<RetailStoreItemType> getRetailStoreItem() {
        if (retailStoreItem == null) {
            retailStoreItem = new ArrayList<RetailStoreItemType>();
        }
        return this.retailStoreItem;
    }

    /**
     * Gets the value of the displayMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemLevelMessagesType }
     * 
     * 
     */
    public List<ItemLevelMessagesType> getDisplayMessage() {
        if (displayMessage == null) {
            displayMessage = new ArrayList<ItemLevelMessagesType>();
        }
        return this.displayMessage;
    }

    /**
     * Gets the value of the uinLabel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uinLabel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUINLabel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UINLabelType }
     * 
     * 
     */
    public List<UINLabelType> getUINLabel() {
        if (uinLabel == null) {
            uinLabel = new ArrayList<UINLabelType>();
        }
        return this.uinLabel;
    }

    /**
     * Gets the value of the classification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassificationType }
     * 
     * 
     */
    public List<ClassificationType> getClassification() {
        if (classification == null) {
            classification = new ArrayList<ClassificationType>();
        }
        return this.classification;
    }

    /**
     * Gets the value of the relatedItemAssociation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedItemAssociation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedItemAssociation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedItemAssociationType }
     * 
     * 
     */
    public List<RelatedItemAssociationType> getRelatedItemAssociation() {
        if (relatedItemAssociation == null) {
            relatedItemAssociation = new ArrayList<RelatedItemAssociationType>();
        }
        return this.relatedItemAssociation;
    }

    /**
     * Gets the value of the changeType property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeTypeSubtype }
     *     
     */
    public ChangeTypeSubtype getChangeType() {
        if (changeType == null) {
            return ChangeTypeSubtype.ADD;
        } else {
            return changeType;
        }
    }

    /**
     * Sets the value of the changeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeTypeSubtype }
     *     
     */
    public void setChangeType(ChangeTypeSubtype value) {
        this.changeType = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the posDepartmentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSDepartmentID() {
        return posDepartmentID;
    }

    /**
     * Sets the value of the posDepartmentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSDepartmentID(String value) {
        this.posDepartmentID = value;
    }

    /**
     * Gets the value of the itemCost property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getItemCost() {
        return itemCost;
    }

    /**
     * Sets the value of the itemCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setItemCost(BigDecimal value) {
        this.itemCost = value;
    }

    /**
     * Gets the value of the kitSetCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKitSetCode() {
        if (kitSetCode == null) {
            return "0";
        } else {
            return kitSetCode;
        }
    }

    /**
     * Sets the value of the kitSetCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKitSetCode(String value) {
        this.kitSetCode = value;
    }

    /**
     * Gets the value of the uomCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOMCode() {
        return uomCode;
    }

    /**
     * Sets the value of the uomCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOMCode(String value) {
        this.uomCode = value;
    }

    /**
     * Gets the value of the packItemWeightCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPackItemWeightCount() {
        return packItemWeightCount;
    }

    /**
     * Sets the value of the packItemWeightCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPackItemWeightCount(BigDecimal value) {
        this.packItemWeightCount = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize(String value) {
        this.size = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColor(String value) {
        this.color = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

    /**
     * Gets the value of the taxGroup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaxGroup() {
        return taxGroup;
    }

    /**
     * Sets the value of the taxGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaxGroup(Integer value) {
        this.taxGroup = value;
    }

    /**
     * Gets the value of the taxable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isTaxable() {
        if (taxable == null) {
            return true;
        } else {
            return taxable;
        }
    }

    /**
     * Sets the value of the taxable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxable(Boolean value) {
        this.taxable = value;
    }

    /**
     * Gets the value of the discountable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDiscountable() {
        if (discountable == null) {
            return true;
        } else {
            return discountable;
        }
    }

    /**
     * Sets the value of the discountable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDiscountable(Boolean value) {
        this.discountable = value;
    }

    /**
     * Gets the value of the damageDiscountable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDamageDiscountable() {
        if (damageDiscountable == null) {
            return true;
        } else {
            return damageDiscountable;
        }
    }

    /**
     * Sets the value of the damageDiscountable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDamageDiscountable(Boolean value) {
        this.damageDiscountable = value;
    }

    /**
     * Gets the value of the registryEligible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRegistryEligible() {
        return registryEligible;
    }

    /**
     * Sets the value of the registryEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRegistryEligible(Boolean value) {
        this.registryEligible = value;
    }

    /**
     * Gets the value of the authorizedForSale property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAuthorizedForSale() {
        return authorizedForSale;
    }

    /**
     * Sets the value of the authorizedForSale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAuthorizedForSale(Boolean value) {
        this.authorizedForSale = value;
    }

    /**
     * Gets the value of the restockingFee property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRestockingFee() {
        return restockingFee;
    }

    /**
     * Sets the value of the restockingFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRestockingFee(Boolean value) {
        this.restockingFee = value;
    }

    /**
     * Gets the value of the willCall property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWillCall() {
        return willCall;
    }

    /**
     * Sets the value of the willCall property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWillCall(Boolean value) {
        this.willCall = value;
    }

    /**
     * Gets the value of the serializedItem property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSerializedItem() {
        return serializedItem;
    }

    /**
     * Sets the value of the serializedItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSerializedItem(Boolean value) {
        this.serializedItem = value;
    }

    /**
     * Gets the value of the uinType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUINType() {
        return uinType;
    }

    /**
     * Sets the value of the uinType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUINType(String value) {
        this.uinType = value;
    }

    /**
     * Gets the value of the uinCaptureTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUINCaptureTime() {
        return uinCaptureTime;
    }

    /**
     * Sets the value of the uinCaptureTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUINCaptureTime(String value) {
        this.uinCaptureTime = value;
    }

    /**
     * Gets the value of the externalSystemCreateUIN property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isExternalSystemCreateUIN() {
        if (externalSystemCreateUIN == null) {
            return true;
        } else {
            return externalSystemCreateUIN;
        }
    }

    /**
     * Sets the value of the externalSystemCreateUIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExternalSystemCreateUIN(Boolean value) {
        this.externalSystemCreateUIN = value;
    }

    /**
     * Gets the value of the sizeRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSizeRequired() {
        return sizeRequired;
    }

    /**
     * Sets the value of the sizeRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSizeRequired(Boolean value) {
        this.sizeRequired = value;
    }

    /**
     * Gets the value of the activationRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActivationRequired() {
        return activationRequired;
    }

    /**
     * Sets the value of the activationRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActivationRequired(Boolean value) {
        this.activationRequired = value;
    }

    /**
     * Gets the value of the imageFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * Sets the value of the imageFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageFileName(String value) {
        this.imageFileName = value;
    }

    /**
     * Gets the value of the imageLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageLocation() {
        return imageLocation;
    }

    /**
     * Sets the value of the imageLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageLocation(String value) {
        this.imageLocation = value;
    }

}

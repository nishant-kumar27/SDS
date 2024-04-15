//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.06 at 11:57:08 AM IST 
//


package rispl.jaxb.item.pricing;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DiscountRule_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscountRule_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PricingRule" type="{}PricingRule_type"/>
 *         &lt;element name="Sources" type="{}Sources_type" minOccurs="0"/>
 *         &lt;element name="Targets" type="{}Targets_type" minOccurs="0"/>
 *         &lt;element name="CancelItems" type="{}CancelItems_type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscountRule_type", propOrder = {
    "pricingRule",
    "sources",
    "targets",
    "cancelItems"
})
public class DiscountRuleType {

    @XmlElement(name = "PricingRule", required = true)
    protected PricingRuleType pricingRule;
    @XmlElement(name = "Sources")
    protected SourcesType sources;
    @XmlElement(name = "Targets")
    protected TargetsType targets;
    @XmlElement(name = "CancelItems")
    protected List<CancelItemsType> cancelItems;

    /**
     * Gets the value of the pricingRule property.
     * 
     * @return
     *     possible object is
     *     {@link PricingRuleType }
     *     
     */
    public PricingRuleType getPricingRule() {
        return pricingRule;
    }

    /**
     * Sets the value of the pricingRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricingRuleType }
     *     
     */
    public void setPricingRule(PricingRuleType value) {
        this.pricingRule = value;
    }

    /**
     * Gets the value of the sources property.
     * 
     * @return
     *     possible object is
     *     {@link SourcesType }
     *     
     */
    public SourcesType getSources() {
        return sources;
    }

    /**
     * Sets the value of the sources property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourcesType }
     *     
     */
    public void setSources(SourcesType value) {
        this.sources = value;
    }

    /**
     * Gets the value of the targets property.
     * 
     * @return
     *     possible object is
     *     {@link TargetsType }
     *     
     */
    public TargetsType getTargets() {
        return targets;
    }

    /**
     * Sets the value of the targets property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetsType }
     *     
     */
    public void setTargets(TargetsType value) {
        this.targets = value;
    }

    /**
     * Gets the value of the cancelItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancelItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancelItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CancelItemsType }
     * 
     * 
     */
    public List<CancelItemsType> getCancelItems() {
        if (cancelItems == null) {
            cancelItems = new ArrayList<CancelItemsType>();
        }
        return this.cancelItems;
    }

}
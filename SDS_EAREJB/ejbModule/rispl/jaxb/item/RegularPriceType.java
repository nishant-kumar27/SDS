//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.03 at 02:52:33 PM IST 
//


package rispl.jaxb.item;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             The regular price is the initial permanent price for a new item.
 *             This price will effectively become amount of the first
 *             PermanentPriceChange for this item. Do not attempt to change the
 *             regular price afterwards through this element. Instead see
 *             PermanentPriceChange in the PricingImport.xsd. Any effective
 *             promotions or discounts will override, but not replace, the regular
 *             price.
 *         
 * 
 * <p>Java class for RegularPrice_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegularPrice_type">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;>CurrencyAmount_type">
 *       &lt;attribute name="CompareAtPrice" type="{}Amount_type" />
 *       &lt;attribute name="IncludesTax" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegularPrice_type")
public class RegularPriceType
    extends CurrencyAmountType
{

    @XmlAttribute(name = "CompareAtPrice")
    protected BigDecimal compareAtPrice;
    @XmlAttribute(name = "IncludesTax")
    protected Boolean includesTax;

    /**
     * Gets the value of the compareAtPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCompareAtPrice() {
        return compareAtPrice;
    }

    /**
     * Sets the value of the compareAtPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCompareAtPrice(BigDecimal value) {
        this.compareAtPrice = value;
    }

    /**
     * Gets the value of the includesTax property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIncludesTax() {
        if (includesTax == null) {
            return false;
        } else {
            return includesTax;
        }
    }

    /**
     * Sets the value of the includesTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludesTax(Boolean value) {
        this.includesTax = value;
    }

}
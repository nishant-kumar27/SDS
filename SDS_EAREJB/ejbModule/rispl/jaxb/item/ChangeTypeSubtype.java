//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.03 at 02:52:33 PM IST 
//


package rispl.jaxb.item;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangeType_subtype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChangeType_subtype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADD"/>
 *     &lt;enumeration value="UPD"/>
 *     &lt;enumeration value="DEL"/>
 *     &lt;enumeration value="UPS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChangeType_subtype")
@XmlEnum
public enum ChangeTypeSubtype {

    ADD,
    UPD,
    DEL,
    UPS;

    public String value() {
        return name();
    }

    public static ChangeTypeSubtype fromValue(String v) {
        return valueOf(v);
    }

}

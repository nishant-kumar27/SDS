//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.14 at 11:22:41 PM IST 
//


package rispl.jaxb.tax;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FillType_subtype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FillType_subtype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="KillAndFill"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FillType_subtype")
@XmlEnum
public enum FillTypeSubtype {

    @XmlEnumValue("KillAndFill")
    KILL_AND_FILL("KillAndFill");
    private final String value;

    FillTypeSubtype(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FillTypeSubtype fromValue(String v) {
        for (FillTypeSubtype c: FillTypeSubtype.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

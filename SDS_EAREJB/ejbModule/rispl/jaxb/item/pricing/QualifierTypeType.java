//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.06 at 11:57:08 AM IST 
//


package rispl.jaxb.item.pricing;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QualifierType_type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QualifierType_type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Any"/>
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="AnyCombo"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QualifierType_type")
@XmlEnum
public enum QualifierTypeType {

    @XmlEnumValue("Any")
    ANY("Any"),
    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("AnyCombo")
    ANY_COMBO("AnyCombo");
    private final String value;

    QualifierTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QualifierTypeType fromValue(String v) {
        for (QualifierTypeType c: QualifierTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.12 at 05:37:53 PM IST 
//


package rispl.jaxb.employee;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FillType_type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FillType_type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="KillAndFill"/>
 *     &lt;enumeration value="DeltaIncremental"/>
 *     &lt;enumeration value="FullIncremental"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FillType_type")
@XmlEnum
public enum FillTypeType {

    @XmlEnumValue("KillAndFill")
    KILL_AND_FILL("KillAndFill"),
    @XmlEnumValue("DeltaIncremental")
    DELTA_INCREMENTAL("DeltaIncremental"),
    @XmlEnumValue("FullIncremental")
    FULL_INCREMENTAL("FullIncremental");
    private final String value;

    FillTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FillTypeType fromValue(String v) {
        for (FillTypeType c: FillTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

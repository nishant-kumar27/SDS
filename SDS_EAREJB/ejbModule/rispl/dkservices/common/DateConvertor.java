package rispl.dkservices.common;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateConvertor
{
  private static DatatypeFactory df = null;

  static {
    try { df = DatatypeFactory.newInstance();
    } catch (DatatypeConfigurationException dce) {
      throw new IllegalStateException(
        "Exception while obtaining DatatypeFactory instance", dce);
    }
  }

  public static XMLGregorianCalendar asXMLGregorianCalendar(Date date)
  {
    if (date == null) {
      return null;
    }
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTimeInMillis(date.getTime());
    return df.newXMLGregorianCalendar(gc);
  }

  public static Date asDate(XMLGregorianCalendar xgc) throws Exception
  {
    if (xgc == null) {
      return null;
    }
    return xgc.toGregorianCalendar().getTime();
  }
}
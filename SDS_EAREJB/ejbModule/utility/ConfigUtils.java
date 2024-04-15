package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigUtils {
	static ConfigUtils utils;
	private static final Logger LOGGER = LogManager.getLogger(ConfigUtils.class);
	Properties prop;

	ConfigUtils() {
		prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ConfigUtils getInstance() {
		if (utils == null)
			utils = new ConfigUtils();
		return utils;
	}

	public String getRMSInventoryUrl() throws Exception {
		return getValueFromKey("rms.inventory.url");
	}

	public String getDIMEIncomingPath() throws Exception {
		return getValueFromKey("dime.incoming.path");
	}

	public String getDIMEBackupPath() throws Exception {
		return getValueFromKey("dime.backup.path");
	}

	public String getSDSStoreID() throws Exception {
		return getValueFromKey("sds.store.id");
	}

	private String getValueFromKey(String key) throws Exception {
		if (prop != null && prop.containsKey(key))
			return prop.getProperty(key);
		else
			throw new Exception(key + " not found in config.properties");
	}

	public String getBusinessDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String businessDate = dateFormat.format(new Date());

		return businessDate;
	}

	public String getSDSWorkstationID() throws Exception {
		return getValueFromKey("sds.workstation.id");
	}

	public String getWarehouseLocationID() throws Exception {
		return getValueFromKey("warehouse.location.id");
	}

	public String getWarehouseChannelID() throws Exception {
		return getValueFromKey("warehouse.channel.id");
	}

	public URL getCustomerOrderServiceUrl() throws Exception {
		return new URL(getValueFromKey("customerorder.service.url"));
	}

	public String getEmailJndiServiceName() throws Exception {
		return getValueFromKey("mail.jndi.service");
	}

	/*
	 * public String[] getWarehouseNotificaionMailAddresses() throws Exception {
	 * String emailAddress = getValueFromKey("warehouse.notification.mail");
	 * String[] emailAddresses = { "" }; if (emailAddress != null &&
	 * emailAddress.length() > 0) emailAddresses = emailAddress.split(",");
	 * 
	 * return emailAddresses; }
	 */

	public String getDateFormat() throws Exception {
		return getValueFromKey("format.date");
	}

	public String getJPADataSource() throws Exception {
		InputStream is = this.getClass().getResourceAsStream("/META-INF/persistence.xml");
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String line;
		List<String> linesList = new ArrayList<String>();
		while ((line = in.readLine()) != null) {
			linesList.add(line);
		}
		String dataSourceLine = linesList.stream().filter(l -> l.contains("data-source")).findFirst().get().trim();
		return dataSourceLine;
	}
	
	/**
	 * method to get the formatted value depends on the format.
	 * 
	 * it takes the format and value(need to be formatted) as input and produces the BigDecimal 
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public <T> BigDecimal createBigDecimal(T value,String format){
		BigDecimal formattedValue = null;
		if(value != null && format != null){
			try{
			    DecimalFormat decimalFormat = new DecimalFormat(getValueFromKey(format));
			    decimalFormat.setRoundingMode(RoundingMode.FLOOR); // ADDED ROUNDING MODE` TO FLOOR
			    formattedValue = new BigDecimal(decimalFormat.format(Double.parseDouble(value.toString())));
			    
			}catch(Exception e){
				DecimalFormat decimalFormat = new DecimalFormat(format);
				formattedValue = new BigDecimal(decimalFormat.format(Double.parseDouble(value.toString())));
				//LOGGER.error(e.getMessage());
			}
		}
		
	    return formattedValue;
	}
	/*
	 * to get the currencyFormat
	 */
	public String getCurrencyFormat() {
		String format = null;
		try{
			format = getValueFromKey("format.currency");
		}catch(Exception e){
			LOGGER.info(e.getMessage());
		}
		return format;
	}
}

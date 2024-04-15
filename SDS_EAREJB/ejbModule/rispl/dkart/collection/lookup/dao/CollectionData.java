package rispl.dkart.collection.lookup.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class CollectionData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String label; 		//The x-axis value displayed on the chart
	public Date from;			//The range over which the data will be pulled
	public Date to;
	public BigDecimal total;	//The total for collections pulled for the particular range
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
}

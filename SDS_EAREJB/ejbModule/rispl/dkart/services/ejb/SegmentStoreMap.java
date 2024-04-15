package rispl.dkart.services.ejb;

import java.io.Serializable;

public class SegmentStoreMap implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String segment;
	
	private String division;
	
	private String store;
	
	private String storeId;

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreId() {
		return storeId;
	}

}

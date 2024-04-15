package com.rispl.sds.paramter.parser;

import java.io.Serializable;

public class UpdateXMLParamter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String parameterGrpElement;
	
	private String simpleElement;
	
	public String getParameterGrpElement() {
		return parameterGrpElement;
	}

	public void setParameterGrpElement(String parameterGrpElement) {
		this.parameterGrpElement = parameterGrpElement;
	}

	private String newValue;

	public String getSimpleElement() {
		return simpleElement;
	}

	public void setSimpleElement(String simpleElement) {
		this.simpleElement = simpleElement;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
	
}

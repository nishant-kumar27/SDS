package com.rispl.sds.paramter.parser;

import java.io.Serializable;
import java.util.ArrayList;

public class SimpleElementParameterGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String groupParameter;
	
	private String simpleTypeElement;
	
	private String simpleTypeElementCurrentValue;
	
	private String simpleTypeElementDescription;
	
	private ArrayList<String> dataType;
	

	public ArrayList<String> getDataType() {
		return dataType;
	}

	public void setDataType(ArrayList<String> dataType) {
		this.dataType = dataType;
	}

	public String getSimpleTypeElementDescription() {
		return simpleTypeElementDescription;
	}

	public void setSimpleTypeElementDescription(String simpleTypeElementDescription) {
		this.simpleTypeElementDescription = simpleTypeElementDescription;
	}

	public String getGroupParameter() {
		return groupParameter;
	}

	public void setGroupParameter(String groupParameter) {
		this.groupParameter = groupParameter;
	}

	public String getSimpleTypeElement() {
		return simpleTypeElement;
	}

	public void setSimpleTypeElement(String simpleTypeElement) {
		this.simpleTypeElement = simpleTypeElement;
	}

	public String getSimpleTypeElementCurrentValue() {
		return simpleTypeElementCurrentValue;
	}

	public void setSimpleTypeElementCurrentValue(String simpleTypeElementCurrentValue) {
		this.simpleTypeElementCurrentValue = simpleTypeElementCurrentValue;
	}

	

}

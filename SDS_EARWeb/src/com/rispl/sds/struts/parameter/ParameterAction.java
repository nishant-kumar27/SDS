package com.rispl.sds.struts.parameter;

import java.util.ArrayList;

import com.rispl.sds.parameter.service.ParameterException;
import com.rispl.sds.parameter.service.ParameterConfigurationServiceIfc;
import com.rispl.sds.paramter.parser.SimpleElementParameterGroup;
import com.rispl.sds.paramter.parser.UpdateXMLParamter;

import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class ParameterAction extends DSAction {

	private static final long serialVersionUID = 1L;

	private ArrayList<SimpleElementParameterGroup> simpleElementList = new ArrayList<SimpleElementParameterGroup>();

	private ArrayList<String> parameterList = new ArrayList<String>();

	private String parameterGroup;

	private String parameterError;

	public String getParameterError() {
		return parameterError;
	}

	public void setParameterError(String parameterError) {
		this.parameterError = parameterError;
	}

	public ArrayList<UpdateXMLParamter> getUpdateParameterlist() {
		return updateParameterlist;
	}

	private SimpleElementParameterGroup simpleElementParameterGroup;

	private ArrayList<UpdateXMLParamter> updateParameterlist = new ArrayList<UpdateXMLParamter>();

	private UpdateXMLParamter updateXMlParameter = new UpdateXMLParamter();

	public UpdateXMLParamter getUpdateXMlParameter() {
		return updateXMlParameter;
	}

	public void setUpdateXMlParameter(UpdateXMLParamter updateXMlParameter) {
		this.updateXMlParameter = updateXMlParameter;
	}

	public void setUpdateParameterlist(ArrayList<UpdateXMLParamter> updateParameterlist) {
		this.updateParameterlist = updateParameterlist;
	}

	/*
	 * private String parameterName;
	 * 
	 * private String paramterValue;
	 * 
	 * public String getParameterName() { return parameterName; }
	 * 
	 * public void setParameterName(String parameterName) { this.parameterName =
	 * parameterName; }
	 * 
	 * public String getParamterValue() { return paramterValue; }
	 * 
	 * public void setParamterValue(String paramterValue) { this.paramterValue =
	 * paramterValue; }
	 */
	public SimpleElementParameterGroup getSimpleElementParameterGroup() {
		return simpleElementParameterGroup;
	}

	public void setSimpleElementParameterGroup(SimpleElementParameterGroup simpleElementParameterGroup) {
		this.simpleElementParameterGroup = simpleElementParameterGroup;
	}

	public ArrayList<SimpleElementParameterGroup> getSimpleElementList() {
		return simpleElementList;
	}

	public void setSimpleElementList(ArrayList<SimpleElementParameterGroup> simpleElementList) {
		this.simpleElementList = simpleElementList;
	}

	public ArrayList<String> getParameterList() {
		return parameterList;
	}

	public void setParameterList(ArrayList<String> parameterList) {
		this.parameterList = parameterList;
	}

	public String getParameterGroup() {
		return parameterGroup;
	}

	public void setParameterGroup(String parameterGroup) {
		this.parameterGroup = parameterGroup;
	}

	public String getParamterGroupElements() {
		parameterError = "FALSE";
		try {
			ParameterConfigurationServiceIfc parameterService = DKartContext.getParamterBean();
			parameterList = parameterService.getParametergroupDetailsList();
			parameterError = "TRUE";
			return SUCCESS;
		} catch (Exception e) {
			addActionError("Unable To Look Up Parameter Bean From Context");
			return ERROR;
		}
	}

	public String getSimpleParamterElement() {
		getParamterGroupElements();
		parameterError = "FALSE";
		System.out.println("get simple parameter element strats ");
		ParameterConfigurationServiceIfc parameterService = null;
		try {
			parameterService = DKartContext.getParamterBean();
		} catch (Exception e1) {
			parameterError = "TRUE";
			addActionError("Unable To Look Up Parameter Bean From Context");
			return ERROR;
		}
		try {
			simpleElementList = parameterService.getSimpleElementParameterGroup(parameterGroup);
			parameterError = "FALSE";
			return SUCCESS;
		} catch (ParameterException e) {
			parameterError = "TRUE";
			addActionError("Unable To Fetch Parameter Group Elements From Properties File");
			return ERROR;
		}
	}

	public String updateXMLParameter() {
		getParamterGroupElements();
		parameterError = "FALSE";
		ParameterConfigurationServiceIfc parameterService = null;
		if (updateParameterlist.size() == 0) {
			parameterError = "TRUE";
			addActionError("Please Select Parameter Group");
			return ERROR;
		} else {
			try {
				parameterService = DKartContext.getParamterBean();
			} catch (Exception e1) {
				parameterError = "TRUE";
				addActionError("Unable To Look Up Parameter Bean From Context");
				return ERROR;
			}
			try {
				parameterService.updateSDSParameterXMl(updateParameterlist);
				parameterError = "FALSE";
				addActionMessage("Parameter Configuration File Has Been Updated Successfully");
				return SUCCESS;
			} catch (ParameterException e) {
				parameterError = "TRUE";
				addActionError("Unable To Update Parameter Configuration File");
				return ERROR;
			}
		}

	}
}
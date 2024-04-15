package com.rispl.sds.parameter.service;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.rispl.sds.paramter.parser.SimpleElementParameterGroup;
import com.rispl.sds.paramter.parser.UpdateXMLParamter;
import com.rispl.sds.paramter.parser.XMLParameterReader;

@Remote
public interface ParameterConfigurationServiceIfc {
	
	public void updateSDSParameterXMl(ArrayList<UpdateXMLParamter> updateXMLFileList) throws ParameterException;
	
	public ArrayList<SimpleElementParameterGroup> getSimpleElementParameterGroup(String parameterGroup) throws ParameterException;
	
	public ArrayList<String> getParametergroupDetailsList() throws ParameterException;
	
	public XMLParameterReader fetchXMLParameterValues() throws ParameterException;
	
}

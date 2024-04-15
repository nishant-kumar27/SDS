package com.rispl.sds.paramter.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.rispl.sds.parameter.generated.DashboardRangeType;
import com.rispl.sds.parameter.service.ParameterException;
import com.rispl.sds.paramter.parser.SchemaValidator.Handler;
import com.rispl.sds.paramter.parser.SchemaValidator.Resolver;

public class ParameterHandler {

	

	public URL urlXML; // = ParameterHandler.class.getClassLoader().getResource("SDSParameter.xml");
	private String xmlFile; // = urlXML.getPath();
	private URL urlXSD; // = ParameterHandler.class.getClassLoader().getResource("SDSParameter.xsd");
	private String xsdFile; // = urlXSD.getPath();

	public ParameterHandler(String parameterXml, String parameterXsd) {
		urlXML = getClass().getClassLoader().getResource(parameterXml);
		urlXSD = getClass().getClassLoader().getResource(parameterXsd);
		xmlFile = urlXML.getPath();
		xsdFile = urlXSD.getPath();
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public String getXsdFile() {
		return xsdFile;
	}

	public void setXsdFile(String xsdFile) {
		this.xsdFile = xsdFile;
	}

	public String replaceValue(Document doc, String parameterGroupElement, String tagName, String replaceValue)
			throws ParameterException {
		String currentValue = "";
		if (doc != null && parameterGroupElement != null && tagName != null && replaceValue != null) {

			try {
				NodeList groupParameter = doc.getElementsByTagName(parameterGroupElement);
				Node groupParamName = groupParameter.item(0);
				NodeList groupParameterList = groupParamName.getChildNodes();
				for (int i = 0; i < groupParameterList.getLength(); i++) {
					Node sampleParameter = groupParameterList.item(i);
					if (sampleParameter.getNodeName().equals(tagName)) {
						currentValue = sampleParameter.getTextContent();
						System.out.println("New Value" + replaceValue);
						System.out.println("Current Value:" + sampleParameter.getTextContent());
						sampleParameter.getFirstChild().setNodeValue(replaceValue);
					}
				}
			} catch (Exception e) {
				throw new ParameterException();
			}

		} else {
			throw new ParameterException("Please Enter The Correct Paramter Details");
		}
		return currentValue;
	}

	public ArrayList<SimpleElementParameterGroup> returnSimpleTypeElement(Document doc, String complexTypeElement)
			throws ParameterException {
		ArrayList<SimpleElementParameterGroup> simpleTypeParameterGroupList = new ArrayList<SimpleElementParameterGroup>();
		if (doc != null && complexTypeElement != null) {
			try {
				NodeList complexTypeNodeList = doc.getElementsByTagName(complexTypeElement);
				for (int i = 0; i < complexTypeNodeList.getLength(); i++) {
					Node complexEmplement = complexTypeNodeList.item(i);
					NodeList simpleTypeNodeList = complexEmplement.getChildNodes();
					for (int j = 0; j < simpleTypeNodeList.getLength(); j++) {
						Node simpleElement = simpleTypeNodeList.item(j);
						if (simpleElement.hasChildNodes()) {
							SimpleElementParameterGroup simpleElementParameterGroup = new SimpleElementParameterGroup();
							simpleElementParameterGroup.setSimpleTypeElement(simpleElement.getNodeName());
							simpleElementParameterGroup.setGroupParameter(complexEmplement.getNodeName());
							simpleElementParameterGroup
									.setSimpleTypeElementCurrentValue(simpleElement.getTextContent());
							simpleElementParameterGroup.setSimpleTypeElementDescription(simpleElement.getNodeName());
							ArrayList<String> dashBoardrange = new ArrayList<String>();
							if (simpleElement.getNodeName().endsWith("Range")) {
								dashBoardrange = getListOfDashboardRangeDataType();
							}
							if (simpleElement.getNodeName().startsWith("Enable") || simpleElement.getNodeName().startsWith("Check")) {
								dashBoardrange = getListOfBooleanDataType();
							}
							//Saideep: Temp fix to allow for text data type
							if(simpleElement.getNodeName().endsWith("Email")){
								dashBoardrange = new ArrayList<String>(Arrays.asList("Text"));
							}
							simpleElementParameterGroup.setDataType(dashBoardrange);
							simpleTypeParameterGroupList.add(simpleElementParameterGroup);
						}
					}
				}

			} catch (Exception e) {
				throw new ParameterException(e);
			}
		} else {

		}
		return simpleTypeParameterGroupList;

	}

	public Document getDocumentDetails() throws ParameterException {
		DocumentBuilder docBuilder = null;
		Document doc = null;
		DocumentBuilderFactory docFactory = null;
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			docBuilder.setEntityResolver(new EntityResolver() {
				public InputSource resolveEntity(String publicId, String systemId) {
					return new InputSource(
							new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
				}
			});
		} catch (ParserConfigurationException e) {
			throw new ParameterException();
		}
		try {
			File fis = new File(xmlFile);
			doc = docBuilder.parse(fis);
		} catch (SAXException e) {
			throw new ParameterException(e);
		} catch (IOException e) {
			throw new ParameterException(e);
		}
		return doc;
	}

	public boolean validateXMLWithXSD() throws ParameterException {
		boolean flag = false;
		try {
			String args[] = new String[2];
			args[0] = xsdFile;
			args[1] = xmlFile;

			Handler handler = new Handler();

			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			schemaFactory.setErrorHandler(handler);
			Schema schemaGrammar = schemaFactory.newSchema(new File(args[0]));
			Resolver resolver = new Resolver();
			Validator schemaValidator = schemaGrammar.newValidator();
			schemaValidator.setResourceResolver(resolver);
			schemaValidator.setErrorHandler(handler);
			schemaValidator.validate(new StreamSource(args[1]));
			flag = true;
			System.out.println(args[1] + " XML File Validated With " + args[0] + " XSD");

		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

	public ArrayList<String> getListOfDashboardRangeDataType() {
		ArrayList<String> dashBoardRangeDataTypeList = new ArrayList<String>();
		dashBoardRangeDataTypeList.add(DashboardRangeType.CURRENT_WEEK.toString());
		dashBoardRangeDataTypeList.add(DashboardRangeType.CURRENT_MONTH.toString());
		dashBoardRangeDataTypeList.add(DashboardRangeType.CURRENT_QUARTER.toString());
		dashBoardRangeDataTypeList.add(DashboardRangeType.CURRENT_YEAR.toString());
		dashBoardRangeDataTypeList.add(DashboardRangeType.NONE.toString());
		return dashBoardRangeDataTypeList;

	}

	public ArrayList<String> getListOfBooleanDataType() {
		ArrayList<String> booleanDataTypeList = new ArrayList<String>();
		booleanDataTypeList.add("Yes");
		booleanDataTypeList.add("No");
		return booleanDataTypeList;
	}
}
/**
 * InventoryDetailServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1;

import utility.ConfigUtils;

public class InventoryDetailServiceLocator extends org.apache.axis.client.Service
		implements com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailService {
	private static final long serialVersionUID = 1L;

	public InventoryDetailServiceLocator() {

	}

	String getRmsUrl() {
		try {
			return ConfigUtils.getInstance().getRMSInventoryUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public InventoryDetailServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public InventoryDetailServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for InventoryDetailPort
	private java.lang.String InventoryDetailPort_address = getRmsUrl();

	public java.lang.String getInventoryDetailPortAddress() {
		return InventoryDetailPort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String InventoryDetailPortWSDDServiceName = "InventoryDetailPort";

	public java.lang.String getInventoryDetailPortWSDDServiceName() {
		return InventoryDetailPortWSDDServiceName;
	}

	public void setInventoryDetailPortWSDDServiceName(java.lang.String name) {
		InventoryDetailPortWSDDServiceName = name;
	}

	public com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortType getInventoryDetailPort()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(InventoryDetailPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getInventoryDetailPort(endpoint);
	}

	public com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortType getInventoryDetailPort(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortBindingStub _stub = new com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortBindingStub(
					portAddress, this);
			_stub.setPortName(getInventoryDetailPortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setInventoryDetailPortEndpointAddress(java.lang.String address) {
		InventoryDetailPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortType.class
					.isAssignableFrom(serviceEndpointInterface)) {
				com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortBindingStub _stub = new com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortBindingStub(
						new java.net.URL(InventoryDetailPort_address), this);
				_stub.setPortName(getInventoryDetailPortWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
				+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("InventoryDetailPort".equals(inputPortName)) {
			return getInventoryDetailPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName(
				"http://www.oracle.com/retail/rms/integration/services/InventoryDetailService/v1",
				"InventoryDetailService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName(
					"http://www.oracle.com/retail/rms/integration/services/InventoryDetailService/v1",
					"InventoryDetailPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {

		if ("InventoryDetailPort".equals(portName)) {
			setInventoryDetailPortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}

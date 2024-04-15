package com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1;

public class InventoryDetailPortTypeProxy implements com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortType {
  private String _endpoint = null;
  private com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortType inventoryDetailPortType = null;
  
  public InventoryDetailPortTypeProxy() {
    _initInventoryDetailPortTypeProxy();
  }
  
  public InventoryDetailPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initInventoryDetailPortTypeProxy();
  }
  
  private void _initInventoryDetailPortTypeProxy() {
    try {
      inventoryDetailPortType = (new com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailServiceLocator()).getInventoryDetailPort();
      if (inventoryDetailPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)inventoryDetailPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)inventoryDetailPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (inventoryDetailPortType != null)
      ((javax.xml.rpc.Stub)inventoryDetailPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.oracle.www.retail.rms.integration.services.InventoryDetailService.v1.InventoryDetailPortType getInventoryDetailPortType() {
    if (inventoryDetailPortType == null)
      _initInventoryDetailPortTypeProxy();
    return inventoryDetailPortType;
  }
  
  public com.oracle.www.retail.integration.base.bo.InvAvailColDesc.v1.InvAvailColDesc lookupInvAvailCriVo(com.oracle.www.retail.integration.base.bo.InvAvailCriVo.v1.InvAvailCriVo invAvailCriVo) throws java.rmi.RemoteException, com.oracle.www.retail.integration.services.exception.v1.IllegalArgumentWSFaultException, com.oracle.www.retail.integration.services.exception.v1.IllegalStateWSFaultException, com.oracle.www.retail.integration.services.exception.v1.ValidationWSFaultException{
    if (inventoryDetailPortType == null)
      _initInventoryDetailPortTypeProxy();
    return inventoryDetailPortType.lookupInvAvailCriVo(invAvailCriVo);
  }
  
  public java.lang.String ping(java.lang.String arg0) throws java.rmi.RemoteException{
    if (inventoryDetailPortType == null)
      _initInventoryDetailPortTypeProxy();
    return inventoryDetailPortType.ping(arg0);
  }
  
  
}
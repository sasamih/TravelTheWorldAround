package net.etfbl.travel;

public class TopTravelsProxy implements net.etfbl.travel.TopTravels {
  private String _endpoint = null;
  private net.etfbl.travel.TopTravels topTravels = null;
  
  public TopTravelsProxy() {
    _initTopTravelsProxy();
  }
  
  public TopTravelsProxy(String endpoint) {
    _endpoint = endpoint;
    _initTopTravelsProxy();
  }
  
  private void _initTopTravelsProxy() {
    try {
      topTravels = (new net.etfbl.travel.TopTravelsServiceLocator()).getTopTravels();
      if (topTravels != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)topTravels)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)topTravels)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (topTravels != null)
      ((javax.xml.rpc.Stub)topTravels)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.etfbl.travel.TopTravels getTopTravels() {
    if (topTravels == null)
      _initTopTravelsProxy();
    return topTravels;
  }
  
  public net.etfbl.travel.Putopis[] getTopTenTravels() throws java.rmi.RemoteException{
    if (topTravels == null)
      _initTopTravelsProxy();
    return topTravels.getTopTenTravels();
  }
  
  
}
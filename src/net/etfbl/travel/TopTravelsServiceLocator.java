/**
 * TopTravelsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.etfbl.travel;

public class TopTravelsServiceLocator extends org.apache.axis.client.Service implements net.etfbl.travel.TopTravelsService {

    public TopTravelsServiceLocator() {
    }


    public TopTravelsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TopTravelsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TopTravels
    private java.lang.String TopTravels_address = "http://localhost:8080/TopTenService/services/TopTravels";

    public java.lang.String getTopTravelsAddress() {
        return TopTravels_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TopTravelsWSDDServiceName = "TopTravels";

    public java.lang.String getTopTravelsWSDDServiceName() {
        return TopTravelsWSDDServiceName;
    }

    public void setTopTravelsWSDDServiceName(java.lang.String name) {
        TopTravelsWSDDServiceName = name;
    }

    public net.etfbl.travel.TopTravels getTopTravels() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TopTravels_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTopTravels(endpoint);
    }

    public net.etfbl.travel.TopTravels getTopTravels(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.etfbl.travel.TopTravelsSoapBindingStub _stub = new net.etfbl.travel.TopTravelsSoapBindingStub(portAddress, this);
            _stub.setPortName(getTopTravelsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTopTravelsEndpointAddress(java.lang.String address) {
        TopTravels_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.etfbl.travel.TopTravels.class.isAssignableFrom(serviceEndpointInterface)) {
                net.etfbl.travel.TopTravelsSoapBindingStub _stub = new net.etfbl.travel.TopTravelsSoapBindingStub(new java.net.URL(TopTravels_address), this);
                _stub.setPortName(getTopTravelsWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TopTravels".equals(inputPortName)) {
            return getTopTravels();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://travel.etfbl.net", "TopTravelsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://travel.etfbl.net", "TopTravels"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TopTravels".equals(portName)) {
            setTopTravelsEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

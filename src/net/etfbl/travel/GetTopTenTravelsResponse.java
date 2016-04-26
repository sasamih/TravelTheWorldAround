/**
 * GetTopTenTravelsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.etfbl.travel;

public class GetTopTenTravelsResponse  implements java.io.Serializable {
    private java.lang.Object[] getTopTenTravelsReturn;

    public GetTopTenTravelsResponse() {
    }

    public GetTopTenTravelsResponse(
           java.lang.Object[] getTopTenTravelsReturn) {
           this.getTopTenTravelsReturn = getTopTenTravelsReturn;
    }


    /**
     * Gets the getTopTenTravelsReturn value for this GetTopTenTravelsResponse.
     * 
     * @return getTopTenTravelsReturn
     */
    public java.lang.Object[] getGetTopTenTravelsReturn() {
        return getTopTenTravelsReturn;
    }


    /**
     * Sets the getTopTenTravelsReturn value for this GetTopTenTravelsResponse.
     * 
     * @param getTopTenTravelsReturn
     */
    public void setGetTopTenTravelsReturn(java.lang.Object[] getTopTenTravelsReturn) {
        this.getTopTenTravelsReturn = getTopTenTravelsReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTopTenTravelsResponse)) return false;
        GetTopTenTravelsResponse other = (GetTopTenTravelsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getTopTenTravelsReturn==null && other.getGetTopTenTravelsReturn()==null) || 
             (this.getTopTenTravelsReturn!=null &&
              java.util.Arrays.equals(this.getTopTenTravelsReturn, other.getGetTopTenTravelsReturn())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetTopTenTravelsReturn() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetTopTenTravelsReturn());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGetTopTenTravelsReturn(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTopTenTravelsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://travel.etfbl.net", ">getTopTenTravelsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getTopTenTravelsReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "getTopTenTravelsReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://travel.etfbl.net", "item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

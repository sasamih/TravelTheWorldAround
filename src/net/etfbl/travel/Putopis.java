/**
 * Putopis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.etfbl.travel;

public class Putopis  implements java.io.Serializable {
    private java.lang.String datumObjavljivanja;

    private int idPutopisa;

    private net.etfbl.travel.Korisnik korisnik;

    private java.lang.String nazivPutopisa;

    private java.lang.String podaciOMjestu;

    private double prosjecnaOcjena;

    private java.lang.String putanja;

    private int status;

    private java.lang.String tekstPutopisa;

    public Putopis() {
    }

    public Putopis(
           java.lang.String datumObjavljivanja,
           int idPutopisa,
           net.etfbl.travel.Korisnik korisnik,
           java.lang.String nazivPutopisa,
           java.lang.String podaciOMjestu,
           double prosjecnaOcjena,
           java.lang.String putanja,
           int status,
           java.lang.String tekstPutopisa) {
           this.datumObjavljivanja = datumObjavljivanja;
           this.idPutopisa = idPutopisa;
           this.korisnik = korisnik;
           this.nazivPutopisa = nazivPutopisa;
           this.podaciOMjestu = podaciOMjestu;
           this.prosjecnaOcjena = prosjecnaOcjena;
           this.putanja = putanja;
           this.status = status;
           this.tekstPutopisa = tekstPutopisa;
    }


    /**
     * Gets the datumObjavljivanja value for this Putopis.
     * 
     * @return datumObjavljivanja
     */
    public java.lang.String getDatumObjavljivanja() {
        return datumObjavljivanja;
    }


    /**
     * Sets the datumObjavljivanja value for this Putopis.
     * 
     * @param datumObjavljivanja
     */
    public void setDatumObjavljivanja(java.lang.String datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }


    /**
     * Gets the idPutopisa value for this Putopis.
     * 
     * @return idPutopisa
     */
    public int getIdPutopisa() {
        return idPutopisa;
    }


    /**
     * Sets the idPutopisa value for this Putopis.
     * 
     * @param idPutopisa
     */
    public void setIdPutopisa(int idPutopisa) {
        this.idPutopisa = idPutopisa;
    }


    /**
     * Gets the korisnik value for this Putopis.
     * 
     * @return korisnik
     */
    public net.etfbl.travel.Korisnik getKorisnik() {
        return korisnik;
    }


    /**
     * Sets the korisnik value for this Putopis.
     * 
     * @param korisnik
     */
    public void setKorisnik(net.etfbl.travel.Korisnik korisnik) {
        this.korisnik = korisnik;
    }


    /**
     * Gets the nazivPutopisa value for this Putopis.
     * 
     * @return nazivPutopisa
     */
    public java.lang.String getNazivPutopisa() {
        return nazivPutopisa;
    }


    /**
     * Sets the nazivPutopisa value for this Putopis.
     * 
     * @param nazivPutopisa
     */
    public void setNazivPutopisa(java.lang.String nazivPutopisa) {
        this.nazivPutopisa = nazivPutopisa;
    }


    /**
     * Gets the podaciOMjestu value for this Putopis.
     * 
     * @return podaciOMjestu
     */
    public java.lang.String getPodaciOMjestu() {
        return podaciOMjestu;
    }


    /**
     * Sets the podaciOMjestu value for this Putopis.
     * 
     * @param podaciOMjestu
     */
    public void setPodaciOMjestu(java.lang.String podaciOMjestu) {
        this.podaciOMjestu = podaciOMjestu;
    }


    /**
     * Gets the prosjecnaOcjena value for this Putopis.
     * 
     * @return prosjecnaOcjena
     */
    public double getProsjecnaOcjena() {
        return prosjecnaOcjena;
    }


    /**
     * Sets the prosjecnaOcjena value for this Putopis.
     * 
     * @param prosjecnaOcjena
     */
    public void setProsjecnaOcjena(double prosjecnaOcjena) {
        this.prosjecnaOcjena = prosjecnaOcjena;
    }


    /**
     * Gets the putanja value for this Putopis.
     * 
     * @return putanja
     */
    public java.lang.String getPutanja() {
        return putanja;
    }


    /**
     * Sets the putanja value for this Putopis.
     * 
     * @param putanja
     */
    public void setPutanja(java.lang.String putanja) {
        this.putanja = putanja;
    }


    /**
     * Gets the status value for this Putopis.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Putopis.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the tekstPutopisa value for this Putopis.
     * 
     * @return tekstPutopisa
     */
    public java.lang.String getTekstPutopisa() {
        return tekstPutopisa;
    }


    /**
     * Sets the tekstPutopisa value for this Putopis.
     * 
     * @param tekstPutopisa
     */
    public void setTekstPutopisa(java.lang.String tekstPutopisa) {
        this.tekstPutopisa = tekstPutopisa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Putopis)) return false;
        Putopis other = (Putopis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datumObjavljivanja==null && other.getDatumObjavljivanja()==null) || 
             (this.datumObjavljivanja!=null &&
              this.datumObjavljivanja.equals(other.getDatumObjavljivanja()))) &&
            this.idPutopisa == other.getIdPutopisa() &&
            ((this.korisnik==null && other.getKorisnik()==null) || 
             (this.korisnik!=null &&
              this.korisnik.equals(other.getKorisnik()))) &&
            ((this.nazivPutopisa==null && other.getNazivPutopisa()==null) || 
             (this.nazivPutopisa!=null &&
              this.nazivPutopisa.equals(other.getNazivPutopisa()))) &&
            ((this.podaciOMjestu==null && other.getPodaciOMjestu()==null) || 
             (this.podaciOMjestu!=null &&
              this.podaciOMjestu.equals(other.getPodaciOMjestu()))) &&
            this.prosjecnaOcjena == other.getProsjecnaOcjena() &&
            ((this.putanja==null && other.getPutanja()==null) || 
             (this.putanja!=null &&
              this.putanja.equals(other.getPutanja()))) &&
            this.status == other.getStatus() &&
            ((this.tekstPutopisa==null && other.getTekstPutopisa()==null) || 
             (this.tekstPutopisa!=null &&
              this.tekstPutopisa.equals(other.getTekstPutopisa())));
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
        if (getDatumObjavljivanja() != null) {
            _hashCode += getDatumObjavljivanja().hashCode();
        }
        _hashCode += getIdPutopisa();
        if (getKorisnik() != null) {
            _hashCode += getKorisnik().hashCode();
        }
        if (getNazivPutopisa() != null) {
            _hashCode += getNazivPutopisa().hashCode();
        }
        if (getPodaciOMjestu() != null) {
            _hashCode += getPodaciOMjestu().hashCode();
        }
        _hashCode += new Double(getProsjecnaOcjena()).hashCode();
        if (getPutanja() != null) {
            _hashCode += getPutanja().hashCode();
        }
        _hashCode += getStatus();
        if (getTekstPutopisa() != null) {
            _hashCode += getTekstPutopisa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Putopis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://travel.etfbl.net", "Putopis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datumObjavljivanja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "datumObjavljivanja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPutopisa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "idPutopisa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("korisnik");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "korisnik"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://travel.etfbl.net", "Korisnik"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nazivPutopisa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "nazivPutopisa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("podaciOMjestu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "podaciOMjestu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prosjecnaOcjena");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "prosjecnaOcjena"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("putanja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "putanja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tekstPutopisa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "tekstPutopisa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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

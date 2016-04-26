/**
 * Korisnik.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.etfbl.travel;

public class Korisnik  implements java.io.Serializable {
    private java.lang.String datumRodjenja;

    private java.lang.String eMail;

    private java.lang.String ime;

    private java.lang.String korisnickaGrupa;

    private java.lang.String korisnickoIme;

    private java.lang.String kratkaBiografija;

    private java.lang.String lozinka;

    private java.lang.String prezime;

    private int status;

    public Korisnik() {
    }

    public Korisnik(
           java.lang.String datumRodjenja,
           java.lang.String eMail,
           java.lang.String ime,
           java.lang.String korisnickaGrupa,
           java.lang.String korisnickoIme,
           java.lang.String kratkaBiografija,
           java.lang.String lozinka,
           java.lang.String prezime,
           int status) {
           this.datumRodjenja = datumRodjenja;
           this.eMail = eMail;
           this.ime = ime;
           this.korisnickaGrupa = korisnickaGrupa;
           this.korisnickoIme = korisnickoIme;
           this.kratkaBiografija = kratkaBiografija;
           this.lozinka = lozinka;
           this.prezime = prezime;
           this.status = status;
    }


    /**
     * Gets the datumRodjenja value for this Korisnik.
     * 
     * @return datumRodjenja
     */
    public java.lang.String getDatumRodjenja() {
        return datumRodjenja;
    }


    /**
     * Sets the datumRodjenja value for this Korisnik.
     * 
     * @param datumRodjenja
     */
    public void setDatumRodjenja(java.lang.String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }


    /**
     * Gets the eMail value for this Korisnik.
     * 
     * @return eMail
     */
    public java.lang.String getEMail() {
        return eMail;
    }


    /**
     * Sets the eMail value for this Korisnik.
     * 
     * @param eMail
     */
    public void setEMail(java.lang.String eMail) {
        this.eMail = eMail;
    }


    /**
     * Gets the ime value for this Korisnik.
     * 
     * @return ime
     */
    public java.lang.String getIme() {
        return ime;
    }


    /**
     * Sets the ime value for this Korisnik.
     * 
     * @param ime
     */
    public void setIme(java.lang.String ime) {
        this.ime = ime;
    }


    /**
     * Gets the korisnickaGrupa value for this Korisnik.
     * 
     * @return korisnickaGrupa
     */
    public java.lang.String getKorisnickaGrupa() {
        return korisnickaGrupa;
    }


    /**
     * Sets the korisnickaGrupa value for this Korisnik.
     * 
     * @param korisnickaGrupa
     */
    public void setKorisnickaGrupa(java.lang.String korisnickaGrupa) {
        this.korisnickaGrupa = korisnickaGrupa;
    }


    /**
     * Gets the korisnickoIme value for this Korisnik.
     * 
     * @return korisnickoIme
     */
    public java.lang.String getKorisnickoIme() {
        return korisnickoIme;
    }


    /**
     * Sets the korisnickoIme value for this Korisnik.
     * 
     * @param korisnickoIme
     */
    public void setKorisnickoIme(java.lang.String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }


    /**
     * Gets the kratkaBiografija value for this Korisnik.
     * 
     * @return kratkaBiografija
     */
    public java.lang.String getKratkaBiografija() {
        return kratkaBiografija;
    }


    /**
     * Sets the kratkaBiografija value for this Korisnik.
     * 
     * @param kratkaBiografija
     */
    public void setKratkaBiografija(java.lang.String kratkaBiografija) {
        this.kratkaBiografija = kratkaBiografija;
    }


    /**
     * Gets the lozinka value for this Korisnik.
     * 
     * @return lozinka
     */
    public java.lang.String getLozinka() {
        return lozinka;
    }


    /**
     * Sets the lozinka value for this Korisnik.
     * 
     * @param lozinka
     */
    public void setLozinka(java.lang.String lozinka) {
        this.lozinka = lozinka;
    }


    /**
     * Gets the prezime value for this Korisnik.
     * 
     * @return prezime
     */
    public java.lang.String getPrezime() {
        return prezime;
    }


    /**
     * Sets the prezime value for this Korisnik.
     * 
     * @param prezime
     */
    public void setPrezime(java.lang.String prezime) {
        this.prezime = prezime;
    }


    /**
     * Gets the status value for this Korisnik.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Korisnik.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Korisnik)) return false;
        Korisnik other = (Korisnik) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datumRodjenja==null && other.getDatumRodjenja()==null) || 
             (this.datumRodjenja!=null &&
              this.datumRodjenja.equals(other.getDatumRodjenja()))) &&
            ((this.eMail==null && other.getEMail()==null) || 
             (this.eMail!=null &&
              this.eMail.equals(other.getEMail()))) &&
            ((this.ime==null && other.getIme()==null) || 
             (this.ime!=null &&
              this.ime.equals(other.getIme()))) &&
            ((this.korisnickaGrupa==null && other.getKorisnickaGrupa()==null) || 
             (this.korisnickaGrupa!=null &&
              this.korisnickaGrupa.equals(other.getKorisnickaGrupa()))) &&
            ((this.korisnickoIme==null && other.getKorisnickoIme()==null) || 
             (this.korisnickoIme!=null &&
              this.korisnickoIme.equals(other.getKorisnickoIme()))) &&
            ((this.kratkaBiografija==null && other.getKratkaBiografija()==null) || 
             (this.kratkaBiografija!=null &&
              this.kratkaBiografija.equals(other.getKratkaBiografija()))) &&
            ((this.lozinka==null && other.getLozinka()==null) || 
             (this.lozinka!=null &&
              this.lozinka.equals(other.getLozinka()))) &&
            ((this.prezime==null && other.getPrezime()==null) || 
             (this.prezime!=null &&
              this.prezime.equals(other.getPrezime()))) &&
            this.status == other.getStatus();
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
        if (getDatumRodjenja() != null) {
            _hashCode += getDatumRodjenja().hashCode();
        }
        if (getEMail() != null) {
            _hashCode += getEMail().hashCode();
        }
        if (getIme() != null) {
            _hashCode += getIme().hashCode();
        }
        if (getKorisnickaGrupa() != null) {
            _hashCode += getKorisnickaGrupa().hashCode();
        }
        if (getKorisnickoIme() != null) {
            _hashCode += getKorisnickoIme().hashCode();
        }
        if (getKratkaBiografija() != null) {
            _hashCode += getKratkaBiografija().hashCode();
        }
        if (getLozinka() != null) {
            _hashCode += getLozinka().hashCode();
        }
        if (getPrezime() != null) {
            _hashCode += getPrezime().hashCode();
        }
        _hashCode += getStatus();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Korisnik.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://travel.etfbl.net", "Korisnik"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datumRodjenja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "datumRodjenja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "eMail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "ime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("korisnickaGrupa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "korisnickaGrupa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("korisnickoIme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "korisnickoIme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kratkaBiografija");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "kratkaBiografija"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lozinka");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "lozinka"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prezime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "prezime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://travel.etfbl.net", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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

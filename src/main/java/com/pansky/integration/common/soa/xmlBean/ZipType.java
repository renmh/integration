//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.16 at 04:19:38 PM CST 
//


package com.pansky.integration.common.soa.xmlBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zipType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zipType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isZip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="zipType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zipType", propOrder = {
    "isZip",
    "zipType"
})
public class ZipType {

    protected Boolean isZip;
    protected String zipType;

    /**
     * Gets the value of the isZip property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsZip() {
        return isZip;
    }

    /**
     * Sets the value of the isZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsZip(Boolean value) {
        this.isZip = value;
    }

    /**
     * Gets the value of the zipType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipType() {
        return zipType;
    }

    /**
     * Sets the value of the zipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipType(String value) {
        this.zipType = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.23 at 09:46:55 AM CST 
//


package com.pansky.integration.common.soa.xmlBean.bussbusinessContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 网上业务通用请求报文请求信息
 * 
 * <p>Java class for wsywTyReqItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsywTyReqItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zsjgDm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nsrsbh" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xzqh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsywTyReqItem", propOrder = {
    "zsjgDm",
    "nsrsbh",
    "xzqh"
})
public class WsywTyReqItem {

    protected String zsjgDm;
    @XmlElement(required = true)
    protected String nsrsbh;
    protected String xzqh;

    /**
     * Gets the value of the zsjgDm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZsjgDm() {
        return zsjgDm;
    }

    /**
     * Sets the value of the zsjgDm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZsjgDm(String value) {
        this.zsjgDm = value;
    }

    /**
     * Gets the value of the nsrsbh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNsrsbh() {
        return nsrsbh;
    }

    /**
     * Sets the value of the nsrsbh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNsrsbh(String value) {
        this.nsrsbh = value;
    }

    /**
     * Gets the value of the xzqh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXzqh() {
        return xzqh;
    }

    /**
     * Sets the value of the xzqh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXzqh(String value) {
        this.xzqh = value;
    }

}

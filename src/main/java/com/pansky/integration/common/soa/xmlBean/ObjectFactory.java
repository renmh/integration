//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.16 at 04:19:38 PM CST 
//


package com.pansky.integration.common.soa.xmlBean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.pansky.integration.common.soa.xmlBean package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TiripPackage_QNAME = new QName("http://www.chinatax.gov.cn/tirip/dataspec", "tiripPackage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.pansky.integration.common.soa.xmlBean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TiripPackageType }
     * 
     */
    public TiripPackageType createTiripPackageType() {
        return new TiripPackageType();
    }

    /**
     * Create an instance of {@link SubPackageType }
     * 
     */
    public SubPackageType createSubPackageType() {
        return new SubPackageType();
    }

    /**
     * Create an instance of {@link ZipType }
     * 
     */
    public ZipType createZipType() {
        return new ZipType();
    }

    /**
     * Create an instance of {@link IdentityType }
     * 
     */
    public IdentityType createIdentityType() {
        return new IdentityType();
    }

    /**
     * Create an instance of {@link RouterSessionType }
     * 
     */
    public RouterSessionType createRouterSessionType() {
        return new RouterSessionType();
    }

    /**
     * Create an instance of {@link ReturnStateType }
     * 
     */
    public ReturnStateType createReturnStateType() {
        return new ReturnStateType();
    }

    /**
     * Create an instance of {@link ParamListType }
     * 
     */
    public ParamListType createParamListType() {
        return new ParamListType();
    }

    /**
     * Create an instance of {@link CodeType }
     * 
     */
    public CodeType createCodeType() {
        return new CodeType();
    }

    /**
     * Create an instance of {@link EncryptType }
     * 
     */
    public EncryptType createEncryptType() {
        return new EncryptType();
    }

    /**
     * Create an instance of {@link ContentControlType }
     * 
     */
    public ContentControlType createContentControlType() {
        return new ContentControlType();
    }

    /**
     * Create an instance of {@link BusinessContentType }
     * 
     */
    public BusinessContentType createBusinessContentType() {
        return new BusinessContentType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TiripPackageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.chinatax.gov.cn/tirip/dataspec", name = "tiripPackage")
    public JAXBElement<TiripPackageType> createTiripPackage(TiripPackageType value) {
        return new JAXBElement<TiripPackageType>(_TiripPackage_QNAME, TiripPackageType.class, null, value);
    }

}


package com.zc.bsm.webgetservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.zc.bsm.webservice package. 
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

    private final static QName _Zhangshaofangfa_QNAME = new QName("http://webService.bsm.zc.com", "zhangshaofangfa");
    private final static QName _ZhangshaofangfaResponse_QNAME = new QName("http://webService.bsm.zc.com", "zhangshaofangfaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.zc.bsm.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Zhangshaofangfa }
     * 
     */
    public Zhangshaofangfa createZhangshaofangfa() {
        return new Zhangshaofangfa();
    }

    /**
     * Create an instance of {@link ZhangshaofangfaResponse }
     * 
     */
    public ZhangshaofangfaResponse createZhangshaofangfaResponse() {
        return new ZhangshaofangfaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Zhangshaofangfa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.bsm.zc.com", name = "zhangshaofangfa")
    public JAXBElement<Zhangshaofangfa> createZhangshaofangfa(Zhangshaofangfa value) {
        return new JAXBElement<Zhangshaofangfa>(_Zhangshaofangfa_QNAME, Zhangshaofangfa.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZhangshaofangfaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.bsm.zc.com", name = "zhangshaofangfaResponse")
    public JAXBElement<ZhangshaofangfaResponse> createZhangshaofangfaResponse(ZhangshaofangfaResponse value) {
        return new JAXBElement<ZhangshaofangfaResponse>(_ZhangshaofangfaResponse_QNAME, ZhangshaofangfaResponse.class, null, value);
    }

}

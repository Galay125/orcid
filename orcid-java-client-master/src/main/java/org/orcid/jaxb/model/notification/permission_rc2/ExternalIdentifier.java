/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.24 at 04:27:39 PM GMT 
//

package org.orcid.jaxb.model.notification.permission_rc2;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.orcid.org/ns/activities}external-identifier-type"/>
 *         &lt;element ref="{http://www.orcid.org/ns/activities}external-identifier-id"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "externalIdentifierType", "externalIdentifierId" })
@XmlRootElement(name = "external-id")
public class ExternalIdentifier implements Serializable {

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "external-identifier-type", namespace = "http://www.orcid.org/ns/activities", required = true)
    protected String externalIdentifierType;
    @XmlElement(name = "external-identifier-id", namespace = "http://www.orcid.org/ns/activities", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String externalIdentifierId;

    /**
     * Gets the value of the externalIdType property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getExternalIdentifierType() {
        return externalIdentifierType;
    }

    /**
     * Sets the value of the externalIdType property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setExternalIdentifierType(String value) {
        this.externalIdentifierType = value;
    }

    /**
     * Gets the value of the externalIdValue property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getExternalIdentifierId() {
        return externalIdentifierId;
    }

    /**
     * Sets the value of the externalIdValue property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setExternalIdentifierId(String value) {
        this.externalIdentifierId = value;
    }

}

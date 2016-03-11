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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.09 at 01:52:56 PM BST 
//

package org.orcid.jaxb.model.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}scope"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "fundings" })
@XmlRootElement(name = "funding-list")
public class FundingList implements ActivitiesContainer, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "funding")
    protected List<Funding> fundings;
    @XmlAttribute
    protected Scope scope;

    /**
     * Gets the value of the Fundings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the Fundings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getFundings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Funding }
     * 
     * 
     */
    public List<Funding> getFundings() {
        if (fundings == null) {
            fundings = new ArrayList<Funding>();
        }
        return this.fundings;
    }

    public void setFundings(List<Funding> fundings) {
        this.fundings = fundings;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return possible object is {@link Scope }
     * 
     */
    public Scope getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *            allowed object is {@link Scope }
     * 
     */
    public void setScope(Scope value) {
        this.scope = value;
    }

    @Override
    public Map<String, ? extends Activity> retrieveActivitiesAsMap() {
        Map<String, Funding> map = new HashMap<String, Funding>();
        if (fundings != null) {
            for (Funding f : fundings) {
                if (StringUtils.isNotBlank(f.putCode)) {
                    map.put(f.putCode, f);
                }
            }
        }
        return map;
    }

    @Override
    public Collection<? extends Activity> retrieveActivities() {
        return getFundings();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundingList)) {
            return false;
        }

        FundingList that = (FundingList) o;

        if (fundings != null ? !fundings.equals(that.fundings) : that.fundings != null) {
            return false;
        }
        if (scope != that.scope) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = fundings != null ? fundings.hashCode() : 0;
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        return result;
    }

}

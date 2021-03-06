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
package org.orcid.jaxb.model.record_rc2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.orcid.jaxb.model.common_rc2.LastModifiedDate;

/**
 * 
 * @author Angel Montenegro
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "lastModifiedDate", "keywords" })
@XmlRootElement(name = "keywords", namespace = "http://www.orcid.org/ns/keyword")
public class Keywords implements Serializable {
    private static final long serialVersionUID = 8977681069375479763L;

    @XmlElement(namespace = "http://www.orcid.org/ns/common", name = "last-modified-date")
    protected LastModifiedDate lastModifiedDate;
    @XmlElement(name = "keyword", namespace = "http://www.orcid.org/ns/keyword")
    List<Keyword> keywords;

    @XmlAttribute
    protected String path;

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Keywords other = (Keywords) obj;
        if (keywords == null) {
            if (other.keywords != null)
                return false;
        } else if (!keywords.equals(other.keywords))
            return false;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        return true;
    }

    public void updateIndexingStatusOnChilds() {
        if (this.getKeywords() != null && !this.getKeywords().isEmpty()) {
            List<Keyword> sorted = new ArrayList<Keyword>();
            List<Keyword> unsorted = new ArrayList<Keyword>();
            Long maxDisplayIndex = 0L;
            for(Keyword k : this.getKeywords()) {
                if(k.getDisplayIndex() == null || Long.valueOf(-1).equals(k.getDisplayIndex())) {
                    unsorted.add(k);
                } else {
                    if(k.getDisplayIndex() > maxDisplayIndex) {
                        maxDisplayIndex = k.getDisplayIndex();
                    }
                    sorted.add(k);
                }                
            }      
            
            if(!unsorted.isEmpty()) {
                Collections.sort(unsorted);
                for(Keyword k : unsorted) {
                    k.setDisplayIndex((maxDisplayIndex++) + 1);
                    sorted.add(k);
                }
            }
        }
    }
    
	public LastModifiedDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LastModifiedDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.02 at 09:35:21 AM CET 
//


package uk.bl.odin.orcid.schema.messages.onepointtwo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}given-permission-to" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}given-permission-by" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}visibility"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "givenPermissionTo",
    "givenPermissionBy"
})
@XmlRootElement(name = "delegation")
public class Delegation {

    @XmlElement(name = "given-permission-to")
    protected GivenPermissionTo givenPermissionTo;
    @XmlElement(name = "given-permission-by")
    protected GivenPermissionBy givenPermissionBy;
    @XmlAttribute(name = "visibility")
    protected Visibility visibility;

    /**
     * Gets the value of the givenPermissionTo property.
     * 
     * @return
     *     possible object is
     *     {@link GivenPermissionTo }
     *     
     */
    public GivenPermissionTo getGivenPermissionTo() {
        return givenPermissionTo;
    }

    /**
     * Sets the value of the givenPermissionTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivenPermissionTo }
     *     
     */
    public void setGivenPermissionTo(GivenPermissionTo value) {
        this.givenPermissionTo = value;
    }

    /**
     * Gets the value of the givenPermissionBy property.
     * 
     * @return
     *     possible object is
     *     {@link GivenPermissionBy }
     *     
     */
    public GivenPermissionBy getGivenPermissionBy() {
        return givenPermissionBy;
    }

    /**
     * Sets the value of the givenPermissionBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivenPermissionBy }
     *     
     */
    public void setGivenPermissionBy(GivenPermissionBy value) {
        this.givenPermissionBy = value;
    }

    /**
     * Gets the value of the visibility property.
     * 
     * @return
     *     possible object is
     *     {@link Visibility }
     *     
     */
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * Sets the value of the visibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link Visibility }
     *     
     */
    public void setVisibility(Visibility value) {
        this.visibility = value;
    }

}

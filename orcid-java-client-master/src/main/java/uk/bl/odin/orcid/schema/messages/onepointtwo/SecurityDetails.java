//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.02 at 09:35:21 AM CET 
//


package uk.bl.odin.orcid.schema.messages.onepointtwo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}encrypted-password"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}security-question-id" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}encrypted-security-answer" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}encrypted-verification-code"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "encryptedPassword",
    "securityQuestionId",
    "encryptedSecurityAnswer",
    "encryptedVerificationCode"
})
@XmlRootElement(name = "security-details")
public class SecurityDetails {

    @XmlElement(name = "encrypted-password", required = true)
    protected EncryptedPassword encryptedPassword;
    @XmlElement(name = "security-question-id")
    protected SecurityQuestionId securityQuestionId;
    @XmlElement(name = "encrypted-security-answer")
    protected EncryptedSecurityAnswer encryptedSecurityAnswer;
    @XmlElement(name = "encrypted-verification-code", required = true)
    protected EncryptedVerificationCode encryptedVerificationCode;

    /**
     * Gets the value of the encryptedPassword property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedPassword }
     *     
     */
    public EncryptedPassword getEncryptedPassword() {
        return encryptedPassword;
    }

    /**
     * Sets the value of the encryptedPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedPassword }
     *     
     */
    public void setEncryptedPassword(EncryptedPassword value) {
        this.encryptedPassword = value;
    }

    /**
     * Gets the value of the securityQuestionId property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityQuestionId }
     *     
     */
    public SecurityQuestionId getSecurityQuestionId() {
        return securityQuestionId;
    }

    /**
     * Sets the value of the securityQuestionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityQuestionId }
     *     
     */
    public void setSecurityQuestionId(SecurityQuestionId value) {
        this.securityQuestionId = value;
    }

    /**
     * Gets the value of the encryptedSecurityAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedSecurityAnswer }
     *     
     */
    public EncryptedSecurityAnswer getEncryptedSecurityAnswer() {
        return encryptedSecurityAnswer;
    }

    /**
     * Sets the value of the encryptedSecurityAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedSecurityAnswer }
     *     
     */
    public void setEncryptedSecurityAnswer(EncryptedSecurityAnswer value) {
        this.encryptedSecurityAnswer = value;
    }

    /**
     * Gets the value of the encryptedVerificationCode property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedVerificationCode }
     *     
     */
    public EncryptedVerificationCode getEncryptedVerificationCode() {
        return encryptedVerificationCode;
    }

    /**
     * Sets the value of the encryptedVerificationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedVerificationCode }
     *     
     */
    public void setEncryptedVerificationCode(EncryptedVerificationCode value) {
        this.encryptedVerificationCode = value;
    }

}

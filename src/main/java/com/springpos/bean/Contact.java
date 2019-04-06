package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    @ManyToOne
    @JoinColumn(name = "custId", nullable = false)
    private CustomerSite customerSite;
    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private ContactStatus contactStatus;
    @ManyToOne
    @JoinColumn(name = "typeId", nullable = false)
    private ContactType contactType;
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
    @Column(name = "contact_fname")
    private String contact_fname;
    @Column(name = "contact_lname")
    private String contact_lname;
    @Column(name = "contact_phone")
    private String contact_phone;
    @Column(name = "contact_email")
    private String contact_email;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public CustomerSite getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSite customerSite) {
        this.customerSite = customerSite;
    }

    public ContactStatus getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(ContactStatus contactStatus) {
        this.contactStatus = contactStatus;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContact_fname() {
        return contact_fname;
    }

    public void setContact_fname(String contact_fname) {
        this.contact_fname = contact_fname;
    }

    public String getContact_lname() {
        return contact_lname;
    }

    public void setContact_lname(String contact_lname) {
        this.contact_lname = contact_lname;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

 

}

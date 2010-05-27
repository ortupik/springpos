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
import javax.persistence.Transient;

@Entity
@Table(name = "CUSTOMER_SITE")
public class CustomerSite implements Serializable {

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
    @Id
    @Column(name = "cust_site_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cust_site_name")
    private String custSiteName;
    @Column(name = "cust_site_address")
    private String cust_site_address;
    @Column(name = "cust_site_city")
    private String cust_site_city;
    @Column(name = "cust_site_zip")
    private String cust_site_zip;
    @Column(name = "cust_site_phone")
    private String cust_site_phone;
    @Column(name = "cust_site_email")
    private String cust_site_email;
    @Column(name = "cust_site_status_id")
    private int cust_site_status_id;
    @Column(name = "cust_site_type_id")
    private int cust_site_type_id;
    @Column(name = "country_id")
    private int country_id;
    @Transient
    private String storeId;
    @Transient
    private String contactName;
    @Transient
    private String contactEmail;
    @Transient
    private String contactPhone;

    public CustomerSite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustSiteName() {
        return custSiteName;
    }

    public void setCustSiteName(String custSiteName) {
        this.custSiteName = custSiteName;
    }

    public String getCust_site_address() {
        return cust_site_address;
    }

    public void setCust_site_address(String cust_site_address) {
        this.cust_site_address = cust_site_address;
    }

    public String getCust_site_city() {
        return cust_site_city;
    }

    public void setCust_site_city(String cust_site_city) {
        this.cust_site_city = cust_site_city;
    }

    public String getCust_site_zip() {
        return cust_site_zip;
    }

    public void setCust_site_zip(String cust_site_zip) {
        this.cust_site_zip = cust_site_zip;
    }

    public String getCust_site_phone() {
        return cust_site_phone;
    }

    public void setCust_site_phone(String cust_site_phone) {
        this.cust_site_phone = cust_site_phone;
    }

    public String getCust_site_email() {
        return cust_site_email;
    }

    public void setCust_site_email(String cust_site_email) {
        this.cust_site_email = cust_site_email;
    }

    public int getCust_site_status_id() {
        return cust_site_status_id;
    }

    public void setCust_site_status_id(int cust_site_status_id) {
        this.cust_site_status_id = cust_site_status_id;
    }

    public int getCust_site_type_id() {
        return cust_site_type_id;
    }

    public void setCust_site_type_id(int cust_site_type_id) {
        this.cust_site_type_id = cust_site_type_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}

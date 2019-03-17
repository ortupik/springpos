package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "contact_fname")
    private String contact_fname;
    @Column(name = "contact_lname")
    private String contact_lname;
    @Column(name = "contact_phone")
    private String contact_phone;
    @Column(name = "contact_email")
    private String contact_email;
    @Column(name = "contact_status_id")
    private int contact_status_id;
    @Column(name = "contact_type_id")
    private int contact_type_id;
    @Column(name = "cust_site_id")
    private int cust_site_id;

    public Contact() {
    }

    public Contact(CustomerSite cust) {
        this.contact_fname = cust.getContactName();
        this.contact_lname = "";
        this.contact_phone = cust.getContactPhone();
        this.contact_email = cust.getContactEmail();
        this.contact_status_id = 1;
        this.contact_type_id = 1;
        this.cust_site_id = cust.getCustId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getContact_status_id() {
        return contact_status_id;
    }

    public void setContact_status_id(int contact_status_id) {
        this.contact_status_id = contact_status_id;
    }

    public int getContact_type_id() {
        return contact_type_id;
    }

    public void setContact_type_id(int contact_type_id) {
        this.contact_type_id = contact_type_id;
    }

    public int getCust_site_id() {
        return cust_site_id;
    }

    public void setCust_site_id(int cust_site_id) {
        this.cust_site_id = cust_site_id;
    }

}

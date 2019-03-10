package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    @Transient
    private String city;
    @Transient
    private String zip;
    @Transient
    private int state;
    @Transient
    private int country;
    @Transient
    private String status;
    @Transient
    private String type;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", contact_fname=" + contact_fname + ", contact_lname=" + contact_lname + ", contact_phone=" + contact_phone + ", contact_email=" + contact_email + ", contact_status_id=" + contact_status_id + ", contact_type_id=" + contact_type_id + ", cust_site_id=" + cust_site_id + '}';
    }

}

package com.springpos.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRACTOR")
public class Contractor implements Serializable {

    @Id
    @Column(name = "contractor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "contact_type")
    private String contact_type;
    @Column(name = "contractor_fname")
    private String contractor_fname;
    @Column(name = "contractor_lname")
    private String contractor_lname;
    @Column(name = "contractor_address")
    private String contractor_address;
    @Column(name = "contractor_city")
    private String contractor_city;
    @Column(name = "contractor_zip")
    private String contractor_zip;
    @Column(name = "contractor_phone")
    private String contractor_phone;
    @Column(name = "contractor_hire_date")
    private Date contractor_hire_date;
    @Column(name = "contractor_email")
    private String contractor_email;
    @Column(name = "contractor_username")
    private String contractor_username;
    @Column(name = "contractor_password")
    private String contractor_password;
    @Column(name = "contractor_availability")
    private String contractor_availability;
    @Column(name = "contractor_status_id")
    private int contractor_status_id;
    @Column(name = "country_id")
    private String country_id;
    @Column(name = "state_id")
    private int state_id;
    @Column(name = "contractor_type_id")
    private int contractor_type_id;
    @Column(name = "acc_level_id")
    private int acc_level_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    public String getContractor_fname() {
        return contractor_fname;
    }

    public void setContractor_fname(String contractor_fname) {
        this.contractor_fname = contractor_fname;
    }

    public String getContractor_lname() {
        return contractor_lname;
    }

    public void setContractor_lname(String contractor_lname) {
        this.contractor_lname = contractor_lname;
    }

    public String getContractor_address() {
        return contractor_address;
    }

    public void setContractor_address(String contractor_address) {
        this.contractor_address = contractor_address;
    }

    public String getContractor_city() {
        return contractor_city;
    }

    public void setContractor_city(String contractor_city) {
        this.contractor_city = contractor_city;
    }

    public String getContractor_zip() {
        return contractor_zip;
    }

    public void setContractor_zip(String contractor_zip) {
        this.contractor_zip = contractor_zip;
    }

    public String getContractor_phone() {
        return contractor_phone;
    }

    public void setContractor_phone(String contractor_phone) {
        this.contractor_phone = contractor_phone;
    }

    public Date getContractor_hire_date() {
        return contractor_hire_date;
    }

    public void setContractor_hire_date(Date contractor_hire_date) {
        this.contractor_hire_date = contractor_hire_date;
    }

    public String getContractor_email() {
        return contractor_email;
    }

    public void setContractor_email(String contractor_email) {
        this.contractor_email = contractor_email;
    }

    public String getContractor_username() {
        return contractor_username;
    }

    public void setContractor_username(String contractor_username) {
        this.contractor_username = contractor_username;
    }

    public String getContractor_password() {
        return contractor_password;
    }

    public void setContractor_password(String contractor_password) {
        this.contractor_password = contractor_password;
    }

    public String getContractor_availability() {
        return contractor_availability;
    }

    public void setContractor_availability(String contractor_availability) {
        this.contractor_availability = contractor_availability;
    }

    public int getContractor_status_id() {
        return contractor_status_id;
    }

    public void setContractor_status_id(int contractor_status_id) {
        this.contractor_status_id = contractor_status_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getContractor_type_id() {
        return contractor_type_id;
    }

    public void setContractor_type_id(int contractor_type_id) {
        this.contractor_type_id = contractor_type_id;
    }

    public int getAcc_level_id() {
        return acc_level_id;
    }

    public void setAcc_level_id(int acc_level_id) {
        this.acc_level_id = acc_level_id;
    }

    @Override
    public String toString() {
        return "Contractor{" + "id=" + id + ", contact_type=" + contact_type + ", contractor_fname=" + contractor_fname + ", contractor_lname=" + contractor_lname + ", contractor_address=" + contractor_address + ", contractor_city=" + contractor_city + ", contractor_zip=" + contractor_zip + ", contractor_phone=" + contractor_phone + ", contractor_hire_date=" + contractor_hire_date + ", contractor_email=" + contractor_email + ", contractor_username=" + contractor_username + ", contractor_password=" + contractor_password + ", contractor_availability=" + contractor_availability + ", contractor_status_id=" + contractor_status_id + ", country_id=" + country_id + ", state_id=" + state_id + ", contractor_type_id=" + contractor_type_id + ", acc_level_id=" + acc_level_id + '}';
    }

}

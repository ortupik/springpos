package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_PROVIDER")
public class HwProvider implements Serializable {

    @Id
    @Column(name = "hw_provider_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_provider_name")
    private String hw_provider_name;
    @Column(name = "hw_provider_address")
    private String hw_provider_address;
    @Column(name = "hw_provider_city")
    private String hw_provider_city;
    @Column(name = "hw_provider_phone")
    private String hw_provider_phone;
    @Column(name = "hw_provider_email")
    private String hw_provider_email;
    @Column(name = "hw_provider_web")
    private String hw_provider_web;
    @Column(name = "hw_provider_status_id")
    private int hw_provider_status_id;
    @Column(name = "country_id")
    private int country_id;
    @Column(name = "state_id")
    private int state_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_provider_name() {
        return hw_provider_name;
    }

    public void setHw_provider_name(String hw_provider_name) {
        this.hw_provider_name = hw_provider_name;
    }

    public String getHw_provider_address() {
        return hw_provider_address;
    }

    public void setHw_provider_address(String hw_provider_address) {
        this.hw_provider_address = hw_provider_address;
    }

    public String getHw_provider_city() {
        return hw_provider_city;
    }

    public void setHw_provider_city(String hw_provider_city) {
        this.hw_provider_city = hw_provider_city;
    }

    public String getHw_provider_phone() {
        return hw_provider_phone;
    }

    public void setHw_provider_phone(String hw_provider_phone) {
        this.hw_provider_phone = hw_provider_phone;
    }

    public String getHw_provider_email() {
        return hw_provider_email;
    }

    public void setHw_provider_email(String hw_provider_email) {
        this.hw_provider_email = hw_provider_email;
    }

    public String getHw_provider_web() {
        return hw_provider_web;
    }

    public void setHw_provider_web(String hw_provider_web) {
        this.hw_provider_web = hw_provider_web;
    }

    public int getHw_provider_status_id() {
        return hw_provider_status_id;
    }

    public void setHw_provider_status_id(int hw_provider_status_id) {
        this.hw_provider_status_id = hw_provider_status_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

}

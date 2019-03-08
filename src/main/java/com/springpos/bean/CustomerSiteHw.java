package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_SITE_HW")
public class CustomerSiteHw implements Serializable {

    @Id
    @Column(name = "cust_site_hw_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cust_site_serial_number")
    private String cust_site_serial_number;
    @Column(name = "cust_site_mac_address")
    private String cust_site_mac_address;
    @Column(name = "cust_site_id")
    private int cust_site_id;
    @Column(name = "cust_site_hw_status_id")
    private int cust_site_hw_status_id;
    @Column(name = "hw_id")
    private int hw_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCust_site_serial_number() {
        return cust_site_serial_number;
    }

    public void setCust_site_serial_number(String cust_site_serial_number) {
        this.cust_site_serial_number = cust_site_serial_number;
    }

    public String getCust_site_mac_address() {
        return cust_site_mac_address;
    }

    public void setCust_site_mac_address(String cust_site_mac_address) {
        this.cust_site_mac_address = cust_site_mac_address;
    }

    public int getCust_site_id() {
        return cust_site_id;
    }

    public void setCust_site_id(int cust_site_id) {
        this.cust_site_id = cust_site_id;
    }

    public int getCust_site_hw_status_id() {
        return cust_site_hw_status_id;
    }

    public void setCust_site_hw_status_id(int cust_site_hw_status_id) {
        this.cust_site_hw_status_id = cust_site_hw_status_id;
    }

    public int getHw_id() {
        return hw_id;
    }

    public void setHw_id(int hw_id) {
        this.hw_id = hw_id;
    }

    @Override
    public String toString() {
        return "CustomerSiteHw{" + "id=" + id + ", cust_site_serial_number=" + cust_site_serial_number + ", cust_site_mac_address=" + cust_site_mac_address + ", cust_site_id=" + cust_site_id + ", cust_site_hw_status_id=" + cust_site_hw_status_id + ", hw_id=" + hw_id + '}';
    }

}

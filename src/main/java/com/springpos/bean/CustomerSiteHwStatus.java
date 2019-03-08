package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_SITE_STATUS")
public class CustomerSiteHwStatus implements Serializable {

    @Id
    @Column(name = "cust_site_hw_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cust_site_hw_status")
    private String cust_site_hw_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCust_site_hw_status() {
        return cust_site_hw_status;
    }

    public void setCust_site_hw_status(String cust_site_hw_status) {
        this.cust_site_hw_status = cust_site_hw_status;
    }

    @Override
    public String toString() {
        return "CustomerSiteHwStatus{" + "id=" + id + ", cust_site_hw_status=" + cust_site_hw_status + '}';
    }

}

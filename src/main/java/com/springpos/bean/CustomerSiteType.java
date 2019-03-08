package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT_STATUS")
public class CustomerSiteType implements Serializable {

    @Id
    @Column(name = "cust_site_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cust_site_type")
    private String cust_site_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCust_site_type() {
        return cust_site_type;
    }

    public void setCust_site_type(String cust_site_type) {
        this.cust_site_type = cust_site_type;
    }

    @Override
    public String toString() {
        return "CustomerSiteType{" + "id=" + id + ", cust_site_type=" + cust_site_type + '}';
    }

}

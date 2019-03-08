package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIAL_MEDIA_ACCOUNT")
public class SocialMediaAccount implements Serializable {

    @Id
    @Column(name = "sm_acct_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sm_url")
    private String sm_url;
    @Column(name = "sm_acct_status_id")
    private int sm_acct_status_id;
    @Column(name = "sm_acct_type_id")
    private int sm_acct_type_id;
    @Column(name = "cust_site_id")
    private int cust_site_id;
    @Column(name = "contractor_id")
    private int contractor_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSm_url() {
        return sm_url;
    }

    public void setSm_url(String sm_url) {
        this.sm_url = sm_url;
    }

    public int getSm_acct_status_id() {
        return sm_acct_status_id;
    }

    public void setSm_acct_status_id(int sm_acct_status_id) {
        this.sm_acct_status_id = sm_acct_status_id;
    }

    public int getSm_acct_type_id() {
        return sm_acct_type_id;
    }

    public void setSm_acct_type_id(int sm_acct_type_id) {
        this.sm_acct_type_id = sm_acct_type_id;
    }

    public int getCust_site_id() {
        return cust_site_id;
    }

    public void setCust_site_id(int cust_site_id) {
        this.cust_site_id = cust_site_id;
    }

    public int getContractor_id() {
        return contractor_id;
    }

    public void setContractor_id(int contractor_id) {
        this.contractor_id = contractor_id;
    }

    @Override
    public String toString() {
        return "SocialMediaAccount{" + "id=" + id + ", sm_url=" + sm_url + ", sm_acct_status_id=" + sm_acct_status_id + ", sm_acct_type_id=" + sm_acct_type_id + ", cust_site_id=" + cust_site_id + ", contractor_id=" + contractor_id + '}';
    }

}

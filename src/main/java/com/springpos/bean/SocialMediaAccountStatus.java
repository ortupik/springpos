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
public class SocialMediaAccountStatus implements Serializable {

    @Id
    @Column(name = "sm_acct_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sm_acct_status")
    private String sm_acct_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSm_acct_status() {
        return sm_acct_status;
    }

    public void setSm_acct_status(String sm_acct_status) {
        this.sm_acct_status = sm_acct_status;
    }

    @Override
    public String toString() {
        return "SocialMediaAccountStatus{" + "id=" + id + ", sm_acct_status=" + sm_acct_status + '}';
    }

}

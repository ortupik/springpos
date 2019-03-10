package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIAL_MEDIA_ACCOUNT_TYPE")
public class SocialMediaAccountType implements Serializable {

    @Id
    @Column(name = "sm_acct_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sm_acct_type")
    private String sm_acct_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSm_acct_type() {
        return sm_acct_type;
    }

    public void setSm_acct_type(String sm_acct_type) {
        this.sm_acct_type = sm_acct_type;
    }

    @Override
    public String toString() {
        return "SocialMediaAccountType{" + "id=" + id + ", sm_acct_type=" + sm_acct_type + '}';
    }

}

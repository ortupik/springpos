package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASSIGNMENT")
public class Assignment implements Serializable {

    @Id
    @Column(name = "asgmt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "asgmt_date")
    private String asgmt_date;
    @Column(name = "svo_id")
    private String svo_id;
    @Column(name = "contractor_id")
    private String contractor_id;
    @Column(name = "asgmt_status_id")
    private String asgmt_status_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsgmt_date() {
        return asgmt_date;
    }

    public void setAsgmt_date(String asgmt_date) {
        this.asgmt_date = asgmt_date;
    }

    public String getSvo_id() {
        return svo_id;
    }

    public void setSvo_id(String svo_id) {
        this.svo_id = svo_id;
    }

    public String getContractor_id() {
        return contractor_id;
    }

    public void setContractor_id(String contractor_id) {
        this.contractor_id = contractor_id;
    }

    public String getAsgmt_status_id() {
        return asgmt_status_id;
    }

    public void setAsgmt_status_id(String asgmt_status_id) {
        this.asgmt_status_id = asgmt_status_id;
    }

    @Override
    public String toString() {
        return "Assignment{" + "id=" + id + ", asgmt_date=" + asgmt_date + ", svo_id=" + svo_id + ", contractor_id=" + contractor_id + ", asgmt_status_id=" + asgmt_status_id + '}';
    }

}

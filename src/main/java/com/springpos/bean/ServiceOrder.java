package com.springpos.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_ORDER")
public class ServiceOrder implements Serializable {

    @Id
    @Column(name = "svo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_started")
    private Timestamp date_started;
    @Column(name = "date_finished")
    private Timestamp date_finished;
    @Column(name = "work_summary")
    private int work_summary;
    @Column(name = "work_request")
    private int work_request;
    @Column(name = "cust_site_id")
    private int cust_site_id;
    @Column(name = "svo_status_id")
    private int svo_status_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate_started() {
        return date_started;
    }

    public void setDate_started(Timestamp date_started) {
        this.date_started = date_started;
    }

    public Timestamp getDate_finished() {
        return date_finished;
    }

    public void setDate_finished(Timestamp date_finished) {
        this.date_finished = date_finished;
    }

    public int getWork_summary() {
        return work_summary;
    }

    public void setWork_summary(int work_summary) {
        this.work_summary = work_summary;
    }

    public int getWork_request() {
        return work_request;
    }

    public void setWork_request(int work_request) {
        this.work_request = work_request;
    }

    public int getCust_site_id() {
        return cust_site_id;
    }

    public void setCust_site_id(int cust_site_id) {
        this.cust_site_id = cust_site_id;
    }

    public int getSvo_status_id() {
        return svo_status_id;
    }

    public void setSvo_status_id(int svo_status_id) {
        this.svo_status_id = svo_status_id;
    }

}

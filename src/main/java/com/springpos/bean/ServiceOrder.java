package com.springpos.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime date_started;
    @Column(name = "date_finished")
    private LocalDateTime date_finished;
    @Column(name = "work_summary")
    private String work_summary;
    @Column(name = "work_request")
    private String work_request;
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

    public LocalDateTime getDate_started() {
        return date_started;
    }

    public void setDate_started(LocalDateTime date_started) {
        this.date_started = date_started;
    }

    public LocalDateTime getDate_finished() {
        return date_finished;
    }

    public void setDate_finished(LocalDateTime date_finished) {
        this.date_finished = date_finished;
    }

    public String getWork_summary() {
        return work_summary;
    }

    public void setWork_summary(String work_summary) {
        this.work_summary = work_summary;
    }

    public String getWork_request() {
        return work_request;
    }

    public void setWork_request(String work_request) {
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

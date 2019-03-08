package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_STATUS")
public class ServiceStatus implements Serializable {

    @Id
    @Column(name = "svc_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "svo_status")
    private String svo_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSvo_status() {
        return svo_status;
    }

    public void setSvo_status(String svo_status) {
        this.svo_status = svo_status;
    }

    @Override
    public String toString() {
        return "ServiceStatus{" + "id=" + id + ", svo_status=" + svo_status + '}';
    }
    
    
}

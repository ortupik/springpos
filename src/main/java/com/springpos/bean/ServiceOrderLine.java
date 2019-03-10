package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_ORDER_LINE")
public class ServiceOrderLine implements Serializable {

    @Id
    @Column(name = "svo_LINE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "svo_id")
    private int svo_id;
    @Column(name = "svc_id")
    private int svc_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSvo_id() {
        return svo_id;
    }

    public void setSvo_id(int svo_id) {
        this.svo_id = svo_id;
    }

    public int getSvc_id() {
        return svc_id;
    }

    public void setSvc_id(int svc_id) {
        this.svc_id = svc_id;
    }

}

package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SVC")
public class Svc implements Serializable {

    @Id
    @Column(name = "svc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "svc_type_id")
    private int svc_type_id;
    @Column(name = "svc_status_id")
    private int svc_status_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSvc_type_id() {
        return svc_type_id;
    }

    public void setSvc_type_id(int svc_type_id) {
        this.svc_type_id = svc_type_id;
    }

    public int getSvc_status_id() {
        return svc_status_id;
    }

    public void setSvc_status_id(int svc_status_id) {
        this.svc_status_id = svc_status_id;
    }

    @Override
    public String toString() {
        return "Svc{" + "id=" + id + ", svc_type_id=" + svc_type_id + ", svc_status_id=" + svc_status_id + '}';
    }

}

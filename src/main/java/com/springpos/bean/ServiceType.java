package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_TYPE")
public class ServiceType implements Serializable {

    @Id
    @Column(name = "svc_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "svc_type")
    private String svc_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSvc_type() {
        return svc_type;
    }

    public void setSvc_type(String svc_type) {
        this.svc_type = svc_type;
    }

    @Override
    public String toString() {
        return "ServiceType{" + "id=" + id + ", svc_type=" + svc_type + '}';
    }

}

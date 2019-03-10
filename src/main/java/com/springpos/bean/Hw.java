package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "HW")
public class Hw implements Serializable {

    @Id
    @Column(name = "hw_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_name")
    private String hw_name;
    @Column(name = "hw_model_id")
    private int hw_model_id;
    @Transient
    private String type;
    @Transient
    private String serial;
    @Transient
    private String model;
    @Transient
    private int customerId;
    @Transient
    private String macAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_name() {
        return hw_name;
    }

    public void setHw_name(String hw_name) {
        this.hw_name = hw_name;
    }

    public int getHw_model_id() {
        return hw_model_id;
    }

    public void setHw_model_id(int hw_model_id) {
        this.hw_model_id = hw_model_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Hw{" + "id=" + id + ", hw_name=" + hw_name + ", hw_model_id=" + hw_model_id + '}';
    }

}

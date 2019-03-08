package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_MANUFACTURER_STATUS")
public class HwModelStatus implements Serializable {

    @Id
    @Column(name = "hw_model_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_model_status")
    private String hw_model_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_model_status() {
        return hw_model_status;
    }

    public void setHw_model_status(String hw_model_status) {
        this.hw_model_status = hw_model_status;
    }

    @Override
    public String toString() {
        return "HwModelStatus{" + "id=" + id + ", hw_model_status=" + hw_model_status + '}';
    }

}

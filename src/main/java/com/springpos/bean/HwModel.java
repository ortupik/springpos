package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_MODEL")
public class HwModel implements Serializable {

    @Id
    @Column(name = "hw_model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_model_number")
    private String hw_model_number;
    @Column(name = "hw_type_id")
    private int hw_type_id;
    @Column(name = "hw_manu_id")
    private int hw_manu_id;
     @Column(name = "hw_model_status_id")
    private int hw_model_status_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_model_number() {
        return hw_model_number;
    }

    public void setHw_model_number(String hw_model_number) {
        this.hw_model_number = hw_model_number;
    }

    public int getHw_type_id() {
        return hw_type_id;
    }

    public void setHw_type_id(int hw_type_id) {
        this.hw_type_id = hw_type_id;
    }

    public int getHw_manu_id() {
        return hw_manu_id;
    }

    public void setHw_manu_id(int hw_manu_id) {
        this.hw_manu_id = hw_manu_id;
    }

    public int getHw_model_status_id() {
        return hw_model_status_id;
    }

    public void setHw_model_status_id(int hw_model_status_id) {
        this.hw_model_status_id = hw_model_status_id;
    }

    @Override
    public String toString() {
        return "HwModel{" + "id=" + id + ", hw_model_number=" + hw_model_number + ", hw_type_id=" + hw_type_id + ", hw_manu_id=" + hw_manu_id + ", hw_model_status_id=" + hw_model_status_id + '}';
    }

}

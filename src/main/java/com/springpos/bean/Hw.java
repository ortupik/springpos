package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW")
public class Hw implements Serializable {

    @Id
    @Column(name = "hw_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_name")
    private int hw_name;
    @Column(name = "hw_model_id")
    private int hw_model_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHw_name() {
        return hw_name;
    }

    public void setHw_name(int hw_name) {
        this.hw_name = hw_name;
    }

    public int getHw_model_id() {
        return hw_model_id;
    }

    public void setHw_model_id(int hw_model_id) {
        this.hw_model_id = hw_model_id;
    }

    @Override
    public String toString() {
        return "Hw{" + "id=" + id + ", hw_name=" + hw_name + ", hw_model_id=" + hw_model_id + '}';
    }
    
    
}

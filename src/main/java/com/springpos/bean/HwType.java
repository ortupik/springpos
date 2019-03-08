package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_TYPE")
public class HwType implements Serializable {

    @Id
    @Column(name = "hw_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_type")
    private String hw_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_type() {
        return hw_type;
    }

    public void setHw_type(String hw_type) {
        this.hw_type = hw_type;
    }

    @Override
    public String toString() {
        return "HwType{" + "id=" + id + ", hw_type=" + hw_type + '}';
    }

}

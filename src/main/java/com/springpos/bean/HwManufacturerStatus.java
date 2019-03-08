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
public class HwManufacturerStatus implements Serializable {

    @Id
    @Column(name = "hw_manu_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_manu_status")
    private String hw_manu_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_manu_status() {
        return hw_manu_status;
    }

    public void setHw_manu_status(String hw_manu_status) {
        this.hw_manu_status = hw_manu_status;
    }

    @Override
    public String toString() {
        return "HwManufacturerStatus{" + "id=" + id + ", hw_manu_status=" + hw_manu_status + '}';
    }

}

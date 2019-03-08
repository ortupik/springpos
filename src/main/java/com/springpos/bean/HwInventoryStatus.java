package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_INVENTORY_STATUS")
public class HwInventoryStatus implements Serializable {

    @Id
    @Column(name = "hw_inv_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_inv_status")
    private String hw_inv_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_inv_status() {
        return hw_inv_status;
    }

    public void setHw_inv_status(String hw_inv_status) {
        this.hw_inv_status = hw_inv_status;
    }

    @Override
    public String toString() {
        return "HwInventoryStatus{" + "id=" + id + ", hw_inv_status=" + hw_inv_status + '}';
    }

}

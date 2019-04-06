package com.springpos.bean;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HW_INVENTORY_STATUS")
public class HwInventoryStatus implements Serializable {

    @OneToMany(mappedBy = "hwInventoryStatus")
    private Set<HwInventory> hwInventory;
    @Id
    @Column(name = "hw_inv_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(name = "hw_inv_status")
    private String hw_inv_status;
    @Column(name = "hw_inv_status_desc")
    private String hw_inv_status_desc;

    public Set<HwInventory> getHwInventory() {
        return hwInventory;
    }

    public void setHwInventory(Set<HwInventory> hwInventory) {
        this.hwInventory = hwInventory;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getHw_inv_status() {
        return hw_inv_status;
    }

    public void setHw_inv_status(String hw_inv_status) {
        this.hw_inv_status = hw_inv_status;
    }

    public String getHw_inv_status_desc() {
        return hw_inv_status_desc;
    }

    public void setHw_inv_status_desc(String hw_inv_status_desc) {
        this.hw_inv_status_desc = hw_inv_status_desc;
    }

  
}

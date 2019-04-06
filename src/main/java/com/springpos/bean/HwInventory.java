package com.springpos.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HW_INVENTORY")
public class HwInventory implements Serializable {

    @ManyToOne
    @JoinColumn(name = "hwModelId", nullable = false)
    private HwModel hwModel;
    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private HwInventoryStatus hwInventoryStatus;
    @ManyToOne
    @JoinColumn(name = "providerId", nullable = false)
    private HwProvider hwProvider;

    @Id
    @Column(name = "hw_inv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    @Column(name = "hw_cost")
    private String hw_cost;
    @Column(name = "hw_serial_number")
    private String hw_serial_number;
    @Column(name = "hw_mac_address")
    private String hw_mac_address;
     @Column(name = "hw_purchase_date")
    private Date hw_purchase_date;

    public HwModel getHwModel() {
        return hwModel;
    }

    public void setHwModel(HwModel hwModel) {
        this.hwModel = hwModel;
    }

    public HwInventoryStatus getHwInventoryStatus() {
        return hwInventoryStatus;
    }

    public void setHwInventoryStatus(HwInventoryStatus hwInventoryStatus) {
        this.hwInventoryStatus = hwInventoryStatus;
    }

    public HwProvider getHwProvider() {
        return hwProvider;
    }

    public void setHwProvider(HwProvider hwProvider) {
        this.hwProvider = hwProvider;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getHw_cost() {
        return hw_cost;
    }

    public void setHw_cost(String hw_cost) {
        this.hw_cost = hw_cost;
    }

    public String getHw_serial_number() {
        return hw_serial_number;
    }

    public void setHw_serial_number(String hw_serial_number) {
        this.hw_serial_number = hw_serial_number;
    }

    public String getHw_mac_address() {
        return hw_mac_address;
    }

    public void setHw_mac_address(String hw_mac_address) {
        this.hw_mac_address = hw_mac_address;
    }

    public Date getHw_purchase_date() {
        return hw_purchase_date;
    }

    public void setHw_purchase_date(Date hw_purchase_date) {
        this.hw_purchase_date = hw_purchase_date;
    }

   
    
    

}

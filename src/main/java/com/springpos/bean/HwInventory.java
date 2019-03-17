package com.springpos.bean;

import java.io.Serializable;
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
    
    @Id
    @Column(name = "hw_inv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_inv_cost")
    private String hw_inv_cost;
    @Column(name = "hw_serial_number")
    private String hw_serial_number;
    @Column(name = "hw_mac_address")
    private String hw_mac_address;
    @Column(name = "hw_provider_id")
    private int hw_provider_id;
    @Column(name = "hw_inv_status_id")
    private int hw_inv_status_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_inv_cost() {
        return hw_inv_cost;
    }

    public void setHw_inv_cost(String hw_inv_cost) {
        this.hw_inv_cost = hw_inv_cost;
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

    public int getHw_provider_id() {
        return hw_provider_id;
    }

    public void setHw_provider_id(int hw_provider_id) {
        this.hw_provider_id = hw_provider_id;
    }

    public int getHw_inv_status_id() {
        return hw_inv_status_id;
    }

    public void setHw_inv_status_id(int hw_inv_status_id) {
        this.hw_inv_status_id = hw_inv_status_id;
    }

    public HwModel getHwModel() {
        return hwModel;
    }

    public void setHwModel(HwModel hwModel) {
        this.hwModel = hwModel;
    }


}

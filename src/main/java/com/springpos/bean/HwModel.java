package com.springpos.bean;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HW_MODEL")
public class HwModel implements Serializable {

    @OneToMany(mappedBy = "hwModel")
    private Set<HwInventory> hwInventory;
    @OneToMany(mappedBy = "hwModel")
    private Set<CustomerSiteHw> customerSiteHw;

    @ManyToOne
    @JoinColumn(name = "hwModelStatusId", nullable = false)
    private HwModelStatus hwModelStatus;
    @ManyToOne
    @JoinColumn(name = "hwSeriesId", nullable = false)
    private HwSeries hwSeries;

    @Id
    @Column(name = "hw_model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hwModelId;
    @Column(name = "hw_model")
    private String hw_model;

    public Set<HwInventory> getHwInventory() {
        return hwInventory;
    }

    public void setHwInventory(Set<HwInventory> hwInventory) {
        this.hwInventory = hwInventory;
    }

    public Set<CustomerSiteHw> getCustomerSiteHw() {
        return customerSiteHw;
    }

    public void setCustomerSiteHw(Set<CustomerSiteHw> customerSiteHw) {
        this.customerSiteHw = customerSiteHw;
    }

    public HwModelStatus getHwModelStatus() {
        return hwModelStatus;
    }

    public void setHwModelStatus(HwModelStatus hwModelStatus) {
        this.hwModelStatus = hwModelStatus;
    }

    public HwSeries getHwSeries() {
        return hwSeries;
    }

    public void setHwSeries(HwSeries hwSeries) {
        this.hwSeries = hwSeries;
    }

    public int getHwModelId() {
        return hwModelId;
    }

    public void setHwModelId(int hwModelId) {
        this.hwModelId = hwModelId;
    }

    public String getHw_model() {
        return hw_model;
    }

    public void setHw_model(String hw_model) {
        this.hw_model = hw_model;
    }

}

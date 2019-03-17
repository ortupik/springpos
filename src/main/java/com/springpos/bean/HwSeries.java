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
@Table(name = "HW_SERIES")
public class HwSeries implements Serializable {

    @OneToMany(mappedBy = "hwSeries")
    private Set<HwModel> hwModel;

    @ManyToOne
    @JoinColumn(name = "hwSeriesStatusId", nullable = false)
    private HwSeriesStatus hwSeriesStatus;
    @ManyToOne
    @JoinColumn(name = "hwTypeId", nullable = false)
    private HwType hwType;
    @ManyToOne
    @JoinColumn(name = "hwManuId", nullable = false)
    private HwManufacturer hwManufacturer;

    @Id
    @Column(name = "hw_series_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hwSeriesId;
    @Column(name = "hw_series_name")
    private String hw_series_name;

    public Set<HwModel> getHwModel() {
        return hwModel;
    }

    public void setHwModel(Set<HwModel> hwModel) {
        this.hwModel = hwModel;
    }

    public HwSeriesStatus getHwSeriesStatus() {
        return hwSeriesStatus;
    }

    public void setHwSeriesStatus(HwSeriesStatus hwSeriesStatus) {
        this.hwSeriesStatus = hwSeriesStatus;
    }

    public HwType getHwType() {
        return hwType;
    }

    public void setHwType(HwType hwType) {
        this.hwType = hwType;
    }

    public HwManufacturer getHwManufacturer() {
        return hwManufacturer;
    }

    public void setHwManufacturer(HwManufacturer hwManufacturer) {
        this.hwManufacturer = hwManufacturer;
    }

    public int getHwSeriesId() {
        return hwSeriesId;
    }

    public void setHwSeriesId(int hwSeriesId) {
        this.hwSeriesId = hwSeriesId;
    }

    public String getHw_series_name() {
        return hw_series_name;
    }

    public void setHw_series_name(String hw_series_name) {
        this.hw_series_name = hw_series_name;
    }

}

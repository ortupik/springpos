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
@Table(name = "HW_SERIES_STATUS")
public class HwSeriesStatus implements Serializable {

    @OneToMany(mappedBy = "hwSeriesStatus")
    private Set<HwSeries> hwSeries;
    @Id
    @Column(name = "hw_series_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hwSeriesStatusId;
    @Column(name = "hw_series_status")
    private String hw_series_status;

    public Set<HwSeries> getHwSeries() {
        return hwSeries;
    }

    public void setHwSeries(Set<HwSeries> hwSeries) {
        this.hwSeries = hwSeries;
    }

    public int getHwSeriesStatusId() {
        return hwSeriesStatusId;
    }

    public void setHwSeriesStatusId(int hwSeriesStatusId) {
        this.hwSeriesStatusId = hwSeriesStatusId;
    }

    public String getHw_series_status() {
        return hw_series_status;
    }

    public void setHw_series_status(String hw_series_status) {
        this.hw_series_status = hw_series_status;
    }

}

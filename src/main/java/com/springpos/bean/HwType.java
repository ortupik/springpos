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
@Table(name = "HW_TYPE")
public class HwType implements Serializable {

    @OneToMany(mappedBy = "hwType")
    private Set<HwSeries> hwSeries;

    @Id
    @Column(name = "hw_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hwTypeId;
    @Column(name = "hw_type")
    private String hw_type;

    public Set<HwSeries> getHwSeries() {
        return hwSeries;
    }

    public void setHwSeries(Set<HwSeries> hwSeries) {
        this.hwSeries = hwSeries;
    }

    public int getHwTypeId() {
        return hwTypeId;
    }

    public void setHwTypeId(int hwTypeId) {
        this.hwTypeId = hwTypeId;
    }

    public String getHw_type() {
        return hw_type;
    }

    public void setHw_type(String hw_type) {
        this.hw_type = hw_type;
    }

}

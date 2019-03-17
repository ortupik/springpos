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
@Table(name = "HW_MANUFACTURER_STATUS")
public class HwManufacturerStatus implements Serializable {

    @OneToMany(mappedBy = "hwManufacturerStatus")
    private Set<HwManufacturer> hwManufacturer;
    @Id
    @Column(name = "hw_manu_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hwManuStatusId;
    @Column(name = "hw_manu_status")
    private String hw_manu_status;

    public Set<HwManufacturer> getHwManufacturer() {
        return hwManufacturer;
    }

    public void setHwManufacturer(Set<HwManufacturer> hwManufacturer) {
        this.hwManufacturer = hwManufacturer;
    }

    public int getHwManuStatusId() {
        return hwManuStatusId;
    }

    public void setHwManuStatusId(int hwManuStatusId) {
        this.hwManuStatusId = hwManuStatusId;
    }

    public String getHw_manu_status() {
        return hw_manu_status;
    }

    public void setHw_manu_status(String hw_manu_status) {
        this.hw_manu_status = hw_manu_status;
    }

}

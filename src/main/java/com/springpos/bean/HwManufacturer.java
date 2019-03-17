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
@Table(name = "HW_MANUFACTURER")
public class HwManufacturer implements Serializable {

    @ManyToOne
    @JoinColumn(name = "hwManuStatusId", nullable = false)
    private HwManufacturerStatus hwManufacturerStatus;
    @Id
    @Column(name = "hw_manu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hwManuId;
    @Column(name = "hw_manu_name")
    private String hwManuName;
    @Column(name = "hw_manu_phone")
    private String hwManuPhone;

    public HwManufacturerStatus getHwManufacturerStatus() {
        return hwManufacturerStatus;
    }

    public void setHwManufacturerStatus(HwManufacturerStatus hwManufacturerStatus) {
        this.hwManufacturerStatus = hwManufacturerStatus;
    }

    public int getHwManuId() {
        return hwManuId;
    }

    public void setHwManuId(int hwManuId) {
        this.hwManuId = hwManuId;
    }

    public String getHwManuName() {
        return hwManuName;
    }

    public void setHwManuName(String hwManuName) {
        this.hwManuName = hwManuName;
    }

    public String getHwManuPhone() {
        return hwManuPhone;
    }

    public void setHwManuPhone(String hwManuPhone) {
        this.hwManuPhone = hwManuPhone;
    }


}

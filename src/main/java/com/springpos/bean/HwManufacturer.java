package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_MANUFACTURER")
public class HwManufacturer implements Serializable {

    @Id
    @Column(name = "hw_manu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_manu_name")
    private String hwManuName;
    @Column(name = "hw_manu_phone")
    private String hwManuPhone;
    @Column(name = "hw_manu_status_id")
    private int hwManuStatusId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getHwManuStatusId() {
        return hwManuStatusId;
    }

    public void setHwManuStatusId(int hwManuStatusId) {
        this.hwManuStatusId = hwManuStatusId;
    }

    @Override
    public String toString() {
        return "HwManufacturer{" + "id=" + id + ", hwManuName=" + hwManuName + ", hwManuPhone=" + hwManuPhone + ", hwManuStatusId=" + hwManuStatusId + '}';
    }

}

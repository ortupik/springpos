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
@Table(name = "HW_MODEL_STATUS")
public class HwModelStatus implements Serializable {

    @OneToMany(mappedBy = "hwModelStatus")
    private Set<HwModel> hwModel;
    @Id
    @Column(name = "hw_model_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hwModelStatusId;
    @Column(name = "hw_model_status")
    private String hw_model_status;

    public Set<HwModel> getHwModel() {
        return hwModel;
    }

    public void setHwModel(Set<HwModel> hwModel) {
        this.hwModel = hwModel;
    }

    public int getHwModelStatusId() {
        return hwModelStatusId;
    }

    public void setHwModelStatusId(int hwModelStatusId) {
        this.hwModelStatusId = hwModelStatusId;
    }

    public String getHw_model_status() {
        return hw_model_status;
    }

    public void setHw_model_status(String hw_model_status) {
        this.hw_model_status = hw_model_status;
    }

}

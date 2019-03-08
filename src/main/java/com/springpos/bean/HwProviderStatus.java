package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_PROVIDER_STATUS")
public class HwProviderStatus implements Serializable {

    @Id
    @Column(name = "hw_provider_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hw_provider_status")
    private String hw_provider_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHw_provider_status() {
        return hw_provider_status;
    }

    public void setHw_provider_status(String hw_provider_status) {
        this.hw_provider_status = hw_provider_status;
    }

    @Override
    public String toString() {
        return "HwProviderStatus{" + "id=" + id + ", hw_provider_status=" + hw_provider_status + '}';
    }

}

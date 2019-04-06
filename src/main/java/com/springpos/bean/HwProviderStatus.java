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
@Table(name = "HW_PROVIDER_STATUS")
public class HwProviderStatus implements Serializable {

    @OneToMany(mappedBy = "hwProviderStatus")
    private Set<HwProvider> HwProvider;
    @Id
    @Column(name = "hw_provider_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(name = "hw_provider_status")
    private String hw_provider_status;

    public Set<HwProvider> getHwProvider() {
        return HwProvider;
    }

    public void setHwProvider(Set<HwProvider> HwProvider) {
        this.HwProvider = HwProvider;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getHw_provider_status() {
        return hw_provider_status;
    }

    public void setHw_provider_status(String hw_provider_status) {
        this.hw_provider_status = hw_provider_status;
    }
    

}

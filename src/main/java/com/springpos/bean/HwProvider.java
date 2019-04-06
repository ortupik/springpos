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
@Table(name = "HW_PROVIDER")
public class HwProvider implements Serializable {

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private HwProviderStatus hwProviderStatus;

    @OneToMany(mappedBy = "hwProvider")
    private Set<HwInventory> hwInventory;

    @Id
    @Column(name = "hw_provider_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int providerId;
    @Column(name = "hw_provider_name", unique = true)
    private String hw_provider_name;
    @Column(name = "hw_provider_address")
    private String hw_provider_address;
    @Column(name = "hw_provider_city")
    private String hw_provider_city;
    @Column(name = "hw_provider_phone")
    private String hw_provider_phone;
    @Column(name = "hw_provider_email")
    private String hw_provider_email;
    @Column(name = "hw_provider_web")
    private String hw_provider_web;

    public HwProviderStatus getHwProviderStatus() {
        return hwProviderStatus;
    }

    public void setHwProviderStatus(HwProviderStatus hwProviderStatus) {
        this.hwProviderStatus = hwProviderStatus;
    }


    public Set<HwInventory> getHwInventory() {
        return hwInventory;
    }

    public void setHwInventory(Set<HwInventory> hwInventory) {
        this.hwInventory = hwInventory;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getHw_provider_name() {
        return hw_provider_name;
    }

    public void setHw_provider_name(String hw_provider_name) {
        this.hw_provider_name = hw_provider_name;
    }

    public String getHw_provider_address() {
        return hw_provider_address;
    }

    public void setHw_provider_address(String hw_provider_address) {
        this.hw_provider_address = hw_provider_address;
    }

    public String getHw_provider_city() {
        return hw_provider_city;
    }

    public void setHw_provider_city(String hw_provider_city) {
        this.hw_provider_city = hw_provider_city;
    }

    public String getHw_provider_phone() {
        return hw_provider_phone;
    }

    public void setHw_provider_phone(String hw_provider_phone) {
        this.hw_provider_phone = hw_provider_phone;
    }

    public String getHw_provider_email() {
        return hw_provider_email;
    }

    public void setHw_provider_email(String hw_provider_email) {
        this.hw_provider_email = hw_provider_email;
    }

    public String getHw_provider_web() {
        return hw_provider_web;
    }

    public void setHw_provider_web(String hw_provider_web) {
        this.hw_provider_web = hw_provider_web;
    }

}

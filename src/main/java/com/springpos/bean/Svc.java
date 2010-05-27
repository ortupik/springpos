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
@Table(name = "SVC")
public class Svc implements Serializable {

    @ManyToOne
    @JoinColumn(name = "svc_type_id", nullable = false)
    private ServiceType serviceType;
    @ManyToOne
    @JoinColumn(name = "svc_status_id", nullable = false)
    private ServiceStatus serviceStatus;

    @Id
    @Column(name = "svc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int svcId;
    @Column(name = "svc_name")
    private String svcName;

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public int getSvcId() {
        return svcId;
    }

    public void setSvcId(int svcId) {
        this.svcId = svcId;
    }

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

}

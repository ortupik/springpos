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
@Table(name = "SERVICE_STATUS")
public class ServiceStatus implements Serializable {

    @OneToMany(mappedBy = "serviceStatus")
    private Set<Svc> svc;
    
    @Id
    @Column(name = "svc_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int svcStatusId;
    
    @Column(name = "svc_status")
    private String svc_status;

   
    public int getSvcStatusId() {
        return svcStatusId;
    }

    public void setSvcStatusId(int svcStatusId) {
        this.svcStatusId = svcStatusId;
    }

    public String getSvc_status() {
        return svc_status;
    }

    public void setSvc_status(String svc_status) {
        this.svc_status = svc_status;
    }

    public Set<Svc> getSvc() {
        return svc;
    }

    public void setSvc(Set<Svc> svc) {
        this.svc = svc;
    }
}
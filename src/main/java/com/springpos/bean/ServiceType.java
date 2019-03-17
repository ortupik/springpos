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
@Table(name = "SERVICE_TYPE")
public class ServiceType implements Serializable {

    @OneToMany(mappedBy = "serviceType")
    private Set<Svc> svc;

    @Id
    @Column(name = "svc_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int svcTypeId;
    @Column(name = "svc_type")
    private String svc_type;

    public int getSvcTypeId() {
        return svcTypeId;
    }

    public void setSvcTypeId(int svcTypeId) {
        this.svcTypeId = svcTypeId;
    }

    public String getSvc_type() {
        return svc_type;
    }

    public void setSvc_type(String svc_type) {
        this.svc_type = svc_type;
    }

    public Set<Svc> getSvc() {
        return svc;
    }

    public void setSvc(Set<Svc> svc) {
        this.svc = svc;
    }

}

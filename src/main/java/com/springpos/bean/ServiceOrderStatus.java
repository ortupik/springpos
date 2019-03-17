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
@Table(name = "SERVICE_ORDER_STATUS")
public class ServiceOrderStatus implements Serializable {

    @OneToMany(mappedBy = "serviceOrderStatus")
    private Set<ServiceOrder> serviceOrder;
    @Id
    @Column(name = "svo_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int svId;
    @Column(name = "svo_status")
    private String svo_status;

    public int getSvId() {
        return svId;
    }

    public void setSvId(int svId) {
        this.svId = svId;
    }

    public String getSvo_status() {
        return svo_status;
    }

    public void setSvo_status(String svo_status) {
        this.svo_status = svo_status;
    }

    public Set<ServiceOrder> getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(Set<ServiceOrder> serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

}

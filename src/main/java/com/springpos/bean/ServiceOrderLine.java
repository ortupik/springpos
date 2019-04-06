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
@Table(name = "SERVICE_ORDER_LINE")
public class ServiceOrderLine implements Serializable {

    @ManyToOne
    @JoinColumn(name = "svcId", nullable = false)
    private Svc svc;
    
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private ServiceOrder serviceOrder;
    
    @ManyToOne
    @JoinColumn(name = "contractorId", nullable = false)
    private Contractor contractor;
    
    @Id
    @Column(name = "svo_line_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderLineId;

    public Svc getSvc() {
        return svc;
    }

    public void setSvc(Svc svc) {
        this.svc = svc;
    }


    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }


}

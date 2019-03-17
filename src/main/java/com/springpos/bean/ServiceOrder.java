package com.springpos.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_ORDER")
public class ServiceOrder implements Serializable {

    @ManyToOne
    @JoinColumn(name = "svcId", nullable = false)
    private Svc svc;
    @ManyToOne
    @JoinColumn(name = "svId", nullable = false)
    private ServiceOrderStatus serviceOrderStatus;
    @ManyToOne
    @JoinColumn(name = "custId", nullable = false)
    private CustomerSite customerSite;
    @ManyToOne
    @JoinColumn(name = "contractorId", nullable = false)
    private Contractor contractor;

    @Id
    @Column(name = "svo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_started")
    private LocalDateTime date_started;
    @Column(name = "date_finished")
    private LocalDateTime date_finished;
    @Column(name = "work_summary")
    private String work_summary;
    @Column(name = "work_request")
    private String work_request;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate_started() {
        return date_started;
    }

    public void setDate_started(LocalDateTime date_started) {
        this.date_started = date_started;
    }

    public LocalDateTime getDate_finished() {
        return date_finished;
    }

    public void setDate_finished(LocalDateTime date_finished) {
        this.date_finished = date_finished;
    }

    public String getWork_summary() {
        return work_summary;
    }

    public void setWork_summary(String work_summary) {
        this.work_summary = work_summary;
    }

    public String getWork_request() {
        return work_request;
    }

    public void setWork_request(String work_request) {
        this.work_request = work_request;
    }

    public Svc getSvc() {
        return svc;
    }

    public void setSvc(Svc svc) {
        this.svc = svc;
    }

    public ServiceOrderStatus getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setServiceOrderStatus(ServiceOrderStatus serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }

    public CustomerSite getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSite customerSite) {
        this.customerSite = customerSite;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

}

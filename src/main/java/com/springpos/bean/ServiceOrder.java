package com.springpos.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    @OneToMany(mappedBy = "serviceOrder")
    private Set<ServiceOrderLine> serviceOrderLine;
    @Id
    @Column(name = "svo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(name = "date_started")
    private LocalDateTime date_started;
    @Column(name = "date_finished")
    private LocalDateTime date_finished;
    @Column(name = "date_requested")
    private LocalDateTime date_requested;
    @Column(name = "date_scheduled")
    private LocalDateTime date_scheduled;
    @Column(name = "work_summary")
    private String work_summary;
    @Column(name = "work_request")
    private String work_request;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public LocalDateTime getDate_requested() {
        return date_requested;
    }

    public void setDate_requested(LocalDateTime date_requested) {
        this.date_requested = date_requested;
    }

    public LocalDateTime getDate_scheduled() {
        return date_scheduled;
    }

    public void setDate_scheduled(LocalDateTime date_scheduled) {
        this.date_scheduled = date_scheduled;
    }

    public Set<ServiceOrderLine> getServiceOrderLine() {
        return serviceOrderLine;
    }

    public void setServiceOrderLine(Set<ServiceOrderLine> serviceOrderLine) {
        this.serviceOrderLine = serviceOrderLine;
    }

}

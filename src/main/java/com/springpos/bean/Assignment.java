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
@Table(name = "ASSIGNMENT")
public class Assignment implements Serializable {

    @ManyToOne
    @JoinColumn(name = "contractorId", nullable = false)
    private Contractor contractor;
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private ServiceOrder serviceOrder;
    @ManyToOne
    @JoinColumn(name = "statusId", nullable = true)
    private AssignmentStatus assignmentStatus;
    @Id
    @Column(name = "asgmt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId;
    @Column(name = "asgmt_date")
    private String asgmt_date;

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAsgmt_date() {
        return asgmt_date;
    }

    public void setAsgmt_date(String asgmt_date) {
        this.asgmt_date = asgmt_date;
    }
    
    

}

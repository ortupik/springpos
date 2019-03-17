package com.springpos.bean;

import java.io.Serializable;
import java.sql.Date;

public class Summary implements Serializable {

    private String companyName;
    private Date dateRecieved;
    private Date dateScheduled;
    private String store;
    private String contractor;
    private String clientContact;
    private String service;
    private String hardwareNeeded;
    private String otherComments;

    public Summary() {
    }

    public Summary(CustomerSite customer, ServiceOrder order, Contractor contract) {
        this.companyName = customer.getCustSiteName();
        this.contractor = contract.getContractor_fname() + " " + contract.getContractor_lname();
        this.clientContact = customer.getContactName();
        this.service = "ser";
        this.hardwareNeeded = order.getWork_request();
        this.otherComments = order.getWork_summary();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDateRecieved() {
        return dateRecieved;
    }

    public void setDateRecieved(Date dateRecieved) {
        this.dateRecieved = dateRecieved;
    }

    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getHardwareNeeded() {
        return hardwareNeeded;
    }

    public void setHardwareNeeded(String hardwareNeeded) {
        this.hardwareNeeded = hardwareNeeded;
    }

    public String getOtherComments() {
        return otherComments;
    }

    public void setOtherComments(String otherComments) {
        this.otherComments = otherComments;
    }

}

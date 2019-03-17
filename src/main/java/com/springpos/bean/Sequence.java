package com.springpos.bean;

import java.io.Serializable;

public class Sequence implements Serializable {

    private CustomerSite customer;
    private Contact contact;
    private ServiceOrder serviceOrder;
    private Contractor contractor;

    public CustomerSite getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerSite customer) {
        this.customer = customer;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

}

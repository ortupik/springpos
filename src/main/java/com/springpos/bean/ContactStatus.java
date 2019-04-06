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
@Table(name = "CONTACT_STATUS")
public class ContactStatus implements Serializable {

    @OneToMany(mappedBy = "contactStatus")
    private Set<Contact> contact;
    @Id
    @Column(name = "contact_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(name = "contact_status")
    private String contact_status;

    public Set<Contact> getContact() {
        return contact;
    }

    public void setContact(Set<Contact> contact) {
        this.contact = contact;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getContact_status() {
        return contact_status;
    }

    public void setContact_status(String contact_status) {
        this.contact_status = contact_status;
    }

}

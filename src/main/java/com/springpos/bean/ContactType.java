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
@Table(name = "CONTACT_TYPE")
public class ContactType implements Serializable {

    @OneToMany(mappedBy = "contactType")
    private Set<Contact> contact;
    @Id
    @Column(name = "contact_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contact_type_id;
    @Column(name = "contact_type")
    private String contact_type;

    public Set<Contact> getContact() {
        return contact;
    }

    public void setContact(Set<Contact> contact) {
        this.contact = contact;
    }

    public int getContact_type_id() {
        return contact_type_id;
    }

    public void setContact_type_id(int contact_type_id) {
        this.contact_type_id = contact_type_id;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

}

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "STATE_PROVINCE")
public class State implements Serializable {

    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int state_id;

    @NotNull
    @Size(max = 100)
    @Column(name = "state_name", unique = true)
    private String state_name;

    @OneToMany(mappedBy = "state")
    private Set<CustomerSite> customer;
    @OneToMany(mappedBy = "state")
    private Set<Contractor> contractor;

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public Set<CustomerSite> getCustomer() {
        return customer;
    }

    public void setCustomer(Set<CustomerSite> customer) {
        this.customer = customer;
    }

    public Set<Contractor> getContractor() {
        return contractor;
    }

    public void setContractor(Set<Contractor> contractor) {
        this.contractor = contractor;
    }

}

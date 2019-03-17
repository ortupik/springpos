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
@Table(name = "CONTRACTOR_STATUS")
public class ContractorStatus implements Serializable {

    @OneToMany(mappedBy = "country")
    private Set<Contractor> contractor;
    @Id
    @Column(name = "contractor_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(name = "contractor_status")
    private String contractor_status;

    public Set<Contractor> getContractor() {
        return contractor;
    }

    public void setContractor(Set<Contractor> contractor) {
        this.contractor = contractor;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getContractor_status() {
        return contractor_status;
    }

    public void setContractor_status(String contractor_status) {
        this.contractor_status = contractor_status;
    }

   
}

package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRACTOR_STATUS")
public class ContractorStatus implements Serializable {

    @Id
    @Column(name = "contractor_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "contractor_status")
    private String contractor_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractor_status() {
        return contractor_status;
    }

    public void setContractor_status(String contractor_status) {
        this.contractor_status = contractor_status;
    }

    @Override
    public String toString() {
        return "ContractorStatus{" + "id=" + id + ", contractor_status=" + contractor_status + '}';
    }

}

package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRACTOR_TYPE")
public class ContractorType implements Serializable {

    @Id
    @Column(name = "contractor_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "contractor_type")
    private String contractor_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractor_type() {
        return contractor_type;
    }

    public void setContractor_type(String contractor_type) {
        this.contractor_type = contractor_type;
    }

    @Override
    public String toString() {
        return "ContractorType{" + "id=" + id + ", contractor_type=" + contractor_type + '}';
    }

}

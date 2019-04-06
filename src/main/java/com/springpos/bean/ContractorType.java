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
@Table(name = "CONTRACTOR_TYPE")
public class ContractorType implements Serializable {

    @OneToMany(mappedBy = "contractorType")
    private Set<Contractor> contractor;
    @Id
    @Column(name = "contractor_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;
    @Column(name = "contractor_type")
    private String contractor_type;

    public Set<Contractor> getContractor() {
        return contractor;
    }

    public void setContractor(Set<Contractor> contractor) {
        this.contractor = contractor;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getContractor_type() {
        return contractor_type;
    }

    public void setContractor_type(String contractor_type) {
        this.contractor_type = contractor_type;
    }

}

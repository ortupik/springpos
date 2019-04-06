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
@Table(name = "COUNTRY")
public class Country implements Serializable {

    @OneToMany(mappedBy = "country")
    private Set<CustomerSite> customerSite;
    @OneToMany(mappedBy = "country")
    private Set<Contractor> contractor;

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countryId;
    @Column(name = "country_name")
    private String countryName;

    public Set<CustomerSite> getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(Set<CustomerSite> customerSite) {
        this.customerSite = customerSite;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

 

    public Set<Contractor> getContractor() {
        return contractor;
    }

    public void setContractor(Set<Contractor> contractor) {
        this.contractor = contractor;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

 
}

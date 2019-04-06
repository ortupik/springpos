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
@Table(name = "ACCESS_LEVEL")
public class AccessLevel implements Serializable {
  
    @OneToMany(mappedBy = "accessLevel")
    private Set<Contractor> contractor;
    @Id
    @Column(name = "acc_level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int acc_level_id;
    @Column(name = "acc_level_name")
    private String acc_level_name;
    @Column(name = "acc_level_desc")
    private String acc_level_desc;

    public Set<Contractor> getContractor() {
        return contractor;
    }

    public void setContractor(Set<Contractor> contractor) {
        this.contractor = contractor;
    }

    public int getAcc_level_id() {
        return acc_level_id;
    }

    public void setAcc_level_id(int acc_level_id) {
        this.acc_level_id = acc_level_id;
    }

    public String getAcc_level_name() {
        return acc_level_name;
    }

    public void setAcc_level_name(String acc_level_name) {
        this.acc_level_name = acc_level_name;
    }

    public String getAcc_level_desc() {
        return acc_level_desc;
    }

    public void setAcc_level_desc(String acc_level_desc) {
        this.acc_level_desc = acc_level_desc;
    }


}

package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCESS_LEVEL")
public class AccessLevel implements Serializable {

    @Id
    @Column(name = "contact_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "acc_level_name")
    private String acc_level_name;
     @Column(name = "acc_level_desc")
    private String acc_level_desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AccessLevel{" + "id=" + id + ", acc_level_name=" + acc_level_name + ", acc_level_desc=" + acc_level_desc + '}';
    }
     

}

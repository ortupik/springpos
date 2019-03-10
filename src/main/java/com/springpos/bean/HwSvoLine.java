package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HW_SVO_LINE")
public class HwSvoLine implements Serializable {

    @Id
    @Column(name = "hw_svo_line_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cust_site_hw_id")
    private int cust_site_hw_id;
    @Column(name = "svo_line_id")
    private int svo_line_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCust_site_hw_id() {
        return cust_site_hw_id;
    }

    public void setCust_site_hw_id(int cust_site_hw_id) {
        this.cust_site_hw_id = cust_site_hw_id;
    }

    public int getSvo_line_id() {
        return svo_line_id;
    }

    public void setSvo_line_id(int svo_line_id) {
        this.svo_line_id = svo_line_id;
    }

    @Override
    public String toString() {
        return "HwSvoLine{" + "id=" + id + ", cust_site_hw_id=" + cust_site_hw_id + ", svo_line_id=" + svo_line_id + '}';
    }

}

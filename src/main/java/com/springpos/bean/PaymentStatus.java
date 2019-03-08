package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_STATUS")
public class PaymentStatus implements Serializable {

    @Id
    @Column(name = "pmt_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pmt_status")
    private String pmt_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPmt_status() {
        return pmt_status;
    }

    public void setPmt_status(String pmt_status) {
        this.pmt_status = pmt_status;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" + "id=" + id + ", pmt_status=" + pmt_status + '}';
    }

}

package com.springpos.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT")
public class Payment implements Serializable {

    @Id
    @Column(name = "pmt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "amt",precision=10, scale=2)
    private Double amt;
    @Column(name = "date_paid")
    private Date datePaid;
    @Column(name = "pmt_type_id")
    private String pmtTypeId;
    @Column(name = "pmt_status_id")
    private String pmtStatusId;
    @Column(name = "svo_id")
    private String svoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public String getPmtTypeId() {
        return pmtTypeId;
    }

    public void setPmtTypeId(String pmtTypeId) {
        this.pmtTypeId = pmtTypeId;
    }

    public String getPmtStatusId() {
        return pmtStatusId;
    }

    public void setPmtStatusId(String pmtStatusId) {
        this.pmtStatusId = pmtStatusId;
    }

    public String getSvoId() {
        return svoId;
    }

    public void setSvoId(String svoId) {
        this.svoId = svoId;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", amt=" + amt + ", datePaid=" + datePaid + ", pmtTypeId=" + pmtTypeId + ", pmtStatusId=" + pmtStatusId + ", svoId=" + svoId + '}';
    }

    
   

}

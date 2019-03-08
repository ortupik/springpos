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
@Table(name = "CONTRACTOR_INCIDENT")
public class IncidentContractor implements Serializable {

    @Id
    @Column(name = "contractor_inc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "contractor_inc_date")
    private Date contractor_inc_date;
    @Column(name = "contractor_inc_notes")
    private String contractor_inc_notes;
    @Column(name = "contractor_id")
    private int contractor_id;
    @Column(name = "incident_id")
    private int incident_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getContractor_inc_date() {
        return contractor_inc_date;
    }

    public void setContractor_inc_date(Date contractor_inc_date) {
        this.contractor_inc_date = contractor_inc_date;
    }

    public String getContractor_inc_notes() {
        return contractor_inc_notes;
    }

    public void setContractor_inc_notes(String contractor_inc_notes) {
        this.contractor_inc_notes = contractor_inc_notes;
    }

    public int getContractor_id() {
        return contractor_id;
    }

    public void setContractor_id(int contractor_id) {
        this.contractor_id = contractor_id;
    }

    public int getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(int incident_id) {
        this.incident_id = incident_id;
    }

    @Override
    public String toString() {
        return "IncidentContractor{" + "id=" + id + ", contractor_inc_date=" + contractor_inc_date + ", contractor_inc_notes=" + contractor_inc_notes + ", contractor_id=" + contractor_id + ", incident_id=" + incident_id + '}';
    }

}

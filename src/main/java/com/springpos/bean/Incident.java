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
@Table(name = "INCIDENT")
public class Incident implements Serializable {

    @Id
    @Column(name = "incident_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "incident_date")
    private Date incident_date;
    @Column(name = "incident")
    private String incidentName;
    @Column(name = "incident_status_id")
    private int incident_status_id;
    @Column(name = "svo_id")
    private int svo_id;
    @Column(name = "incident_type_id")
    private int incident_type_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getIncident_date() {
        return incident_date;
    }

    public void setIncident_date(Date incident_date) {
        this.incident_date = incident_date;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public int getIncident_status_id() {
        return incident_status_id;
    }

    public void setIncident_status_id(int incident_status_id) {
        this.incident_status_id = incident_status_id;
    }

    public int getSvo_id() {
        return svo_id;
    }

    public void setSvo_id(int svo_id) {
        this.svo_id = svo_id;
    }

    public int getIncident_type_id() {
        return incident_type_id;
    }

    public void setIncident_type_id(int incident_type_id) {
        this.incident_type_id = incident_type_id;
    }



}

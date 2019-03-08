package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INCIDENT_STATUS")
public class IncidenceStatus implements Serializable {

    @Id
    @Column(name = "incident_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "incident_status")
    private String incident_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncident_status() {
        return incident_status;
    }

    public void setIncident_status(String incident_status) {
        this.incident_status = incident_status;
    }

    @Override
    public String toString() {
        return "IncidenceStatus{" + "id=" + id + ", incident_status=" + incident_status + '}';
    }

}

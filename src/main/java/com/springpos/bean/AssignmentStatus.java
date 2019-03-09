package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
    @Table(name = "ASSIGNMENT_STATUS")
public class AssignmentStatus implements Serializable {

    @Id
    @Column(name = "asgmt_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "asgmt_status")
    private String asgmt_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsgmt_status() {
        return asgmt_status;
    }

    public void setAsgmt_status(String asgmt_status) {
        this.asgmt_status = asgmt_status;
    }

    @Override
    public String toString() {
        return "AssignmentStatus{" + "id=" + id + ", asgmt_status=" + asgmt_status + '}';
    }

}

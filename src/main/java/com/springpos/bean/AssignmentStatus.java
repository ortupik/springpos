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
@Table(name = "ASSIGNMENT_STATUS")
public class AssignmentStatus implements Serializable {

    @OneToMany(mappedBy = "assignmentStatus")
    private Set<Assignment> assignment;
    @Id
    @Column(name = "asgmt_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(name = "asgmt_status")
    private String asgmt_status;

    public Set<Assignment> getAssignment() {
        return assignment;
    }

    public void setAssignment(Set<Assignment> assignment) {
        this.assignment = assignment;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getAsgmt_status() {
        return asgmt_status;
    }

    public void setAsgmt_status(String asgmt_status) {
        this.asgmt_status = asgmt_status;
    }

}

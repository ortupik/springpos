package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIAL_MEDIA_ACCOUNT")
public class RequiredSkill implements Serializable {

    @Id
    @Column(name = "req_skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "svc_id")
    private int svc_id;
    @Column(name = "skill_id")
    private int skill_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSvc_id() {
        return svc_id;
    }

    public void setSvc_id(int svc_id) {
        this.svc_id = svc_id;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    @Override
    public String toString() {
        return "RequiredSkill{" + "id=" + id + ", svc_id=" + svc_id + ", skill_id=" + skill_id + '}';
    }

}

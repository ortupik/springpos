package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KNOWLEDGE_BASE_CATEGORY")
public class KnowledgeBaseCategory implements Serializable {

    @Id
    @Column(name = "kb_cat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "kb_cat")
    private String kb_cat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKb_cat() {
        return kb_cat;
    }

    public void setKb_cat(String kb_cat) {
        this.kb_cat = kb_cat;
    }

    @Override
    public String toString() {
        return "KnowledgeBaseCategory{" + "id=" + id + ", kb_cat=" + kb_cat + '}';
    }

}

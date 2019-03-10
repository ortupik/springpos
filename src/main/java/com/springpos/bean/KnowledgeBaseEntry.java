package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KNOWLEDGE_BASE_ENTRY")
public class KnowledgeBaseEntry implements Serializable {

    @Id
    @Column(name = "kb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "kb_title", nullable = false)
    private String kb_title;
    @Column(name = "kb_soruces", nullable = false)
    private String kb_soruces;
    @Column(name = "kb_content", nullable = false)
    private String kb_content;
    @Column(name = "kb_cat_id")
    private int kb_cat_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKb_title() {
        return kb_title;
    }

    public void setKb_title(String kb_title) {
        this.kb_title = kb_title;
    }

    public String getKb_soruces() {
        return kb_soruces;
    }

    public void setKb_soruces(String kb_soruces) {
        this.kb_soruces = kb_soruces;
    }

    public String getKb_content() {
        return kb_content;
    }

    public void setKb_content(String kb_content) {
        this.kb_content = kb_content;
    }

    public int getKb_cat_id() {
        return kb_cat_id;
    }

    public void setKb_cat_id(int kb_cat_id) {
        this.kb_cat_id = kb_cat_id;
    }

    @Override
    public String toString() {
        return "KnowledgeBaseEntry{" + "id=" + id + ", kb_title=" + kb_title + ", kb_soruces=" + kb_soruces + ", kb_content=" + kb_content + ", kb_cat_id=" + kb_cat_id + '}';
    }

}

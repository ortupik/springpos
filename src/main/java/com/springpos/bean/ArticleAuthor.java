package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICLE_AUTHOR")
public class ArticleAuthor implements Serializable {

    @Id
    @Column(name = "article_author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "kb_id")
    private String kb_id;
     @Column(name = "contractor_id")
    private String contractor_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKb_id() {
        return kb_id;
    }

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getContractor_id() {
        return contractor_id;
    }

    public void setContractor_id(String contractor_id) {
        this.contractor_id = contractor_id;
    }

    @Override
    public String toString() {
        return "ArticleAuthor{" + "id=" + id + ", kb_id=" + kb_id + ", contractor_id=" + contractor_id + '}';
    }

}

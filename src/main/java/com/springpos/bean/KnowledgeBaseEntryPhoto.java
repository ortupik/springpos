package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KNOWLEDGE_BASE_ENTRY_PHOTO")
public class KnowledgeBaseEntryPhoto implements Serializable {

    @Id
    @Column(name = "kb_photo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "kb_photo_title", nullable = false)
    private String kb_photo_title;
    @Column(name = "kb_photo", nullable = false, columnDefinition = "longblob")
    private byte[] kbPhoto;    
    @Column(name = "kb_id")
    private int kb_id;
    @Column(name = "photo_type_id")
    private int photo_type_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKb_photo_title() {
        return kb_photo_title;
    }

    public void setKb_photo_title(String kb_photo_title) {
        this.kb_photo_title = kb_photo_title;
    }

    public byte[] getKbPhoto() {
        return kbPhoto;
    }

    public void setKbPhoto(byte[] kbPhoto) {
        this.kbPhoto = kbPhoto;
    }

    public int getKb_id() {
        return kb_id;
    }

    public void setKb_id(int kb_id) {
        this.kb_id = kb_id;
    }

    public int getPhoto_type_id() {
        return photo_type_id;
    }

    public void setPhoto_type_id(int photo_type_id) {
        this.photo_type_id = photo_type_id;
    }

    @Override
    public String toString() {
        return "KnowledgeBaseEntryPhoto{" + "id=" + id + ", kb_photo_title=" + kb_photo_title + ", kbPhoto=" + kbPhoto + ", kb_id=" + kb_id + ", photo_type_id=" + photo_type_id + '}';
    }

}

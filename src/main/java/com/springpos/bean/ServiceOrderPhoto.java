package com.springpos.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_ORDER_PHOTO")
public class ServiceOrderPhoto implements Serializable {

    @Id
    @Column(name = "svo_photo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "svo_photo_title", nullable = false)
    private String svo_photo_title;
    @Column(name = "svo_photo", nullable = false, columnDefinition = "longblob")
    private byte[] svo_photo;
    @Column(name = "svo_line_id")
    private int svo_line_id;
    @Column(name = "photo_type_id")
    private int photo_type_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSvo_photo_title() {
        return svo_photo_title;
    }

    public void setSvo_photo_title(String svo_photo_title) {
        this.svo_photo_title = svo_photo_title;
    }

    public byte[] getSvo_photo() {
        return svo_photo;
    }

    public void setSvo_photo(byte[] svo_photo) {
        this.svo_photo = svo_photo;
    }

    public int getSvo_line_id() {
        return svo_line_id;
    }

    public void setSvo_line_id(int svo_line_id) {
        this.svo_line_id = svo_line_id;
    }

    public int getPhoto_type_id() {
        return photo_type_id;
    }

    public void setPhoto_type_id(int photo_type_id) {
        this.photo_type_id = photo_type_id;
    }

    @Override
    public String toString() {
        return "ServiceOrderPhoto{" + "id=" + id + ", svo_photo_title=" + svo_photo_title + ", svo_photo=" + svo_photo + ", svo_line_id=" + svo_line_id + ", photo_type_id=" + photo_type_id + '}';
    }

}

package com.wql.poetry.model;

public class ImageEntity {
    private Integer image_id;
    private String image_url;
    private String class_info;
    private String image_tag;
    private String image_base_url;
    private String origin_url;
    private String thumb_url;

    public String getImage_base_url() {
        return image_base_url;
    }

    public void setImage_base_url(String image_base_url) {
        this.image_base_url = image_base_url;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getClass_info() {
        return class_info;
    }

    public void setClass_info(String class_info) {
        this.class_info = class_info;
    }

    public String getImage_tag() {
        return image_tag;
    }

    public void setImage_tag(String image_tag) {
        this.image_tag = image_tag;
    }

}

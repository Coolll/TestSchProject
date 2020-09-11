package com.wql.poetry.model;

public class HeadImageEntity {
    private Integer head_image_id;
    private String head_image_base_url;
    private String origin_url;
    private String thumb_url;


    public Integer getHead_image_id() {
        return head_image_id;
    }

    public void setHead_image_id(Integer head_image_id) {
        this.head_image_id = head_image_id;
    }

    public String getHead_image_base_url() {
        return head_image_base_url;
    }

    public void setHead_image_base_url(String head_image_base_url) {
        this.head_image_base_url = head_image_base_url;
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
}

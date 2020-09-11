package com.wql.poetry.model;

public class PoetryDetailEntity {
    //从mybatis映射的实体类
    private Integer poetry_id;
    private String name;
    private String author;
    private String content;
    private String addition_info;
    private String class_info;
    private String main_class;
    private String source;
    private String source_explain;
    private String transfer_info;
    private Integer likes;
    private String text_color;//0为默认色 1为白色
    private ImageEntity imageEntity;

    public String getSource_explain() {
        return source_explain;
    }

    public void setSource_explain(String source_explain) {
        this.source_explain = source_explain;
    }

    public Integer getPoetry_id() {
        return poetry_id;
    }

    public void setPoetry_id(Integer poetry_id) {
        this.poetry_id = poetry_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddition_info() {
        return addition_info;
    }

    public void setAddition_info(String addition_info) {
        this.addition_info = addition_info;
    }

    public String getClass_info() {
        return class_info;
    }

    public void setClass_info(String class_info) {
        this.class_info = class_info;
    }

    public String getMain_class() {
        return main_class;
    }

    public void setMain_class(String main_class) {
        this.main_class = main_class;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTransfer_info() {
        return transfer_info;
    }

    public void setTransfer_info(String transfer_info) {
        this.transfer_info = transfer_info;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }
}

package com.wql.poetry.model;

//获取测评的诗词时使用 不需要太多的属性，只需要内容
public class PoetrySimpleEntity {
    private String name;
    private String author;
    private String content;

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
}

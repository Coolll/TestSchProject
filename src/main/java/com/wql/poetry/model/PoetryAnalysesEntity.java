package com.wql.poetry.model;

public class PoetryAnalysesEntity {
    private Integer poetry_id;
    private String transfer_info;
    private String addition_info;
    private String analyses_info;
    private String background_info;

    public Integer getPoetry_id() {
        return poetry_id;
    }

    public void setPoetry_id(Integer poetry_id) {
        this.poetry_id = poetry_id;
    }

    public String getTransfer_info() {
        return transfer_info;
    }

    public void setTransfer_info(String transfer_info) {
        this.transfer_info = transfer_info;
    }

    public String getAddition_info() {
        return addition_info;
    }

    public void setAddition_info(String addition_info) {
        this.addition_info = addition_info;
    }

    public String getAnalyses_info() {
        return analyses_info;
    }

    public void setAnalyses_info(String analyses_info) {
        this.analyses_info = analyses_info;
    }

    public String getBackground_info() {
        return background_info;
    }

    public void setBackground_info(String background_info) {
        this.background_info = background_info;
    }
}

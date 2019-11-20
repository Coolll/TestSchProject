package com.wql.poetry.model;

import com.wql.baseFile.BaseParam;

public class SearchPoetryParam extends BaseParam {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}

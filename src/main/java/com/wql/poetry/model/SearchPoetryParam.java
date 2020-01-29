package com.wql.poetry.model;

import com.wql.baseFile.BaseParam;

public class SearchPoetryParam extends BaseParam {
    private String keyword;
    private Integer page;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}

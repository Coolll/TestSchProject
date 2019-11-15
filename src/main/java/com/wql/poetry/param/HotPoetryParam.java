package com.wql.poetry.param;

import com.wql.baseFile.BaseParam;

public class HotPoetryParam extends BaseParam {
    private Integer from_index;
    private Integer count;

    public Integer getFrom_index() {
        return from_index;
    }

    public void setFrom_index(Integer from_index) {
        this.from_index = from_index;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

package com.wql.user.param;

import com.wql.baseFile.BaseParam;

public class PoetryCollectionParam extends BaseParam {
    private Integer user_id;
    private Integer from_index;
    private Integer count;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

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

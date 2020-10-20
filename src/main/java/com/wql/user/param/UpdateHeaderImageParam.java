package com.wql.user.param;

import com.wql.baseFile.BaseParam;

public class UpdateHeaderImageParam extends BaseParam {
    private Integer user_id;
    private String headImageUrl;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}

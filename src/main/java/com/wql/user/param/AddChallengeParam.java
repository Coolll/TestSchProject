package com.wql.user.param;

import com.wql.baseFile.BaseParam;

public class AddChallengeParam extends BaseParam {
    private Integer user_id;
    private Integer storage;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }


}

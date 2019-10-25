package com.wql.user.model;

public class UserEntity {
    private Integer user_id;
    private String nick_name;
    private String password;
    private String user_poetry_class;
    private String poetry_storage;
    private String email;
    private String create_time;
    private String last_login_time;
    private String last_loginout_time;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_poetry_class() {
        return user_poetry_class;
    }

    public void setUser_poetry_class(String user_poetry_class) {
        this.user_poetry_class = user_poetry_class;
    }

    public String getPoetry_storage() {
        return poetry_storage;
    }

    public void setPoetry_storage(String poetry_storage) {
        this.poetry_storage = poetry_storage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_loginout_time() {
        return last_loginout_time;
    }

    public void setLast_loginout_time(String last_loginout_time) {
        this.last_loginout_time = last_loginout_time;
    }
}

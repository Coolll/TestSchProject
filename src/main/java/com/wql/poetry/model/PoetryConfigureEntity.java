package com.wql.poetry.model;

public class PoetryConfigureEntity {
    /*
    main_class 用来查询具体的诗词内容
    configure_tag用来区分大的种类（1:学前 2:小学 3:初中 4:高中 5:唐诗 6:宋词 7:宋词精选 8:论语）
    main_title 导航栏标题
    sub_title 每一个子项目
    */
    private Integer configure_id;
    private String main_class;
    private Integer configure_tag;
    private String main_title;
    private String sub_title;
    private Integer table_section;
    private Integer table_index;
    private String section_title;

    public String getSection_title() {
        return section_title;
    }

    public void setSection_title(String section_title) {
        this.section_title = section_title;
    }

    public Integer getTable_section() {
        return table_section;
    }

    public void setTable_section(Integer table_section) {
        this.table_section = table_section;
    }

    public Integer getTable_index() {
        return table_index;
    }

    public void setTable_index(Integer table_index) {
        this.table_index = table_index;
    }

    public Integer getConfigure_id() {
        return configure_id;
    }

    public void setConfigure_id(Integer configure_id) {
        this.configure_id = configure_id;
    }

    public String getMain_class() {
        return main_class;
    }

    public void setMain_class(String main_class) {
        this.main_class = main_class;
    }

    public Integer getConfigure_tag() {
        return configure_tag;
    }

    public void setConfigure_tag(Integer configure_tag) {
        this.configure_tag = configure_tag;
    }

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }
}

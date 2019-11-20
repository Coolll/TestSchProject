package com.wql.utils.publicUtils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class DBCPDataSourceFactory extends UnpooledDataSourceFactory {
    public DBCPDataSourceFactory(){
        this.dataSource = new BasicDataSource();
    }
}
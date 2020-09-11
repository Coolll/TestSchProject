package com.wql.utils.publicUtils;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NullValueUtil implements TypeHandler<Boolean> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        if (aBoolean == null && jdbcType==jdbcType.BOOLEAN){
            preparedStatement.setBoolean(i,false);
        }else {
            preparedStatement.setBoolean(i,aBoolean);
        }
    }

    @Override
    public Boolean getResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getBoolean(s);
    }

    @Override
    public Boolean getResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBoolean(i);
    }

    @Override
    public Boolean getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getBoolean(i);
    }
}

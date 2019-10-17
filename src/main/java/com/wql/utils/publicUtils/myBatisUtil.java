package com.wql.utils.publicUtils;

import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class myBatisUtil {
    private static GlobalUtil.BLEnvironment isProduction = GlobalUtil.isProduction();

    private static SqlSessionFactory foodAI_sqlSessionFactory = null;
    private static SqlSessionFactory recipe_sqlSessionFactory = null;
    private static SqlSessionFactory user_sqlSessionFactory = null;
    private static SqlSessionFactory scale_sqlSessionFactory = null;
    private myBatisUtil(){}

    //连接不同数据库的环境变量
    public enum DatabaseEnvironment{
        DEV_FOODAI("development_foodAI"),
        RT_FOODAI("remoteTest_foodAI"),
        PRO_FOODAI("production_foodAI");


        private DatabaseEnvironment(String environmentString){
            this.environmentStr = environmentString;
        }
        private String environmentStr;

        public String getEnvironmentStr() {
            return environmentStr;
        }

        public void setEnvironmentStr(String environmentStr) {
            this.environmentStr = environmentStr;
        }
    }





    //打开foodAI数据库session
    public static SqlSessionFactory openFoodAISqlFactory(){
        switch (isProduction){
            case BL_ENVIRONMENT_DEV:
                return openFactory(DatabaseEnvironment.DEV_FOODAI);
            case BL_ENVIRONMENT_REMOTE_TEST:
                return openFactory(DatabaseEnvironment.RT_FOODAI);
            case BL_ENVIRONMENT_PRODUCTION:
                return openFactory(DatabaseEnvironment.PRO_FOODAI);
            default:
                System.out.println("未配置环境");

        }

        //默认返回开发环境
        return openFactory(DatabaseEnvironment.DEV_FOODAI);

    }



    public static SqlSessionFactory openFactory(DatabaseEnvironment environment){
        //区分环境变量
        switch (environment){
            case DEV_FOODAI:case RT_FOODAI:case PRO_FOODAI:
                //需要使用food数据库
                if (foodAI_sqlSessionFactory == null){
                    foodAI_sqlSessionFactory =  initSessionFactory(environment);
                    return foodAI_sqlSessionFactory;
                }else {
                    return foodAI_sqlSessionFactory;
                }


            default:
                System.out.println("环境变量异常");
                return null;
        }

    }

    public static SqlSessionFactory initSessionFactory(DatabaseEnvironment environment){
        InputStream stream = null;
        String source = "mybatis-conf.xml";

        try {
            //获取到配置数据
            stream = Resources.getResourceAsStream(source);
        }catch (IOException e){
            e.printStackTrace();
        }
        //创建sessionFactory，注意，最后一个参数是环境变量，需要和mybatis-conf中的environment对应
        return new SqlSessionFactoryBuilder().build(stream,environment.environmentStr);

    }




}

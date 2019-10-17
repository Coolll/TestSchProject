package com.wql.utils.publicUtils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalUtil {

    public enum BLEnvironment{
        BL_ENVIRONMENT_DEV,
        BL_ENVIRONMENT_REMOTE_TEST,
        BL_ENVIRONMENT_PRODUCTION
    }

    private static  ResourceLoader loader = new DefaultResourceLoader();
    private static  GlobalUtil util = new GlobalUtil();
    private static  Properties properties = util.loadProperties("Global.properties");

    public GlobalUtil(){

    }

    public static BLEnvironment  isProduction(){

        String environment = properties.getProperty("Environment.tag");

        if (environment.equals("2")){
            return BLEnvironment.BL_ENVIRONMENT_PRODUCTION;
        }else if (environment.equals("1")){
            return BLEnvironment.BL_ENVIRONMENT_REMOTE_TEST;
        }else {
            return BLEnvironment.BL_ENVIRONMENT_DEV;
        }


    }


    private Properties loadProperties(String sourcePath){
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            Resource resource = loader.getResource(sourcePath);
            inputStream = resource.getInputStream();
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return properties;

    }

}

package com.wql.utils.publicUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;



public class CheckParam {

    public String errorMessage;//报错时的提示信息
    private static CheckParam checkParam;//公用的实例对象

    //构造方法
    public static CheckParam shareParam(){
        if (checkParam == null){
            checkParam = new CheckParam();
        }
        return checkParam;
    }

    //校验参数
    public <T> boolean checkValid(T t){

        //获取到所有的属性
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields){
            //如果属性存在NotNull注解
            if (field.isAnnotationPresent(BLNotNull.class)){
                //取消访问控制检查，为了能顺利访问该属性
                field.setAccessible(true);
                //接收属性的值
                Object value = null;
                try {
                    //获取属性的值
                    value = field.get(t);
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }

                //如果值为空，则报错
                if (null == value){
                    //获取注解对象
                    BLNotNull notNull = field.getAnnotation(BLNotNull.class);
                    //把参数名拼入报错信息，这样就能保证报错时能精准到属性名，便于快速定位错误
                    this.errorMessage = "参数"+ notNull.value()+"不可为空";
                    return false;
                }


            }
        }
        return true;
    }


}


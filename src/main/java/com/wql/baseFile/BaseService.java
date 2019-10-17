package com.wql.baseFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BaseService {

    public final Logger logger = LogManager.getLogger("com.wql.controllers");

    public void dealException(Exception e,String fileName,int lineNumber){
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        String errorStr = "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>错误开始<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n====文件:"+fileName+"\n====行数："+lineNumber+"\n====错误信息："+buffer.toString()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>错误结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n\n\n";

        logger.error(errorStr);

    }
}

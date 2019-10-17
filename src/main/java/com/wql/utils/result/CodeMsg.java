package com.wql.utils.result;



public class CodeMsg {
    private int returnCode;
    private String message;
    private String tipMessage;

    public static CodeMsg SUCCESS = new CodeMsg(1000,"Success!");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(1001,"服务端异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(1002,"参数为空");
    public static CodeMsg VERIFY_FAILED = new CodeMsg(1003,"身份校验失败");
    public static CodeMsg MESSAGE_TIP = new CodeMsg(1004,"温馨提示");
    private CodeMsg(int returnCode,String message){
        this.returnCode = returnCode;
        this.message = message;
        this.tipMessage = message;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTipMessage() {
        return tipMessage;
    }
}



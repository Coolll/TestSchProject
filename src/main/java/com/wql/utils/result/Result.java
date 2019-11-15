package com.wql.utils.result;


public class Result<T> {
    private String message;
    private int retCode;
    private T data;
//    public static Result shareResult;

    private Result(T data){
        this.retCode = 1000;
        this.message = "Success!";
        this.data = data;
    }

    private Result(){

    }

    public static<T> Result<T> success(T data){

        Result shareResult = new Result();

        shareResult.setRetCode(1000);
        shareResult.setData(data);
        shareResult.setMessage("Success!");
        return shareResult;
    }

    public static <T> Result<T> success(){
        Result shareResult = new Result();

        shareResult.setRetCode(1000);
        shareResult.setData("");
        shareResult.setMessage("Success!");
        return shareResult;

    }

    public static <T> Result<T> error(CodeMsg msg){
        Result shareResult = new Result();

        shareResult.setRetCode(msg.getReturnCode());
        shareResult.setMessage(msg.getMessage());
        shareResult.setData("");
        return shareResult;
    }

    public static <T> Result<T> error(CodeMsg msg,String msgStr){
        Result shareResult = new Result();
        shareResult.setRetCode(msg.getReturnCode());
        shareResult.setData("");
        shareResult.setMessage(msg.getTipMessage()+msgStr);
        return shareResult;

    }


//    public static<T> Result<T> success(T data){
//        if (shareResult == null){
//            shareResult = new Result();
//        }
//
//        shareResult.setRetCode(1000);
//        shareResult.setData(data);
//        shareResult.setMessage("Success!");
//        return shareResult;
//    }
//
//    public static <T> Result<T> success(){
//        if (shareResult == null){
//            shareResult = new Result();
//        }
//        shareResult.setRetCode(1000);
//        shareResult.setData("");
//        shareResult.setMessage("Success!");
//        return shareResult;
//
//    }
//
//    public static <T> Result<T> error(CodeMsg msg){
//        if (shareResult == null){
//            shareResult = new Result();
//        }
//        shareResult.setRetCode(msg.getReturnCode());
//        shareResult.setMessage(msg.getMessage());
//        shareResult.setData("");
//        return shareResult;
//    }
//
//    public static <T> Result<T> error(CodeMsg msg,String msgStr){
//        if (shareResult == null){
//            shareResult = new Result();
//        }
//        shareResult.setRetCode(msg.getReturnCode());
//        shareResult.setData("");
//        shareResult.setMessage(msg.getTipMessage()+"--"+msgStr);
//        return shareResult;
//
//    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public void setData(T data) {
        this.data = data;
    }

//    public static Result getShareResult() {
//        return shareResult;
//    }
//
//    public static void setShareResult(Result shareResult) {
//        Result.shareResult = shareResult;
//    }
}

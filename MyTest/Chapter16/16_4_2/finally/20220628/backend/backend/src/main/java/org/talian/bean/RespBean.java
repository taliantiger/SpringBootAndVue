package org.talian.bean;

public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;


    /**
     * 构造器
     */
    public RespBean(){
    }

    public RespBean(Integer status, String msg, Object obj){
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }


    /**
     * ok
     */
    //ok1
    public static RespBean ok(String msg){
        return new RespBean(200, msg, null);
    }

    //ok2
    public static RespBean ok(String msg, Object obj){
        return new RespBean(200, msg, obj);
    }


    /**
     * error
     */
    //error1
    public static RespBean error(String msg){
        return new RespBean(500, msg, null);
    }

    //error2
    public static RespBean error(String msg, Object obj){
        return new RespBean(500, msg, obj);
    }




    /**
     * getter、setter
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}






















//
//
//package org.talian.bean;
//
///**
// * Created by sang on 2017/12/29.
// */
//public class RespBean {
//    private String status;
//    private String msg;
//
//    public RespBean() {
//    }
//
//    public RespBean(String status, String msg) {
//
//        this.status = status;
//        this.msg = msg;
//    }
//
//    public String getStatus() {
//
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//}
//
//
//
//

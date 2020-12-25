package com.simple.add.core.thrift;

/**
 * @author : zenghao
 * @Title:
 * @description : thrift 调用结果生成工具类
 * @version: 1.0
 * @date : 2019/07/26 15:38
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class ResponseUtils {

    private ResponseUtils(){}
    public static Response failResult(){
        return failResult(null);
    }

    public static Response failResult(String msg){
        Response response = new Response();
        response.setMsg(msg);
        response.setCode(0);
        return response;
    }

    public static Response successResult(){
        return successResult(null,null);
    }


    public static Response successResult(String data){
        return successResult(data,null);
    }
    public static Response successResult(String data, String msg){
        Response response = new Response();
        response.setData(data);
        response.setMsg(msg);
        response.setCode(1);
        return response;
    }
}

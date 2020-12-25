package com.simple.add.core.constant;

/**
 * @author : zenghao
 * @date : 2018/12/24 17:09
 * @description : 常量类
 */
public class Constant {

    //################# 接口配置 ###############

    public static final String TRANSFER_TYPE_RECE ="TRANSFER_TYPE_RECE";

    public static final String TRANSFER_TYPE_SEND ="TRANSFER_TYPE_SEND";

    /**
     * http token
     */
    public static final String HTTP_TOKEN = "HTTP_TOKEN";

    /**
     * http 传输协议
     */
    public static final String TRANSFER_PROTOCOL_HTTP = "HTTP";

    /**
     * 广州机场传输协议
     */
    public static final String TRANSFER_PROTOCOL_ZGGG_SDK = "ZGGG-SDK";
    /**
     * 哈尔滨机场传输协议
     */
    public static final String TRANSFER_PROTOCOL_ZYHB_SDK = "ZYHB-SDK";

    /**
     * ftp 传输协议
     */
    public static final String TRANSFER_PROTOCOL_FTP = "FTP";

    /**
     * activeMQ传输协议
     */
    public static final String TRANSFER_PROTOCOL_ACTIVEMQ = "activeMQ";

    /**
     * token 过期时间  second
     */
    public static final int HTTP_TOKEN_TIMEOUT = 600;

    /**
     * http 登录
     */
    public static final String  HTTP_LOGIN = "login";

    /**
     * 消息类型
     */
    public static final String  TYPE_RECEIVE= "rece";
    public static final String  TYPE_SEND = "send";
    public static final String  TYPE_FORWARDED = "forwarded";

    /**
     * xml 类型消息
     */
    public static final String XML_TYPE="XML";

    /**
     * json 类型消息
     */
    public static final String JSON_TYPE="JSON";


    //==================内部消息通讯使用====================

    /**
     * 原始数据
     */
    public static final String ORIGIN_DATA = "ORIGIN_DATA";
    /**
     * 新增数据
     */
    public static final String INCREASE_DATA = "INCREASE_DATA";
    /**
     * 修改数据
     */
    public static final String UPDATE_DATA = "UPDATE_DATA";
    /**
     * 删除数据
     */
    public static final String DELETE_DATA = "DELETE_DATA";

    //==================模板缓存key =====================


    /**
     * 模板启用
     */
    public static final String TEMPLATE_IN_USE = "Y";
    /**
     * 模板不启用
     */
    public static final String TEMPLATE_NOT_IN_USE = "N";


    /**
     * 消息接收模板的key
     */
    public static final String RECEIVE_TEMPLATE_KEY = "RECEIVE_TEMPLATE_KEY";
    /**
     * 发送消息模板的key
     */
    public static final String SEND_TEMPLATE_KEY = "SEND_TEMPLATE_KEY";


    /**
     * 消息网元的key
     */
    public  static final String  HEAD_ELEMENT_KEY = "HEAD_ELEMENT_KEY";

    //#############消息模板##################

    public  static final String  HEAD_TEMPLATE_NAME = "HEAD";

    public  static final String  BODY_TEMPLATE_NAME = "BODY";


    //##############登录状态######################

    public  static final String  IS_LOGIN = "LOGIN_STATUS";

    //##############同步消息内容######################

    public static final  String SYNC_TYPE_PLAN="Plan";

    public static final  String SYNC_TYPE_UNFINISH="Unfinished";


    //####################### SDK调用地址 #############################
    /**
     * 广州机场调用
     */
    public static final String AOMIP_REGISTER_KEY="AOMIP_REGISTER_KEY";
    /**
     * 广州机场注册互斥
     */
    public static final String AOMIP_MUTEX_KEY="AOMIP_MUTEX_KEY";
    /**
     * 哈尔滨机场调用
     */
    public static final String ZYHB_REGISTER_KEY="ZYHB_REGISTER_KEY";
    /**
     * 哈尔滨机场注册互斥
     */
    public static final String ZYHB_MUTEX_KEY="ZYHB_MUTEX_KEY";




}

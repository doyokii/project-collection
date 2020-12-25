package com.simple.add.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author : zenghao
 * @Title:
 * @description :
 * @version: 1.0
 * @date : 2019/07/30 10:25
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class PropertiesUtils {
    private static Properties properties = new Properties();

    static {
        InputStream resource = PropertiesUtils.class.getClassLoader().getResourceAsStream("service.properties");
        try {
            properties.load(resource);
        } catch (IOException e) {
            throw  new RuntimeException("read config fail:"+e.getMessage(),e);
        }
    }

    public static String getProperty(String propertyName){
       return getProperty(propertyName,null);
    }

    public static String getProperty(String propertyName,String defaultValue){
        try {
            String value= (String) properties.get(propertyName);
            if(StringUtils.isNotEmpty(value)){
                return value.trim();
            }else {
                return defaultValue;
            }
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     * 根据网卡获得IP地址
     * @return
     * @throws SocketException
     */
    public static String getNetWorkIp() throws SocketException {
        String ip="";
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
            NetworkInterface intf = en.nextElement();
            String name = intf.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    //获得IP
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {

                            if(!"127.0.0.1".equals(ip)){
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        }
        return ip;
    }


}

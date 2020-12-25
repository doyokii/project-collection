package com.simple.add.core.entity;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;

/**
 * @author : zenghao
 * @date : 2019/01/07 17:01
 * @description : 消息响应
 */
public class CanResponse {
    private String sender;

    private String type;


    private Integer code;

    private String description;

    public CanResponse(){}

    public CanResponse(String response){
        parseXml(response);
    }


    public String getSender() {
        return sender;
    }


    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    private  void  parseXml(String xmlResponse) {
        //解析xml
       // Element element =null;
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(xmlResponse.getBytes()));
            Element rootElement = document.getRootElement();
            Element meta = rootElement.element("META");
            this.sender= (String) meta.element("SNDR").getData();
           this.type= (String) meta.element("TYPE").getData();
            Element content = rootElement.element("CONTENT");
            this.description= (String) content.element("DESC").getData();
            this.code = Integer.parseInt((String) content.element("CODE").getData());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /* public static void main(String[] args) {
        String response=("   <?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MSG>\n" +
                "<META>\n" +
                "<SNDR>SYSTEM</SNDR>\n" +
                "<TYPE>ERROR</TYPE>\n" +
                "<STYP/>\n" +
                "<DDTM>2011-05-10 23:21:04</DDTM>\n" +
                "<SEQN/>\n" +
                "</META>\n" +
                "<CONTENT>\n" +
                "<CODE>2</CODE>\n" +
                "<DESC>登录失败,与验证服务器连接失败</DESC>\n" +
                "</CONTENT>\n" +
                "</MSG>").trim();

        CanResponse canResponse = new CanResponse(response);
        Integer code = canResponse.getCode();
        System.out.println(code);
        System.out.println(canResponse.getDescription());
        System.out.println(canResponse.getSender());
        System.out.println(canResponse.getType());
    }
*/
}

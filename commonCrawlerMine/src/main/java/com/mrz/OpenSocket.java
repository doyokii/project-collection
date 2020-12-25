package com.mrz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class OpenSocket {
//    public static void main(String[] args) throws Exception {
//        //成功，但不断开连接
//        getInfoBySocket();
//    }w

    /**
     * 通过Socket访问
     * @throws Exception
     */
    public static void getInfoBySocket() throws Exception{
        Socket socket = new Socket("127.0.0.1", 1080);//这里若是要代理就设置，若无，可以不传参数
        socket.setSoTimeout(5000);//设置读操作超时时间5s,防止长连接
        socket.getOutputStream().write(new String("GET https://www.baidu.com HTTP/1.1\r\n\r\n").getBytes());
        InputStream is = socket.getInputStream();
        /*
        byte[] bs = new byte[1024];
		int i;
		StringBuilder str = new StringBuilder();
		while ((i = is.read(bs)) > 0) {
		    System.out.println(new String(bs, 0, i,"utf-8"));
			//str.append(new String(bs,0,1,"utf-8"));
		}
        */
        //解决乱码和个别中文乱码
        StringBuilder str = new StringBuilder("");
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            str.append(line + "\n");
            //这里必须自行判断跳出，因为socket是不会断开访问的
            if(line.contains("</html>")){
                break;
            }
        }
        br.close();
        isr.close();
        is.close();
    }
}

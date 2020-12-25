package com.mrz;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadPic {
    public void getHtmlPicture(String httpUrl) {
        URL url;
        BufferedInputStream in;

        try {
            System.out.println("取网络图片");
            String fileName = httpUrl.substring(httpUrl.lastIndexOf("/"));
            String filePath = "E:/pic/";
            url = new URL(httpUrl);

            in = new BufferedInputStream(url.openStream());
            File file = new File(filePath + fileName);
            if(!file.exists()){
                file.getParentFile().mkdir();
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int t;
            while ((t = in.read()) != -1) {
                fileOutputStream.write(t);
            }
            fileOutputStream.close();
            in.close();
            System.out.println("图片获取成功");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHtmlCode(String httpUrl) throws IOException {
        String proxyHost = "127.0.0.1";
        String proxyPort = "1080";

        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", proxyPort);

        // 对https也开启代理
        System.setProperty("https.proxyHost", proxyHost);
        System.setProperty("https.proxyPort", proxyPort);

        String content ="";
        URL uu = new URL(httpUrl); // 创建URL类对象
        BufferedReader ii = new BufferedReader(new InputStreamReader(uu
                .openStream())); // //使用openStream得到一输入流并由此构造一个BufferedReader对象
        String input;
        while ((input = ii.readLine()) != null) { // 建立读取循环，并判断是否有读取值
            content += input;
        }
        ii.close();
        return content;
    }

    public void get(String url) throws IOException {

        String searchImgReg = "(?x)(src|SRC|background|BACKGROUND)=('|\")/?(([\\w-]+/)*([\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";
        String searchImgReg2 = "(?x)(src|SRC|background|BACKGROUND)=('|\")(http://([\\w-]+\\.)+[\\w-]+(:[0-9]+)*(/[\\w-]+)*(/[\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";

        String content = this.getHtmlCode(url);
        System.out.println(content);

        Pattern pattern = Pattern.compile(searchImgReg);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(3));
            this.getHtmlPicture(url+matcher.group(3));

        }

        pattern = Pattern.compile(searchImgReg2);
        matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(3));
            this.getHtmlPicture(matcher.group(3));

        }
// searchImgReg =
// "(?x)(src|SRC|background|BACKGROUND)=('|\")/?(([\\w-]+/)*([\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";
    }
    public static void main(String[] args) throws IOException {
//        String url = "https://www.instagram.com/?hl=zh-cn";
//        String url = "https://www.instagram.com/wanimalzoo/?hl=zh-cn";
//        String url = "https://instagram.fhkg3-1.fna.fbcdn.net/v/t51.2885-15/e35/99427172_267408207649277_1334221223640802875_n.jpg?_nc_ht=instagram.fhkg3-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=BmQoqKsLrFYAX_b6Brx&oh=519a6f05fbb3014b8fe12b6153cad7f9&oe=5ECD99BE";
//        String url = "https://www.apple.com.cn/";
//        String url = "https://www.apple.com.cn/iphone-se/";
        String url = "https://www.douban.com/group/szsh/";

        DownloadPic gcp = new DownloadPic();
        gcp.get(url);
//        GetTargetUrlSource getSource = new GetTargetUrlSource();
//        String source = getSource.getSource(url);
//
//        System.out.println(source);
    }
}

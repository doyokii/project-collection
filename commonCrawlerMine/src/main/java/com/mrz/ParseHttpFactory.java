package com.mrz;

import com.mrz.entity.DouBanRentData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Zhuang Jialong
 * @description : 解析类（通用）
 * @date : 2020/5/25 20:25
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class ParseHttpFactory {
    private static final Log log = LogFactory.getLog(ParseHttpFactory.class);

    private final static String PARSE_ENCODEING = "UTF-8";


    public static List<DouBanRentData> pasreHttpByJSoup(String html, String sheetNum) {
        Document parse = null;
        int i = 0;
        int listNo =0;
        List<DouBanRentData> parseData = new LinkedList<>();
        //解析html，按照什么编码进行解析html
        parse = Jsoup.parse(html, PARSE_ENCODEING);
        Element elementById = parse.getElementById("wrapper");
        Elements elementsByClass = elementById.getElementsByClass("olt");
        for (Element element : elementsByClass) {
            Elements time = element.getElementsByClass("time");
            for (Element t : time) {
                DouBanRentData douBanRentData = new DouBanRentData();
                douBanRentData.setLastRespTime(t.text());
                parseData.add(douBanRentData);
            }

            Elements elementsByClass2 = element.getElementsByClass("title");
            for (Element element1 : elementsByClass2) {

                Elements td = element1.select("td");
                for (Element element2 : td) {
                    Elements td2 = element2.select("td");
                    for (Element element3 : td2) {
                        if(i==parseData.size()){break;}
                        //获取a标签
                        Elements a = element3.select("a");
                        parseData.get(listNo).setTitle(a.attr("title"));
                        parseData.get(listNo).setUrl(a.attr("href"));
                        parseData.get(listNo).setNumber(java.lang.String.valueOf(i++));
                        parseData.get(listNo).setSheet("第" + sheetNum + "页");

//                        parseData.add(douBanRentData);
//                        System.out.println(a.attr("title"));
//                        System.out.println(a.attr("href"));
//                        System.out.println(i++);
                    }
                }
                listNo++;
            }
        }
        return parseData;
    }
}

package com.mrz;

import com.mrz.entity.DouBanRentData;
import com.mrz.excelUtil.ExcelWriter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Zhuang Jialong
 * @description : 数据爬取启动类
 * @date : 2020/5/25 20:20
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class ToDo {
    private static final String DOUBAN_URL = "https://www.douban.com/group/szsh/discussion?start=";
    public static void main(String[] args) throws Exception{

        //创建一个工作簿 即excel文件,再在该文件中创建一个sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheetOne");
        //25条每页
        for (int i=1;i<32;i++){
            System.out.println("开始抓取第"+i+"页数据............");
            String url = DOUBAN_URL + i*25;
            String source = GetTargetUrlSource.getSource(url);
            System.out.println("当前url为"+url+"............");
            List<DouBanRentData> douBanRentData = ParseHttpFactory.pasreHttpByJSoup(source, String.valueOf(i));
            List<String> rowName = new ArrayList<>();
            if (i==1){
                rowName.add("页码");
                rowName.add("序号");
                rowName.add("标题");
                rowName.add("链接");
                rowName.add("最后更新时间");
                ExcelWriter.excelWriter(rowName,douBanRentData,sheet,wb);
            }else {
                ExcelWriter.excelWriter(douBanRentData,String.valueOf(i),sheet,wb);
//                int da = 1 ;
//                int i1 = da / 0;
            }
        }
    }
}

package com.mrz.excelUtil;

import com.mrz.entity.DouBanRentData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author : Zhuang Jialong
 * @description : excel写入类
 * @date : 2020/5/26 1:54
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class ExcelWriter {

    private static  String FILE_PATH_PREFIX ;
    private static final String FILE_DEFAULT_NAME = "book1.xls";
    private static String outputFile;

    //支持配置文件配置默认路径，根据不同环境对应配置
    //路径最后需加 "/"
    static {
        InputStream inputStream=null;
        try{
            inputStream = ExcelWriter.class.getClass().
                    getResourceAsStream("/service.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            FILE_PATH_PREFIX = properties.getProperty("FILE_PATH_PREFIX");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param rowName
     * @param rowValue
     * @throws Exception
     */
    public static void excelWriter(List<String> rowName, List<DouBanRentData> rowValue, HSSFSheet sheet, HSSFWorkbook wb) throws Exception {
        //在sheet中创建一行
        HSSFRow row = sheet.createRow(0);
        //创建表头
        for (int i = 0; i < rowName.size(); i++) {
            row.createCell(i).setCellValue(rowName.get(i));
        }
        for (int i = 1; i < rowValue.size(); i++) {
            HSSFRow valueRow = sheet.createRow(i);
            valueRow.createCell(0).setCellValue(rowValue.get(i).getSheet());
            valueRow.createCell(1).setCellValue(rowValue.get(i).getNumber());
            valueRow.createCell(2).setCellValue(rowValue.get(i).getTitle());
            valueRow.createCell(3).setCellValue(rowValue.get(i).getUrl());
            valueRow.createCell(4).setCellValue(rowValue.get(i).getLastRespTime());
        }

        //最后写回磁盘
        FileOutputStream out = new FileOutputStream("C:\\Users\\wrunv\\OneDrive\\TEMPORARY_FOLDER\\douBanRent.xls");
        wb.write(out);
        out.close();

    }

    public static void excelWriter(List<DouBanRentData> rowValue, String rowNum, HSSFSheet sheet, HSSFWorkbook wb) throws Exception {

        for (int i = 1; i < rowValue.size(); i++) {
            HSSFRow row = sheet.createRow(((Integer.valueOf(rowNum) - 1) * 25) + 1 + i);
            for (int j = 0; j < rowValue.size(); j++) {

                row.createCell(0).setCellValue(rowValue.get(i).getSheet());
                row.createCell(1).setCellValue(rowValue.get(i).getNumber());
                row.createCell(2).setCellValue(rowValue.get(i).getTitle());
                row.createCell(3).setCellValue(rowValue.get(i).getUrl());
                row.createCell(4).setCellValue(rowValue.get(i).getLastRespTime());

            }
        }
        //最后写回磁盘
        String filePath = null;
        String fileName = null;
        if(outputFile==null){
            Scanner scanner = new Scanner(System.in);
        System.out.println("========请输入文件保存路径========" + " \r\n" +
                "文件只能存放在桌面目录下以便随时取用，生成文件默认文件名：book1");
        while (scanner.hasNextLine()) {
            //todo scanner.nextLine()&scanner.next() 后者必须输入有效字符串；前者以enter为结束标示
            filePath = scanner.nextLine();
            if (filePath.isEmpty()) break;
        }
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("========请输入文件名========" + " \r\n"
//      + "文件只能存放在桌面目录下以便随时取用，生成文件默认文件名：book1"
        );
        while (scanner2.hasNextLine()) {
            fileName = scanner.nextLine();
            if (fileName.isEmpty()) break;
        }
        scanner.close();
        scanner2.close();
        if (!filePath.isEmpty() && !fileName.isEmpty()) {
            File file = new File(FILE_PATH_PREFIX + filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File zzz = new File(FILE_PATH_PREFIX + filePath, fileName);
            zzz.createNewFile();
            outputFile = FILE_PATH_PREFIX + filePath + fileName;
        } else {
            File file = new File(FILE_PATH_PREFIX, FILE_DEFAULT_NAME);
            file.createNewFile();
            outputFile = FILE_PATH_PREFIX + FILE_DEFAULT_NAME;
        }
        }
//            FileOutputStream out = new FileOutputStream("C:\\Users\\wrunv\\OneDrive\\TEMPORARY_FOLDER\\douBanRent.xls");
        FileOutputStream out = new FileOutputStream(outputFile);
        wb.write(out);
        out.close();
    }

}


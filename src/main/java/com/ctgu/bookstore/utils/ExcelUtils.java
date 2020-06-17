package com.ctgu.bookstore.utils;

import com.ctgu.bookstore.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 操作excel
 * */
@Slf4j
public class ExcelUtils {

    public static List<Book> excelToShopIdList(InputStream inputStream) {
        List<Book> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum();
            //            System.out.println("总行数有多少行" + rowLength);
            //工作表的列
            Row row = sheet.getRow(0);

            //总列数
            int colLength = row.getLastCellNum();
            //            System.out.println("总列数有多少列" + colLength);
            //得到指定的单元格
            Cell cell = row.getCell(0);
            for (int i = 1; i <= rowLength; i++) {
                Book jiFenExcel = new Book();
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    //                    System.out.print(cell + ",");
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String data = cell.getStringCellValue();
                        data = data.trim();
                        //                        System.out.print(data);
                        //                        if (StringUtils.isNumeric(data)) {
                        if (j == 0) {
                            jiFenExcel.setIsbn(data);
                        }else if (j == 1) {
                            jiFenExcel.setBookName(data);
                        }else if (j == 2) {
                            jiFenExcel.setBookPicture(data);
                        }else if (j == 3) {
                            jiFenExcel.setAuthor(data);
                        }else if (j == 4) {
                            jiFenExcel.setPrice(Float.valueOf(data));
                        }else if (j == 5) {
                            jiFenExcel.setDescription(data);
                        }else if (j == 6) {
                            jiFenExcel.setBookType(data);
                        }else if (j == 7) {
                            jiFenExcel.setRepertory(Integer.valueOf(data));
                        }else if (j == 8) {
                            jiFenExcel.setPress(data);
                        }else if (j == 9) {
                            jiFenExcel.setCllectNum(Integer.valueOf(data));
                        }else if (j == 10) {
                            jiFenExcel.setSaleNum(Integer.valueOf(data));
                        }

                        //                        }
                    }
                }
                list.add(jiFenExcel);

            }
        } catch (Exception e) {
        }
        return list;
    }
}

package cn.poi.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

/**
 * @author Jason
 * @create 2019-12-13-14:51
 */


public class PoiTest {

    @Test
    public void testRead07() throws Exception{
        //1、创建输入流
        InputStream in = new FileInputStream("F:\\template\\07.xlsx");

        //2、创建workbook
        Workbook workbook = new XSSFWorkbook(in);

        //3、创建sheet
        Sheet sheet = workbook.getSheetAt(0);

        //4、创建row
        Row row = sheet.getRow(0);

        //5、创建cell
        Cell cell = row.getCell(0);

        //6、读取
        String value = cell.getStringCellValue();
        System.out.println(value);

        //7、关闭流
        in.close();
    }

    @Test
    public void testRead03() throws Exception{
        //1、创建输入流
        InputStream in = new FileInputStream("F:\\template\\03.xls");

        //2、创建workbook
        Workbook workbook = new HSSFWorkbook(in);

        //3、创建sheet
        Sheet sheet = workbook.getSheetAt(0);

        //4、创建row
        Row row = sheet.getRow(0);

        //5、创建cell
        Cell cell = row.getCell(0);

        //6、读取
        String value = cell.getStringCellValue();
        System.out.println(value);

        //7、关闭流
        in.close();
    }

    @Test
    public void testWrite07() throws Exception {
        //1、创建workbook
        Workbook workbook = new XSSFWorkbook();

        //2、创建sheet
        Sheet sheet = workbook.createSheet();

        //3、创建行row
        Row row = sheet.createRow(0);

        //4、创建cell
        Cell cell = row.createCell(0);

        //5、写入数据
        cell.setCellValue("jason");

        //6、创建输出流
        OutputStream out = new FileOutputStream("F:\\template\\07.xlsx");

        //7、输出流写出到文件
        workbook.write(out);

        //8、关闭输出流
        out.close();
    }

    @Test
    public void testWrite03() throws Exception {
        //1、创建workbook
        Workbook workbook = new HSSFWorkbook();

        //2、创建sheet
        Sheet sheet = workbook.createSheet();

        //3、创建行row
        Row row = sheet.createRow(0);

        //4、创建cell
        Cell cell = row.createCell(0);

        //5、写入数据
        cell.setCellValue("jason");

        //6、创建输出流
        OutputStream out = new FileOutputStream("F:\\template\\03.xls");

        //7、输出流写出到文件
        workbook.write(out);

        //7、关闭输出流
        out.close();
    }
}

package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private String path;

    public ExcelUtil(String path) {
    	this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(sheetName);
            return (ws != null) ? ws.getLastRowNum() : 0;
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(sheetName);
            XSSFRow row = (ws != null) ? ws.getRow(rownum) : null;
            return (row != null) ? row.getLastCellNum() : 0;
        }
    }

    public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(sheetName);
            XSSFRow row = (ws != null) ? ws.getRow(rownum) : null;
            XSSFCell cell = (row != null) ? row.getCell(cellnum) : null;

            DataFormatter formatter = new DataFormatter();
            return (cell != null) ? formatter.formatCellValue(cell) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
        File xlfile = new File(path);
        XSSFWorkbook wb;
        XSSFSheet ws;

        if (!xlfile.exists()) {
            wb = new XSSFWorkbook();
            ws = wb.createSheet(sheetName);
        } else {
            try (FileInputStream fi = new FileInputStream(path)) {
                wb = new XSSFWorkbook(fi);
                ws = wb.getSheet(sheetName);
                if (ws == null) {
                    ws = wb.createSheet(sheetName);
                }
            }
        }

        XSSFRow row = ws.getRow(rownum);
        if (row == null) {
            row = ws.createRow(rownum);
        }

        XSSFCell cell = row.createCell(cellnum);
        cell.setCellValue(data);

        try (FileOutputStream fo = new FileOutputStream(path)) {
            wb.write(fo);
        } finally {
            wb.close();
        }
    }
}

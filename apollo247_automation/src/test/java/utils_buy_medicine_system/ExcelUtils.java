package utils_buy_medicine_system;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    public static String getData(int row, int col) {

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/testdata/data.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet("Sheet1");

            return sheet.getRow(row).getCell(col).getStringCellValue();

        } catch (Exception e) {
            return null;
        }
    }
}
package utils_doctor_appointment_system;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    private Sheet sheet;
    private DataFormatter formatter = new DataFormatter();

    public ExcelReader(String path, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook wb = WorkbookFactory.create(fis);
            sheet = wb.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException("Error loading Excel file: " + e.getMessage());
        }
    }

    public Map<String, String> getData(String tcID) {
        Map<String, String> map = new HashMap<>();

        Row header = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            if (formatter.formatCellValue(row.getCell(0)).equals(tcID)) {
                for (int j = 0; j < header.getLastCellNum(); j++) {
                    map.put(
                        formatter.formatCellValue(header.getCell(j)),
                        formatter.formatCellValue(row.getCell(j))
                    );
                }
            }
        }
        return map;
    }
}
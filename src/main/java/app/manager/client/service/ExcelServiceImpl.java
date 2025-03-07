package app.manager.client.service;

import app.manager.client.service.implement.ExcelService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void exportToExcel(HttpServletResponse response, List<Object> data, String sheetName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        int rowNum = 0;
        for (Object obj : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true); // Cho phép truy cập vào các trường private
                Cell cell = row.createCell(colNum++);
                try {
                    Object value = field.get(obj); // Lấy giá trị của trường
                    cell.setCellValue(value != null ? value.toString() : ""); // Ghi giá trị
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=export.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

//    public List<Object> importFromExcel(MultipartFile file) throws IOException {
//        List<String[]> data = new ArrayList<>();
//        Workbook workbook = new XSSFWorkbook(file.getInputStream());
//        Sheet sheet = workbook.getSheetAt(0);
//
//        for (Row row : sheet) {
//            List<String> rowData = new ArrayList<>();
//            for (Cell cell : row) {
//                rowData.add(cell.getStringCellValue());
//            }
//            data.add(rowData.toArray(new String[0]));
//        }
//
//        workbook.close();
//        return data;
//    }
}

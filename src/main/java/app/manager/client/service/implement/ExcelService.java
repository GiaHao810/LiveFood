package app.manager.client.service.implement;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ExcelService {
    void exportToExcel(HttpServletResponse response, List<Object> data, String sheetname) throws IOException;
}

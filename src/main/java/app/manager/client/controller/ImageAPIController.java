package app.manager.client.controller;

import app.manager.client.dto.response.ResponseObject;
import app.manager.client.entity.Product;
import app.manager.client.service.implement.ProductService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/media/")
@RequiredArgsConstructor
public class ImageAPIController {
    @Autowired
    ProductService productService;
    

    /**
     * Function to get Media from database with Media's ID
     * @param id
     */

//    /**
//     * Function to upload multiple File to Product Media with Product's ID and save it to DIRECTORY
//     * @param productId
//     * @param files
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/uploadproductMedia/{productId}")
//    public ResponseEntity<ResponseObject> uploadproductMedias(@PathVariable String productId,
//                                                      @RequestParam("files") MultipartFile[] files) throws IOException {
//        // Kiểm tra sản phẩm có tồn tại
//        Optional<Product> product = productService.findById(productId);
//        if (product.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("Product not found",
//                            "NOT FOUND",
//                            productId)
//            );
//        }
//
//        try {
//            // Lưu từng hình ảnh
//            for (MultipartFile file : files) {
//                // Định nghĩa thư mục lưu trữ hình ảnh
//                String DIRECTORY = "src/main/resources/static/image/product/";
//                String FILENAME = file.getOriginalFilename();
//                Path FILEPATH = Paths.get(DIRECTORY + FILENAME);
//
//                // Lưu hình ảnh vào thư mục
//                Files.copy(file.getInputStream(), FILEPATH, StandardCopyOption.REPLACE_EXISTING);
//
//            }
//            return ResponseEntity.ok(
//                    new ResponseObject("Images uploaded successfully",
//                            "OK",
//                            product)
//            );
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                    new ResponseObject(e.toString(),
//                            "INTERNAL SERVER ERROR",
//                            productId)
//            );
//        }
//    }
}

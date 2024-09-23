package app.manager.client.controller;

import app.manager.client.dto.ProductMedia;
import app.manager.client.model.Product;
import app.manager.client.service.implement.ProductMediaService;
import app.manager.client.service.implement.ProductService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @Autowired
    ProductMediaService productMediaService;

    @GetMapping("/getMedia/{id}")
    public ResponseEntity<ProductMedia> getMedia(@PathVariable String id){
        return ResponseEntity.ok(productMediaService.findById(id));
    }

    @PostMapping("/uploadproductMedia/{productId}")
    public ResponseEntity<String> uploadproductMedias(@PathVariable String productId,
                                                      @RequestParam("files") MultipartFile[] files) throws IOException {
        // Kiểm tra sản phẩm có tồn tại
        Optional<Product> product = productService.findById(productId);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        try {
            // Lưu từng hình ảnh
            for (MultipartFile file : files) {
                // Định nghĩa thư mục lưu trữ hình ảnh
                String directory = "src/main/resources/static/image/";
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(directory + fileName);

                // Lưu hình ảnh vào thư mục
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Lưu đường dẫn hình ảnh vào database
                ProductMedia productMedia = new ProductMedia();
                productMedia.setProduct(product.get());
                productMedia.setUrl("/image/" + fileName);

                ProductMedia product1 = productMediaService.save(productMedia);
            }
            return ResponseEntity.ok("Images uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

}

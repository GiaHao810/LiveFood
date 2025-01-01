package app.manager.client.controller;

import app.manager.client.dto.request.AddProductRequest;
import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.exeption.resource.ResourceExistException;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.entity.Product;
import app.manager.client.service.implement.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductAPIController {
    private final ProductService productService;
    private final Logger log = LoggerFactory.getLogger(ProductAPIController.class);

    /**
     * Get all Product Information
     * @return List of Product
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        productService.getAllProduct())
        );
    }

    /**
     * Send an add product request
     * @param request
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AddProductRequest request) {
        productService.addProduct(request);
        return ResponseEntity.ok(new ResponseObject<>(true));
    }

    /**
     * Get product information with ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseObject<>(true,
                        productService.findById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject<>(true)
                );
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable(required = true) String id,
            @Valid @RequestBody(required = true) UpdateProductRequest updateProductRequest
    ) {
        return ResponseEntity.ok(new ResponseObject<>(true,
                        productService.updateProduct(id, updateProductRequest)
                    )
                );
    }

    @GetMapping("/products")
    public Page<Product> getWithPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "VEGETABLE") String category) {
        return productService.getPage(page, size, category);
    }
}

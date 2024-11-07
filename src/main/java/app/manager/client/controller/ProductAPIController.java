package app.manager.client.controller;

import app.manager.client.dto.Response;
import app.manager.client.dto.request.AddProductRequest;
import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.exeption.resource.ResourceExistException;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.model.Category;
import app.manager.client.model.Product;
import app.manager.client.service.implement.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<Response> getAllProduct() {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        productService.getAllProduct())
        );
    }

    /**
     * Send an add product request
     * @param request
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Response> addProduct(@Valid @RequestBody AddProductRequest request) {

        productService.findByName(request.getName())
                .ifPresent(product -> {
                    throw new ResourceExistException("Product Information is existed");
                });

        Product product = Product.builder()
                        .code("TEMP-CODE")
                        .name(request.getName())
                        .price(request.getPrice())
                        .category(Category.valueOf(request.getCategory().toUpperCase()))
                        .build();

        productService.save(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(
                        "Product added successfully!!",
                        "SUCCESS",
                        product
                        )
                );
    }

    /**
     * Get product information with ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        productService.findById(id))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable String id) {
        productService.findById(id)
                .ifPresentOrElse(product -> productService.deleteProduct(id),
                        () -> {
                    throw new ResourceNotFoundException("ID invalid");
                }
                );
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new ResponseObject("Product Deleted",
                                "SUCCESS",
                                id)
                );
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateProduct(
            @PathVariable(required = true) String id,
            @RequestBody(required = true) UpdateProductRequest updateProductRequest
    ) {

        if(updateProductRequest.code().isBlank() ||
                updateProductRequest.name().isBlank() ||
                updateProductRequest.price().isNaN() ||
                updateProductRequest.category().isBlank()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ResponseObject("Invalid update data",
                                    "FAIL",
                                    updateProductRequest
                            )
                    );
        }
        Optional<Product> productOptional = productService.findById(id);

        if(productOptional.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResponseObject("Cant find any product with this ID " + id,
                                    "FAIL",
                                    id
                            )
                    );
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ResponseObject("Product updated",
                                "OK",
                                updateProductRequest)
                );
    }

    @GetMapping("/products")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "VEGETABLE") String category) {
        return productService.getPage(page, size, category);
    }
}

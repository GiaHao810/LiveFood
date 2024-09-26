package app.manager.client.controller;

import app.manager.client.dto.Response;
import app.manager.client.dto.request.AddProductRequest;
import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.model.Category;
import app.manager.client.model.Product;
import app.manager.client.service.implement.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/")
    public ResponseEntity<Response> getAllProduct() {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        productService.getAllProduct())
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addProduct(@RequestBody AddProductRequest request) {

        if(request.category().isBlank() ||
                request.price().isNaN() ||
                request.name().isBlank()
        ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseObject("The register information is invalid!!!",
                            "FAIL",
                            request
                    ));
        }

        if(productService.findByName(
                request.name()).isPresent()
        ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseObject("Product name already existed!!!",
                            "FAIL",
                            request
                    ));
        }

        productService.save(
                Product.builder()
                        .code("TEMP-CODE")
                        .name(request.name())
                        .price(request.price())
                        .category(Category.valueOf(request.category().toUpperCase()))
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(
                        "Product added successfully!!",
                        "SUCCESS",
                        productService.findByName(request.name()).get()
                        )
                );
    }

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
        if(productService.findById(id).isEmpty()){
            return ResponseEntity.status(200)
                    .body(
                            new ResponseObject("Cant find any product with this ID " + id,
                                    "FAIL"
                                    , id
                            )
                    );
        }
        productService.deleteProduct(id);
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
}

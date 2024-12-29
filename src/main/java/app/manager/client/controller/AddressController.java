package app.manager.client.controller;

import app.manager.client.dto.response.ResponseObject;
import app.manager.client.entity.Address;
import app.manager.client.service.implement.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/address")
@Validated
@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        service.getAllAddress())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseObject<>(true,
                service.findById(id)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.deleteAddress(id);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Address address){
        service.addAddress(address);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }
}

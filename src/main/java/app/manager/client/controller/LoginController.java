package app.manager.client.controller;

import app.manager.client.dto.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String loginA(){
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> loginB(){

        return ResponseEntity.ok(
                new ResponseObject("", null)
        );
    }
}

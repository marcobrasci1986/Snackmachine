package be.avidoo.ddd.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {


    public AppController() {

    }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("ok");
    }
}

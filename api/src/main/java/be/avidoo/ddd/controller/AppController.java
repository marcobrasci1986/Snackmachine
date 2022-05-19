package be.avidoo.ddd.controller;


import be.avidoo.ddd.service.NameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final NameService nameService;

    public AppController(NameService nameService) {
        this.nameService = nameService;
    }


    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok(nameService.findName());
    }
}

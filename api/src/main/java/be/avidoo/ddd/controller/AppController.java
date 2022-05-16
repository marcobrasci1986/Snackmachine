package be.avidoo.ddd.controller;

import be.avidoo.ddd.core.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public ResponseEntity<Person> index() {
        Person person = new Person("test");
        return ResponseEntity.ok(person);
    }
}

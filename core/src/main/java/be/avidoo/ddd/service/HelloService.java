package be.avidoo.ddd.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final NameService nameService;

    public HelloService(NameService nameService) {
        this.nameService = nameService;
    }

    public String printHello() {
        return "Hello " + nameService.findName();
    }
}

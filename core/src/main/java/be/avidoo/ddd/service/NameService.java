package be.avidoo.ddd.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @Value("${name}")
    private String name;

    public String findName() {
        return name;
    }
}

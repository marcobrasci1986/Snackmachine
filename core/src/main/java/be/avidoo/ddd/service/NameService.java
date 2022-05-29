package be.avidoo.ddd.service;

import org.springframework.stereotype.Service;

@Service
public class NameService {

    public String findName() {
        return "Marco";
    }
}

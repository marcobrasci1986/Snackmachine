package be.avidoo.ddd.controller;


import be.avidoo.ddd.boundedcontext.snackmachine.snackmachine.SnackMachine;
import be.avidoo.ddd.boundedcontext.snackmachine.snackmachine.SnackMachineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final SnackMachineRepository snackMachineRepository;

    public AppController(SnackMachineRepository snackMachineRepository) {
        this.snackMachineRepository = snackMachineRepository;
    }

    @GetMapping("/")
    public ResponseEntity<SnackMachine> snackmachine() {

        return ResponseEntity.ok(snackMachineRepository.findById(1L).orElseThrow());
    }
}

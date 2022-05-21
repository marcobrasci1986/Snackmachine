package be.avidoo.ddd.controller;


import be.avidoo.ddd.boundedcontext.snackmachine.snackmachine.SnackMachine;
import be.avidoo.ddd.boundedcontext.snackmachine.snackmachine.SnackMachineRepository;
import be.avidoo.ddd.service.SnackmachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final SnackMachineRepository snackMachineRepository;
    private final SnackmachineService snackmachineService;

    public AppController(SnackMachineRepository snackMachineRepository, SnackmachineService snackmachineService) {
        this.snackMachineRepository = snackMachineRepository;
        this.snackmachineService = snackmachineService;
    }

    @GetMapping("/")
    public ResponseEntity<SnackMachine> snackmachine() {

        return ResponseEntity.ok(snackMachineRepository.findById(1L).orElseThrow());
    }

    @GetMapping("/run")
    public void run() {
        snackmachineService.run();
    }
}

package be.avidoo.ddd.service;

import be.avidoo.ddd.boundedcontext.atm.Atm;
import be.avidoo.ddd.boundedcontext.atm.AtmRepository;
import be.avidoo.ddd.boundedcontext.management.HeadOfficeRepository;
import be.avidoo.ddd.sharedkernel.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SnackmachineService {
    private final AtmRepository atmRepository;
    private final HeadOfficeRepository headOfficeRepository;

    public SnackmachineService(AtmRepository atmRepository, HeadOfficeRepository headOfficeRepository) {
        this.atmRepository = atmRepository;
        this.headOfficeRepository = headOfficeRepository;

    }

    public void run() {
        Atm atm = atmRepository.findById(1L).orElseThrow();

        atm.loadMoney(Money.ONE_EURO);
        atm.loadMoney(Money.ONE_EURO);


        atm.takeMoney(1);

        atmRepository.save(atm);
    }
}

package be.avidoo.ddd.boundedcontext.management;

import be.avidoo.ddd.boundedcontext.atm.BalanceChangedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class HeadOfficeDomainService {

    private final HeadOfficeRepository headOfficeRepository;

    public HeadOfficeDomainService(HeadOfficeRepository headOfficeRepository) {
        this.headOfficeRepository = headOfficeRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handle(BalanceChangedEvent event) {
        HeadOffice headOffice = headOfficeRepository.findById(1L).orElseThrow();
        headOffice.changeBalance(event.delta());
    }
}

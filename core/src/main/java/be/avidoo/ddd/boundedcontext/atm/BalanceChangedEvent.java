package be.avidoo.ddd.boundedcontext.atm;

import be.avidoo.ddd.common.DomainEvent;

public record BalanceChangedEvent(double delta) implements DomainEvent {

}

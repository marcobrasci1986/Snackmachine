package be.avidoo.ddd.boundedcontext.management;

import be.avidoo.ddd.common.AggregateRoot;
import be.avidoo.ddd.sharedkernel.Money;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HeadOffice")
@Getter
public class HeadOffice extends AggregateRoot<HeadOffice> {

    @Column(name = "balance")
    private double balance;
    @Embedded
    private Money cash;

    public void changeBalance(double delta) {
        this.balance += delta;
    }


}

package be.avidoo.ddd.boundedcontext.atm;


import be.avidoo.ddd.sharedkernel.Money;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
@Table(name = "Atm")
@Getter
public class Atm extends AbstractAggregateRoot<Atm> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public static final double COMMISSION = 1.01;
    @Embedded
    private Money moneyInside = Money.NONE;
    @Column(name = "moneyCharged")
    private double moneyCharged;

    public boolean canTakeMoney(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Cannot request 0 or less than 0");
        if (amount > this.moneyInside.amount())
            throw new IllegalArgumentException("Not enough money inside");
        if (!this.moneyInside.canAllocate(amount))
            throw new IllegalArgumentException("Not enough change to match request");

        return true;
    }

    public void takeMoney(double amount) {
        if (this.canTakeMoney(amount)) {
            Money output = this.moneyInside.allocate(amount);
            this.moneyInside = this.moneyInside.subtract(output);

            double commission = calculateAmountWithCommission(amount);
            this.moneyCharged = this.moneyCharged + commission;

            registerEvent(new BalanceChangedEvent(this.moneyCharged));
        }
    }

    private double calculateAmountWithCommission(double amount) {
        return amount * COMMISSION;
    }

    public void loadMoney(Money money) {
        this.moneyInside = this.moneyInside.sum(money);
    }

}

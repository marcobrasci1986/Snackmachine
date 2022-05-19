package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
class Money extends ValueObject {

    public static Money NONE = new Money(0, 0, 0, 0, 0);
    public static Money FIFTY_CENT = new Money(1, 0, 0, 0, 0);
    public static Money ONE_EURO = new Money(0, 1, 0, 0, 0);
    public static Money TWO_EURO = new Money(0, 0, 2, 0, 0);
    public static Money FIVE_EURO = new Money(0, 0, 0, 1, 0);
    public static Money TEN_EURO = new Money(0, 0, 0, 0, 1);

    private int fiftyCentCoin;
    private int oneEuroCoin;
    private int twoEuroCoin;
    private int fiveEuroBill;
    private int tenEuroBill;

    @Builder
    public Money(int fiftyCentCoin, int oneEuroCoin, int twoEuroCoin, int fiveEuroBill, int tenEuroBill) {
        if (fiftyCentCoin < 0)
            throw new IllegalArgumentException();
        if (oneEuroCoin < 0)
            throw new IllegalArgumentException();
        if (twoEuroCoin < 0)
            throw new IllegalArgumentException();
        if (fiveEuroBill < 0)
            throw new IllegalArgumentException();
        if (tenEuroBill < 0)
            throw new IllegalArgumentException();

        this.fiftyCentCoin = fiftyCentCoin;
        this.oneEuroCoin = oneEuroCoin;
        this.twoEuroCoin = twoEuroCoin;
        this.fiveEuroBill = fiveEuroBill;
        this.tenEuroBill = tenEuroBill;
    }

    public Money sum(Money money) {
        return Money.builder()
                .fiftyCentCoin(this.fiftyCentCoin + money.fiftyCentCoin)
                .oneEuroCoin(this.oneEuroCoin + money.oneEuroCoin)
                .twoEuroCoin(this.twoEuroCoin + money.twoEuroCoin)
                .fiveEuroBill(this.fiveEuroBill + money.fiveEuroBill)
                .tenEuroBill(this.tenEuroBill + money.tenEuroBill)
                .build();
    }

    public double amount() {
        return this.fiftyCentCoin * 0.5 +
                this.oneEuroCoin +
                this.twoEuroCoin * 2 +
                this.fiveEuroBill * 5 +
                this.tenEuroBill * 10;
    }

    public Money subtract(Money money) {
        return new Money(
                this.fiftyCentCoin - money.getFiftyCentCoin(),
                this.oneEuroCoin - money.getOneEuroCoin(),
                this.twoEuroCoin - money.getTwoEuroCoin(),
                this.fiveEuroBill - money.getFiveEuroBill(),
                this.tenEuroBill - money.getTenEuroBill()
        );
    }

    public boolean canAllocate(double amount) {
        Money money = this.allocateCore(amount);

        return money.amount() == amount;
    }

    public Money allocate(double amount) {
        if (!canAllocate(amount)) {
            throw new IllegalStateException("Cannot allocate change");
        }

        return this.allocateCore(amount);
    }

    private Money allocateCore(double amount) {
        int tenEuroCount = (int) Math.min((amount / 10), this.tenEuroBill);
        amount = amount - tenEuroCount * 10;

        int fiveEuroCount = (int) Math.min((amount / 5), this.fiveEuroBill);
        amount = amount - fiveEuroCount * 5;

        int twoEuroCount = (int) Math.min((amount / 2), this.twoEuroCoin);
        amount = amount - twoEuroCount * 2;

        int oneEuroCount = (int) Math.min(amount, this.oneEuroCoin);
        amount = amount - oneEuroCount;

        int fiftyCentCount = (int) Math.min((amount / 0.5), this.fiftyCentCoin);

        return new Money(fiftyCentCount, oneEuroCount, twoEuroCount, fiveEuroCount, tenEuroCount);
    }


}

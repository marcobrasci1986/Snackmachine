package be.avidoo.ddd.aggregate;

import be.avidoo.ddd.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
class Money extends ValueObject {

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
}

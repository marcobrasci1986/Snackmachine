package be.avidoo.ddd.core.aggregate;

public class MoneyFactory {

    public static Money NONE = new Money(0, 0, 0, 0, 0);
    public static Money FIFTY_CENT = new Money(1, 0, 0, 0, 0);
    public static Money ONE_EURO = new Money(0, 1, 0, 0, 0);
    public static Money TWO_EURO = new Money(0, 0, 2, 0, 0);
    public static Money FIVE_EURO = new Money(0, 0, 0, 1, 0);
    public static Money TEN_EURO = new Money(0, 0, 0, 0, 1);
}

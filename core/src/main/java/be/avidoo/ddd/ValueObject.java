package be.avidoo.ddd;

public abstract class ValueObject {

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}

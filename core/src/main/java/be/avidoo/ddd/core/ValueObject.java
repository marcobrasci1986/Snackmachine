package be.avidoo.ddd.core;

public abstract class ValueObject {

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}

package org.example;

public interface Storable {
    boolean isStored();
    void unStore();
    void store(Storage<?> storage);
}
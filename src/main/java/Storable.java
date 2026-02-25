public interface Storable {
    boolean isStored();
    void unStore();
    void store(Storage<?> storage);
}
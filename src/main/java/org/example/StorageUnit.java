package org.example;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class StorageUnit<T extends Storable> implements Storage<T> {
        private final int capacity;
        private final ArrayList<T> storage;

        public StorageUnit(int capacity) {
            this.capacity = capacity;
            this.storage = new ArrayList<>();
        }

        public abstract Point2D.Double getPosition();

        public boolean isEmpty() { return this.storage.isEmpty(); }

        public boolean isFull() { return this.storage.size() == this.capacity; }

        @Override
        public void load(T item) {
            if (isFull()) {
                throw new IllegalStateException("Storage is full.");
            }
            if (item.isStored()) {
                throw new IllegalStateException("Item is already stored.");
            }
            storage.add(item);
            item.store(this);
        }

        @Override
        public T unload() {
            if (isEmpty()) {
                throw new IllegalStateException("Storage is empty.");
            }
            T item = storage.removeLast();
            item.unStore();
            return item;
        }
    }
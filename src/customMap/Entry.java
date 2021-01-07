package customMap;

import java.util.Objects;

class Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next;
    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
    // getters, equals, hashCode and toString


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return Objects.equals(key, entry.key);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key);
    }
}
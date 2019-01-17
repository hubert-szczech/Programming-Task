package main.java.com.data;

import java.util.Objects;

public class Pair<U, K> {
   private U id;
   private K data;

    public Pair(U id, K data) {
        this.id = id;
        this.data = data;
    }

    public Pair() {
    }

    public U getId() {
        return id;
    }

    public void setId(U id) {
        this.id = id;
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "id=" + id +
                ", main.java.com.data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return getId() == pair.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

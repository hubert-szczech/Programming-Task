package main.java.com.dataprovider;


import main.java.com.data.Pair;
import java.io.IOException;
import java.util.Collection;

public interface DataProvider<U extends Pair> {
    int count() throws IOException;
    Collection<U> get(int page, int pageLength);
    Collection<U> getAll();
}

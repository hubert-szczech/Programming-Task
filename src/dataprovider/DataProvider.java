package dataprovider;


import data.Pair;
import java.io.IOException;
import java.util.Collection;

public interface DataProvider<U extends Pair> {
    int count() throws IOException;
    Collection<U> get(int page, int pageLength);
    Collection<U> getAll();
}

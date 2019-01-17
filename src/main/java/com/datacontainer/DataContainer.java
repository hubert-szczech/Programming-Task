package main.java.com.datacontainer;

import main.java.com.data.Pair;
import main.java.com.dataprovider.DataProvider;

import java.util.Collection;
import java.util.function.Predicate;

interface DataContainer<ID,DATA> {

    void load(DataProvider<Pair<ID,DATA>> data);
    void add(ID id,DATA data);
    void update(ID id,DATA data);
    DATA findById(ID id);
    void remove(ID id);
    void printAll();
    void print(int from,int to);
    void clear();
    Collection<DATA> get(int from, int to);
    Collection<DATA> filter(Predicate<DATA> filter);
}

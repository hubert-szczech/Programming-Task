package main.java.com.datacontainer;

import main.java.com.data.Data;
import main.java.com.data.Pair;
import main.java.com.dataprovider.DataProvider;


import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CsvDataContainer implements DataContainer<Integer, Data> {

    private final Logger log = (Logger) Logger.getLogger(String.valueOf(getClass()));
    private List<Pair<Integer, Data>> collection;


    public CsvDataContainer() {
        this.collection = new ArrayList<>();
    }

    @Override
    public void load(DataProvider<Pair<Integer, Data>> data) {
        collection.addAll(data.getAll());
    }

    @Override
    public void add(Integer integer, Data data) {
        Pair<Integer, Data> pair = new Pair<>(integer, data);
        if (!collection.contains(pair)) {
            collection.add(pair);
        } else {
            log.info("add(): pair with id= '" + integer + "' already exist");
            throw new UncheckedIOException(new IOException());
        }
    }

    @Override
    public void update(Integer integer, Data data) {
        Pair<Integer, Data> pair = new Pair<>(integer, data);
        int index;
        if (collection.contains(pair)) {
            index = collection.indexOf(pair);
            collection.get(index).setData(data);
        } else {
            log.info("update(): pair with id= '" + integer + "' does not exist");
            throw new UncheckedIOException(new IOException());
        }
    }

    @Override
    public Data findById(Integer integer) {
        Pair<Integer, Data> pair = new Pair<>();
        pair.setId(integer);
        int index;
        if (collection.contains(pair)) {
            index = collection.indexOf(pair);
            return  collection.get(index).getData();
        } else {
            return null;
        }
    }

    @Override
    public void remove(Integer integer) {
        Pair<Integer, Data> pair = new Pair<>();
        pair.setId(integer);
        int index;
        if (integer >= 0 && integer < collection.size()) {
            index = collection.indexOf(pair);
            collection.remove(index);
        } else {
            log.info(" remove():main.java.com.data with id= '" + integer + "' does not exist");
            throw new UncheckedIOException(new IOException());
        }
    }

    @Override
    public void printAll() {
        collection.forEach(System.out::println);
    }

    @Override
    public void print(int from, int to) {
        collection.forEach(x -> {
            if (x.getId() >= from && x.getId() < to) {
                System.out.println(x);
            }
        });
    }

    @Override
    public void clear() {
        collection.removeAll(collection);
    }

    @Override
    public Collection<Data> get(int from, int to) {
        Collection<Data> list = new ArrayList<>();
        collection.forEach(x -> {
            if (x.getId() >= from && x.getId() < to) {
                list.add(x.getData());
            }
        });
        return list;
    }

    @Override
    public Collection<Data> filter(Predicate<Data> filter) {
        Collection<Data> listOfData = new ArrayList<>();
        collection.forEach(x -> listOfData.add(x.getData()));
        return listOfData.stream().filter(filter).collect(Collectors.<Data>toList());
    }
}

package datacontainer;

import data.Pair;
import data.SimpleData;
import dataprovider.DataProvider;
import dataprovider.SimpleDataProvider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SimpleDataContainer implements DataContainer<Integer, SimpleData> {

    protected final Logger log = (Logger) Logger.getLogger(String.valueOf(getClass()));

    private ArrayList<Pair<Integer, SimpleData>> collection;



    @Override
    public void load(DataProvider<Pair<Integer, SimpleData>> data) {
    collection = new ArrayList<>(data.getAll());

    }




    @Override
    public void add(Integer integer, SimpleData simpleData) {
        Pair<Integer,SimpleData> pair = new Pair<>(integer,simpleData);

        if(!collection.contains(pair)){
            collection.add(pair);

        }
        else{
            log.info("add(): pair with id= '" + integer + "' already exist");

    }



    }



    @Override
    public void update(Integer integer, SimpleData simpleData) {
        Pair<Integer,SimpleData> pair = new Pair<>(integer,simpleData);
        int index ;
        if(collection.contains(pair)) {
            index = collection.indexOf(pair);
            collection.get(index).setData(simpleData);
        } else{
            log.info("update(): pair with id= '" + integer + "' does not exist");
        }
    }

    @Override
    public SimpleData findById(Integer integer) {
        Pair<Integer,SimpleData> pair = new Pair<>();
        pair.setId(integer);
        int index ;
        if(collection.contains(pair)){
            index =collection.indexOf(pair);
            SimpleData simpleData;
           return simpleData = collection.get(index).getData();
        }
        else{
            log.info("findById(): data with id= '" + integer + "' does not exist");
            return null;
        }

    }

    @Override
    public void remove(Integer integer) {
        if(integer >= 0 && integer < collection.size()) {
            collection.remove(integer);
        } else {
            log.info(" remove():data with id= '" + integer + "' does not exist");
        }
    }

    @Override
    public void printAll() {
        collection.forEach(System.out::println);
    }
    @Override
    public void print(int from, int to) {

        collection.forEach( x -> {
            if(x.getId() >= from  && x.getId() < to  ){
                System.out.println(x);
            }

        });
    }
    @Override
    public void clear() {
        collection.removeAll(collection);
    }

    @Override
    public Collection<SimpleData> get(int from, int to) {
        Collection<SimpleData> list = new ArrayList<>();
        collection.forEach( x -> {
            if(x.getId() >= from  && x.getId() < to  ){
                list.add(x.getData());

            }
        });
            return  list;
    }
    @Override
    public Collection<SimpleData> filter(Predicate<SimpleData> filter) {

                ArrayList<SimpleData> listOfData = new ArrayList<>();
                collection.forEach(x -> listOfData.add(x.getData()));

         return listOfData.stream().filter(filter).collect(Collectors.<SimpleData>toList());

    }
}

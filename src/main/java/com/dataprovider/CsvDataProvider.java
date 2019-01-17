package main.java.com.dataprovider;


import main.java.com.data.Data;
import main.java.com.data.Pair;


import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class CsvDataProvider implements DataProvider<Pair<Integer, Data>> {

    private final Logger log = (Logger) Logger.getLogger(String.valueOf(getClass()));
    private final String path;

    public CsvDataProvider(String path) {
        this.path = path;
    }

    @Override
    public int count() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = null;
            int numberOfElements = 0;
            while ((line = reader.readLine()) != null) {
                numberOfElements++;
            }
            return numberOfElements;

        } catch (FileNotFoundException e) {
            log.warning(" count(): FILE NOT FOUND");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Collection<Pair<Integer, Data>> get(int page, int pageLength) {
        if (page < 0 || pageLength < 0) {
            log.warning("get(): INVALID PARAMETERS ");
            return new ArrayList<>();
        }
        CsvDataProvider simpleDataProvider = new CsvDataProvider("Data/Data.csv");
        ArrayList<Pair<Integer, Data>> list = (ArrayList<Pair<Integer, Data>>) simpleDataProvider.getAll();
        int minIndex = page * pageLength;
        int maxIndex = page * pageLength + pageLength;
        if (maxIndex > list.size()) {
            maxIndex = list.size();
        }
        if (maxIndex <= minIndex) {
            return new ArrayList<>();
        }
        try {
            list = new ArrayList<>(list.subList(minIndex, maxIndex));
        } catch (IndexOutOfBoundsException e) {
            log.warning("get(): INVALID PARAMETERS ");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Collection<Pair<Integer, Data>> getAll() {
        List<Pair<Integer, Data>> list = new ArrayList<>();
        String csvFile = path;
        String line = "";
        String cvsSplitBy = ";";
        String x;
        int ID;
        Pair<Integer, Data> pair;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] place = line.split(cvsSplitBy);
                Data data = new Data();
                data.setCountry(place[1].toString());
                data.setCapital(place[2].toString());
                System.out.println();
                pair = (Pair<Integer, Data>) new Pair(parseInt(place[0].toString()), data);
                list.add(pair);
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            log.warning("getAll(): INVALID DATA IN FILE WAS FOUND");
        }
        return list;
    }
}

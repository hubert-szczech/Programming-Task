package dataprovider;



import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import data.Pair;
import data.SimpleData;


import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class SimpleDataProvider implements DataProvider<Pair<Integer, SimpleData>> {

    protected final Logger log = (Logger) Logger.getLogger(String.valueOf(getClass()));


    @Override
   public int count()  {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Data/SimpleData.csv"));
            String line = null;
            int numberOfElements = 0;
            while ((line = reader.readLine()) != null) {
            numberOfElements++;
        }
        return numberOfElements;

    }catch(FileNotFoundException e){
            log.warning(" count(): FILE NOT FOUND");
        }
     catch(IOException e) {
            e.printStackTrace();
     }

        return 0;
    }

    @Override
    public Collection<Pair<Integer, SimpleData>> get(int page, int pageLength){
        if(page < 0 || pageLength < 0 ){
            log.warning("get(): INVALID PARAMETERS ");
            return  new ArrayList<>();
        }


        SimpleDataProvider simpleDataProvider = new SimpleDataProvider();
        ArrayList<Pair<Integer, SimpleData>> list = (ArrayList<Pair<Integer, SimpleData>>) simpleDataProvider.getAll();
        int minIndex = page*pageLength;
        int maxIndex = page*pageLength + pageLength;
        if(maxIndex > list.size() ){
            maxIndex = list.size();
        }
        if(maxIndex <= minIndex ){
            return  new ArrayList<>();
        }

        try {
            list = new ArrayList<>(list.subList(minIndex, maxIndex));
        }
        catch (IndexOutOfBoundsException e){
            log.warning("get(): INVALID PARAMETERS " );  // jakiś błąd
            e.printStackTrace();
        }

        return list;
}



    @Override
    public Collection<Pair<Integer, SimpleData>> getAll() {
        List<Pair<Integer, SimpleData>> list = new ArrayList<>();
        String csvFile = "Data/SimpleData.csv";
        String line = "";
        String cvsSplitBy = ";";
        String x;
        int ID;
        Pair<Integer, SimpleData> pair;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] place = line.split(cvsSplitBy);
                SimpleData simpleData = new SimpleData();
                simpleData.setCountry(place[1].toString());
                simpleData.setCapital(place[2].toString());
                System.out.println();

                pair = (Pair<Integer, SimpleData>) new Pair(parseInt(place[0].toString()), simpleData);
                list.add(pair);
            }

        } catch (IOException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e){
            log.warning("getAll(): INVALID DATA IN FILE WAS FOUND");
        }

        return list;
    }
}

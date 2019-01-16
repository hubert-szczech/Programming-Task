
import data.SimpleData;
import datacontainer.SimpleDataContainer;

import dataprovider.SimpleDataProvider;

;import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {

        SimpleDataProvider simpleDataProvider = new SimpleDataProvider();
        simpleDataProvider.get(1,12).forEach(System.out::println);


        SimpleDataContainer simpleDataContainer = new SimpleDataContainer();
        simpleDataContainer.load(new SimpleDataProvider());

        simpleDataContainer.printAll();

        System.out.println("dodanie");
        simpleDataContainer.add(1, new SimpleData("asfd","asd"));
        simpleDataContainer.printAll();

        System.out.println("update");
        simpleDataContainer.update(2,new SimpleData("aaaa", "aaaa"));
        simpleDataContainer.update(5,new SimpleData("aaaa", "aaaa"));
        simpleDataContainer.printAll();

        System.out.println(simpleDataContainer.findById(5));

        System.out.println("======================================================");
        System.out.println("od-do");
        simpleDataContainer.print(0,6);

        System.out.println("======================================================");
        System.out.println("get data from");
        System.out.println(simpleDataContainer.get(4,13));


        System.out.println("======================================================");
        System.out.println("using funcional interface");
        System.out.println(simpleDataContainer.filter( x -> x.getCapital().equals("Paris")));
        SimpleDataProvider x =new SimpleDataProvider();

         System.out.println( new SimpleDataProvider().count());

    }
}

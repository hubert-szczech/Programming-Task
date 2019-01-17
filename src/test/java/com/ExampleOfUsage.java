package test.java.com;

import main.java.com.data.Data;
import main.java.com.datacontainer.CsvDataContainer;
import main.java.com.dataprovider.CsvDataProvider;
import org.junit.Test;
import java.util.ArrayList;

public class ExampleOfUsage {

    private String path = "resources/Data/TestData.csv";

    @Test
    public void dataLoading() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        csvDataContainer.printAll();
    }

    @Test
    public void addingElement() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);

        System.out.println("====Data Before Adding====");
        csvDataContainer.printAll();
        Data data = new Data("Germany", "Berlin");
        csvDataContainer.add(10, data);
        System.out.println("====Data After Adding====");
        csvDataContainer.printAll();
    }

    @Test
    public void updatingElement() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);

        System.out.println("====Data Before Updating====");
        csvDataContainer.printAll();
        Data data = new Data("Germany", "Berlin");
        csvDataContainer.update(1, data);
        System.out.println("====Data After Updating====");
        csvDataContainer.printAll();
    }

    @Test
    public void findingElementById() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);

        int id = 1;
        Data data = csvDataContainer.findById(id);
        System.out.println(data);
    }

    @Test
    public void removeElementByIdInTestData() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);

        int id = 2;
        System.out.println("====Data Before Removing====");
        csvDataContainer.printAll();
        csvDataContainer.remove(id);
        System.out.println("====Data After Removing====");
        csvDataContainer.printAll();
    }

    @Test
    public void printAllElementsFromTestData() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        csvDataContainer.printAll();
    }

    @Test
    public void printFromToElementsInTestData() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        csvDataContainer.print(2, 4);
    }

    @Test
    public void clearAllElementsFromTestData() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);

        System.out.println("====Data Before Clearing====");
        csvDataContainer.printAll();
        csvDataContainer.clear();
        System.out.println("====Data After Clearing====");
        csvDataContainer.printAll();

    }

    @Test
    public void getElementsFromToFromTestData() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);

        int from = 1;
        int to = 3;
        System.out.println("====Data====");
        csvDataContainer.printAll();
        System.out.println("====Getting elements from <1,0) ====");
        ArrayList<Data> listOfData = (ArrayList<Data>) csvDataContainer.get(from, to);
        listOfData.forEach(System.out::println);
    }

    @Test
    public void filterFromTestData() {
        CsvDataContainer csvDataContainer = new CsvDataContainer();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);

        System.out.println("====Before Filtering====");
        csvDataContainer.printAll();
        ArrayList listOfData = (ArrayList) csvDataContainer.filter(data -> data.getCountry().equals("France"));
        System.out.println("====After Filtering====");
        listOfData.forEach(System.out::println);
    }
}

package test.java.com;

import main.java.com.data.Data;
import main.java.com.data.Pair;
import main.java.com.datacontainer.CsvDataContainer;
import main.java.com.dataprovider.CsvDataProvider;
import org.junit.Test;
import java.util.ArrayList;
import java.util.stream.Collectors;
import static java.lang.Integer.parseInt;

public class  CsvDataContainerTest{

        private String path = "resources/Data/TestData.csv";

        @Test
        public void dataLoadingTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                int numberOfElementsInTestData = csvDataProvider.count();
                csvDataContainer.load(csvDataProvider);
                assert(csvDataContainer.get(0,numberOfElementsInTestData + 1).size() ==  numberOfElementsInTestData);
        }
        @Test
        public void addingElementTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);

                Data data = new Data("Germany", "Berlin");
                csvDataContainer.add(10,data);
                assert(csvDataContainer.findById(10).equals(data));

        }
        @Test
        public void updatingElementTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);

                Data dataBeforeUpdate = csvDataContainer.findById(1);
                Data data = new Data("Germany", "Berlin");
                assert(!data.equals(dataBeforeUpdate));

                csvDataContainer.update(1,data);
                Data dataAfterUpdate = csvDataContainer.findById(1);
                assert(dataAfterUpdate == data);
        }

        @Test
        public void findingElementByIdTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);
                int id = 1;

                Data data = csvDataContainer.findById(id);
                ArrayList<Pair> listOfElement = (ArrayList<Pair>) csvDataProvider.getAll().stream().collect(Collectors.<Pair>toList());
                int index = listOfElement.indexOf(new Pair<>(id,data));
                assert (listOfElement.get(index).getData().equals(data));
        }

        @Test
        public void removeElementByIdTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);
                int id = 2;
                csvDataContainer.remove(id);
                Data data = csvDataContainer.findById(id);
                assert(data == null);

        }
        @Test
        public void printAllElementsTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);
                csvDataContainer.printAll();

        }

        @Test
        public void printFromToElementsTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);
                int from = 2;
                int to = 4;
                csvDataContainer.print(from,to);

        }
        @Test
        public void clearAllElementsTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);
                csvDataContainer.clear();
                int minId = 0;
                int maxId = csvDataProvider.count() ;
                assert(csvDataContainer.get(minId,maxId).size() == 0);
        }

        @Test
        public void getElementsFromToTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);
                int from = 2;
                int to = 3;
                Data data = new Data("Poland", "Warsaw");
                ArrayList<Data> listOfData = (ArrayList<Data>) csvDataContainer.get(from,to);
                assert(listOfData.get(0).equals(data));
        }

        @Test
        public void filterFromTest(){
                CsvDataContainer csvDataContainer = new CsvDataContainer();
                CsvDataProvider csvDataProvider = new CsvDataProvider(path);
                csvDataContainer.load(csvDataProvider);
                ArrayList listOfData = (ArrayList) csvDataContainer.filter(data -> data.getCountry().equals("France"));
                listOfData.forEach(x -> {
                        Data data = (Data) x;
                        assert(data.getCountry().equals("France"));
                });
        }
}



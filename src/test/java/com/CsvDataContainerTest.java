package test.java.com;

        import main.java.com.data.Data;
        import main.java.com.data.Pair;
        import main.java.com.datacontainer.DataContainerImpl;
        import main.java.com.dataprovider.CsvDataProvider;
        import org.junit.Test;
        import java.io.UncheckedIOException;
        import java.util.ArrayList;
        import java.util.stream.Collectors;


public class CsvDataContainerTest {

    private String path = "resources/Data/TestData.csv";

    @Test
    public void dataLoadingTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        int numberOfElementsInTestData = csvDataProvider.count();
        csvDataContainer.load(csvDataProvider);
        assert (csvDataContainer.get(0, numberOfElementsInTestData + 1).size() == numberOfElementsInTestData);
    }

    @Test
    public void addingElementTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 10;

        Data data = new Data("Germany", "Berlin");
        csvDataContainer.add(id, data);
        assert (csvDataContainer.findById(id).equals(data));

    }

    @Test(expected = UncheckedIOException.class)
    public void addingExistingElementTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 1;

        Data data = csvDataContainer.findById(id);
        csvDataContainer.add(id, data);
    }
    @Test(expected = UncheckedIOException.class)
    public void addingElementWithNegativeIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = -1;

        Data data = csvDataContainer.findById(id);
        csvDataContainer.add(id, data);
    }

    @Test
    public void updatingElementTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 1;

        Data dataBeforeUpdate = csvDataContainer.findById(id);
        Data data = new Data("Germany", "Berlin");
        assert (!data.equals(dataBeforeUpdate));

        csvDataContainer.update(id, data);
        Data dataAfterUpdate = csvDataContainer.findById(id);
        assert (dataAfterUpdate == data);
    }

    @Test(expected = UncheckedIOException.class)
    public void updatingInvalidElementTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 9;

        Data data = new Data("Germany", "Berlin");
        csvDataContainer.update(id, data);
    }

    @Test(expected = UncheckedIOException.class)
    public void updatingElementWithNegativeIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = -10;

        Data data = new Data("Germany", "Berlin");
        csvDataContainer.update(id, data);
    }

    @Test
    public void findingElementByIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 1;

        Data data = csvDataContainer.findById(id);
        ArrayList<Pair> listOfElement = (ArrayList<Pair>) csvDataProvider.getAll().stream().collect(Collectors.<Pair>toList());
        int index = listOfElement.indexOf(new Pair<>(id, data));
        assert (listOfElement.get(index).getData().equals(data));
    }

    @Test
    public void findingElementByInvalidIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 100;

        Data data = csvDataContainer.findById(id);
        assert (data == null);
    }

    @Test
    public void findingElementByNegativeIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = -10;

        Data data = csvDataContainer.findById(id);
        assert (data == null);
    }

    @Test
    public void removeElementByIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 2;
        csvDataContainer.remove(id);
        Data data = csvDataContainer.findById(id);
        assert (data == null);
    }

    @Test(expected = UncheckedIOException.class)
    public void removeElementByInvalidIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = 100;
        csvDataContainer.remove(id);
    }

    @Test(expected = UncheckedIOException.class)
    public void removeElementByNegativeIdTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int id = -100;
        csvDataContainer.remove(id);
    }

    @Test
    public void printAllElementsTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        csvDataContainer.printAll();

    }

    @Test
    public void printFromToElementsTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int from = 2;
        int to = 4;
        csvDataContainer.print(from, to);
    }

    @Test
    public void printFromToElementsTestWithInvalidParameters() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int from = 100;
        int to = 200;
        csvDataContainer.print(from, to);
    }

    @Test
    public void printFromToElementsTestWithNegativeAndPositiveParameters() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int from = -100;
        int to = 2;
        csvDataContainer.print(from, to);
    }

    @Test
    public void clearAllElementsTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        csvDataContainer.clear();
        int minId = 0;
        int maxId = csvDataProvider.count();
        assert (csvDataContainer.get(minId, maxId).size() == 0);
    }

    @Test
    public void getElementsFromToTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int from = 2;
        int to = 3;
        Data data = new Data("Poland", "Warsaw");
        ArrayList<Data> listOfData = (ArrayList<Data>) csvDataContainer.get(from, to);
        assert (listOfData.get(0).equals(data));
    }

    @Test
    public void getElementsFromToTestWithToBigRange() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int from = 2;
        int to = 312341234;
        Data data = new Data("Poland", "Warsaw");
        ArrayList<Data> listOfData = (ArrayList<Data>) csvDataContainer.get(from, to);
        assert (listOfData.get(0).equals(data));
    }

    @Test
    public void getElementsFromToTestWithInvalidParameters() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int from = 8;
        int to = 1;

        ArrayList<Data> listOfData = (ArrayList<Data>) csvDataContainer.get(from, to);
        listOfData.forEach(System.out::println);
        assert (listOfData.size() == 0);
    }

    @Test
    public void getElementsFromToTestWithNegativeAndPositiveParameters() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        int from = -8;
        int to = 8;

        ArrayList<Data> listOfData = (ArrayList<Data>) csvDataContainer.get(from, to);
        listOfData.forEach(System.out::println);
        assert (listOfData.size() == 3);
    }

    @Test
    public void filterFromTest() {
        DataContainerImpl csvDataContainer = new DataContainerImpl();
        CsvDataProvider csvDataProvider = new CsvDataProvider(path);
        csvDataContainer.load(csvDataProvider);
        ArrayList listOfData = (ArrayList) csvDataContainer.filter(data -> data.getCountry().equals("France"));
        listOfData.forEach(x -> {
            Data data = (Data) x;
            assert (data.getCountry().equals("France"));
        });
    }
}
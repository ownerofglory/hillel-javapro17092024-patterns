package ua.ithillel;

//OPENWEATHER_API_KEY

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.gof.chain.*;
import ua.ithillel.gof.memento.TextDocument;
import ua.ithillel.gof.proxy.cache.CacheHandler;
import ua.ithillel.gof.proxy.client.CocktailDbClient;
import ua.ithillel.gof.proxy.client.DrinkClient;
import ua.ithillel.gof.proxy.manager.DefaultDrinkManager;
import ua.ithillel.gof.proxy.manager.DrinkManager;
import ua.ithillel.gof.proxy.model.Drink;
import ua.ithillel.gof.state.Order;
import ua.ithillel.gof.state.PaymentProcessor;
import ua.ithillel.gof.state.Product;
import ua.ithillel.gof.state.ShipmentProcessor;
import ua.ithillel.gof.visitor.exporter.ExcelExporter;
import ua.ithillel.gof.visitor.model.*;
import ua.ithillel.grasp.cohesion.Database;
import ua.ithillel.grasp.cohesion.Movie;
import ua.ithillel.grasp.cohesion.MoviesDao;
import ua.ithillel.grasp.cohesion.MoviesDaoDb;

import java.lang.reflect.Proxy;
import java.net.http.HttpClient;
import java.util.List;

public class Main {

    private static void processTextDoc(TextDocument textDocument) {
        textDocument.setText(textDocument.getText() + "changes");

        textDocument.restore();
    }

    public static void main(String[] args) {
//        Database database = new Database();
//        //database.createConn();
//
//        MoviesDao moviDao =  new MoviesDaoDb(database);
//        List<Movie> allMovies = moviDao.getAllMovies();

//        ServerFactory serverFactory = new ServerFactory();
//        Server server = serverFactory.createServer(ServerType.MULTI_THREADED, 8080);

        Message[] messages = {
            new Message("hello!"),
                new Message("how are you?"),
                new Message("bad message"),
                new Message("very looooooo0o0oong meessage"),
                new Message("como estas?"),
        };
        MessageHanlder ah = new AgressivnessHandler();
        MessageHanlder lh = new LengthHandler();
        MessageHanlder pl = new PreferableLanguageHandler(List.of("EN", "UA"));

        ah.setNext(lh);
        lh.setNext(pl);

        MessageReceiver messageReceiver = new MessageReceiver(ah);

        for (Message message : messages) {
            messageReceiver.receiveMessage(message);
        }

        Order order = new Order();
        order.setProducts(List.of(new Product("T-Shirt", 12.0), new Product("Phone", 200.0)));

        PaymentProcessor paymentProcessor = new PaymentProcessor();
        paymentProcessor.process(order);

        ShipmentProcessor shipmentProcessor = new ShipmentProcessor();
        shipmentProcessor.process(order);


        TextDocument textDocument = new TextDocument();
        textDocument.setText("Hello");
        textDocument.persist();

        textDocument.setText(" world");
        textDocument.persist();
        textDocument.setText("\n");
        textDocument.persist();
        textDocument.setText("How are you?");
        textDocument.persist();


        textDocument.setText("bad");

        textDocument.restore();

        System.out.println("Hello world!");
        Book book1 = new Book.Builder()
                .setId("1")
                .setName("Effective Java")
                .setDescription("A comprehensive guide to best practices in Java")
                .setAuthor("Joshua Bloch")
                .setPublisher("Addison-Wesley")
                .setCategory("Programming")
                .setType("Technical")
                .build();

        Book book2 = new Book.Builder()
                .setId("2")
                .setName("Clean Code")
                .setDescription("A Handbook of Agile Software Craftsmanship")
                .setAuthor("Robert C. Martin")
                .setPublisher("Prentice Hall")
                .setCategory("Programming")
                .setType("Technical")
                .build();

        Book book3 = new Book.Builder()
                .setId("3")
                .setName("Java: The Complete Reference")
                .setDescription("A comprehensive reference to the Java programming language")
                .setAuthor("Herbert Schildt")
                .setPublisher("McGraw-Hill Education")
                .setCategory("Programming")
                .setType("Reference")
                .build();

        Book book4 = new Book.Builder()
                .setId("4")
                .setName("Introduction to Algorithms")
                .setDescription("The standard text for algorithms and data structures")
                .setAuthor("Thomas H. Cormen")
                .setPublisher("MIT Press")
                .setCategory("Computer Science")
                .setType("Textbook")
                .build();

        BookCollection bookCollection = new BookCollection(List.of(book1, book2, book3, book4));

        Visitor counterVisitor = new CounterVisitor();
        Visitor exportVisitor = new ExporterVisitor(new ExcelExporter());
        Visitor jsonVisitor = new JsonVisitor();

        bookCollection.accept(counterVisitor);
        bookCollection.accept(exportVisitor);
        bookCollection.accept(jsonVisitor);


        List.of(book1, book2, book3, book4).forEach(System.out::println);


        //  cache
        DrinkClient drinkClient = new CocktailDbClient(HttpClient.newBuilder().build(), new ObjectMapper());

        DrinkClient clientProxy = (DrinkClient) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[] {
                        DrinkClient.class
                },
                new CacheHandler(drinkClient)
        );


        DrinkManager drinkManager = new DefaultDrinkManager(clientProxy);

        Drink mojito = drinkManager.getDrinkByName("Mojito");
        Drink longIslandTea = drinkManager.getDrinkByName("Long island tea");
        Drink negroni = drinkManager.getDrinkByName("Negroni");

        System.out.println();

    }
}
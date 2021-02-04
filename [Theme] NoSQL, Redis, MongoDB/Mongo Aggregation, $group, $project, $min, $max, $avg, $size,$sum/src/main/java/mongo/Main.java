package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mongo.commands.*;
import mongo.commands.parent.ShoppingCommand;
import org.bson.Document;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> collectionOfStores = database.getCollection("Stores");
        MongoCollection<Document> collectionOfProducts = database.getCollection("Products");

        //collectionOfStores.drop();
        //collectionOfProducts.drop();

        HashMap<String, ShoppingCommand> mapOfCommands = new HashMap<>();

        mapOfCommands.put("ДОБАВИТЬ_МАГАЗИН", new AddStore());
        mapOfCommands.put("ДОБАВИТЬ_ТОВАР", new AddProduct());
        mapOfCommands.put("ВЫСТАВИТЬ_ТОВАР", new AddProductToStore());
        mapOfCommands.put("СТАТИСТИКА_ТОВАРОВ", new ShowStatistics());

        for (; ; ) {
            System.out.print("Введите команду: ");
            String input = SCANNER.nextLine();

            String[] nodes = input.split("\\s", 2);
            String command = nodes[0].toUpperCase();
            String commandBody = nodes.length > 1 ? nodes[1] : "";

            try {
                if (mapOfCommands.containsKey(command)) {
                    mapOfCommands.get(command).executeCommand(commandBody, collectionOfStores, collectionOfProducts);
                } else {
                    System.out.println("Нет такой команды");
                }
            } catch (NullPointerException ignored) {}
        }
    }
}
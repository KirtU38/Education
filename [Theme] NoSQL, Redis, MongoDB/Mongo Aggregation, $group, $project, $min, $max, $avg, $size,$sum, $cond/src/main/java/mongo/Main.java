package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mongo.store.StoreService;
import org.bson.Document;

import java.util.Scanner;

public class Main {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> collectionOfStores = database.getCollection("Stores");
        MongoCollection<Document> collectionOfProducts = database.getCollection("Products");

        //collectionOfStores.drop();
        //collectionOfProducts.drop();

        StoreService storeService = new StoreService();
        for (; ; ) {
            System.out.print("Введите команду: ");
            String command = SCANNER.nextLine();
            storeService.execute(command, collectionOfStores, collectionOfProducts);
        }
    }
}
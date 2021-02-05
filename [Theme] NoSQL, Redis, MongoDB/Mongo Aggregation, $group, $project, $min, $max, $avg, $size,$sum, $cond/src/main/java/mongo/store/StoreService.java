package mongo.store;

import com.mongodb.client.MongoCollection;
import mongo.store.commands.*;
import mongo.store.commands.parent.ShoppingCommand;
import org.bson.Document;

import java.util.HashMap;

public class StoreService {

    private final HashMap<String, ShoppingCommand> mapOfCommands = new HashMap<>();

    public StoreService() {
        mapOfCommands.put("ДОБАВИТЬ_МАГАЗИН", new AddStore());
        mapOfCommands.put("ДОБАВИТЬ_ТОВАР", new AddProduct());
        mapOfCommands.put("ВЫСТАВИТЬ_ТОВАР", new AddProductToStore());
        mapOfCommands.put("СТАТИСТИКА_ТОВАРОВ", new ShowStatistics());
        mapOfCommands.put("УДАЛИТЬ_ТОВАР", new RemoveProduct());
    }

    public void execute(String input,
                        MongoCollection<Document> collectionOfStores,
                        MongoCollection<Document> collectionOfProducts) {

        String[] nodes = input.split("\\s", 2);
        String command = nodes[0].toUpperCase();
        String commandBody = nodes.length > 1 ? nodes[1] : "";

        if (mapOfCommands.containsKey(command)) {
            mapOfCommands.get(command).executeCommand(commandBody, collectionOfStores, collectionOfProducts);
        } else {
            System.out.println("Нет такой команды");
        }
    }
}

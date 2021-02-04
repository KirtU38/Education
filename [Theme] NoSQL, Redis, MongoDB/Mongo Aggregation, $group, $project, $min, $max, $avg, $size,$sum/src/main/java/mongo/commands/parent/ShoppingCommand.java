package mongo.commands.parent;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface ShoppingCommand {

    void executeCommand(String commandBody,
                        MongoCollection<Document> collectionOfStores,
                        MongoCollection<Document> collectionOfProducts);

}

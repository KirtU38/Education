package mongo.store.commands;

import com.mongodb.client.MongoCollection;
import mongo.store.commands.parent.ShoppingCommand;
import org.bson.BsonDocument;
import org.bson.Document;

public class RemoveProduct implements ShoppingCommand {

    @Override
    public void executeCommand(String productName,
                               MongoCollection<Document> collectionOfStores,
                               MongoCollection<Document> collectionOfProducts) {

        collectionOfProducts.deleteOne(BsonDocument.parse("{name: \"" + productName + "\"}"));
    }
}

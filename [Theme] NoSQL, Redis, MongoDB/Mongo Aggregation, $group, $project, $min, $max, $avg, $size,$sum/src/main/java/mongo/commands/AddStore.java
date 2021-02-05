package mongo.commands;

import com.mongodb.client.MongoCollection;
import mongo.commands.parent.ShoppingCommand;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class AddStore implements ShoppingCommand {

    @Override
    public void executeCommand(String nameOfTheStore,
                               MongoCollection<Document> collectionOfStores,
                               MongoCollection<Document> collectionOfProducts) {

        List<String> listOfProducts = new ArrayList<>();

        Document storeDocument = new Document()
                .append("name", nameOfTheStore)
                .append("products", listOfProducts);

        collectionOfStores.insertOne(storeDocument);
    }
}
/*
db.Stores.insert({
  name: "Магнит",
  products: []
})
*/

package mongo.commands;

import com.mongodb.client.MongoCollection;
import mongo.commands.parent.ShoppingCommand;
import org.bson.Document;

public class AddProduct implements ShoppingCommand {

    @Override
    public void executeCommand(String nameAndPriceOfProduct,
                               MongoCollection<Document> collectionOfStores,
                               MongoCollection<Document> collectionOfProducts) {

        String[] nodes = nameAndPriceOfProduct.split("\\s", 2);

        Document storeDocument = new Document()
                .append("name", nodes[0])
                .append("price", Integer.parseInt(nodes[1]));

        collectionOfProducts.insertOne(storeDocument);
    }
}

package mongo.commands;

import com.mongodb.client.MongoCollection;
import mongo.commands.parent.ShoppingCommand;
import org.bson.BsonDocument;
import org.bson.Document;

public class AddProductToStore implements ShoppingCommand {

    @Override
    public void executeCommand(String nameOfProductAndStore,
                               MongoCollection<Document> collectionOfStores,
                               MongoCollection<Document> collectionOfProducts) {

        String[] nodes = nameOfProductAndStore.split("\\s", 2);
        String productName = nodes[0];
        String storeName = nodes[1];

        BsonDocument productQuery = BsonDocument.parse("{name: \"" + productName + "\" }");
        Document productDocument = collectionOfProducts.find(productQuery).limit(1).first();

        if (productDocument == null) {
            System.out.println("Такого товара нет");
        } else {
            BsonDocument storeQuery = BsonDocument.parse("{name: \"" + storeName + "\"}");
            BsonDocument updateQuery = BsonDocument.parse("{$push: {products: \"" + productDocument.get("name") + "\"}}");

            collectionOfStores.updateOne(storeQuery, updateQuery);
        }


        // db.Stores.update({name: "Магнит"}, {$push: {products: {name: "Молоко", price: 54}}})
    }
}

package mongo.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
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
            collectionOfStores.updateOne(
                    BsonDocument.parse("{name: \"" + storeName + "\"}"),
                    Updates.addToSet("products", productName));
        }
    }
}
/*
db.Stores.update(
  {name: "Магнит"},
  {$push: {products: {name: "Молоко"}}}
)
*/

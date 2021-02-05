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
        String productName = nodes[0];
        int productPrice = Integer.parseInt(nodes[1]);

        Document productDocument = new Document()
                .append("name", productName)
                .append("price", productPrice);

        collectionOfProducts.insertOne(productDocument);
    }
}
/*
db.Products.insert({
  name: "Молоко",
  price: 69
})
*/

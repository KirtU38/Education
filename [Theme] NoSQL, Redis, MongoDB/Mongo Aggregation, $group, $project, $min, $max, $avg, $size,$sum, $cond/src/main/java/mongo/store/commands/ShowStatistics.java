package mongo.store.commands;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import mongo.store.Store;
import mongo.store.commands.parent.ShoppingCommand;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;

public class ShowStatistics implements ShoppingCommand {

    public static final int PRICE = 100;

    @Override
    public void executeCommand(String command,
                               MongoCollection<Document> collectionOfStores,
                               MongoCollection<Document> collectionOfProducts) {

        // Статистика
        List<Bson> pipeline = Arrays.asList(
                lookup("Products", "products", "name", "productsArray"),
                unwind("$productsArray"),
                group("$name",
                        avg("avgPrice", "$productsArray.price"),
                        max("biggestPrice", "$productsArray.price"),
                        min("lowestPrice", "$productsArray.price"),
                        sum("productsCount", 1L),
                        sum("lessThan100Count", BsonDocument.parse(
                                "{$cond: [{$lt: [\"$productsArray.price\", " + PRICE + "]}, 1, 0]}"))));

        AggregateIterable<Document> result = collectionOfStores.aggregate(pipeline);

        for (Document document : result) {
            Store store = new Store();
            store.setStoreName(document.get("_id").toString());
            store.setAvgPrice(document.getDouble("avgPrice"));
            store.setBiggestPrice(document.getInteger("biggestPrice"));
            store.setLowestPrice(document.getInteger("lowestPrice"));
            store.setProductsCount(document.getLong("productsCount"));
            store.setLessThan100Count(document.getInteger("lessThan100Count"));

            System.out.println(store);
        }
    }
}
/*
Статистика товаров:
db.Stores.aggregate(
  [
    {$lookup: {
      from: 'Products',
      localField: 'products',
      foreignField: 'name',
      as: 'productsArray'}},
    {$unwind: {path: "$productsArray"} },
    {$group: {
      _id: "$name",
      avgPrice: {$avg: "$productsArray.price"},
      biggestPrice: {$max: "$productsArray.price"},
      lowestPrice: {$min: "$productsArray.price"},
      numberOfProducts: {$sum: 1} }},
      numberOfGreaterThanProducts: {$sum : {$cond : [{$gt: ["$productsArray.price", 100]}, 1, 0]}}
  ]
)

Дороже чем:
db.Stores.aggregate(
  [
    {$lookup: {
      from: 'Products',
      localField: 'products',
      foreignField: 'name',
      as: 'productsArray'}},
    {$unwind: {path: "$productsArray"}},
    {$match: {"productsArray.price": {$gt: 40}}},
    {$group: {
      _id: "$name",
      numberOfProducts: {$sum: 1}}}
  ]
)
*/

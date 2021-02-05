package mongo.commands;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import mongo.Store;
import mongo.commands.parent.ShoppingCommand;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.gt;

public class ShowStatistics implements ShoppingCommand {

    public static final Long PRICE = 100L;

    @Override
    public void executeCommand(String command,
                               MongoCollection<Document> collectionOfStores,
                               MongoCollection<Document> collectionOfProducts) {

        // Средняя цена, наибольшая цена, наименьшая цена и общее число товаров в каждом магазине
        List<Bson> pipelineStats = Arrays.asList(
                lookup("Products", "products", "name", "productsArray"),
                unwind("$productsArray"),
                group("$name",
                        avg("avgPrice", "$productsArray.price"),
                        max("biggestPrice", "$productsArray.price"),
                        min("lowestPrice", "$productsArray.price"),
                        sum("numberOfProducts", 1L)));

        // Количество товаров, цена которых выше нужной (100 рублей)
        List<Bson> pipelineGreaterThan = Arrays.asList(
                lookup("Products", "products", "name", "productsArray"),
                unwind("$productsArray"),
                match(
                        gt("productsArray.price", PRICE)),
                group("$name",
                        sum("numberOfGreaterThanProducts", 1L)));

        AggregateIterable<Document> result = collectionOfStores.aggregate(pipelineStats);
        AggregateIterable<Document> resultGreaterThan = collectionOfStores.aggregate(pipelineGreaterThan);
        MongoCursor<Document> iterator = result.iterator();
        MongoCursor<Document> iteratorGreaterThan = resultGreaterThan.iterator();

        // Добавляем в лист Магазинов все магазины и заполняем их поля
        List<Store> listOfStores = new ArrayList<>();

        while (iterator.hasNext()) {
            Document document = iterator.next();
            Store store = new Store();
            store.setStoreName(document.get("_id").toString());
            store.setAvgPrice(document.getDouble("avgPrice"));
            store.setBiggestPrice(document.getInteger("biggestPrice"));
            store.setLowestPrice(document.getInteger("lowestPrice"));
            store.setNumberOfProducts(document.getLong("numberOfProducts"));
            store.setNumberOfGreaterThanProducts(0L);

            listOfStores.add(store);
        }

        while (iteratorGreaterThan.hasNext()) {
            Document document2;
            Long numberOfGreaterThanProducts;

            document2 = iteratorGreaterThan.next();
            numberOfGreaterThanProducts = document2.getLong("numberOfGreaterThanProducts");

            Store storeToFind = new Store(document2.get("_id").toString());
            int indexOfStore = listOfStores.indexOf(storeToFind);
            listOfStores.get(indexOfStore).setNumberOfGreaterThanProducts(numberOfGreaterThanProducts);
        }

        // Распечатываем все магазины в консоль
        listOfStores.forEach(System.out::println);
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
      numberOfProducts: {$sum: 1} }}
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

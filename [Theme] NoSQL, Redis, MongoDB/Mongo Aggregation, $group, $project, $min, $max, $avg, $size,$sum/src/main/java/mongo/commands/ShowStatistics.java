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
import java.util.NoSuchElementException;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Sorts.ascending;

public class ShowStatistics implements ShoppingCommand {

    public static final Long PRICE = 100L;

    @Override
    public void executeCommand(String command,
                               MongoCollection<Document> collectionOfStores,
                               MongoCollection<Document> collectionOfProducts) {

        // Средняя цена, наибольшая цена, наименьшая цена и общее число товаров в каждом магазине
        // Через билдер не получилось, выдавал ошибку "Can't find a codec for class com.mongodb.client.model.BsonField"
        List<Bson> pipelineStats = Arrays.asList(
                new Document("$project",
                        new Document("name", "$name")
                                .append("avgPrice", new Document("$avg", "$products.price"))
                                .append("biggestPrice", new Document("$max", "$products.price"))
                                .append("lowestPrice", new Document("$min", "$products.price"))
                                .append("numberOfProducts", new Document("$size", "$products"))),
                new Document("$sort", new Document("name", 1L)));

        // Количество товаров, цена которых выше нужной (100 рублей)
        List<Bson> pipelineGreaterThan = Arrays.asList(
                unwind("$products"),
                match(gt("products.price", PRICE)),
                group("$name", sum("number", 1L)),
                sort(ascending("_id")));

        AggregateIterable<Document> result = collectionOfStores.aggregate(pipelineStats);
        AggregateIterable<Document> resultGreaterThan = collectionOfStores.aggregate(pipelineGreaterThan);
        MongoCursor<Document> iterator = result.iterator();
        MongoCursor<Document> iteratorGreaterThan = resultGreaterThan.iterator();

        // Добавляем в лист Магазинов все магазины и заполняем их поля
        List<Store> listOfStores = new ArrayList<>();

        while (iterator.hasNext()) {
            Document document = iterator.next();
            Store store = new Store();
            store.setStoreName(document.get("name").toString());
            store.setAvgPrice(document.getDouble("avgPrice"));
            store.setBiggestPrice(document.getInteger("biggestPrice"));
            store.setLowestPrice(document.getInteger("lowestPrice"));
            store.setNumberOfProducts(document.getInteger("numberOfProducts"));
            store.setNumberOfGreaterThanProducts(0L);

            listOfStores.add(store);
        }

        while (iteratorGreaterThan.hasNext()) {
            Document document2;
            Long numberOfGreaterThanProducts;
            try {
                document2 = iteratorGreaterThan.next();
                numberOfGreaterThanProducts = document2.getLong("number");
            } catch (NoSuchElementException e) {
                break;
            }

            Store storeToFind = new Store(document2.get("_id").toString());
            int indexOfStore = listOfStores.indexOf(storeToFind);
            listOfStores.get(indexOfStore).setNumberOfGreaterThanProducts(numberOfGreaterThanProducts);
        }

        // Распечатываем все магазины в консоль
        listOfStores.forEach(System.out::println);
    }
}

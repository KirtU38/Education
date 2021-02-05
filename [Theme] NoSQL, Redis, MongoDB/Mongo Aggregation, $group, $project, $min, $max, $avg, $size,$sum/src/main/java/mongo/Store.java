package mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import mongo.commands.ShowStatistics;

import java.util.Objects;

@NoArgsConstructor
@Data
public class Store {

    private String storeName;
    private double avgPrice;
    private int biggestPrice;
    private int lowestPrice;
    private Long numberOfProducts;
    private Long numberOfGreaterThanProducts;

    public Store(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(storeName, store.storeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeName);
    }

    @Override
    public String toString() {
        return "Название магазина : " + storeName + "\n" +
                "Средняя цена товаров: " + avgPrice + "\n" +
                "Самый дорогой товар : " + biggestPrice + " рублей\n" +
                "Самый дешевый товар : " + lowestPrice + " рублей\n" +
                "Количество товаров : " + numberOfProducts + "\n" +
                "Количество товаров дороже " + ShowStatistics.PRICE + " рублей : " + numberOfGreaterThanProducts + "\n\n";

    }
}

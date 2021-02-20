public class InterfacesTest implements Interface2, Interface1 {


    @Override
    public void print(String s) {
        Interface1.super.print("Working");
    }

    public static void main(String[] args) {

        Interface2 test = new InterfacesTest();
        test.print("asd");
    }
}
// Если у интерфейсов одинаково названы методы, но разные аргументы, нужно будет Override оба, получится Перегруженный метод

// Если у интерфейсов одинаково названы методы и одинаковые аргументы, нужно Override только один метод
// Но стоит сказать, что имплементится метод из интерфейса, который указан первым, но мы можем указать тип переменной и
// Interface1 и Interface2

// Если у интерфейсов одинаково названы методы и одинаковые аргументы, но один из методов default с какой-то реализацией,
// то нам всё равно нужно будет его Override

// Если у интерфейсов одинаково названы методы и одинаковые аргументы, и оба метода dafault, то придется Override
// этот метод

// Можно выбрать какую default реализацию использовать через Interface1.super.print();

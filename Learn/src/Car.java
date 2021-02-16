public class Car {

    public static int carCounter = 10;
    private String description = "30";

    // Это статический блок, он вызывается один раз за всё время при первой инициализации класса
    static {
        System.out.println("static super-class блок и переменные " + carCounter);
    }
    // А этот блок вызывается каждый раз при создании нового обьекта, вызывается до конструктора
    {
        System.out.println("обычный super-class блок и переменные " + description);
    }


    public Car() {
        System.out.println("super-class конструктор Car " + description);
    }

    public String getDescription() {
        return description;
    }
}

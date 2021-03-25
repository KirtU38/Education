public class Singleton {

  private final String name;
  private static boolean isCreated = false;

  private Singleton(String name) {
    this.name = name;
  }

  public static Singleton getInstance(String name) {
    if (!isCreated) {
      isCreated = true;
      System.out.println("");
      return new Singleton(name);
    }
    System.out.println("Already created");
    return null;
  }
  
  @Override
  public String toString() {
    return "Singleton{" +
            "name='" + name + '\'' +
            '}';
  }
}
// Здесь Класс, который можно создать только в одном экземпляре.
// Для этого нужно сделать private Конструктор и public Метод, который будет возвращать Обьект,
// но только если он ещё не создан.

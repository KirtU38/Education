public class Main {
  public static void main(String[] args) {
    Singleton singleton = Singleton.getInstance("Egor");
    System.out.println(singleton);
    Singleton singletonCopy = Singleton.getInstance("Tasia");
    System.out.println(singletonCopy);
  }
}
// Singleton - это порождающий паттерн.
// Singleton позволяет создать Обьект только в единственном экземпляре, и запрещает создавать новые
// Обьекты этого Класса.

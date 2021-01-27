public interface TestInterface {

    default void print(String text){
        System.out.println("fuck");
    }

    String getName();

}

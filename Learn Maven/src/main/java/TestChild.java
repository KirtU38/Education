public class TestChild extends Test {

    public TestChild(int x) {
        super(x+1);
    }

    /*public TestChild() {
        this(5);
    }*/

    public void print(){
        System.out.println(x);
        System.out.println(super.x);
    }
}

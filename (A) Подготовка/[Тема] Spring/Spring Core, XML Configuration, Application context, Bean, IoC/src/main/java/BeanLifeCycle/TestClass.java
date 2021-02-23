package BeanLifeCycle;

public class TestClass {

    InjectedClass injectedClass;

    public TestClass(InjectedClass injectedClass) {
        this.injectedClass = injectedClass;
        System.out.println("TestClass constructor");
    }

    public void myInitMethod(){
        System.out.println("TestClass init method");
    }

    public void myDestroyMethod(){
        System.out.println("TestClass destroy method");
    }

    public void simpleMethod(){
        System.out.println("TestClass simple method");
    }
}

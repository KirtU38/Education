public class StackAndFrame {

    // main будет внизу Стека
    public static void main(String[] args) {

        foo();
    }
    // foo() вызывает bar()
    private static void foo() {
        System.out.println("foo");
        bar();
    }
    // bar() вызывает end()
    private static void bar() {
        System.out.println("bar");
        end();
    }
    // end() последний, будет сверху Стэка
    private static void end() {
        System.out.println("end");
    }
}
//
//         end()
//         bar()
//         foo()   <- каждый из этих методов называется Frame
//         main
//       --------
//
// Когда Джава вызывает методы, она кладёт их в Stack, это структура данных типа FILO (First In Last Out)
// То есть по принципу тарелок, кладешь их сверху, потом убираешь тоже сверху

// Когда вызывается метод, сверху нынешнего Стэка создается новый Frame

// У каждого метода(или фрейма) есть свой scope, то есть метод, то есть переменные внутри метода больше никому не видны
// Также как у for лупа есть свой scope, например ты не может использовать переменные, инициализированные внутри лупа


import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // Non-static
        TestCommon test = new TestCommon();
        new Thread(() -> {
            try {
                test.print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                test.wtf();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Static
        /*new Thread(() -> {
            try {
                TestStatic.print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TestStatic.wtf();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        // Static with Non-static
        /*TestStaticAndNonStatic test = new TestStaticAndNonStatic();
        new Thread(() -> {
            try {
                test.print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TestStaticAndNonStatic.wtf();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/





    }
}
// ТЕРМИНЫ:
// Test test = new Test() - Обьект класса
// Монитор - понятие только в synchronized методах и блоках. Если например мы вызвали non-static synchronized метод
// у обьекта, то thread захватывает монитор у всего Обьекта класса, то есть на время выполнения этого метода все остальные
// non-static synchronized методы будут ждать монитора Обьекта класса
// mutex = monitor

// synchronized означает "Тебе нужно иметь монитор (этого) обьекта, чтобы выполнить код ниже"

// Определение synchronized: Если блок кода помечен ключевым словом synchronized, это значит, что блок может выполняться
// только одним потоком одновременно. Thread вошедший в synchronized метод или блок захватит монитор у обьекта, по которому
// синхронизирован метод или блок.
// Если это non-static synchronized - то у "this", то есть самого обьекта класса.
// Если это static synchronized - то у самого класса "Test.class".
// Если это блок synchronized(dummy) - то у обьекта dummy.
// Когда thread встречает например synchronized(dummy), то этот блок ему говорит "Если монитор dummy свободен,
// то пройди дальше и захвати его монитор. Если монитор dummy захвачен, то подожди пока другой thread его освободит или
// уснет через wait() освободив монитор"

// TestCommonUnderstand - там понятно как работают non-static

// Thread может захватывать сколько угодно мониторов у разных обьектов

// Обьект имеет только один монитор, и если он кому то его отдал, то все остальные thread`ы которые ждут монитора этого
// обьекта должны ждать пока он освободится

// Можно вызвать метод wait() и notify() только на обьект, монитор которого ты захватил (ключ от которого у тебя)
// Соответственно, wait() и notify() работают только в synchronized методах и блоках

// Просто wait() без обьекта означает this.wait(), то есть вызывается на обьект класса

// wait() - "Заморозить thread который наткнулся на эту строку и освободить монитор Обьекта класса,
// но только если у thread`а был захвачен монитор на Обьект класса"

// dummy.wait() - "Заморозить thread который наткнулся на эту строку и свободить монитор обьекта dummy"
// notify() - "Разморозить случайный thread которы ждет монитора Обьект класса, но только если у тебя сейчас
// захвачен этот монитор"

// Если это просто synchronized метод, то при выполнении он берет монитор обьекта, у которого вызывается этот метод
// Если это static synchronized метод, то просто вызвать wait() notify() не получится тк это non-static
// методы класса Object и не могут быть вызваны в static методе.
// Но можно создать dummy-Object и static методы будут захватывать у него монитор, а когда нужно, можно будет вызвать
// на этом обьекте wait() и notify










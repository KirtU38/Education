import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Square extends RecursiveAction {

    long[] array;
    private int start;
    private int end;
    int threshold = 13000000;

    public Square(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {

        //System.out.println(end - start + "   " + Thread.currentThread().getName());

        if ((end - start) <= threshold) {
            for (int i = start; i < end; i++) {
                array[i] = array[i] * array[i];
            }
            //System.out.println(Arrays.toString(array));
        } else {
            int mid = (start + end) / 2;
            Square leftTask = new Square(array, start, mid);
            Square rightTask = new Square(array, mid, end);

            // fork() без join()
            // Иногда нам не нужно ждать возвращения результата, потому что нам не важно,
            // закончит ли первый таск выполнение перед началом второго, и тогда можно использовать просто fork(),
            // который будет просто разветвлять наше дерево задач
            leftTask.fork();
            rightTask.fork();





            // fork(), join()
            // Метод fork реально начинает асинхронное выполнение задачи,
            // то что задача начинает выполняться только когда вызван join() это пиздеж
            /*leftTask.fork();
            System.out.println("Левый форкается");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rightTask.fork();
            System.out.println("Правый воркается");

            leftTask.join();
            System.out.println("leftTask is joined()");
            rightTask.join();
            System.out.println("rightTask is joined()");*/










            // invoke(), compute()
            // Эти методы внутри задачи делают одно и то же - выполняют задачу последовательно тем же тредом,
            // что их вызвал, без разветвления(без fork())
            /*leftTask.invoke();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("leftTask is joined()");
            rightTask.invoke();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("rightTask is joined()");*/










            // invokeAll
            // А вот он по настоящему форкает задачи, запускает их асинхронно и потом возвращает значения(если есть)
            /*invokeAll(leftTask, rightTask);
            System.out.println("Левый и правый таск посчитаны и возведены в квадрат " + Thread.currentThread().getName());
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class WithSharedResource {

    List<Integer> list = new ArrayList<>();
    //Vector<Integer> list = new Vector<>();

    public void print(int start, int end) throws InterruptedException {

        for (int i = start; i < end; i++) {
            list.add(i);
        }
        System.out.println(list.size() + " " + Thread.currentThread().getName());
    }
}

import java.util.ArrayList;
import java.util.List;

public class NoSharedResource {

    public void print(int start,int end) throws InterruptedException {

        List<Integer> list = new ArrayList<>();

        for (int i = start; i < end; i++) {
            list.add(i);
        }
        System.out.println(list.size() + " " + Thread.currentThread().getName());
    }
}

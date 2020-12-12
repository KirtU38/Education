import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FormatListForkJoin extends RecursiveTask<List<String>> {

    private static final HashSet<String> SET_OF_UNIQUE_LINKS = new HashSet<>();
    String url;
    int numOfTabs;
    List<String> list;
    List<FormatListForkJoin> listOfTasks = new ArrayList<>();

    public FormatListForkJoin(String url, int numOfTabs, List<String> list) {
        this.url = url;
        this.numOfTabs = numOfTabs;
        this.list = list;
    }

    @Override
    protected List<String> compute() {

        List<String> finalList = new ArrayList<>();
        if (SET_OF_UNIQUE_LINKS.contains(url)) {
            return finalList;
        }
        SET_OF_UNIQUE_LINKS.add(url);
        finalList.add("\t".repeat(numOfTabs) + url);
        numOfTabs++;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches(url + ".+")
                    && !list.get(i).matches("^" + url + "$")) {

                FormatListForkJoin task = new FormatListForkJoin(list.get(i), numOfTabs, list);
                listOfTasks.add(task);
            }
        }
        ForkJoinTask.invokeAll(listOfTasks);
        listOfTasks.forEach(task->finalList.addAll(task.join()));

        return finalList;
    }
}


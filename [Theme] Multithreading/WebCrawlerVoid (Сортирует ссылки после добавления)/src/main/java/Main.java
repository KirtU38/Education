import java.io.FileNotFoundException;

public class Main {

    public static final String PATH = "src/main/resources/skillbox.txt";
    public static final String ROOT_URL = "https://skillbox.ru/";

    public static void main(String[] args) throws FileNotFoundException {

        WebCrawlerVoid webCrawler = new WebCrawlerVoid();
        webCrawler.writeMapOfURLToFile(PATH, ROOT_URL);
    }
}
// Неплохо обьяснено https://www.baeldung.com/java-fork-join
// А еще лучше у Шилдта
// По сути ForkJoin это система разделения задач на отдельные мелкие задачи, а потом задачи подряд заканчиваются
// и отдают свое значение по очереди благодаря команде join(), плюс применяетя принцип work-stealing когда потоки
// без работы забирают работу у других задач(с конца очереди)
//
// fork() - "Я отправляю эту задачу в пул задач и она начнет асинхронно выполняться сразу, а поток который вызвал эту задачу продолжается"
// join() - "Я верну значение, когда задача будет выполнена полностью, а код дальше меня выполняться до тех пор не будет"
//
// Лучше всего задачи отправлять в Лист задач, и потом можно будет итерировать задачи через лист вызывая у низ метод join(),
// который запустит эти задачи и вернут значение в конце, таким образом мы ускоряем выполнение программы
// благодаря многопоточности, но при этом сохраняем очередность выполнения(которая часто нужна)
// Это показано в этой WebCrawler
//
// fork() join() метод
//
// WebCrawler task = new WebCrawler(link, rootURL, isAbsolute, numOfTabs); - создаем таск который extends RecursiveTask с методом compute()
// task.fork();                                                            - добавляем задачу в пул асинхронного выполнения
// listOfTasks.add(task);                                                  - добавляем эту задачу в лист задач
// for (WebCrawler task : listOfTasks) {                                   - запускаем каждую задачу методом join()(вызываем у обьекта метод compute()) из листа задач, и каждая задача будет выполняться до конца, потом возвращять значение, и потом то же самое со второй задачей и тд
//     listOfNestedLinks.addAll(task.join());
// }
//
//
// invokeAll() метод
//
//for(String link : listOfLinks){                                              - прозодим сразу по всем таскам(это важно)
//        WebCrawler task = new WebCrawler(link,rootURL,isAbsolute,numOfTabs); - создаем таск
//        listOfTasks.add(task);                                               - добавляем каждый таск в лист
//        }
//        Collection<WebCrawlerInvokeAll> returnedTasks = ForkJoinTask.invokeAll(listOfTasks); - автоматом поочереди форкает и запускает(запускает у каждой задачи метод compute()) каждую задачу
//
//        for(WebCrawler taskResult : returnedTasks){
//        listOfNestedLinks.addAll(taskResult.join());                         - дожидаемся выполнения каждой из задач и возвращаем её значение
//        }


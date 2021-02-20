import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class XMLHandler extends DefaultHandler {

    private Thread savingThread;
    private DBConnection dbConnection;
    private StringBuilder insertQuery;
    // Копия, в которую будем передавать оригинал и отдавать копию в multiinsert()
    private StringBuilder insertQueryCopy;
    // А это обьект Future(пока что null), который примет в себя submit(multiInsertTask)
    private Future<?> dbRunnableFuture;

    public XMLHandler(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        insertQuery = new StringBuilder(Loader.SIZE);
        insertQueryCopy = new StringBuilder(Loader.SIZE);
    }

    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) {
        try {
            if (tag.equals("voter")) {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay").replace('.', '-');

                insertQuery.append((insertQuery.length() == 0 ? "" : ",")
                        + "('" + name + "', '" + birthDay + "')");

                // ЗДЕСЬ ВСЁ
                // Буфер Билдера, если size больше 20мил то сбрасывает всё отдельному потоку в БД и очищает Билдер
                if (insertQuery.length() > Loader.SIZE) {
                    System.out.println("Набралось в оригинал " + Thread.currentThread().getName());
                    // Защита от первой итерации
                    if (dbRunnableFuture != null) {
                        // Если выгрузка в БД ещё выполняется, подождать её выполнения
                        dbRunnableFuture.get();
                    }
                    // Удаляем предыдущие данные из копии Билдера и загружаем новые в копию
                    insertQueryCopy.delete(0, Loader.SIZE);
                    insertQueryCopy = new StringBuilder(insertQuery);
                    // Сабмитим таск(то есть начинаем его) и создаем из него Future, который потом сможем подождать через get()
                    dbRunnableFuture = Loader.service.submit(() -> dbConnection.multiInsert(insertQueryCopy.toString()));
                    System.out.println("String передался в Multi-insert " + Thread.currentThread().getName());
                    insertQuery.delete(0, insertQuery.length());
                    System.out.println("Удалился оригинал");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endDocument() {
        try {
            if (insertQuery.length() > 0) {
                System.out.println("Сбрасываются последние элементы");
                //                savingThread.join();
                // Здесь для примера имплементация с Runnable, а не с лямбдой
                //                savingThread = new Thread(new Saver(insertQuery.toString(), dbConnection));
                System.out.println("String передался в Multi-insert");
                //                savingThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

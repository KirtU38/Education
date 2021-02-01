
import java.io.File;

public class Main {

    private static int newWidth = 300;
    private static int numberOfCores = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        String srcFolder = "source";
        String dstFolder = "destination";

        File srcDir = new File(srcFolder);

        File[] files = srcDir.listFiles();

        int numberOfFiles = files.length;

        int sizeOfSubArray = (int) Math.round((double) numberOfFiles / numberOfCores);

        System.out.println(sizeOfSubArray + " size of sub arrays");
        System.out.println(numberOfCores + " number of cores");

        int startOffset = 0;
        boolean lastThread = false;

        for (int i = 0; i < numberOfCores && numberOfFiles > startOffset; i++) { // Если будет только 1 файл, то не будет открыто 4 потока на каждое ядро

            File[] subArray;
            if (numberOfCores == (i + 1)) {    // Если 9 картинок и 4 потока, все примут по 2 элемената, а последний примет 3 (примет оставшиеся)
                lastThread = true;
            }
            if (lastThread) {                                              // Реализация последнего потока
                int sizeOfLastSubArray = files.length - startOffset;
                subArray = new File[sizeOfLastSubArray];
                System.arraycopy(files, startOffset, subArray, 0, subArray.length);
                System.out.println("LAST THREAD SIZE " + subArray.length);
            } else {                                                       // Реализация обычного потока
                subArray = new File[sizeOfSubArray];
                System.arraycopy(files, startOffset, subArray, 0, subArray.length);
                startOffset += subArray.length;
            }

            ImageResizer resizer = new ImageResizer(subArray, newWidth, dstFolder, start, i + 1);
            resizer.start();
        }
    }
}

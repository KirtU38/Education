import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CarNumberGenerator implements Runnable {

    private final int startRegion;
    private final int endRegion;
    private final char[] letters;
    private final int buffer_size;

    public CarNumberGenerator(int startRegion, int endRegion, char[] letters, int buffer_size) {
        this.startRegion = startRegion;
        this.endRegion = endRegion;
        this.letters = letters;
        this.buffer_size = buffer_size;
    }

    @Override
    public void run() {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("res/numbers" + startRegion + "-" + (endRegion - 1) + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder(buffer_size + 6);
        StringBuilder padNumberBuilder = new StringBuilder(3);

        String[] carNumbers = new String[1000];

        for (int regionCode = startRegion; regionCode < endRegion; regionCode++) {
            builder.delete(0, 15_536_455);
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {

                            if (regionCode > startRegion) {
                                builder.append(firstLetter)
                                        .append(carNumbers[number])
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(carNumbers[regionCode], 1, 3)
                                        .append("\n");
                            } else {
                                String carNumber = padNumber(number, 3, padNumberBuilder).toString();
                                carNumbers[number] = carNumber;
                                builder.append(firstLetter)
                                        .append(carNumber)
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(regionCode, 2, padNumberBuilder))
                                        .append("\n");
                            }
                        }
                    }
                }
            }
            writer.write(builder.toString());
        }
        writer.flush();
        writer.close();
    }

    private StringBuilder padNumber(int number, int requiredLength, StringBuilder padNumberBuilder) {

        padNumberBuilder.delete(0, 3);
        padNumberBuilder.append(number);
        int numberLength = padNumberBuilder.length();

        // Добавляем нули, только если длина номера меньше нужной
        if (numberLength < requiredLength) {
            int padSize = requiredLength - numberLength;

            for (int i = 0; i < padSize; i++) {
                padNumberBuilder.insert(0, "0");
            }
        }
        return padNumberBuilder;
    }
}

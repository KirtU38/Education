import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {

    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;
    int threadNumber;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start, int thread) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
        this.threadNumber = thread;
    }

    public void run() {
        try {

            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                BufferedImage newImage = Scalr.resize(
                        image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, newWidth);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
                System.out.println(threadNumber + " thread - " + newFile.getName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Thread " + threadNumber + " duration: " + (System.currentTimeMillis() - start));
    }
























    /*public void run() {

        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));

                BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }*/
}

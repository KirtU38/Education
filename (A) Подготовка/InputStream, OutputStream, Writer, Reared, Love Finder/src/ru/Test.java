package ru;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
  public static void main(String[] args) throws IOException, InterruptedException {
    File file = new File("/Users/egor/Downloads/wp2324433-retrowave-wallpapers.jpg");
    FileInputStream fis = new FileInputStream(file);

    File file1 = new File("/Users/egor/Desktop/test.jpg");
    FileOutputStream fos = new FileOutputStream(file1);

    while (true) {
      int byteRead = fis.read();
      if (byteRead == -1) {
        break;
      }
      fos.write(byteRead);
    }
  }
}

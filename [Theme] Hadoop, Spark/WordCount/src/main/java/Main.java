import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern SPACE = Pattern.compile("\\s+");

  public static void main(String[] args) throws Exception {

    if (args.length != 2) {
      System.err.println("Usage: JavaWordCount <in_file> <out_file>");
      System.exit(1);
    }

    SparkSession spark =
        SparkSession.builder()
            .config("spark.master", "local")
            .appName("JavaWordCount")
            .getOrCreate();

    JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();

    JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

    JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

    JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

    counts.saveAsTextFile(args[1]);

    spark.stop();
  }
}

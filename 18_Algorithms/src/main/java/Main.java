import rabin_karp.RabinKarpExtended;

public class Main {
  public static void main(String[] args) throws Exception {
    RabinKarpExtended text = new RabinKarpExtended("oiuytriqweoiuyqwtyetqwe");
    System.out.println(text.search("QWE"));
  }
}

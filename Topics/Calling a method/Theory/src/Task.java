// You can experiment here, it won’t be checked

import java.util.List;

public class Task {
  public static void main(String[] args) {
    List<String> list = List.of("1 tree", "trees", "17 trees", "7 trees", "117 trees");

    list.forEach(s -> System.out.println(s.matches("..? trees?")));
  }
}

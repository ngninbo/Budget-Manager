import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int units = scanner.nextInt();
        System.out.println(category(units));
    }

    private static String category(int units) {
        if (units < 1) {
            return "no army";
        } else if (units < 20) {
            return "pack";
        } else if (units < 250) {
            return "throng";
        } else if (units < 1000) {
            return "zounds";
        } else {
            return "legion";
        }
    }
}
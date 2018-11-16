import java.util.Scanner;

public class InputProcessor {
    private Scanner scan;

    public InputProcessor() {
        scan = new Scanner(System.in);
    }

    public int nextInt() {
        return scan.nextInt();
    }

    public String nextLine() {
        return scan.nextLine();
    }
}

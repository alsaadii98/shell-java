// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.printf(input + ": command not found");
    }
}

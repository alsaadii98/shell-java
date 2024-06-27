// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            System.out.println(input + ": command not found");
        }
    }
}

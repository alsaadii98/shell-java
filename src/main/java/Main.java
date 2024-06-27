// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("exit 0")) {
                scanner.close();
                break;
            } else if (input.startsWith("echo ")) {
                System.out.println(input.substring(5));
            } else if(input.startsWith("type ")) {
                switch (input.substring(5)) {
                    case "echo":
                    case "exit":
                    case "type":
                        System.out.println(input.substring(5) + " is a shell builtin");
                        break;
                    default:
                        System.out.println(input.substring(5) + ": command not found");
                        break;
                }
            } else {
                System.out.println(input + ": command not found");
            }
        }


    }
}

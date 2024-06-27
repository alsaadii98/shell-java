// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                List<String> builtInCommands = Arrays.asList("echo", "type", "exit");
                if(builtInCommands.contains(input.substring(5))) {
                    System.out.println(input.substring(5) + " is a shell builtin");
                } else if (System.getenv("PATH") != null) {
                    String pathEnv = System.getenv("PATH");
                    String[] paths = pathEnv.split(":");
                    boolean found = false;
                    for (String path : paths) {
                        File file = new File(path + "/" + input.substring(5));
                        if(file.exists() && file.canExecute()) {
                            System.out.println(input.substring(5) + " is " + file.getAbsolutePath());
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        System.out.println(input.substring(5) + ": not found");
                    }
                } else {
                    System.out.println(input.substring(5) + ": not found");
                }
            } else if(System.getenv("PATH") != null && input.endsWith("alice")) {
                String pathEnv = System.getenv("PATH");
                String[] paths = pathEnv.split(":");
                String[] names = input.split(" ");
                boolean found = false;
                for (String path : paths) {

                    File file = new File(path + "/" + names[0]);
                    if (file.exists() && file.canExecute()) {

                        Process process = Runtime.getRuntime().exec(path);
                        process.getInputStream().transferTo(System.out);
                        found = true;
                        break;
                    }
                }

                if(!found) {
                    System.out.println(names[0] + ": not found");
                }

            } else {
                System.out.println(input + ": command not found");
            }
        }


    }
}

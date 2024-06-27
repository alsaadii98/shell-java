// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
            } else if(input.endsWith("alice")) {
                executeExternalCommand(input);
            } else {
                System.out.println(input + ": command not found");
            }
        }


    }

    private static void executeExternalCommand(String input) {
        String[] words = input.split(" ");
        String command = words[0];

        String pathEnv = System.getenv("PATH");
        String[] paths = pathEnv.split(":");
        boolean found = false;

        for (String path : paths) {
            File file = new File(path + "/" + command);
            if (file.exists() && file.canExecute()) {
               try {
                   ProcessBuilder processBuilder = new ProcessBuilder(words);
                   processBuilder.directory(new File(path));
                   Process process = processBuilder.start();
                   Scanner scanner = new Scanner(process.getInputStream());
                   while (scanner.hasNextLine()) {
                       System.out.println(scanner.nextLine());
                   }
                   int existCode = process.waitFor();
                   if (existCode != 0) {
                       Scanner errorScanner = new Scanner(process.getErrorStream());
                       while (errorScanner.hasNextLine()) {
                           System.out.println(errorScanner.nextLine());
                       }
                   }
                   found = true;
                   break;
               } catch (IOException e) {
                   System.out.println("Error executing command: " + e.getMessage());
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }


            }
        }
    }
}

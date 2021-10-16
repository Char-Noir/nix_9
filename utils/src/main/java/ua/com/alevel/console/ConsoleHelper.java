package ua.com.alevel.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleHelper {

    public static final String DELIMETR = "##########################################################";

    public static void runConsoleApplication(List<ConsoleSubroutine> subroutines) {

        StringBuilder programDescription = new StringBuilder("Hello, dude!\n This program allow you to perform some actions by your choice.\n Just pick number from the list bellow. For exit type something else\n");

        for (int i = 0; i < subroutines.size(); i++) {
            programDescription.append(i + 1).append(". ").append(subroutines.get(i).getShortDescription()).append("\n");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(programDescription);

            for (String line = br.readLine(); line != null; line = br.readLine()) {

                int choice = Integer.parseInt(line)-1;
                if (choice >= 0 && choice < subroutines.size()) {
                    ConsoleSubroutine subroutine = subroutines.get(choice);
                    System.out.println("Задание №"+(choice+1));
                    System.out.println(DELIMETR);
                    System.out.println(subroutine.getLongDescription());
                    subroutine.run(br);
                    System.out.println(programDescription);
                } else {
                    System.out.println("Хорошего дня!😀");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

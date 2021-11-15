package ua.com.alevel.console;

import ua.com.alevel.console.annotation.ListSubroutineAnnotationAnalyze;
import ua.com.alevel.console.responce.ConsoleResponceSubroutine;
import ua.com.alevel.console.responce.Responce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class ConsoleHelper {

    public static final String DELIMETR = "##########################################################";

    public static void runConsoleApplication(List<ConsoleSubroutine> subroutines) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            printDescriptions(subroutines);

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (!line.matches("[0-9]+")) {
                    System.out.println("Хорошего дня!😀");
                    break;
                }
                int choice = Integer.parseInt(line) - 1;
                if (choice >= 0 && choice < subroutines.size()) {
                    ConsoleSubroutine subroutine = subroutines.get(choice);
                    System.out.println("Задание №" + (choice + 1));
                    System.out.println(DELIMETR);
                    System.out.println(subroutine.getLongDescription());
                    if (subroutine.getExpectedInput() != "") {
                        System.out.println("Ожидаемый ввод: " + subroutine.getExpectedInput());
                    }
                    subroutine.run(br);
                    printDescriptions(subroutines);
                } else {
                    System.out.println("Хорошего дня!😀");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runConsoleResponseApplication(List<ConsoleResponceSubroutine> subroutines) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String redirect = null;
            ListSubroutineAnnotationAnalyze.runFirstSubroutine(subroutines.toArray(new Subroutine[0]), bufferedReader);
            while (true) {
                String line = null;
                if (redirect == null) {
                    printDescriptions(subroutines);
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                }
                if ((line != null && line.matches("[0-9]+")) && redirect == null) {
                    int choice = Integer.parseInt(line) - 1;
                    if (choice >= 0 && choice < subroutines.size()) {
                        ConsoleResponceSubroutine subroutine = subroutines.get(choice);
                        redirect = runSubroutine(bufferedReader, String.valueOf(choice + 1), subroutine);
                    } else {
                        System.out.println("Хорошего дня!😀");
                        break;
                    }
                } else {
                    if (redirect != null) {
                        line = redirect;
                    }
                    String name = line;
                    line = line.toLowerCase(Locale.ROOT);
                    boolean isWorked = false;
                    for (ConsoleResponceSubroutine subroutine :
                            subroutines) {
                        if (line.contentEquals(subroutine.getSubroutineURL())) {
                            redirect = runSubroutine(bufferedReader, name, subroutine);
                            isWorked = true;
                            break;
                        }
                    }
                    if (!isWorked) {
                        System.out.println("Хорошего дня!😀");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String runSubroutine(BufferedReader bufferedReader, String path, ConsoleResponceSubroutine subroutine) {
        System.out.println("Задание №" + (path));
        System.out.println(DELIMETR);
        System.out.println(subroutine.getLongDescription());
        if (subroutine.getExpectedInput() != "") {
            System.out.println("Ожидаемый ввод: " + subroutine.getExpectedInput());
        }
        Responce responce = subroutine.run(bufferedReader);
        return responce.getRedirectTo();
    }

    private static void printDescriptions(List<? extends Subroutine> subroutines) {
        StringBuilder programDescription = new StringBuilder("Hello, dude!\n This program allow you to perform some actions by your choice.\n Just pick number from the list bellow. For exit type something else\n");
        for (int i = 0; i < subroutines.size(); i++) {
            Subroutine subroutine = subroutines.get(i);
            programDescription.append(i + 1).append(". ").append(subroutine.getShortDescription());
            if (subroutine instanceof ConsoleResponceSubroutine) {
                programDescription.append(" or \"").append(((ConsoleResponceSubroutine) subroutine).getSubroutineURL()).append("\"");
            }

            programDescription.append("\n");
        }
        System.out.println(programDescription);
    }


}

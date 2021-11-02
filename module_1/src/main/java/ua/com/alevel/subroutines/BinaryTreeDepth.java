package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.level2.TreeHelper;
import ua.com.alevel.level2.entity.BinaryTree;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeDepth extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Позволяет посчитать максимальную глубину бинарного дерева.";
    public final String SHORT_DESCRIPTION = "Глубина бинарного дерева.";

    public final String EXPECTED_INPUT = "Взаимодействуйте с интерфейсом";
    List<String> var;

    @Override
    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public String getExpectedInput() {
        return EXPECTED_INPUT;
    }

    private void initialize() {
        var = new ArrayList<>();
        var.add(". Выйти");
        var.add(". Сгененировать случайное дерево");
        var.add(". Сгенерировать предварительное дерево");
        var.add(". Добавить новое значение в дерево");
        var.add(". Вывести дерево в консоль");
        var.add(". Найти размер дерева(выводит и само дерево в консоль)");
    }

    @Override
    public void run(BufferedReader bufferedReader) {
        BinaryTree binaryTree = new BinaryTree();
        initialize();
        try {
            while (true) {
                for (int i = 0; i < var.size(); i++) {
                    System.out.println((i) + var.get(i));
                }
                String choice = bufferedReader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1:
                        binaryTree = TreeHelper.generateRandomBinaryTree();
                        break;
                    case 2:
                        binaryTree = BinaryTree.create();
                        break;
                    case 3:
                        System.out.println("Введите число");
                        String value = bufferedReader.readLine();
                        binaryTree.add(Integer.parseInt(value));
                        break;
                    case 4:
                        System.out.println(binaryTree.toString());
                        break;
                    case 5:
                        System.out.println(binaryTree.toString());
                        int depth = binaryTree.depth();
                        System.out.println("Максимальная глубина дерева " + depth);
                        break;
                    case 0:
                    default:
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

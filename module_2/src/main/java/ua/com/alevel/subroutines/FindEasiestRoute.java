package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.graph.Dijkstra;
import ua.com.alevel.graph.NodeIndexer;
import ua.com.alevel.graph.ShortestWayGraphAlghorithm;

import java.io.BufferedReader;

public class FindEasiestRoute extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Находит самый короткий путь между городами.";
    public final String SHORT_DESCRIPTION = "Поиск пути";
    public final String EXPECTED_INPUT = "";

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

    @Override
    public void run(BufferedReader bufferedReader) {
        try {
            System.out.println("Введите количество узлов");
            int vNum;
            do {
                try {
                    vNum = Integer.parseInt(bufferedReader.readLine());
                    if (vNum > 10000 || vNum < 1) {
                        throw new RuntimeException("Количество узлов должно быть меньше или равно 10000");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Я вас не понял. Попробуйте еще");
                    continue;
                }
                break;
            } while (true);
            int[][] graph = new int[vNum][vNum];
            NodeIndexer nodeIndexer = new NodeIndexer();
            for (int i = 0; i < vNum; i++) {
                System.out.println("Введите имя узла");
                String name = bufferedReader.readLine();
                nodeIndexer.add(name.trim());
                int id = nodeIndexer.getIndex(name);
                System.out.println("Введите количество соседей");
                int neighbors;
                do {
                    try {
                        neighbors = Integer.parseInt(bufferedReader.readLine());
                    } catch (Exception e) {
                        System.out.println("Я вас не понял. Попробуйте еще");
                        continue;
                    }
                    break;
                } while (true);
                for (int j = 0; j < neighbors; j++) {
                    System.out.println("Введите индекс соседа и стоимость пути");
                    String neighbor;
                    int index;
                    int cost;
                    do {
                        try {
                            neighbor = bufferedReader.readLine();
                            String[] strs = neighbor.split(" ");
                            index = Integer.parseInt(strs[0]);
                            if (index < 1 || index > vNum) {
                                throw new RuntimeException("Сосед должен быть из этой задачи");
                            }
                            cost = Integer.parseInt(strs[1]);
                            if (cost < 1 || cost > 200000) {
                                throw new RuntimeException("Cost must be between 1 and 200000");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Я вас не понял. Попробуйте еще");
                            continue;
                        }
                        break;
                    } while (true);
                    graph[id - 1][index - 1] = cost;
                    graph[index - 1][id - 1] = cost;
                }
            }
            ShortestWayGraphAlghorithm floydWarshall = new Dijkstra().setVNum(vNum).setGraph(graph).setNodeIndexer(nodeIndexer);
            int routes;
            System.out.println("Введите количество путей, которые вы хотите найти");
            do {
                try {
                    routes = Integer.parseInt(bufferedReader.readLine());
                    if (routes > 100 || routes < 1) {
                        throw new RuntimeException("Количество путей должно быть меньше или равно 100");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Я вас не понял. Попробуйте еще");
                    continue;
                }
                break;
            } while (true);
            System.out.println("Введите название узла начального и конечного");
            for (int o = 0; o < routes; o++) {
                String route = bufferedReader.readLine();
                String[] parts = route.split(" ");
                if (parts.length != 2) {
                    throw new RuntimeException("Кажется узлов должно быть два");
                }
                System.out.println(floydWarshall.getDist(parts[0].trim(), parts[1].trim()));
            }
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
}

package ua.com.alevel.graph;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Dijkstra {

    private final int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
    int[] dist;
    private int vNum; // количество вершин
    private int[][] graph; // матрица смежности
    //private int[] dist; // матрица расстояний
    private NodeIndexer nodeIndexer;
    private boolean isSet = false;

    public Dijkstra setNodeIndexer(NodeIndexer nodeIndexer) {
        this.nodeIndexer = nodeIndexer;
        return this;
    }

    //Сам собственно алгоритм
    void run(int[][] mat, int st, int n) {
        dist = new int[n]; //массив расстояний(по умолчанию все значения равны ИНТ_МАКС ака бесконечность)
        int in = 0, u, m = st + 1;//Всякие технические переменные
        boolean[] pos = new boolean[n]; //Массив, где я отмечаю посещенные вершины, по умолчанию они не такие
        for (int i = 0; i < n; i++)//Заполняю массивы значениями по умолчанию
        {
            dist[i] = INF;
            pos[i] = false;
        }
        dist[st] = 0;//растояние до начальной вершины 0
        for (int count = 0; count < n - 1; count++) {
            int min = INF;//начальное значение минимального равен бесконечности
            for (int i = 0; i < n; i++)
                if (!pos[i] && dist[i] <= min) {
                    min = dist[i];
                    in = i;
                }
            u = in;
            pos[u] = true;
            for (int i = 0; i < n; i++)
                if ((!pos[i]) && (mat[u][i] != 0) && (dist[u] != INF) && (dist[u] + mat[u][i] < dist[i]))
                    dist[i] = dist[u] + mat[u][i];//Подсчет новых расстояний для каждой активной вершины
        }
    }


    public Dijkstra setVNum(int vNum) {
        this.vNum = vNum;
        return this;
    }

    public Dijkstra setGraph(int[][] matrix) {
        if (vNum == 0) {
            throw new IllegalArgumentException("First setUp number of v");
        }
        if (matrix.length != vNum || matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix need to be square and mathes number of v");
        }
        this.graph = matrix;
        isSet = true;
        return this;
    }


    public int getDist(int s, int d) {
        run(graph, s, vNum);
        return dist[d];
    }


    public int getDist(String s, String d) {
        return getDist(nodeIndexer.getIndex(s) - 1, nodeIndexer.getIndex(d) - 1);
    }

}

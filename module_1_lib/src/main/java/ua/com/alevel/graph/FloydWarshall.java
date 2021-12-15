package ua.com.alevel.graph;

import static java.lang.Math.min;

public class FloydWarshall {

    private final int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
    private int vNum; // количество вершин
    private int[][] graph; // матрица смежности
    private int[][] dist; // матрица расстояний
    private NodeIndexer nodeIndexer;
    private boolean isSet = false;

    public FloydWarshall setNodeIndexer(NodeIndexer nodeIndexer) {
        this.nodeIndexer = nodeIndexer;
        return this;
    }

    /* Алгоритм Флойда-Уоршелла за O(V^3) */
    public FloydWarshall run() {
        if (!isSet) {
            throw new IllegalArgumentException("First you need to set up method");
        }
        dist = new int[vNum][vNum]; // dist[i][j] = минимальное_расстояние(i, j)
        for (int i = 0; i < vNum; i++) System.arraycopy(graph[i], 0, dist[i], 0, vNum);
        for (int k = 0; k < vNum; k++)
            for (int i = 0; i < vNum; i++)
                for (int j = 0; j < vNum; j++)
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
        return this;
    }

    public FloydWarshall setVNum(int vNum) {
        this.vNum = vNum;
        return this;
    }

    public FloydWarshall setGraph(int[][] matrix) {
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
        if (dist == null) {
            throw new IllegalArgumentException("You need to run method first");
        }
        return dist[s][d];
    }

    public int getDist(String s, String d) {
        System.out.println(nodeIndexer.getIndex(s) - 1);
        System.out.println(nodeIndexer.getIndex(d) - 1);
        return getDist(nodeIndexer.getIndex(s) - 1, nodeIndexer.getIndex(d) - 1);
    }

    public int[][] getDist() {
        return dist;
    }
}

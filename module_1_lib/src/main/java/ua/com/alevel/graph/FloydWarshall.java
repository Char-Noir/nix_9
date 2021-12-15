package ua.com.alevel.graph;

import static java.lang.Math.min;

public class FloydWarshall extends ShortestWayGraphAlghorithm {

    private int[][] dist; // матрица расстояний

    /* Алгоритм Флойда-Уоршелла за O(V^3) */
    public void run(int st) {
        if (!isSet) {
            throw new IllegalArgumentException("First you need to set up method");
        }
        dist = new int[vNum][vNum]; // dist[i][j] = минимальное_расстояние(i, j)
        for (int i = 0; i < vNum; i++) System.arraycopy(graph[i], 0, dist[i], 0, vNum);
        for (int k = 0; k < vNum; k++)
            for (int i = 0; i < vNum; i++)
                for (int j = 0; j < vNum; j++)
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
    }

    @Override
    public int getDist(int s, int d) {
        if (dist == null) {
            throw new IllegalArgumentException("You need to run method first");
        }
        return dist[s][d];
    }

}

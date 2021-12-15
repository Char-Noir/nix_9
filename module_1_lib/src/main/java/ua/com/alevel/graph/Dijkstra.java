package ua.com.alevel.graph;

import ua.com.alevel.level1.ArrayHelper;

import java.util.Arrays;

public class Dijkstra extends ShortestWayGraphAlghorithm {

    private int[][] cashe;

    //Сам собственно алгоритм
    void run(int st) {
        if (ArrayHelper.sum(cashe[st]) > 0) {
            dist = cashe[st];
            return;
        }
        dist = new int[vNum]; //массив расстояний(по умолчанию все значения равны ИНТ_МАКС ака бесконечность)
        int in = 0, u; //Всякие технические переменные
        boolean[] pos = new boolean[vNum]; //Массив, где я отмечаю посещенные вершины, по умолчанию они не такие
        for (int i = 0; i < vNum; i++)//Заполняю массивы значениями по умолчанию
        {
            dist[i] = INF;
            pos[i] = false;
        }
        dist[st] = 0;//растояние до начальной вершины 0
        for (int count = 0; count < vNum - 1; count++) {
            int min = INF;//начальное значение минимального равен бесконечности
            for (int i = 0; i < vNum; i++)
                if (!pos[i] && dist[i] <= min) {
                    min = dist[i];
                    in = i;
                }
            u = in;
            pos[u] = true;
            for (int i = 0; i < vNum; i++)
                if ((!pos[i]) && (graph[u][i] != 0) && (dist[u] != INF) && (dist[u] + graph[u][i] < dist[i]))
                    dist[i] = dist[u] + graph[u][i];//Подсчет новых расстояний для каждой активной вершины
        }
        cashe[st] = dist;
    }

    @Override
    public Dijkstra setVNum(int vNum) {
        this.vNum = vNum;
        this.cashe = new int[vNum][vNum];
        for (int i = 0; i < vNum; i++) {
            Arrays.fill(cashe[i], 0);
        }
        return this;
    }
}
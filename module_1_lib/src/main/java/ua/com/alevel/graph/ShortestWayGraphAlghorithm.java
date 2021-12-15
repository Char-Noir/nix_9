package ua.com.alevel.graph;

public abstract class ShortestWayGraphAlghorithm {
    protected final int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
    protected int[] dist;
    protected int vNum; // количество вершин
    protected int[][] graph; // матрица смежности
    protected NodeIndexer nodeIndexer;
    protected boolean isSet = false;

    public ShortestWayGraphAlghorithm setNodeIndexer(NodeIndexer nodeIndexer) {
        this.nodeIndexer = nodeIndexer;
        return this;
    }

    public ShortestWayGraphAlghorithm setVNum(int vNum) {
        this.vNum = vNum;
        return this;
    }

    public ShortestWayGraphAlghorithm setGraph(int[][] matrix) {
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
        run(s);
        return dist[d];
    }

    public int getDist(String s, String d) {
        return getDist(nodeIndexer.getIndex(s) - 1, nodeIndexer.getIndex(d) - 1);
    }

    abstract void run(int st);
}

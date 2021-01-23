import java.util.Arrays;

public class UnionFind {
    private int[] parent; // parent[i] i的根节点
    private int[] rank;   // rank[i]以i为根节点的连通分量的大小(主要应用于根节点)
    private int count;    // 连通分量数

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
    }

    // 小树 连接到 大树
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
            rank[rootY] += rank[rootX];
        } else {
            parent[rootY] = rootX;
            rank[rootX] += rank[rootY];
        }
        count--;

    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }


    // 非递归
    public int find2(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    public boolean isConnected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        return rootX == rootY;
    }

    public int getCount() {
        return count;
    }
    
    public void logMsg() {
        System.out.println("count = " + count);
        System.out.println("parent: " + Arrays.toString(parent));
        System.out.println("rank: " + Arrays.toString(rank));
    }

}


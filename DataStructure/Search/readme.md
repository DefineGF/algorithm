#### 常规并查集

##### 辅助内存 & 构造函数

```java
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
```

##### 获取根节点

```java
// x = parent[x] 为 根节点索引
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
```

##### 判断是否连接

```java
public boolean isConnected(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    return rootX == rootY;
}
```

##### 合并节点

```java
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX == rootY)
        return;

    if (rank[rootX] < rank[rootY]) { // x to y
        parent[rootX] = rootY;
        rank[rootY] += rank[rootX];
    } else {						 // y to x
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
    }
    count--;
}
```

### 精简内存

**核心思想就是：只使用一个数组record:**

- record[i] = i; 表明 i 节点的根节点是其本身；
- record[i] >= 0: 表明 i 节点的父节点索引为：record[i]; 递归获取指向根节点；
- record[i] < 0: 表明 i 节点为根节点，且 | record[i] | +  1值为连通分量的大小；



##### 完整代码

```java
public class UnionFindSmallMemory {
    private int[] record;
    private int count;

    public UnionFindSmallMemory(int len) {
        count = len;
        record = new int[len];
        for (int i = 0; i < len; i++) {
            record[i] = i;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean isConnected(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        return xRoot == yRoot;
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot)
            return;

        count--;
        if (record[xRoot] < 0 && record[yRoot] >=0) {
            record[yRoot] = xRoot;
            record[xRoot]--;
        } else if (record[xRoot] >= 0 && record[yRoot] < 0) {
            record[xRoot] = yRoot;
            record[yRoot]--;
        } else if (record[xRoot] < 0 && record[yRoot] < 0) {
            if (record[xRoot] < record[yRoot]) { // x -> y
                record[yRoot] += (record[xRoot] - 1);
                record[xRoot] = yRoot;
            } else {
                record[xRoot] += (record[yRoot] - 1);
                record[yRoot] = xRoot;
            }
        } else { // 均为正数 (根节点是其本身)
            record[xRoot] = -1;
            record[yRoot] = xRoot;
        }

    }


    /**
     *
     * @param i
     * @return the index of the find of i
     */
    public int find(int i) {
        if (i == record[i] || record[i] < 0)
            return i;

        // has find
        int parent = i;
        while (record[parent] >= 0) {
            parent = record[parent];
        }
        record[i] = parent; // 趁机修改根节点减少下次搜索时间
        return i;
    }

    public void logMsg() {
        System.out.println("count = " + count + "\n" + Arrays.toString(record));
    }
}
```

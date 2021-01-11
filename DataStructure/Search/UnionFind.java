// 实现并查集
public class UnionFind {
        private int[] parent;   // parent[i] 保存i节点的根节点index
        private int[] rank;     // rank[i] 以i为节点的子树高度

        private UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) { // 初始化值
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y) { // 合并
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) // 同根
                return;
            // low connect high
            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
            }
        }

        public int find(int x) { // 获取节点的根节点index
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean isConnected(int x, int y) { // 判断两个节点是否连通
            int rootX = find(x);
            int rootY = find(y);
            return rootX == rootY;
        }

    }

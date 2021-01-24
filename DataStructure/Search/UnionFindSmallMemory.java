import java.util.Arrays;

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

    public static void main(String []args) {
        int [][]data = {{0,2},{2,3},{2,4}};

        UnionFindSmallMemory unionFindSmallMemory = new UnionFindSmallMemory(5);
        UnionFind unionFind = new UnionFind(5);
        for (int[] aData : data) {
            unionFindSmallMemory.union(aData[0], aData[1]);
            unionFind.union(aData[0], aData[1]);
        }
        unionFindSmallMemory.logMsg();
        unionFind.logMsg();

        System.out.println(unionFindSmallMemory.isConnected(1,3));
        System.out.println(unionFind.isConnected(1,3));
    }
}

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

    public void logMsg() {
        System.out.println("count = " + count + "\n" + Arrays.toString(record));
    }

    public int getCount() {
        return count;
    }

    public boolean isConnected(int x, int y) {
        int xRoot = root(x);
        int yRoot = root(y);
        return xRoot == yRoot;
    }

    public void union(int x, int y) {
        int xRoot = root(x);
        int yRoot = root(y);

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
        } else {
            record[xRoot] = -1;
            record[yRoot] = xRoot;
        }

    }

    public int root(int index) {
        if (index == record[index])
            return index;

        // has root
        while (record[index] >= 0) {
            index = record[index];
        }
        return index;
    }

    public static void main(String []args) {
        UnionFindSmallMemory unionFindSmallMemory = new UnionFindSmallMemory(4);
        unionFindSmallMemory.union(0,1);
        unionFindSmallMemory.union(1,2);
        unionFindSmallMemory.logMsg();
        System.out.println(unionFindSmallMemory.isConnected(0,3));
        System.out.println(unionFindSmallMemory.isConnected(0,2));
    }
}

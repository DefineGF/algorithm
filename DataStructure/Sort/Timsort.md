### Java.TimSort 源码解析

#### 总函数sort

```java
/**
 * work：额外存放数组
 * workBase：work数组 可用空间起始坐标
 * workLen: work数组 可用空间长度
 */
static <T> void sort(T[] a, int lo, int hi, Comparator<? super T> c,
                     T[] work, int workBase, int workLen) {
    assert c != null && a != null && lo >= 0 && lo <= hi && hi <= a.length;

    int nRemaining  = hi - lo;
    if (nRemaining < 2)
        return;  // Arrays of size 0 and 1 are always sorted

    if (nRemaining < MIN_MERGE) { // MIN_MERGE = 32
        int initRunLen = countRunAndMakeAscending(a, lo, hi, c);
        binarySort(a, lo, hi, lo + initRunLen, c);
        return;
    }

    TimSort<T> ts = new TimSort<>(a, c, work, workBase, workLen);
    int minRun = minRunLength(nRemaining);
    do {
        int runLen = countRunAndMakeAscending(a, lo, hi, c);
        
        // ----------------------------------------------------------------------
        // If run is short, extend to min(minRun, nRemaining)  扩展
        if (runLen < minRun) {
            int force = nRemaining <= minRun ? nRemaining : minRun;
            binarySort(a, lo, lo + force, lo + runLen, c);
            runLen = force;
        }
        // ----------------------------------------------------------------------

        // Push run onto pending-run stack, and maybe merge
        ts.pushRun(lo, runLen);
        ts.mergeCollapse();

        // Advance to find next run
        lo += runLen;
        nRemaining -= runLen;
    } while (nRemaining != 0);

    // Merge all remaining runs to complete sort
    assert lo == hi;
    ts.mergeForceCollapse();
    assert ts.stackSize == 1;
}
```



#### 数组长度不大于 MIN_MERGE（32）时，使用 binarySort（二分查找插入排序）

```java
if (nRemaining < MIN_MERGE) {
    int initRunLen = countRunAndMakeAscending(a, lo, hi, c);
    binarySort(a, lo, hi, lo + initRunLen, c);
    return;
}
```

##### countRunAndMakeAscending：无序数组 -> 有序 + 无序

```java
// 返回有序数组长度（可能为1）
private static <T> int countRunAndMakeAscending(T[] a, int lo, int hi,
                                                Comparator<? super T> c) {
    assert lo < hi;
    int runHi = lo + 1;
    if (runHi == hi)	
        return 1;

    if (c.compare(a[runHi++], a[lo]) < 0) { // Descending
        while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) < 0)
            runHi++;
        reverseRange(a, lo, runHi);
    } else {                              // Ascending
        while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) >= 0)
            runHi++;
    }

    return runHi - lo;
}
```

##### binarySort（二分查找插入排序）

```java
private static <T> void binarySort(T[] a, int lo, int hi, int start,
                                   Comparator<? super T> c) {
    assert lo <= start && start <= hi;
    if (start == lo)
        start++;
    for ( ; start < hi; start++) {
        T pivot = a[start];
        int left = lo;
        int right = start;
        assert left <= right;
        while (left < right) {
            int mid = (left + right) >>> 1; // 无符号右移（ /= 2)
            if (c.compare(pivot, a[mid]) < 0)
                right = mid;
            else
                left = mid + 1;
        }
        assert left == right;
        int n = start - left;  // The number of elements to move
        switch (n) {
            case 2:  a[left + 2] = a[left + 1]; // 3(left, right), 5, 4
            case 1:  a[left + 1] = a[left];
                     break;
            default: System.arraycopy(a, left, a, left + 1, n);
        }
        a[left] = pivot;
    }
}
```

​	**与常规二分查找不同的是：判定条件含有等号；其次是 right = mid； 而非right = mid - 1；**





#### 数组长度大于MIN_MERGE(32) 时候

##### 相关工具函数：

```java
int minRun = minRunLength(nRemaining); // sort: nRemaining  = hi - lo;
```

###### minRunLength(nRemaining): 返回值为[16,32], 且 数组长度/min_run_len 略小于或等于 2 的幂次方

```java
private static int minRunLength(int n) {
    assert n >= 0;
    int r = 0;      // Becomes 1 if any 1 bits are shifted off
    while (n >= MIN_MERGE) {
        r |= (n & 1);
        n >>= 1;
    }
    return n + r;
}
```

###### pushRun()

```java
// 将规划好的 run 起始 index 置于 runBase[] 中；长度置于 runLen[] 中
private void pushRun(int runBase, int runLen) {
    this.runBase[stackSize] = runBase;
    this.runLen[stackSize] = runLen;
    stackSize++;
}
```
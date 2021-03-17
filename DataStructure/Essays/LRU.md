#### 链表实现：性能稍差

```java
/**
* 	插入元素，如果元素已经存在，则提至队尾；如果不存在则在队尾插入元素，如果超多指定 capacity，那么删除掉链表 头结点
*	访问元素的时候，如果存在，那么就提至链表尾部；
*	
*/
class LRUCache {
    private int CAP = 0;
    private int count = 0;
    private Pair head, tail;

    public LRUCache(int capacity) {
        this.CAP = capacity;
        head = new Pair(-1, -1);
        tail = head;
    }

    public int get(int key) {
        Pair start = head;
        while (start.next != null) {
            if (start.next.getKey() == key) {
                if (start.next.next != null) { // 提至队尾
                    Pair toTail = start.next;
                    start.next = toTail.next;
                    tail.next = toTail;
                    tail = toTail;
                    tail.next = null;
                }
                return tail.getValue();
            } else {
                start = start.next;
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            tail.setValue(value);
        } else {
            if (count >= CAP) {
                Pair del = head.next;
                head.next = del.next;
                del = null;
                count--;
                if (count == 0) {
                    tail = head;
                }
            }
            Pair newPair = new Pair(key, value);
            newPair.next = null;
            tail.next = newPair;
            tail = newPair;
            tail.next = null;
            count++;
        }
    }

    class Pair {
        private int key, value;
        private Pair next;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getKey() {
            return key;
        }
    }
}
```

缺点：

查找元素从队首开始遍历，而最新元素则是插入队尾，根据局部性原理，访问最近访问的元素概率上最大，因此获取元素的性能会大打折扣；

get() 时间复杂度： O(cap)

put() 由于要遍历一下是否存在，因此需要先get() 一下是否存在于李

解决：

1. 使用双向链表提高遍历性能；
2. 不使用链表；



#### 性能优化


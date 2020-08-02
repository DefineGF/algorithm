#include <iostream>
using namespace std;

/**
 * 逻辑：                             <-----
 *        (deQueue)front <-- |_|_|_|_|_|_|_|_|_|_| <-- rear(enQueue)
 * 存储：                 
 *        |front| -> |_|_|_|_|_|_|_|_|_|_|_| -> NULL
 *                                        ^
 *                                        |         
 *                                       rear
 **/
typedef int ElemType;

typedef struct Node{
    ElemType data;
    struct Node *next;     
}LinkNode;

typedef struct Queue{
    LinkNode *front, *rear;
}LinkQueue;

void initQueue(LinkQueue &Q) {
    Q.front = Q.rear = (LinkNode *)malloc(sizeof(LinkNode)); // 创建头结点
    Q.front -> next = NULL;
}

bool isEmpty(LinkQueue Q) {
    return Q.front == Q.rear; // 或者 Q.front -> next == NULL;
}

void enQueue(LinkQueue &Q, ElemType x) {
    LinkNode *s = (LinkNode *)malloc(sizeof(LinkNode));
    s -> data = x;
    s -> next = NULL;
    Q.rear -> next = s;
    Q.rear = s;
}

bool deQueue(LinkQueue &Q, ElemType &x) {
    if (isEmpty(Q)) return false;
    LinkNode *p = Q.front -> next;
    x = p -> data;
    Q.front -> next = p -> next;
    if (p == Q.rear) {
        Q.rear = Q.front;
    }
    free(p);
    return true;
}

bool frontElem(LinkQueue Q, ElemType &x) { // 获取队首元素
    if (isEmpty(Q))
        return false;
    x = (Q.front -> next) -> data;
    return true;
}

bool rearElem(LinkQueue Q, ElemType &x) { // 获取队尾元素
    if (isEmpty(Q))
        return false;
    x = Q.rear -> data;
    return true;
}

/**
 * 打印队列，并保持队列内容不变
 * 队列前面元素首先输出，然后再插入到队列
 **/
void logQueue(LinkQueue &Q) { 
    if (isEmpty(Q)) {
        cout << "error: queue is empty！" << endl;
        return;
    }
    cout << "output the queue: " << endl;
    enQueue(Q, -1); // 设置标志节点，避免无限出队入队
    int temp = 0;
    while (deQueue(Q, temp)) {
        if (temp == -1) {
            break;
        }
        cout <<  temp << "  ";
        enQueue(Q, temp);
    }
    cout << endl;

}
// 测试
int main() {
    LinkQueue Q;
    int data = -1;
    initQueue(Q);
    enQueue(Q, 1);
    enQueue(Q, 2);
    deQueue(Q, data);
    enQueue(Q, 3);
    logQueue(Q);
    frontElem(Q, data);
    cout << "queue front: " << data << endl;
    rearElem(Q, data);
    cout << "queue rear: " << data << endl;
    system("pause");
    return 0;
}

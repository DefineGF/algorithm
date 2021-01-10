#include <stdio.h>
#include <stdlib.h>

// 交换
void swap(int*a, int*b);
// 堆调整
void adjust(int* arr, int s, int endIndex);
// 建立大根堆
void buildMaxHeap(int* arr, int len);
// 堆排序
void heapSort(int* arr, int len);
// 输出数组
void logArr(int* arr, int len);

int main()
{
    int arr[] = {-1,5,4,3,2,1};
    heapSort(arr, 5);
    logArr(arr, 6);
    system("pause");
    return 0;
}

void swap (int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void adjust(int* arr, int s, int endIndex) {
    arr[0] = arr[s];
    for (int i = 2 * s; i <= endIndex; i *= 2) {
        if (i < endIndex && arr[i] < arr [i + 1]) {
            i++;
        }
        if (arr[0] >= arr[i]) {
            break;
        } else {
            arr[s] = arr[i];
            s = i;
        }
    }
    arr[s] = arr[0];
}

void buildMaxHeap(int* arr, int len) {
    for (int i = len / 2; i > 0; i--) {
        adjust(arr, i, len);
    }
}

void heapSort(int* arr, int len) {
    buildMaxHeap(arr, len);
    for (int i = len; i > 1; i--) {
        swap(arr + i, arr + 1);
        adjust(arr, 1, i - 1);
    }
}

void logArr(int *arr, int len) {
    for (int i = 0; i < len; i++) {
        printf("  %d",*(arr + i));
    }
    printf("\n");
}

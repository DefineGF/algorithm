#### 题目形式

- 计数（多少种方法)
- 求max / min
- 存在性



#### 解题步骤

- 确定状态：dp[i] 的含义
    - 最后一步
    - 子问题
- 转移方程
- 初始条件 和 边界条件

例题1：

三种硬币分别为2,5,7。且每种硬币足够多；

需要27元。使用最少的硬币组合刚好付清。



f(27) = min{f(27 -  2) , f(27 - 5), f(27 -7)} + 1;

```
int f(int x) {
    if (x == o) return 0;
    int res = MAX_VALUE;
    if (x >= 2) {
        res = Math.min(f(x - 2) + 1, res);
    }
    // 思考为什么不用 if-else if
    if (x >= 5) {
        res = Math.min(f(x - 5) + 1, res)
    }
    
    if (x >= 7) {
        res = Math.min(f(x - 7) + 1, res);
    }
}
```




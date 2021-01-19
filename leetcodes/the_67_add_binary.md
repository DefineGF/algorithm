输入两个二进制字符串（ **非空** 字符串且只包含数字 `1` 和 `0`），返回它们的和（用二进制表示）。

##### 思考一：将输入字符串转换为int，相加之后转换为二进制字符串；

```java
int aInt = Integer.parseInt(a, 2);
int bInt = Integer.parseInt(b, 2);
int sum = aInt + bInt;
return Integer.toBinaryString(sum);
```

​	**问题：字符串过长超出int范围**





##### 思考二：常规二进制加&进位，并在原字符串上进行操作；且设置进位标志为：boolean isCarry = false；

自后向前遍历，获取对应的字符, 记为c1、c2：

- c1 == '1' && c2 == '1'
    - isCarry == true：长字符串相应位置设置为 1;
    - isCarry == false：长字符串相应位置设置为0;

​	  并设置 isCarry = true;

- c1 == '0' && c2 == '0':

    - isCarry == true: xxx设置为1；

    并设置 isCarry = false;

- 其他：

    - isCarry == true：xxx设置为 0；

        并设置 isCarry = true；

    - isCarry == false：xxx设置为1；

        并设置 isCarry = false；

    

最后还需要根据 isCarry来判断是否需要在最终结果前面插入‘1’；

最终代码如下：

```java
public String addBinary1(String a, String b) {
    StringBuilder lStr;
    StringBuilder sStr;
    boolean isCarry = false;
    int i = a.length();
    int j = b.length();
    if (i > j) {
        lStr = new StringBuilder(a);
        sStr = new StringBuilder(b);
    } else {
        lStr = new StringBuilder(b);
        sStr = new StringBuilder(a);
        i = b.length();
        j = a.length();
    }

    for(i = i - 1, j = j - 1; i >= 0 && j >= 0; i--, j--) {
        if (lStr.charAt(i) == '1' && sStr.charAt(j) == '1') {
            if (isCarry) {
                lStr.replace(i, i + 1, "1");
            } else {
                lStr.replace(i, i + 1, "0");
            }
            isCarry = true;
        } else if (lStr.charAt(i) == '0' && sStr.charAt(j) == '0') {
            if (isCarry) {
                lStr.replace(i, i + 1, "1");
            }
            isCarry = false;
        } else { // 0 + 1 || 1 + 0
            if (isCarry) {
                lStr.replace(i, i + 1, "0");
                isCarry = true;
            } else {
                lStr.replace(i, i + 1, "1");
                isCarry = false;
            }
        }
    }

    if (isCarry) {
        while (i >= 0) {
            if (lStr.charAt(i) == '1') {
                lStr.replace(i, i + 1, "0");
            } else {
                lStr.replace(i, i + 1, "1");
                return lStr.toString();
            }
            i--;
        }
        if (i < 0) {
            lStr.insert(0, "1");
            return lStr.toString();
        }
    } else {
        return lStr.toString();
    }
    return lStr.toString();
}
```

**问题：代码超级比较繁琐**

##### 思考三：代码优化

相关手段：进位不再设置为 boolean格式，而是设置成 int 类型参与运算，这样无需层层判断，只需将运算结果填入即可：

```java
private String addBinary3(String a, String b) {
    StringBuilder lStr; // 最长字符串
    String sStr = b;

    int cBit = 0;
    int i = a.length();
    int j = b.length();

    if (i > j) {
        lStr = new StringBuilder(a);
    } else {
        lStr = new StringBuilder(b);
        sStr = a;
        i = b.length();
        j = a.length();
    }

    for(i = i - 1, j = j - 1; i >= 0; i--, j--) {
        int sum = cBit;
        sum += (lStr.charAt(i) - '0');
        if (j >= 0) {
            sum += (sStr.charAt(j) - '0');
        }
        lStr.replace(i, i + 1, Integer.toString(sum % 2));
        cBit = sum / 2;
    }

    if (cBit == 1) {
        lStr.insert(0, "1");
    }

    return lStr.toString();
}
```

##### 思考四：无需选择最长字符串，而是新建StringBuffer对象来保存结果（由于不再用StringBuffer 对输入字符串进行封装，因此最终申请内存不会变大）

```java
private String addBinary4(String a, String b) {
    StringBuilder ans = new StringBuilder();
    int ca = 0;
    for(int i = a.length() - 1, j = b.length() - 1;i >= 0 || j >= 0; i--, j--) {
        int sum = ca;
        sum += i >= 0 ? a.charAt(i) - '0' : 0;
        sum += j >= 0 ? b.charAt(j) - '0' : 0;
        ans.append(sum % 2);
        ca = sum / 2;
    }
    ans.append(ca == 1 ? ca : "");
    return ans.reverse().toString();
}
```



 


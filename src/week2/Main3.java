package week2;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;

/*
02-线性结构4 Pop Sequence

关于数据：
1.每轮用一个长度为N数组接收待校验的序列
2.为序列1，2，3，4.。。。N 定义两个变量itemStart、itemEnd分别指向需要被push进堆栈的数据首尾两端，
3.新建一个堆栈
关于算法：
遍历待校验的序列数组中的每一项arr[index1]
当堆栈为空时，另itemEnd = arr[index1];将itemStart、itemEnd之间的序列循环push入堆栈
当堆栈不为空时，比较数组当前项的值arr[index1]与栈顶数据的大小。
a.arr[index1]>栈顶数据,则另itemEnd = arr[index1];将itemStart、itemEnd之间的序列循环push入堆栈
   此处要做一个特殊处理，就是栈满的情况再push会报错，说明形成不了这样的序列，no，跳出循环
b.arr[index1]==栈顶数据，pop栈顶数据，index1++，继续比较下一项
c.arr[index1]<栈顶数据,说明形成不了这样的序列，no，跳出循环

Sample Input:
5 7 5
1 2 3 4 5 6 7
3 2 1 7 5 6 4
7 6 5 4 3 2 1
5 6 4 3 7 2 1
1 7 6 5 4 3 2

Sample Output:
YES
NO
NO
YES
NO

 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr1 = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = arr1[0];
        int N = arr1[1];
        int K = arr1[2];
        //nextInt()后的第一个nextLine()读取的是回车符，所以用nextInt()时后面一般加上nextLine()。之后再加nextLine()读取字符。
        Stack3<Integer> stack = new Stack3<>(M);
        for (int i = 0; i < K; i++) {
            //每轮用一个数组接收待校验的序列，数组长度N
            int[] arr = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int index1 = 0;
            int itemStart = 1;
            int itemEnd = 1;
            String result = "YES";
            lable1:
            while (index1 < N) {
                int topItemInStack = -1;
                if (!stack.isEmpty()) {
                    topItemInStack = stack.peek();
                }
                if (stack.isEmpty() || arr[index1] > topItemInStack) {
                    itemEnd = arr[index1];
                    for (int j = itemStart; j <= itemEnd; j++) {
                        try {
                            stack.push(j);
                        } catch (Exception e) {
                            result = "NO";
                            break lable1;
                        }
                    }
                    itemStart = itemEnd + 1;
                } else if (arr[index1] == topItemInStack) {
                    stack.pop();
                    index1++;
                } else {
                    result = "NO";
                    break;
                }

            }
            stack.clear();
            System.out.println(result);

        }

    }
}

class Stack3<E> {
    private Object[] stackArray;
    private int top;
    private int size;

    public Stack3(int capacity) {
        this.stackArray = new Object[capacity];
        this.top = -1;
        this.size = 0;
    }

    public void push(E e) {
        if (this.top == this.stackArray.length - 1) {
            throw new RuntimeException("Stack overflow");
        }
        top++;
        this.stackArray[this.top] = e;
        this.size++;
    }

    public E pop() {
        if (this.top == -1) {
            throw new EmptyStackException();
        }
        E data = (E) this.stackArray[this.top];
        this.top--;
        this.size--;
        return data;
    }

    // 获取栈顶元素
    public E peek() {
        if (this.top == -1) {
            throw new EmptyStackException();
        }
        return (E) stackArray[top];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return this.top == -1;
    }

    public void clear() {
        this.top = -1;
        this.size = 0;
    }
}

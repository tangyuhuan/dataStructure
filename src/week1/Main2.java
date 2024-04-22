package week1;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//注意：这个版本用 new BufferedReader(new InputStreamReader(System.in));代替new Scanner(System.in); 运行不超时
//作业2：Maximum Subsequence Sum
//https://pintia.cn/problem-sets/1738108464136978432/exam/problems/1738108464208281604?type=7&page=0
//最大子列和基础上改造，输出结果为sum,leftIndex,rightIndex
//思路：用list[0]初始化MaxSum,
// 如果当前子列和的值是负数的，不管后面加什么数，都只会让后面的数字越变越小，还不如从头加起，抛弃之。
// 新建三个变量int leftIndex=0,templeft=0, rightIndex=0;用于标记子列和的左右位置和左侧的临时位置


//nextInt()后的第一个nextLine()读取的是回车符，所以用nextInt()时后面一般加上nextLine()。之后再加nextLine()读取字符。
/*输入
10
-10 1 2 3 4 -5 -23 3 7 -21
输出
10 1 4*/
public class Main2 {
    //算法：在线处理,复杂度O(n)
    public static void main(String[] args) {
/*        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String[] s = in.nextLine().split(" ");
        int[] A = new int[N];
        */
        String[] s = new String[3];
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String n = null;
        try {
            n = in.readLine();
            s = in.readLine().split(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int N = Integer.parseInt(n);
        int A[] = new int[N];

        int MaxSum = -1;
        int leftIndex = 0, templeft = 0, rightIndex = N - 1;
        int intThisSum = 0; //ThisSum是从A[i]到A[j]的子列和
        for (int i = 0; i < N; i++) {
//            A[i] = 9;
            A[i] = Integer.parseInt(s[i]);
            intThisSum += A[i];/* 向右累加*/
            if (intThisSum > MaxSum) {/* 发现更大和则更新当前结果*/
                MaxSum = intThisSum;
                leftIndex = templeft;
                rightIndex = i;
            } else if (intThisSum < 0) {/* 如果当前子列和为负*/
                intThisSum = 0;/* 则不可能使后面的部分和增大，抛弃之*/
                templeft = i + 1;/* 更新左侧临时标记*/
            }
        }
        //If all the K numbers are negative, then its maximum sum is defined to be 0, and you are supposed to output the first and the last numbers of the whole sequence.
        if (MaxSum < 0) {
            MaxSum = 0;
        }
        System.out.print(MaxSum + " " + A[leftIndex] + " " + A[rightIndex]);
    }
}
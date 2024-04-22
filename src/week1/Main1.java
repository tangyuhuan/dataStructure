package week1;
import java.util.Scanner;
//作业1：最大子列和
//四种算法说明见MaxSubseqSum.java
//提交至https://pintia.cn/problem-sets/1738108464136978432/exam/problems/1738108464208281603?type=7&page=0
//输入
//6
//-2 11 -4 13 -5 -2
//输出
//20
public class Main1 {
    //算法：在线处理,复杂度O(n)
    public static int getMaxSubseqSum4(int[] A, int N) {
        int MaxSum = 0;
        int intThisSum = 0; //ThisSum是从A[i]到A[j]的子列和
        for (int i = 0; i < N; i++) {
            intThisSum += A[i];/* 向右累加*/
            if (intThisSum > MaxSum) {/* 发现更大和则更新当前结果*/
                MaxSum = intThisSum;
            } else if (intThisSum < 0) {/* 如果当前子列和为负*/
                intThisSum = 0;/* 则不可能使后面的部分和增大，抛弃之*/
            }
        }
        return MaxSum;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = in.nextInt();
        }
        System.out.println(getMaxSubseqSum4(A, A.length));
    }
}
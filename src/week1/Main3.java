package week1;
import java.util.Scanner;

//注意：这个版本运行超时了
//作业2：Maximum Subsequence Sum
//https://pintia.cn/problem-sets/1738108464136978432/exam/problems/1738108464208281604?type=7&page=0
//最大子列和基础上改造，输出结果为sum,leftIndex,rightIndex
//思路：用list[0]初始化MaxSum,
// 如果当前子列和的值是负数的，不管后面加什么数，都只会让后面的数字越变越小，还不如从头加起，抛弃之。
// 新建三个变量int leftIndex=0,templeft=0, rightIndex=0;用于标记子列和的左右位置和左侧的临时位置

/*输入
10
-10 1 2 3 4 -5 -23 3 7 -21
输出
10 1 4*/
public class Main3 {
    //算法：在线处理,复杂度O(n)
    public static void main(String[] args) {
        Boolean isAllNegative = false;
        int sumOfNegativeItem = 0;
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = in.nextInt();
            if(A[i] < 0) {
                sumOfNegativeItem++;
            }
        }
        if(sumOfNegativeItem==N){
            isAllNegative = true;
        }

        int MaxSum = A[0];
        int leftIndex=0,templeft=0, rightIndex=0;
        int intThisSum = 0; //ThisSum是从A[i]到A[j]的子列和
        for (int i = 0; i < N; i++) {
            intThisSum += A[i];/* 向右累加*/
            if (intThisSum > MaxSum) {/* 发现更大和则更新当前结果*/
                MaxSum = intThisSum;
                rightIndex = i;
                leftIndex = templeft;
            } else if (intThisSum < 0) {/* 如果当前子列和为负*/
                intThisSum = 0;/* 则不可能使后面的部分和增大，抛弃之*/
                templeft = i+1;/* 更新左侧临时标记*/
            }
        }
        //If all the K numbers are negative, then its maximum sum is defined to be 0, and you are supposed to output the first and the last numbers of the whole sequence.
        if(isAllNegative){
            System.out.print(0+" "+A[0]+" "+A[A.length-1]);
        }else{
            System.out.print(MaxSum+" "+A[leftIndex]+" "+A[rightIndex]);
        }
    }
}
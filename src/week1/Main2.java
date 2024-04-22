package week1;
import java.util.Scanner;

//作业2：Maximum Subsequence Sum
//最大子列和基础上改造，输出结果为sum,leftIndex,rightIndex
/*输入
10
-10 1 2 3 4 -5 -23 3 7 -21
输出
10 1 4*/
public class Main2 {
    //算法：在线处理,复杂度O(n)
    public static void main(String[] args) {
        Boolean isAllNegative = false;
        Boolean isNegativeAndZero = false;
        int sumOfNegativeItem = 0;
        int sumOfNegativeZeroItem = 0;
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = in.nextInt();
            if(A[i] < 0) {
                sumOfNegativeItem++;
                sumOfNegativeZeroItem++;
            }else if(A[i] == 0) {
                sumOfNegativeZeroItem++;
            }
        }
        if(sumOfNegativeItem==N){
            isAllNegative = true;
        }
        if(sumOfNegativeZeroItem==N){
            isNegativeAndZero = true;
        }
        int MaxSum = 0;
        int leftIndex=0,templeft=0, rightIndex=0;
        int intThisSum = 0; //ThisSum是从A[i]到A[j]的子列和
        for (int i = 0; i < N; i++) {
            intThisSum += A[i];/* 向右累加*/
            if (((intThisSum > MaxSum)&&(!isNegativeAndZero))||((intThisSum==0)&&isNegativeAndZero)) {/* 发现更大和则更新当前结果*/
                MaxSum = intThisSum;
                rightIndex = i;
                leftIndex = templeft;
            }
            if (intThisSum < 0) {/* 如果当前子列和为负*/
                intThisSum = 0;/* 则不可能使后面的部分和增大，抛弃之*/
                templeft = i+1;
            }
        }
        //In case that the maximum subsequence is not unique, output the one with the smallest indices i and j (as shown by the sample case)
        //If all the K numbers are negative, then its maximum sum is defined to be 0, and you are supposed to output the first and the last numbers of the whole sequence.
        if(isAllNegative){
            System.out.print(MaxSum+" "+A[0]+" "+A[A.length-1]);
        }else{
            System.out.print(MaxSum+" "+A[leftIndex]+" "+A[rightIndex]);
        }
    }
}
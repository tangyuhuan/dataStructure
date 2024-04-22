package week1;
/*给定N个整数的序列 {A1,A2,,,,An}
求子列和的最大值*/

import java.util.Scanner;

public class MaxSubseqSum {
    //方法一,复杂度O(n^3)
    public static int getMaxSubseqSum1(int[] A, int N) {
        int MaxSum = 0;
        for (int i = 0; i < N; i++) {/* i是子列左端位置*/
            for (int j = i; j < N; j++) {/* j是子列右端位置*/
                int intThisSum = 0; //ThisSum是从A[i]到A[j]的子列和
                for (int k = i; k <= j; k++) {
                    intThisSum += A[k];
                    if (intThisSum > MaxSum) {
                        MaxSum = intThisSum;
                    }
                }
            }
        }
        return MaxSum;
    }

    //方法二,复杂度O(n^2)
    public static int getMaxSubseqSum2(int[] A, int N) {
        int MaxSum = 0;
        for (int i = 0; i < N; i++) {
            int intThisSum = 0; //ThisSum是从A[i]到A[j]的子列和
            for (int j = i; j < N; j++) {
                intThisSum += A[j];
                /*对于相同的i，不同的j，只要在j-1次循环的基础上累加1项即可*/
                if (intThisSum > MaxSum) {/* 如果刚得到的这个子列和更大*/
                    MaxSum = intThisSum;
                }
            }
        }
        return MaxSum;
    }

    //方法三：分而治之,时间复杂度O(nlogn)
    //递归的解决左边的问题，得到右边的最大子列和
    //递归的解决右边的问题，得到左边的最大子列和
    //跨越边界的最大子列和
    public static int getMax(int a, int b, int c) {
        /*返回三个整数中的最大值*/
        return a > b ? a > c ? a : c : b > c ? b : c;
    }

    public static int getMaxSubseqSum3(int[] A, int leftIndex, int rightIndex) {
        int rightMaxSubseqSum = 0, leftMaxSubseqSum = 0;//存放左右子问题的解
        int MaxLeftBorderSum = 0, MaxRightBorderSum = 0;//存放跨越分界线的结果
        int intThisRightSum = 0, intThisLeftSum = 0;

        if (leftIndex == rightIndex) {//递归的结束条件,子列只有一个数字
            if (A[leftIndex] > 0) {
                return A[leftIndex];
            } else {
                return 0;
            }
        }
        /*分的过程*/
        int middleIndex = (leftIndex + rightIndex) / 2;
        /*递归求得两边子列和*/
        rightMaxSubseqSum = getMaxSubseqSum3(A, leftIndex, middleIndex);
        leftMaxSubseqSum = getMaxSubseqSum3(A, middleIndex + 1, rightIndex);
        /*下面求跨分界线的最大子列和*/
        /*左边扫描结束*/
        for (int i = middleIndex; i >= leftIndex; i--) {
            intThisLeftSum += A[i];
            if (intThisLeftSum > MaxLeftBorderSum) {
                MaxLeftBorderSum = intThisLeftSum;
            }
        }
        /*右边扫描结束*/
        for (int i = middleIndex + 1; i <= rightIndex; i++) {
            intThisRightSum += A[i];
            if (intThisRightSum > MaxRightBorderSum) {
                MaxRightBorderSum = intThisRightSum;
            }
        }
        /*返回治的结果*/
        return getMax(rightMaxSubseqSum, leftMaxSubseqSum, MaxLeftBorderSum + MaxRightBorderSum);
    }

    //方法四：在线处理,复杂度O(n)
    //如果当前子列和的值是负数的，不管后面加什么数，都只会让后面的数字越变越小，还不如从头加起，抛弃之。
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
//        int[] A = {4, -3, 5, -2, -1, 2, 6, -2};
        int[] A = {4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7, 4, -3, 5, -2, -1, 2, 6, -2, 6, 7};
        long time1 = System.nanoTime();

        System.out.println(getMaxSubseqSum1(A, A.length));//（统计单位：纳秒）1457333
        System.out.println(getMaxSubseqSum2(A, A.length));//（统计单位：纳秒）347666
        System.out.println(getMaxSubseqSum3(A, 0, A.length - 1));//（统计单位：纳秒）120750
        System.out.println(getMaxSubseqSum4(A, A.length));//（统计单位：纳秒）91708

        long time2 = System.nanoTime();
        System.out.println(time2 - time1);

//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//        int[] A = new int[N];
//        for (int i = 0; i < N; i++) {
//            A[i] = in.nextInt();
//        }
//        System.out.println(getMaxSubseqSum4(A, A.length));
    }
}

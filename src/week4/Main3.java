package week4;

import java.util.Arrays;
import java.util.Scanner;

/*
04-树6 Complete Binary Search Tree 完全二叉搜索树
题目：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281613&page=0
题意理解：给定一串整数，填充到完全二叉树的结构里去，同时这棵树还满足二叉搜索树的性质；输出层序遍历的结果。
树的表示：数组（完全二叉树用数组存储时，就是按照层序遍历的顺序去存储）

题解：4.4习题选讲-Complete Binary Search Tree
陈越题解视频：https://www.icourse163.org/learn/ZJU-93001?tid=1471851460#/learn/content?type=detail&id=1257031154&sm=1
此题有难度～～
Sample Input:
10
1 2 3 4 5 6 7 8 9 0
Sample Output:
6 3 8 1 5 7 9 0 2 4
 */
public class Main3 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrlength = scanner.nextInt();
        scanner.nextLine();
        String[] line = scanner.nextLine().split(" ");
        int[] A = new int[arrlength];//排序后的输入序列
        int[] T = new int[arrlength];//结果树T
        for (int i = 0; i < arrlength; i++) {
            A[i] = Integer.parseInt(line[i]);
        }
        Arrays.sort(A);
        solve(0,arrlength-1,0,A,T);
        int i = 0;
        while (i < arrlength) {
            System.out.print(T[i]);
            i++;
            if(i<arrlength){
                System.out.print(" ");
            }

        }
    }
    static void solve(int ALeft, int ARight, int TRoot, int[] A, int[] T){
        int n = ARight-ALeft+1;//n是当前这棵完全二叉树总节点的个数
        if(n==0)return;
        /*算出左子树应该包含多少个元素，左子树的下一个元素就是放在根节点位置上的元素*/
        int L =  getLeftLength(n);//计算出n个节点的树，其左子树有多少个节点
        T[TRoot]=A[ALeft+L];//根左右的先序遍历
        int LeftRoot = TRoot*2+1;
        int RightRoot = LeftRoot+1;
        solve(ALeft,ALeft+L-1,LeftRoot,A,T);
        solve(ALeft+L+1,ARight,RightRoot,A,T);

    }
    //计算出n个节点的树，其左子树有多少个节点
    static int getLeftLength(int n){
        double H = Math.floor(Math.log(n)/Math.log(2));
        double X = n-Math.pow(2,H)+1;
        double a = Math.pow(2,H-1);
        X= Math.min(X,a);
        return (int) (a-1+X);
    }

}
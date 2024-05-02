package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
03-树2 List Leaves
Given a tree, you are supposed to list all the leaves in the order of top down, and left to right.

作业地址：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281609&page=0
求解思路
1.二叉树表示：用结构数组表示二叉树（物理存储是数组，思想是链表思想）left、right是指向左右儿子位置的下标，是个整数，从0开始
2.建二叉树：遍历数据放入树中，获取根节点：没有left/right指向的位置，就是根节点的位置
3.叶子节点：没有left、right儿子的节点就是叶子节点
4.从上到下、从左到右输出所有叶子节点：从上到下、从左到右其实就是层序遍历，本题等价于层序遍历输出所有叶子节点
5.层序遍历：通过队列实现，首先将根节点入队，然后开始执行循环：节点出队，访问该节点，其左右儿子入队。循环条件：队列不为空
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr1length = Integer.parseInt(in.nextLine());
        /*建立树1*/
        Tree2 tree = new Tree2(arr1length);
        for (int i = 0; i < arr1length; i++) {
            tree.addNode(i, in.nextLine());
        }
        int R = tree.Root();

        //arr存储层序遍历结果
        ArrayList arr = new ArrayList();
        Queue<Integer> q = new LinkedList<>();
        q.add(R);
        //层序遍历：通过队列实现，首先将根节点入队，然后开始执行循环：节点出队，访问该节点，其左右儿子入队。循环条件：队列不为空
        while (!q.isEmpty()) {
            int popNode = q.remove();
            arr.add(popNode);
//            System.out.print(popNode+" ");
            int leftNode = tree.getTreeNodeLeft(popNode);
            if (leftNode != -1) {
                q.add(leftNode);
            }
            int rightNode = tree.getTreeNodeRight(popNode);
            if (rightNode != -1) {
                q.add(rightNode);
            }
        }
//        System.out.println(arr);
        ArrayList<Integer> leaveList = tree.Leaves();
        ArrayList<Integer> output = new ArrayList<>();
//        System.out.println(leaveList);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < leaveList.size(); j++) {
                if(arr.get(i)==leaveList.get(j)){
                    output.add(Integer.parseInt(arr.get(i)+""));
//                    System.out.print(arr.get(i) +" ");
                }
            }
        }
        System.out.print(output.get(0));
        for (int i = 1; i < output.size(); i++) {
            System.out.print(" "+output.get(i));
        }
    }
}

class Tree2 {
    TreeNode5[] tree;//用结构数组表示二叉树
    int[] check;//判别根节点的数组：没有left/right指向的位置，就是根节点的位置
    ArrayList<Integer> leaves;

    public Tree2(int length) {
        this.tree = new TreeNode5[length];
        this.check = new int[length];
        for (int i = 0; i < length; i++) {
            check[i] = 0;
        }
        leaves = new ArrayList<>();
    }

    public void addNode(int i, String line) {
        String[] lineArr = line.split(" ");
        tree[i] = new TreeNode5(i, Integer.parseInt(lineArr[0].equals("-") ? "-1" : lineArr[0]), Integer.parseInt(lineArr[1].equals("-") ? "-1" : lineArr[1]));
        if (lineArr[0].equals("-") && lineArr[1].equals("-")) {
            leaves.add(i);
        }
        //如果某个节点有left指向了某个位置，那就把这个位置的check设置为1
        if (tree[i].left != -1) {
            check[tree[i].left] = 1;
        }
        //如果某个节点有right指向了某个位置，那就把这个位置的check设置为1
        if (tree[i].right != -1) {
            check[tree[i].right] = 1;
        }

    }

    //得到根节点：遍历left和right，如果
    public int Root() {
        int j = -1;
        for (int i = 0; i < check.length; i++) {
            if (check[i] == 0) {
                j = i;
                break;//无人指向的就是根节点了
            }
        }
        return j;
    }

    public int getTreeNodeElement(int i) {
        return this.tree[i].element;
    }

    public int getTreeNodeLeft(int i) {
        return this.tree[i].left;
    }

    public int getTreeNodeRight(int i) {
        return this.tree[i].right;
    }

    public ArrayList<Integer> Leaves() {
        return this.leaves;
    }

    static class TreeNode5 {
        int element;
        int left;
        int right;

        public TreeNode5(int element, int left, int right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

    }
}

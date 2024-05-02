package week3;

import java.util.Scanner;

/*
03-树1 树的同构
现给定两棵树，请你判断它们是否是同构的。
如果T1可以通过若干次左右孩子互换就变成 T2，则我们称两棵树是“同构”的

说明：
关于输入的数据，不是根节点就放在第一位数据的，关于根节点需要自行判断
根节点判断：遍历数组中的left和right，未使用的index（无人指向），就是根节点的位置

求解思路
1.二叉树表示：用结构数组表示二叉树（物理存储是数组，思想是链表思想）left、right是指向左右儿子位置的下标，是个整数，从0开始
2.建二叉树：遍历数据放入树中，获取根节点：没有left/right指向的位置，就是根节点的位置
3.同构判别：需要考虑多种情况，详见下方

作业地址：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281608&page=0
题解：3.4小白专场：树的同构.pdf
题解视频：https://www.icourse163.org/learn/ZJU-93001?tid=1471851460#/learn/content?type=detail&id=1257031138&cid=1290304371&contentid=1926008

输入样例1（对应图1）：
8
A 1 2
B 3 4
C 5 -
D - -
E 6 -
G 7 -
F - -
H - -
8
G - 4
B 7 6
F - -
A 5 1
H - -
C 0 -
D - -
E 2 -
输出样例1:
Yes
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr1length = Integer.parseInt(in.nextLine());
        /*建立树1*/
        Tree tree1 = new Tree(arr1length);
        for (int i = 0; i < arr1length; i++) {
            tree1.addNode(i,in.nextLine());
        }
        int R1 = tree1.Root();

        int arr2length = Integer.parseInt(in.nextLine());
        /*建立树2*/
        Tree tree2 = new Tree(arr2length);
        for (int i = 0; i < arr2length; i++) {
            tree2.addNode(i,in.nextLine());
        }
        int R2 = tree2.Root();

        if(isomorphic(tree1,tree2,R1,R2)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }
    //同构判别
    public static boolean isomorphic(Tree tree1,Tree tree2,int R1,int R2){
        if((R1==-1)&&(R2==-1)){ //两棵树都是空的
            return true;
        }
        if(((R1==-1)&&(R2!=-1))||((R1!=-1)&&(R2==-1))){//其中一颗树是空的
            return false;
        }
        if(!tree1.getTreeNodeElement(R1).equals(tree2.getTreeNodeElement(R2))){//根不一样
            return false;
        }
        if((tree1.getTreeNodeLeft(R1)==-1)&&(tree2.getTreeNodeLeft(R2)==-1)){//都没有左子树，就比较右子树是否同构
            return isomorphic(tree1,tree2,tree1.getTreeNodeRight(R1),tree2.getTreeNodeRight(R2));
        }
        //如果都有左子树且Element一样，那就分别比较左边和左边、右边和右边
        if(((tree1.getTreeNodeLeft(R1)!=-1)&&(tree2.getTreeNodeLeft(R2)!=-1))&&(tree1.getTreeNodeElement(tree1.getTreeNodeLeft(R1)).equals(tree2.getTreeNodeElement(tree2.getTreeNodeLeft(R2))))){
            return(isomorphic(tree1,tree2,tree1.getTreeNodeLeft(R1),tree2.getTreeNodeLeft(R2))&&isomorphic(tree1,tree2,tree1.getTreeNodeRight(R1),tree2.getTreeNodeRight(R2)));
        }else{
            //否则就分别比较左边和右边、右边和左边
            //如果一个左边是空树，一个左边非空，也转换成左边和右边的判别
            return(isomorphic(tree1,tree2,tree1.getTreeNodeLeft(R1),tree2.getTreeNodeRight(R2))&&isomorphic(tree1,tree2,tree1.getTreeNodeRight(R1),tree2.getTreeNodeLeft(R2)));
        }
        
    }
}

class Tree{
    TreeNode5[] tree;//用结构数组表示二叉树
    int[] check;//判别根节点的数组：没有left/right指向的位置，就是根节点的位置

    public Tree(int length) {
        this.tree = new TreeNode5[length];
        this.check = new int[length];
        for (int i = 0; i < length; i++) {
            check[i] = 0;
        }
    }
    public void addNode(int i, String line){
        String[] lineArr = line.split(" ");
        tree[i] = new TreeNode5(lineArr[0],Integer.parseInt(lineArr[1].equals("-")? "-1" :lineArr[1]),Integer.parseInt(lineArr[2].equals("-")? "-1" :lineArr[2]));
        //如果某个节点有left指向了某个位置，那就把这个位置的check设置为1
        if(tree[i].left!=-1){
            check[tree[i].left] = 1;
        }
        //如果某个节点有right指向了某个位置，那就把这个位置的check设置为1
        if(tree[i].right!=-1){
            check[tree[i].right] = 1;
        }
    }
    //得到根节点：遍历left和right，如果
    public int Root(){
        int j = -1;
        for (int i = 0; i < check.length; i++) {
            if(check[i]==0){
                j = i;
                break;//无人指向的就是根节点了
            }
        }
        return j;
    }
    public String getTreeNodeElement(int i){
        return this.tree[i].element;
    }
    public int getTreeNodeLeft(int i){
        return this.tree[i].left;
    }
    public int getTreeNodeRight(int i){
        return this.tree[i].right;
    }
    class TreeNode5{
        String element;
        int left;
        int right;

        public TreeNode5(String element, int left, int right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

    }
}

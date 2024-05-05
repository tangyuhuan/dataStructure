package week4;

import java.util.*;

/*
04-树4 是否同一棵二叉搜索树
题目：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281611&page=0

解题思路：
1.中序遍历+前序/后序，即可确定一棵二叉树
故创建两棵二叉搜索树，比较其中序遍历+前序的结果
2.本题主要考察二叉搜索树的插入

输入样例:
4 2
3 1 4 2
3 4 1 2
3 2 4 1
2 1
2 1
1 2
0
输出样例:
Yes
No
No

 */
public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String line = in.nextLine();
            if(line.charAt(0)!='0'){
                String[] words = line.split(" ");
                int N = Integer.parseInt(words[0]);
                int K = Integer.parseInt(words[1]);
                String[] nodes1 = in.nextLine().split(" ");
                TreeNode2 tree1Root = new TreeNode2(Integer.parseInt(nodes1[0]));
                for (int i = 1; i < N; i++) {
                    insert(tree1Root,Integer.parseInt(nodes1[i]));
                }
                ArrayList<Integer> list1 = new ArrayList<>();
                inOrder(tree1Root,list1);
                ArrayList<Integer> list2 = new ArrayList<>();
                preOrder(tree1Root,list2);
                for (int j = 0; j < K; j++) {
                    String[] nodes2 = in.nextLine().split(" ");
                    TreeNode2 tree2Root = new TreeNode2(Integer.parseInt(nodes2[0]));
                    for (int i = 1; i < N; i++) {
                        insert(tree2Root,Integer.parseInt(nodes2[i]));
                    }
                    ArrayList<Integer> list3 = new ArrayList<>();
                    inOrder(tree2Root,list3);
                    ArrayList<Integer> list4 = new ArrayList<>();
                    preOrder(tree2Root,list4);
//                    System.out.println((list1.equals(list3))&&(list2.equals(list4)));
                    if((list1.equals(list3))&&(list2.equals(list4))){
                        System.out.println("Yes");
                    }else{
                        System.out.println("No");
                    }
                }
            }else{
                break;
            }

        }
    }

    /*二叉搜索树的插入：关键是找到应该插入的位置*/
    public static TreeNode2 insert(TreeNode2 root, int data) {
        if (root == null) {/* 若原树为空，生成并返回一个节点的二叉树*/
            root = new TreeNode2(data);
        } else {//开始找要插入的元素位置
            if (data < root.val) {
                root.left = insert(root.left, data);//递归插入左子树
            } else if (data > root.val) {
                root.right = insert(root.right, data);//递归插入右子树
            }/*else X已经存在 什么都不做*/
        }
        return root;
    }
    // 中序遍历
    public static void inOrder(TreeNode2 root,ArrayList<Integer> list){
        if(root != null){
            inOrder(root.left,list);
            list.add(root.val);
//            System.out.print(root.val+" ");
            inOrder(root.right,list);
        }
    }
    //前序遍历
    public static void preOrder(TreeNode2 root,ArrayList<Integer> list){
        if(root != null){
            list.add(root.val);
//            System.out.print(root.val+" ");
            preOrder(root.left,list);
            preOrder(root.right,list);
        }
    }

}

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;

    public TreeNode2(int x) {
        val = x;
    }
}
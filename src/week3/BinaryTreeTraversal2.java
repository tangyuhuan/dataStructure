package week3;
import java.util.Stack;
/*
前序遍历（根左右）的非递归算法，实现一

先中后遍历的路线是一样的，第一次碰到节点就print的是先序
第二次碰到节点print的是中序
第三次碰到节点print的是后序

实现思路
在中序遍历的基础上调整下print的位置即可

在中序遍历中，我们首先访问最左边的节点，然后访问该节点的父节点，最后访问右子树。堆栈在这个过程中用于回溯到父节点并保持遍历的状态。
*/
class TreeNode2 {
   int val;
   TreeNode2 left;
   TreeNode2 right;

    public TreeNode2(int x) {
        val = x;
    }
}

public class BinaryTreeTraversal2 {
    public static void inOrderTraversal(TreeNode2 root) {
        if(root == null){
            return;
        }
        Stack<TreeNode2> stack = new Stack<>();
        TreeNode2 current = root;
        while(current!=null||!stack.isEmpty()){
            while(current!=null){
                System.out.print(current.val + " ");
                stack.push(current);
                current = current.left;
            }
            if(!stack.isEmpty()){
                current = stack.pop();//pop就说明开始要转而遍历右子树了
//                System.out.print(current.val + " ");
                current = current.right;//转向右子树
            }
        }

    }
    public static void main(String[] args) {
        TreeNode2 left = new TreeNode2(1);
        TreeNode2 right = new TreeNode2(2);
        TreeNode2 root = new TreeNode2(3);
        root.left = left;
        root.right = right;
        inOrderTraversal(root);
    }
}
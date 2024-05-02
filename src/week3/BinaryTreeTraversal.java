package week3;
import java.util.Stack;
/*
中序遍历（左根右）的非递归算法

实现思路
创建一个堆栈：用于存储还未访问的树节点。
初始化：从根节点开始，尝试访问节点的左子树。
遍历：执行以下步骤直到当前节点为空或堆栈为空：
1.遇到一个节点，就把它压入栈，并去遍历他的左子树（沿着左子树一直走到底，过程中将沿途的所有节点推入堆栈）。
2.当左子树遍历结束时，从堆栈中弹出一个节点并访问它。
3.然后按其右指针再去中序遍历该节点的右子树。

在中序遍历中，我们首先访问最左边的节点，然后访问该节点的父节点，最后访问右子树。堆栈在这个过程中用于回溯到父节点并保持遍历的状态。
*/
class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;

    public TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreeTraversal {
    public static void inOrderTraversal(TreeNode root) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while(current!=null||!stack.isEmpty()){
            while(current!=null){
                stack.push(current);
                current = current.left;
            }
            if(!stack.isEmpty()){
                current = stack.pop();
                System.out.print(current.val + " ");
                current = current.right;
            }
        }

    }
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode root = new TreeNode(3);
        root.left = left;
        root.right = right;
        inOrderTraversal(root);
    }
}
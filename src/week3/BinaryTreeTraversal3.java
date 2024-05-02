package week3;
import java.util.Stack;
/*

前序遍历（根左右）的非递归算法，实现二

实现思路
创建一个堆栈：用于存储将要访问的树节点。

初始化：将根节点推入堆栈。

遍历：只要堆栈不为空，执行以下操作：

弹出堆栈的顶部元素，并访问该节点。
如果弹出的节点有右子节点，将其推入堆栈。
如果弹出的节点有左子节点，将其推入堆栈。
注意，先推右子节点是为了保证左子节点先被访问，这是因为栈是后进先出的数据结构。
*/
class TreeNode3 {
   int val;
   TreeNode3 left;
   TreeNode3 right;

    public TreeNode3(int x) {
        val = x;
    }
}

public class BinaryTreeTraversal3 {
    public static void inOrderTraversal(TreeNode3 root) {
        if(root == null){
            return;
        }
        Stack<TreeNode3> stack = new Stack<>();
        TreeNode3 current = root;
        stack.push(current);
        while(!stack.isEmpty()){
            current = stack.pop();
            System.out.print(current.val+" ");
            //注意，先推右子节点是为了保证左子节点先被访问，这是因为栈是后进先出的数据结构。
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }


    }
    public static void main(String[] args) {
        TreeNode3 left = new TreeNode3(1);
        TreeNode3 right = new TreeNode3(2);
        TreeNode3 root = new TreeNode3(3);
        root.left = left;
        root.right = right;
        inOrderTraversal(root);
    }
}
package week3;
import java.util.Stack;
/*
后序遍历（左右根）的非递归算法
后序遍历必须在左右子树均输出的情况下才能输出该节点
但根据树的结构，必须通过父节点才能访问左、右
为了输出“左右根”的结构,需要增加一个辅助堆栈
｜      |
｜  右  |
｜__左__|

｜      |
｜      |
｜__根__|
实现思路
1.创建两个堆栈：stack1 用于辅助遍历树，stack2 用于存储后序遍历的结果。
2.初始化：将根节点推入 stack1。
3.遍历stack1：在 stack1 不为空的情况下，执行以下操作：
    弹出 stack1 的顶部元素。
    将弹出的元素推入 stack2。
    如果弹出的元素有左子节点，将左子节点推入 stack1。
    如果弹出的元素有右子节点，将右子节点推入 stack1。
4.处理stack2：在 stack1 完全被处理后，stack2 中将包含按后序遍历顺序的节点。遍历 stack2 并输出或处理节点。

这种方法之所以有效，是因为 stack1 用于以相反的顺序存储访问节点（根-右-左），这与后序遍历的顺序（左-右-根）相反。然后，当这些节点被推入 stack2 时，顺序就被反转了，从而实现了后序遍历的顺序。
*/
class TreeNode4 {
   int val;
   TreeNode4 left;
   TreeNode4 right;

    public TreeNode4(int x) {
        val = x;
    }
}

public class BinaryTreeTraversal4 {
    public static void inOrderTraversal(TreeNode4 root) {
        if(root == null){
            return;
        }
        Stack<TreeNode4> stack1 = new Stack<>();
        Stack<TreeNode4> stack2 = new Stack<>();
        TreeNode4 current = root;
        stack1.push(current);
        while(!stack1.isEmpty()){
            current = stack1.pop();
            stack2.push(current);
            if(current.left!=null){
                stack1.push(current.left);
            }
            if(current.right!=null){
                stack1.push(current.right);
            }
        }
        while(!stack2.isEmpty()){
            current = stack2.pop();
            System.out.print(current.val+" ");
        }

    }
    public static void main(String[] args) {
/*        TreeNode4 left = new TreeNode4(1);
        TreeNode4 right = new TreeNode4(2);
        TreeNode4 root = new TreeNode4(3);
        root.left = left;
        root.right = right;
        inOrderTraversal(root);*/
        TreeNode4 root = new TreeNode4(1);
        root.left = new TreeNode4(2);
        root.right = new TreeNode4(3);
        root.left.left = new TreeNode4(4);
        root.left.right = new TreeNode4(5);
        root.right.left = new TreeNode4(6);
        root.right.right = new TreeNode4(7);
        inOrderTraversal(root);
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1==s2);
    }
}
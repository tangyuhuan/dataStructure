package week3;
import java.util.*;

/*
03-树3 Tree Traversals Again

解析：先建树，再后序遍历
push的顺序就是先序遍历的顺序
pop的顺序就是中序的顺序

先序和中序遍历确定一颗二叉树：
1.根据先序遍历第一个节点确定根节点
2.根据根节点在中序遍历中分割出左右两个子列
3.对左子树和右子树分别递归使用相同的方法求解

题目：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281610&page=0
题解：https://www.cnblogs.com/qinmin/p/12868261.html
陈越题解：3.5习题选讲-Tree Traversals Again
陈越题解视频：https://www.icourse163.org/learn/ZJU-93001?tid=1471851460#/learn/content?type=detail&id=1257031153&cid=1290304457&contentid=1841013
代码执行可视化网站：https://pythontutor.com/render.html#mode=display

Sample Input:
6
Push 1
Push 2
Push 3
Pop
Pop
Push 4
Pop
Pop
Push 5
Push 6
Pop
Pop
Sample Output:
3 4 2 6 5 1
 */
public class Main3 {
    public static void main(String[] args) {
        //此为先序，中序，后序的输入序列
        int[] pre = new int[30];
        int[] in = new int[30];
        int[] post = new int[30];
        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        int nodesNumber = scanner.nextInt();
        scanner.nextLine();
        int preindex=0, inindex=0;
        for (int i = 0; i < nodesNumber*2; i++) {
            String line = scanner.nextLine();
            if(line.substring(0,3).equals("Pus")){
                int tempElement = Integer.parseInt(line.substring(5));
                pre[preindex++] = tempElement;
                stack.push(tempElement);
            }else{
                int tempElement = stack.pop();
                in[inindex++] = tempElement;
            }
        }
        Tree7 tree = new Tree7(pre,in,nodesNumber);
        TreeNode7 root = tree.create(0,nodesNumber-1,0,nodesNumber-1);
        tree.postorder(root);
    }


}
class Tree7{
    int[] pre = new int[30];
    int[] in = new int[30];
    int[] post = new int[30];
    int nodesNumber;
    int num = 0;
    public Tree7(int[] pre, int[] in, int nodesNumber) {
        this.pre = pre;
        this.in = in;
        this.nodesNumber = nodesNumber;
    }
    /*先序和中序遍历确定一颗二叉树
    1.根据先序遍历第一个节点确定根节点
    2.根据根节点在中序遍历中分割出左右两个子列
    3.对左子树和右子树分别递归使用相同的方法求解*/
    public TreeNode7 create(int prel, int prer, int inl, int inr){
        //递归的边界条件
        if(prel>prer){
            return null;
        }
        //非边界条件的递归逻辑
        TreeNode7 root = new TreeNode7(pre[prel]);
        int k;
        for(k = inl; k <= inr; k++){
            if(pre[prel] == in[k]){
                break;
            }
        }
        //k指示了中序遍历的根节点的位置
        int numleft = k-inl;
        root.left = this.create(prel+1,prel+numleft,inl,k-1);
        root.right = this.create(prel+numleft+1,prer,k+1,inr);

        return root;//要知道「返回给谁」用的栈结构存储，最先放进去的节点最后出来
    }
    /*    后序遍历*/
    public void postorder( TreeNode7 root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val);
        this.num++;
        if(num<nodesNumber){
            System.out.print(" ");
        }
    }
}
class TreeNode7 {
    int val;
    TreeNode7 left;
    TreeNode7 right;
    public TreeNode7(int x) {
        val = x;
    }
}


/*
以下可放入代码执行可视化网站，查看递归的执行过程：https://pythontutor.com/render.html#mode=display

public class Main3 {
    public static void main(String[] args) {
        //此为先序，中序的输入序列
        int[] pre = {1,2,3,4,5};
        int[] in = {3,2,4,1,5};
        int nodesNumber = 5;
        Tree7 tree = new Tree7(pre,in,nodesNumber);
        TreeNode7 root = tree.create(0,nodesNumber-1,0,nodesNumber-1);
        tree.postorder(root);
    }


}
class Tree7{
    int[] pre = new int[30];
    int[] in = new int[30];
    int[] post = new int[30];
    TreeNode7 root;
    int nodesNumber;
    int num = 0;
    public Tree7(int[] pre, int[] in, int nodesNumber) {
        this.pre = pre;
        this.in = in;
        this.nodesNumber = nodesNumber;
    }
    public TreeNode7 create(int prel, int prer, int inl, int inr){
        //递归的边界条件
        if(prel>prer){
            return null;
        }
        //非边界条件的递归逻辑
        this.root = new TreeNode7(pre[prel]);
        int k;
        for(k = inl; k <= inr; k++){
            if(pre[prel] == in[k]){
                break;
            }
        }
        //k指示了中序遍历的根节点的位置
        int numleft = k-inl;
        this.root.left = this.create(prel+1,prel+numleft,inl,k-1);
        this.root.right = this.create(prel+numleft+1,prer,k+1,inr);

        return this.root;//要知道「返回给谁」用的栈结构存储，最先放进去的节点最后出来
    }

    public void postorder( TreeNode7 root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val);
        this.num++;
        if(num<nodesNumber){
            System.out.print(" ");
        }
    }
}
class TreeNode7 {
    int val;
    TreeNode7 left;
    TreeNode7 right;
    public TreeNode7(int x) {
        val = x;
    }
}

 */
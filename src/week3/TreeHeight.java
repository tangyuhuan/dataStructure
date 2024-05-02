package week3;

/*
二叉树的高度
递归遍历左子树深度、右子树深度
时间复杂度O(n)：遍历n个节点
空间复杂度O(n)：二叉树采用栈保存每次递归前的根节点，最坏情况下二叉树只有左儿子，就变成了链状结构，空间复杂度就是n

理解递归的本质：https://www.bilibili.com/video/BV1UD4y1Y769/?spm_id_from=333.788.recommend_more_video.0&vd_source=9096009d98e9575f0e391c12407212da
处理递归：只要代码的边界条件和非边界条件的逻辑写对了，其他的事情交给数学归纳法就好了。
1. 如何思考二叉树相关问题？
    - 不要一开始就陷入细节，而是思考整棵树与其左右子树的关系。
2. 为什么需要使用递归？
    - 子问题和原问题是相似的，他们执行的代码也是相同的（类比循环），但是子问题需要把计算结果返回给上一级，这更适合用递归实现。
3. 为什么这样写就一定能算出正确答案？
    - 由于子问题的规模比原问题小，不断“递”下去，总会有个尽头，即递归的边界条件 ( base case )，直接返回它的答案“归”；
    - 类似于数学归纳法（多米诺骨牌），n=1时类似边界条件；n=m时类似往后任意一个节点
4. 计算机是怎么执行递归的？
    - 当程序执行“递”动作时，计算机使用栈保存这个发出“递”动作的对象，程序不断“递”，计算机不断压栈，直到边界时，程序发生“归”动作，正好将执行的答案“归”给栈顶元素，随后程序不断“归”，计算机不断出栈，直到返回原问题的答案，栈空。
5. 另一种递归思路
    - 维护全局变量，使用二叉树遍历函数，不断更新全局变量最大值。
 */
public class TreeHeight {
    public static void main(String[] args) {
        TreeNode6 root = new TreeNode6(1);
        root.left = new TreeNode6(2);
        root.right = new TreeNode6(3);
        root.left.left = new TreeNode6(4);
        root.left.right = new TreeNode6(5);
        root.right.left = new TreeNode6(6);
        root.right.left.left = new TreeNode6(7);

        System.out.println(Height(root));
    }
    //递归获取树的高度
    public static int Height(TreeNode6 root) {
        int H1,H2;
        if(root != null){
            H1 = Height(root.left);
            H2 = Height(root.right);
            return Math.max(H1, H2)+1;
        }
        return 0;
    }
}

class TreeNode6 {
    int val;
    TreeNode6 left;
    TreeNode6 right;
    public TreeNode6(int x) {
        val = x;
    }
}
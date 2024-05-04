package week4;

/*二叉搜索树的插入与删除*/

public class BinTree {
    /*插入：关键是找到应该插入的位置*/
    public static TreeNode insert(TreeNode root, int data) {
        if (root == null) {/* 若原树为空，生成并返回一个节点的二叉树*/
            root = new TreeNode(data);
        } else {//开始找要插入的元素位置
            if (data < root.val) {
                root.left = insert(root.left, data);//递归插入左子树
            } else if (data > root.val) {
                root.right = insert(root.right, data);//递归插入右子树
            }/*else X已经存在 什么都不做*/
        }
        return root;
    }
    /*删除：
    考虑删除的三种情况：
    1.要删除的是叶子节点---直接删除
    2.要删除的有一个儿子节点---将父节点的指针指向儿子节点
    3.要删除的有左右两棵子树---用另一个节点替代被删除节点（右子树的最小元素/左子树的最大元素）
    */
    public static TreeNode delete(TreeNode root, int data) {
        if (root == null) {
            System.out.println("要删除的元素不存在");
            return null;
        } else {
            if (data < root.val) {
                root.left = delete(root.left, data);/*从左树递归删除*/
            } else if (data > root.val) {
                root.right = delete(root.right, data);/*从右树递归删除*/
            } else {/*root就是要删除的节点*/
                if ((root.left != null) && (root.right != null)) {/*如果被删除的节点有左右两个子节点*/
                    TreeNode tmp = FindMin(root.right);/*在右子树中找最小的元素填充删除节点*/
                    root.val = tmp.val;
                    root.right = delete(root.right, root.val);/*在右子树中删除data*/
                }else{/*被删除的节点只有一个叶子节点or无叶子节点*/
                    if(root.left == null){//只有右孩子或无节点
                        root = root.right;
                    }else {//只有左孩子
                        root = root.left;
                    }
                }
            }
            return root;
        }
    }

/*    //    查找最小元素的递归写法
    public static TreeNode FindMin(TreeNode root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root;//找到最左叶节点并返回
        } else {
            return FindMin(root.left);
        }
    }*/
    //    查找最小元素的迭代写法
    public static TreeNode FindMin(TreeNode root){
        if(root!=null){
            while(root.left!=null){
                root = root.left;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        insert(root, 3);
        insert(root, 10);
        delete(root, 2);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }
}
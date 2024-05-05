package week4;

/*
AVL（平衡二叉树）的旋转与插入

RR型状态用左旋、LL型状态用右旋
RL型先右旋后左旋、LR型先左旋后右旋
注意课堂材料此处概念有误

参考资料：https://www.bilibili.com/video/BV1dr4y1j7Mz/?spm_id_from=333.337.search-card.all.click&vd_source=9096009d98e9575f0e391c12407212da
 */
public class AVLTree {
    public static void main(String[] args) {
        AVLTree1 tree = new AVLTree1();

        /* 构造如下的AVL树
               10
              /  \
             5   20
            /    / \
           2    15 30
        */
        //每次插入都要返回新的根节点
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 2);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 30);

        // AVL树中序遍历
        System.out.println("中序遍历AVL树:");
        tree.inOrder(tree.root);
        System.out.println();

        /* 构造如下的AVL树
               10
              /  \
             5   20
            /    / \
           2    15 30
          /
         1

         调整为
               10
              /  \
             2   20
            /\    / \
           1  5  15 30

        */
        tree.root = tree.insert(tree.root, 1);
        tree.inOrder(tree.root);


    }
}

class AVLTree1 {
    AVLNode root;

    // 获取节点的高度
    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // 右旋转方法
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 旋转
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // 返回新的根节点
        return x;
    }

    // 左旋转方法
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 旋转
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // 返回新的根节点
        return y;
    }

    // 获取平衡因子
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // 插入方法
    AVLNode insert(AVLNode node, int key) {
        // 1. 正常的BST插入
        if (node == null)
            return (new AVLNode(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // 不允许重复键
            return node;

        // 2. 更新父节点的高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. 获取平衡因子以检查此节点是否失衡
        int balance = getBalance(node);

        // 如果节点失衡，则有四种情况

        // 左左情况
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // 右右情况
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // 左右情况
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左情况
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // 返回未改变的节点指针
        return node;
    }

    // 中序遍历
    void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
}

class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int d) {
        key = d;
        height = 1;
    }
}
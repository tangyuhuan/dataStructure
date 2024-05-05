package week4;

import java.util.Scanner;

/*
04-树5 Root of AVL Tree

题目：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281612&page=0
Now given a sequence of insertions, you are supposed to tell the root of the resulting AVL tree.

题解：本题考察AVL（平衡二叉树）的旋转与插入，注意左旋、右旋更新树高度时的顺序
AVL的参考视频：https://www.bilibili.com/video/BV1dr4y1j7Mz/?spm_id_from=333.337.search-card.all.click&vd_source=9096009d98e9575f0e391c12407212da

Sample Input 1:
5
88 70 61 96 120
Sample Output 1:
70
Sample Input 2:
7
88 70 61 96 120 90 65
Sample Output 2:
88
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodesNum = scanner.nextInt();
        scanner.nextLine();
        String line = scanner.nextLine();
        String[] nodes = line.split(" ");
        AVLTree2 tree = new AVLTree2();
        for (int i = 0; i < nodesNum; i++) {
            //每次插入都要返回新的根节点
            tree.root = tree.insert(tree.root, Integer.parseInt(nodes[i]));
        }
        System.out.println(tree.root.key);
    }
}


class AVLTree2 {
    AVLNode1 root;

    // 获取节点的高度
    int height(AVLNode1 N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // 右旋转方法
    AVLNode1 rightRotate(AVLNode1 y) {
        AVLNode1 x = y.left;
        AVLNode1 T = x.right;

        // 旋转
        x.right = y;
        y.left = T;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // 返回新的根节点
        return x;
    }

    // 左旋转方法
    AVLNode1 leftRotate(AVLNode1 x) {
        AVLNode1 y = x.right;
        AVLNode1 T = y.left;

        // 旋转
        y.left = x;
        x.right = T;

        // 更新高度：注意左旋转先更新X高度，再更新y高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // 返回新的根节点
        return y;
    }

    // 获取平衡因子
    int getBalance(AVLNode1 N) {
        if (N == null) return 0;
        return height(N.left) - height(N.right);
    }

    // 插入方法
    AVLNode1 insert(AVLNode1 node, int key) {
        // 1. 正常的BST插入
        if (node == null) return new AVLNode1(key);
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {/*不接受相同值插入*/
            return node;
        }
        // 2. 更新父节点的高度
        node.height = Math.max(height(node.left), height(node.right)) + 1;

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

}

class AVLNode1 {
    int key, height;
    AVLNode1 left;
    AVLNode1 right;

    public AVLNode1(int key) {
        this.height = 1;
        this.key = key;
    }
}

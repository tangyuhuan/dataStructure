package week2;

/*
02-线性结构2 一元多项式的乘法与加法运算
设计函数分别求两个一元多项式的乘积与和。

输入格式:
输入分2行，每行分别先给出多项式非零项的个数，再以指数递降方式输入一个多项式非零项系数和指数（绝对值均为不超过1000的整数）。数字间以空格分隔。

输出格式:
输出分2行，分别以指数递降方式输出乘积多项式以及和多项式非零项的系数和指数。数字间以空格分隔，但结尾不能有多余空格。零多项式应输出0 0。

输入样例:
4 3 4 -5 2  6 1  -2 0
3 5 20  -7 4  3 1
输出样例:
15 24 -25 22 30 21 -10 20 -21 8 35 6 -33 5 14 4 -15 3 18 2 -6 1
5 20 -4 4 -5 2 9 1 -2 0

题目：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281605&page=0
完整测试数据：https://blog.csdn.net/acDream_/article/details/82730484

 */

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size1 = in.nextInt();
        Polynomial p1 = new Polynomial();
        for (int i = 0; i < size1; i++) {
            p1.insert(in.nextInt(), in.nextInt());
        }
        int size2 = in.nextInt();
        Polynomial p2 = new Polynomial();
        for (int i = 0; i < size2; i++) {
            p2.insert(in.nextInt(), in.nextInt());
        }

/*        p1.print();
        System.out.println("==================");
        p2.print();*/

        Polynomial p3 = p1.multiplication(p2);
        p3.print();
        System.out.println();
        Polynomial p4 = p1.add(p2);//结果多项式
        p4.print();

    }
}

class Polynomial {
    private int size;
    private Node head;// 头结点

    //    private static
    private static class Node {//系数，指数，指针域
        private int m;//系数
        private int n;//指数
        private Node next;//指针域

        public Node(int m, int n) {
            this.m = m;
            this.n = n;
        }

        @Override
        public String toString() {
            if(m!=0){
                return m + " " + n;
            }
            return "";
        }
    }

    public Polynomial() {
        this.size = 0;
        this.head = null;
    }

    //插入
    public void insert(int m, int n) {
        Node newNode = new Node(m, n);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node preNode = this.head;
            for (int i = 0; i < size - 1; i++) {
                //查找原list的最后一个结点
                preNode = preNode.next;
            }
            preNode.next = newNode;
        }
        this.size++;
    }

    public void print() {
//        注意了！！要用一个Node节点来移动指针，this.head不能动，不然print完，this.head就为null了
        Node current = this.head;
        int tempSize = 0;
        boolean noValue = true;
        while (current != null) {
//            System.out.print(current);
            if(current.toString()!=""){
                noValue = false;
                if (tempSize++ != 0) {
                    System.out.print(" ");
                }
                System.out.print(current);
            }
            current = current.next;
        }

        if(noValue){
            System.out.print("0 0");
        }
    }

    // 获取指定位置的元素
    public Node get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
/*  两个多项式相加的二路归并算法：利用归并排序思想，将两个多项式按照指数高低逐项比较，并将较小的项插入到结果多项式中。
    初始化两个指针，分别指向两个多项式的头节点
    循环比较两个多项式当前节点的指数大小
    a.如果p1指数大于p2指数，则将p1当前节点插入到结果多项式中，p1指针后移。
    b.如果p1指数等于p2指数，则将p1和p2的当前节点系数相加插入到结果多项式中，p1、p2指针后移。
    c.如果p2指数大于p1指数，则将p2当前节点插入到结果多项式中，p2指针后移。
    检查p1和p2是否还有剩余节点，将剩余节点插入多项式中，p1/p2指针后移
    返回结果多项式
    参考：https://wenku.baidu.com/view/0633448400d8ce2f0066f5335a8102d277a26111.html?_wkts_=1713973684961&bdQuery=%E4%BB%8E%E5%A4%B4%E5%BC%80%E5%A7%8B%2C%E6%AF%94%E8%BE%83%E4%B8%A4%E4%B8%AA%E5%A4%9A%E9%A1%B9%E5%BC%8F%E5%BD%93%E5%89%8D%E5%AF%B9%E5%BA%94%E9%A1%B9%E7%9A%84%E6%8C%87%E6%95%B0&needWelcomeRecommand=1
*/
    public Polynomial add(Polynomial p) {
        Polynomial p3 = new Polynomial();//结果多项式
        Node current1 = this.head;
        Node current2 = p.head;

        while ((current1 != null) && (current2 != null)) {
            if (current1.n > current2.n) {
                p3.insert(current1.m, current1.n);
                current1 = current1.next;
            } else if (current1.n == current2.n) {
                p3.insert(current1.m + current2.m, current1.n);
                current1 = current1.next;
                current2 = current2.next;
            } else {
                p3.insert(current2.m, current2.n);
                current2 = current2.next;
            }
        }
        if (current1 == null) {
            while (current2 != null) {
                p3.insert(current2.m, current2.n);
                current2 = current2.next;
            }
        } else {
            while (current1 != null) {
                p3.insert(current1.m, current1.n);
                current1 = current1.next;
            }
        }
        return p3;
    }

    //两个多项式相乘：
    //根据多项书相乘的过程，每轮相乘结果放入Polynomial[this.size* p.size]数组，遍历数组求和：把系数为1的项到n-1项，逐个加到系数为0的项
    public Polynomial multiplication(Polynomial p) {
        if(this.size* p.size==0){
            Polynomial polynomial= new Polynomial();
            polynomial.insert(0,0);
            return polynomial;
        }
        //相乘过程
        Polynomial[] parray = new Polynomial[this.size* p.size];
        for (int i = 0; i < this.size* p.size; i++) {
            parray[i] = new Polynomial();
        }
        Node current1 = this.head;
        Node current2 = p.head;
        int index = 0;
        while (current1 != null) {
            while (current2 != null) {
                parray[index++].insert(current1.m * current2.m, current1.n + current2.n);
//                p3.insert(current1.m * current2.m, current1.n + current2.n);
                current2 = current2.next;
            }
            current1 = current1.next;
            current2 = p.head;
        }
        if(parray.length==1){
            return parray[0];
        }
        for (int i = 1; i < parray.length; i++) {
            parray[0] = parray[0].add(parray[i]);
        }
        return parray[0];

    }

}
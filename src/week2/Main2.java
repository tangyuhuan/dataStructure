package week2;
/*
02-线性结构3 Reversing Linked List
Given a constant K and a singly linked list L, you are supposed to reverse the links of every K elements on L.
For example, given L being 1→2→3→4→5→6, if K=3, then you must output 3→2→1→6→5→4; if K=4, you must output 4→3→2→1→5→6.

Sample Input:
00100 6 4
00000 4 99999
00100 1 12309
68237 6 -1
33218 3 00000
99999 5 68237
12309 2 33218

Sample Output:
00000 4 33218
33218 3 12309
12309 2 00100
00100 1 99999
99999 5 68237
68237 6 -1


00100 1 1
00100 2 -1

题目：https://pintia.cn/problem-sets/1738108464136978432/exam/problems/type/7?problemSetProblemId=1738108464208281606&page=0
题解说明：https://blog.csdn.net/u014800380/article/details/52919739
陈越讲解：https://www.icourse163.org/learn/ZJU-93001?tid=1001757011#/learn/content?type=detail&id=1002249375&cid=1002375950&replay=true

注意：第一行的第一个数为链表头结点所在的位置，第二个数字表示要存入数组中的节点个数，
请注意其中可以有多余节点,给的数据中存在无效数据，即不在同一条链表上

算法：
ADT设计：
模拟链表在内存中存在的状态，开一个充分大的结构数组来代表内存空间
每个节点的位置就是数组的下标,数组中的每一项记录值和下一项地址（整数，也是数组的下标）
从头节点开始顺着next指针走一圈就可以遍历链表

单链表的逆转
1.增加头节点
2.需要两个指针，new是逆转好的链表头节点位置、old是逆转前的链表头节点位置，tmp记住下一个节点
new初始化为1，old初始化为2，tmp为3
将old指向new实现2指向1的逆转，new、tmp后移一位，old后移一位，一直要重复K步
1的指针要指向5（当前还没有逆转的链表的头节点，即old）
head指针要指向4（当前已经逆转的链表的头节点，即new）
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main2 {
    public static int remain;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int head = in.nextInt();
        int nodesNumber = in.nextInt();
        int K = in.nextInt();
        ArrayNode[] arr = new ArrayNode[100000];
        for (int i = 0; i < nodesNumber; i++) {
            arr[in.nextInt()] = new ArrayNode(in.nextInt(), in.nextInt());
        }
        head = reverse(head, K, arr);
//        head = subReverse(head, K, arr);
        display(head, arr);
    }

    /*
    获取有效链表长度
     */
    public static int getN(int head, ArrayNode[] arr) {
        ArrayNode element = arr[head];
        int count = 1;
        while (element.next != -1) {
            element = arr[element.next];
            count++;
        }
        return count;
    }

    /*
     * 打印数组中的静态链表
     */
    public static void display(int head, ArrayNode[] arr) {
        ArrayNode element = arr[head];
        System.out.println(change(head) + " " + element.data + " " + change(element.next));
        while (element.next != -1) {
            System.out.println(change(element.next) + " " + arr[element.next].data + " " + change(arr[element.next].next));
            element = arr[element.next];
        }
    }

    /*
     * 改变输出数字格式 在不够五位数的前面补0
     */
    public static String change(int val) {
        if (val == -1) {
            return "-1";
        }
        String str = val + "";
        String zeroStr = "00000";
        return zeroStr.substring(str.length()) + str;//substring一个参数是beginIndex
    }

    public static int reverse(int head, int K, ArrayNode[] arr) {
        int N = getN(head, arr);
        int times = 1;
        int remain1 = head;
        int remain2 = head;
        int newhead = head;
        if(K==1){
            return head;
        }
        while (times <= N / K) {
            if(times==1){//第一次逆转
                remain1 = head;
                newhead = subReverse(head, K, arr);
            }else{//第i>1次的逆转，需要把两次逆转链表连接起来
                int tmp = remain;//上一次逆转子列的头节点
                remain2 = subReverse(remain, K, arr);
                arr[remain1].next = remain2;//把两次逆转链表连接起来：上一次逆转子列的头节点（逆转后是尾巴） 指向 本次逆转子列的头节点
                remain1 = tmp;
            }
            times++;
        }
        arr[remain1].next = remain;
        return newhead;
    }

  /*
    以K为分界线对单向链表进行逆转
    return是new，用head去接收就相当于头指针指向new的过程
    */

    public static int subReverse(int head, int K, ArrayNode[] arr) {
        int cnt = 1;
        int newP = head;
        int oldP = arr[newP].next;
        while (cnt < K) {
            int tmp = arr[oldP].next;
            remain = tmp;
            arr[oldP].next = newP;// 将链表方向逆转
            newP = oldP;//newP往后移一位
            oldP = tmp;//oldP往后移一位
            cnt++;
        }
//        arr[head].next = oldP;//把原本的第一项.next指向子列的后一项
        return newP;//反转子列的第一位
    }

}

class ArrayNode {
    int data;
    int next;

    public ArrayNode(int data, int next) {
        this.data = data;
        this.next = next;
    }
}



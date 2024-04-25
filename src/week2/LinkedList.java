package week2;

//线性表的定义与操作：链式存储
/*什么是抽象的链表：
1.有块地方存储数据
2.有块地方存指针-------下一个节点的地址*/
public class LinkedList<T> {
    private Node<T> head;// 头结点
    private int size; // 链表的大小

    // 结点类,静态内部类
    private static class Node<T> {
        private T data;//节点数据
        private Node<T> next; //指针域，下一个节点

        // 结点构造函数
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    //建立空单链表
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    //查找：按位查找
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;//一项项往后找
        }
        return current.data;
    }

    // 在指定位置插入元素
    public void insert(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            //查找前一个结点
            Node<T> preElement = this.head;
            for (int i = 0; i < index - 1; i++) {
                preElement = preElement.next;//一项项往后找
            }
            newNode.next = preElement.next;
            preElement.next = newNode;
        }
        this.size++;
    }

    // 删除指定位置的元素
    public void remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (index == 0) {
            this.head = this.head.next;
        } else {
            Node<T> preElement = this.head;
            for (int i = 0; i < index - 1; i++) {
                preElement = preElement.next;//一项项往后找
            }
            preElement.next = preElement.next.next;
        }
        this.size--;
    }

    //清空链表
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    // 打印链表
    public void print() {
        System.out.print("[");
        Node<T> current = this.head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.insert(0,"aaa");
        linkedList.insert(1,"bbb");
        linkedList.insert(2,"ccc");
        linkedList.insert(3,"ddd");
        linkedList.print();
        System.out.println(linkedList.get(3));
        linkedList.remove(3);
        linkedList.print();

    }
}

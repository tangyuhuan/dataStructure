package week2;

import java.util.EmptyStackException;

/*
堆栈的定义与操作：链式存储
栈的链式存储结构实际上就是一个单链表，叫做链栈。插入和删除操作只能在链栈的栈顶进行。
实现了堆栈链式存储的基本操作，包括获取栈的大小、判断栈是否为空、获取栈顶元素、入栈、出栈、清空栈等。
 */
public class Stack2<E> {
    private Node<E> top; // 栈顶结点
    private int size;// 栈的大小

    // 结点类
    private static class Node<E> {
        private E data;
        private Node<E> next;
        // 结点构造函数
        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
    // 构造函数
    public Stack2() {
        this.top = null;
        this.size = 0;
    }
    // 获取栈的大小
    public int size(){
        return this.size;
    }
    // 判断栈是否为空
    public boolean isEmpty(){
        return this.top==null;
    }
    // 获取栈顶元素
    public E peek() {
        if(this.top==null){
            throw new EmptyStackException();
        }
        return this.top.data;
    }
    // 入栈
    //栈的链式存储结构实际上就是一个单链表，叫做链栈。插入和删除操作只能在链栈的栈顶进行。
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = this.top;
        this.top = newNode;
//        this.top.next = node;//这行代码就理解错误了，插入、删除不能在栈底进行
        this.size++;
    }
    //出栈
    public E pop() {
        if(this.top==null){
            throw new EmptyStackException();
        }
        E data = this.top.data;
        this.top = this.top.next;
        this.size--;
        return data;
    }
    // 清空栈
    // 各项操作不要忘了size
    public void clear() {
        this.top = null;
        this.size = 0;
    }
}

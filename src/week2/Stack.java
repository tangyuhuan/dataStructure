package week2;

import java.util.EmptyStackException;

/*
堆栈的定义与操作：顺序存储
栈的顺序存储结构通常由一个一维数组和一个记录栈顶元素位置的变量组成。
 */
public class Stack<E> {
    private Object[] arr;// 存储元素的数组
    private int top;// 栈顶指针，就是数组的下标
    private int size;// 栈的大小

    public Stack(int capacity) {
        this.arr = new Object[capacity]; //泛型的数组无法初始化，因为不确定类型不确定要分配多大存储空间
        this.top = -1;
        this.size = 0;
    }

    // 获取栈的大小
    public int size(){
        return this.size;
    }
    // 判断栈是否为空
    public boolean isEmpty(){
        return this.top==-1;
    }
    // 获取栈顶元素
    public E peek() {
        if(this.top==-1){
            throw new EmptyStackException();
        }
        return (E)arr[top];
    }
    // 入栈
    public void push(E element) {
        if(this.top == this.arr.length-1){
            throw new StackOverflowError();//栈已满，再插入数据就栈溢出
        }
        this.top++;
        this.arr[this.top] = element;
        this.size++;
    }
    //出栈
    public E pop() {
        if(this.top==-1){
            throw new EmptyStackException();
        }
        E data = (E)this.arr[this.top];
        this.top--;
        this.size--;
        return data;
    }
    // 清空栈
    // 各项操作不要忘了size
    public void clear() {
        this.top = -1;
        this.size = 0;
    }

    public static void main(String[] args) {
        Stack<Integer> stack= new Stack<>(10);
        stack.push(1);

        int[] arr = new int[10];
        System.out.println(arr.length);
    }
}

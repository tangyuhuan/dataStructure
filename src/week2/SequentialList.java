package week2;

import java.util.NoSuchElementException;

//线性表的定义与操作： 顺序存储
public class SequentialList<T> {
    private T[] data; // 存储数据的数组
    private int size; // 线性表当前的大小
    private int capacity; // 线性表的总容量
    /*初始化*/
    public SequentialList(T[] data, int size, int capacity) {
        this.data = data;
        this.size = size;
        this.capacity = capacity;
    }
    /*查找:根据值返回其位置*/
    public int find(T x){
        for(int i = 0; i < size; i++){
            if(data[i].equals(x)){
                return i;
            }
        }
        throw new NoSuchElementException("No such element");
    }
    /* 在指定位置插入元素*/
    public void insert(int index,T x){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if(size == capacity){
            throw new RuntimeException("SeqList is full");
        }
        for(int i=size-1;i>index;i--){
            data[i] = data[i-1];
        }
        data[index]= x;
        this.size++;
    }
    /*删除指定位置的元素*/
    public void remove(int index){
        if(index<0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        //注意i的范围 ，i+1为size-1是最后一项
        for(int i=index;i<size-1;i++){
            data[i] = data[i+1];
        }
        //最后一项设为null，释放
        data[this.size-1] = null;
        this.size--;
    }
    // 打印线性表
    public void print() {
        System.out.print("[");
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.data[i]);
            if (i < this.size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        String[] strings = {"abc","def","ghi","jkl","mno","pqrs"};
        SequentialList<String> list = new SequentialList<String>(strings, strings.length, 10);
        System.out.println(list.find("jkl"));
        list.remove(5);
        list.print();
        list.insert(1,"1111");
        list.print();
    }
}

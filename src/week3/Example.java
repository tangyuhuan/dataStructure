package week3;
/*
java中都是值传递（引用类型也是值传递，传递的是地址值）
 */
public class Example {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        System.out.println(a[0]);//1
        method(a);
        System.out.println(a[0]);//10
    }
    public static void method(int[] b){
        //值传递，传递的是地址值，b和a指向同一块地址
//        b = new int[]{8,9,10};//b指向另一个对象
        b[0]=10;
//        System.out.println(b[0]);
    }
}
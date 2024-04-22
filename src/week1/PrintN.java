package week1;
//例子2:顺序打印从1到N的全全部正整数
//1.循环实现
//2.递归实现
public class PrintN {
    //1.循环实现
    private static void printN(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.printf("%d\t",i);
        }
    }
    //2.递归实现
    public static void printN2(int n) {
        if(n>0){
            printN2(n-1);
            System.out.print(n+" ");
        }
    }
    public static void main(String[] args) {
        printN(100000);
        System.out.println();
        printN2(100000);//递归程序把空间全部占用，程序非正常终止
    }

}

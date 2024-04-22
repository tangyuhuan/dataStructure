package week1;

//例三：多项式求值：计算给定多项式在给定点x处的值
//f(x)=a0+a1x+a2x^2+...+anx^n
public class GetValue2 {
    //方案一：愚蠢做法，因为运行慢,比第二个算法慢了一个数量级
    public static double calculate(int n, double a[], double x) {
        double sum = a[0];
        for (int i = 1; i <= n; i++) {
            sum += a[i] * Math.pow(x, i);
        }
        return sum;
    }

    //方案一：多项式可以提取一层层提取公因子x
    //f(x)=a0+x(a1+x(...(an-1+xan))...))
    public static double calculate2(int n, double a[], double x) {
        double p = a[n];
        for (int i = n; i > 0; i--) {
            p = a[i - 1] + x * p;
        }
        return p;
    }

    public static void main(String[] args) {
        double[] arr = {1, 2, 3};
        long time1 = System.nanoTime();;//（统计单位：纳秒）
        System.out.println(calculate(2, arr, 1));
        long time2 = System.nanoTime();
        System.out.println(time2 - time1);
        long time3 = System.nanoTime();
        System.out.println(calculate2(2, arr, 1));
        long time4 = System.nanoTime();
        System.out.println(time4 - time3);
    }
}

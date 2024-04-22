package week1;

//例三：多项式求值：计算给定多项式在给定点x处的值
//f(x)=a0+a1x+a2x^2+...+anx^n
//面对对象写法
public class GetValue {
    private int n;
    private double[] a;
    private double x;

    public GetValue(int n, double[] a, double x) {
        this.n = n;
        this.a = a;
        this.x = x;
    }

    //方案一：愚蠢做法，因为运行慢
    public double calculate1() {
        double sum = a[0];
        for (int i = 1; i <= n; i++) {
            sum += a[i] * Math.pow(x, i);
        }
        return sum;
    }

    //方案一：多项式可以提取一层层提取公因子x
    //f(x)=a0+x(a1+x(...(an-1+xan))...))
    public double calculate2() {
        double p = a[n];
        for (int i = n; i > 0; i--) {
            p = a[i - 1] + x * p;
        }
        return p;
    }

    public void getTimeSpend(GetValue gv,int type){
        long time1 = System.nanoTime();;//（统计单位：纳秒）
        if(type == 1){
            System.out.println(gv.calculate1());
        }else{
            System.out.println(gv.calculate2());
        }
        long time2 = System.nanoTime();
        System.out.println(time2 - time1);
    }
    public static void main(String[] args) {
        double[] arr = {1, 2, 3};
        GetValue gv = new GetValue(2, arr, 1);
        gv.getTimeSpend(gv, 1);
        gv.getTimeSpend(gv, 2);
    }
}

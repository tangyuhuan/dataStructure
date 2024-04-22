package week1;
//查找算法中的二分法
//给定N个从小到大排好序的整数序列List[],待查找整数X，我们的目标是找到X在List中的下标，即若有List[i]=X,则返回i；否则返回-1表示没有找到
//二分发誓先找到序列的中点List[M],与X进行比较，若相等则返回中点的下标，否则，若List[M]>X,则在左边的子系列中查找X；若List[M]<X,则在右边的子系列中查找X
//写出伪代码，分析最坏、最好情况下的时间、空间复杂度

/*伪代码
方案一：递归
binarySrearch(List[],x,low,high){
    if(low>high){return -1}
    mid = (low+high)/2
    if(x>List[mid]) {
        low = mid
        binarySrearch(List[],x,mid+1,high)
    }else if(x<List[mid]){
        high = mid
        binarySrearch(List[],x,low,mid-1)
    }else{
        return mid
    }
}
时间复杂度：最好O(1),最差O(logN)
空间复杂度：递归实现O(logN)，如果用循环方式实现为O(1)
*/


public class BinarySrearch {
    private int[] list;
    private int x;
    private int low;
    private int high;

    public BinarySrearch(int[] list, int x, int low, int high) {
        this.list = list;
        this.x = x;
        this.low = low;
        this.high = high;
    }

    //找到X在list中的下标
    public int binarySrearch() {
        if(this.low>this.high){
            return -1;
        }else{
            int mid = (this.low+this.high)/2;
            if(x>this.list[mid]) {
                this.low = mid+1;
                return binarySrearch();
            }else if(x<this.list[mid]) {
                this.high = mid-1;
                return binarySrearch();
            }else{
                return mid;
            }
        }
    }
    public static void main(String[] args) {
        int[] listTofind = {1,2,3,4,5,6,7,8,9};
        BinarySrearch binarySrearch = new BinarySrearch(listTofind,8,0,listTofind.length-1);
        System.out.println(binarySrearch.binarySrearch());
    }
}

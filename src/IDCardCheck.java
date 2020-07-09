/**
 * 22 02 02 2020 02 02 002 2
 * 省-市-县-年-月-日-顺序码-校验码
 *
 * 顺序码最后一位002中的2，奇数表示男，偶数表示女
 *
 * 最后1位校验码计算(mod表示取余，∑表示取余)：
 *      A1 = (12 - ∑Ai*Wi mod 11) mod 11
 *      其中：i表示从右至左的位数，2开始，[2,18]
 *          当余数为10时，校验码位为X
 *
 * 身份证校验：
 *      ∑Ai*Wi mod 11 = 1 mod 11
 *      其中：i表示从右至左的位数，1开始，[1,18]
 *          Ai为第i的数字
 *          Wi为权重，值为：2的i-1幂次方对11取余结果
 */
public class IDCardCheck {
    public static void main(String[] args){
        String str = "220202202002020022";
        char[] chars = new StringBuffer(str).reverse().toString()
                        .toCharArray();
        int[] W = new int[chars.length+1], A = new int[chars.length+1];
        A[0] = W[0] = Integer.MAX_VALUE; // 0位置不用
        // 计算A1值
        for (int i = 1; i < 19; i++) {
            W[i] = fun(2,i-1) % 11;
        }
        // 计算A1值
        for (int i = 1; i < 19; i++) {
            if(i == 1 && (chars[1] == 'X' || chars[1] == 'x')){
                A[i] = 10;
            }else{
                A[i] = Integer.parseInt(""+chars[i-1]);
            }
        }
        int res1 = 0;
        // 计算A1值
        for (int i = 2; i < 19; i++) {
            res1 += A[i] * W[i];
        }
        int res2 = res1 + A[1] * W[1];
        int a1 = (12 - res1 % 11) % 11;
        System.out.println("A[1]="+A[1]+", a1="+a1+", res2 % 11="+res2 % 11);
        if (A[1] == a1 && res2 % 11 == 1 % 11){
            System.out.println("身份证校验成功");
        }else{
            System.out.println("身份证校验失败");
        }

    }
    // 求m的n次方
    static int fun(int m, int n){
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= m;
        }
        return res;
    }
}

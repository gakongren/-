package cn.sorato;

import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.DoubleStream;

import static java.lang.Double.min;
import static java.lang.System.exit;

/**
 * Created by kongren on 2016/10/20.
 */
public class Main{
    static double N, x;

    private static boolean validate(){
        if(N < 1000){
            System.out.println("你服连一千元帅都凑不齐");
            return false;
        }
        if(x > N){
            System.out.println("你个lowB都掉到服务器外面了");
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        Scanner is = new Scanner(System.in);
        System.out.println("绿色计算器: 想知道每次演习你老婆要接多少客吗?");
        System.out.println("告诉我你服大概有多少提督:");
        N = is.nextDouble();
        System.out.println("然后输入你的战果排名");
        x = is.nextDouble();
        is.close();

        double d;
        if(!validate())
            exit(0);
        if(x < 1000)
            x = 1000;
        OptionalDouble od = DoubleStream
                .iterate(x, i->++i)
                .limit((long) (N - x))
                .parallel()
                .map(v->3 / v)
                .reduce(Double::sum);
        d = od.isPresent() ? od.getAsDouble() : 0;
        if(x == 1000){
            d = d * 999 / 1000 + (5000 / 999) + (N - 1001) / 1000;
        }else{
            d += min(10, x - 1000) * 0.1D;
        }
        System.out.print("每次演习刷新后你的平均接客量大概是:");
        System.out.println(d);
    }
}

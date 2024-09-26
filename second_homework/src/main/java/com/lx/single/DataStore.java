package com.lx.single;

import lombok.Data;

import java.util.Random;
import java.util.Stack;

/**
 * TODO
 *
 * @Description
 * @Author Lx
 * @Date 2024/9/26 上午10:46
 **/
@Data
public class DataStore {
    private static volatile DataStore dataStore;

    /*
      策略1：
      1.从栈顶弹出a1-操作数
      2.
      从栈顶弹出a2-操作符(如果是+或-)则继续下一步单暂不运算，而是判断后面一个是否为(*或/)，依次循环往后看，直到遇到(+或-)。
      如果是(*或/)继续下一步就直接进行运算
      3.从栈顶弹出a3-操作数
      4.从栈顶弹出a4-操作符
      ...
      ...
      ...

      策略2:补偿保证e1>=e2
      当弹出来操作数a1和操作符a2，判断a3，是否a1<a3,如果 a1<a3，则要把a1和a2弹出，重新，入栈一个新的操作数和操作符。直到满足了解，否则继续弹出。


      策略3:式子不能重复
      1.可以通过将式子字符串进行hash运算，然后存到set中，可能会存在误判，但结果肯定可以保证都是唯一的
     **/

    static String op1;
    static int num1;//生成的题目个数
    static String op2;
    static int num2;//生成的值的范围[0~num2)
    static Random random;
    static Stack<Integer> st;//用来存放操作数和操作符
    static {
        num1=1;
        num2=2;
        random = new Random();
        st=new Stack<>();
    }
    public void Init(String[] args){
        if(args.length==4){
            op1=args[0];
            num1=Integer.parseInt(args[1]);
            op2=args[2];
            num2=Integer.parseInt(args[3]);
        }else if(args.length==2){
            op1=args[0];
            num1=Integer.parseInt(args[1]);
        }

    }
    public String Run(){

        //1.生成运算符的个数
        int count = random.nextInt(num2+1);//左闭右开[0~num2)



        return null;
    }
    static public DataStore getInstance(){
        if(dataStore==null){
            synchronized (DataStore.class){
                if(dataStore==null)
                    dataStore=new DataStore();
            }
        }
        return dataStore;
    }
}

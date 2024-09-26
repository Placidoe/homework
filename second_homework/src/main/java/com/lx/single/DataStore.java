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


    static String op1;
    static int num1;//生成的题目个数
    static String op2;
    static int num2;//生成的值的范围[0~num2)
    static Random random;
    static Stack<Integer> st;
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

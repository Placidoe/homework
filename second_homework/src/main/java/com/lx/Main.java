package com.lx;

import com.lx.single.DataStore;

public class Main {

    public static boolean checkOp(String op){
        return op.equals("-n")||op.equals("-e");
    }
    public static boolean checkNum(int num){
        return num>0;
    }
    public static void main(String[] args) {
        if(args.length!=4&&args.length!=2){
            System.out.println("参数个数错误：请输入两个参数");
            return;
        }
        //-n 1 -r 1.至少指定一个操作符和操作数

        if(((args.length==4)&&(!checkOp(args[0])||!checkOp(args[2])))
                && ((args.length==2)&&(!checkOp(args[0])))){
            System.out.println("操作符错误，请输入正确的操作符");
        }

        if(((args.length==4)&&(checkNum(Integer.parseInt(args[1]))||checkNum(Integer.parseInt(args[3]))))
                && ((args.length==2)&&(checkNum(Integer.parseInt(args[1]))))){
            System.out.println("操作数错误，请输入正确的操作数");
        }


        DataStore dataStore=DataStore.getInstance();
        dataStore.InitData(args);

        dataStore.Run();



    }
}
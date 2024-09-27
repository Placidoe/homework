package com.lx.single;

import lombok.Data;

import java.util.*;

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
    static Stack<Object> st;//用来存放操作数和操作符。自栈顶向下，就是自表达式左向右
    static StringBuilder stringBuilder;
    static HashSet set;
    StringBuilder HashString;
    static int index;
    static String[] tokens;
    String[] ops={"+","-","*","/"};
    static {
        num1=1;
        num2=2;
        random = new Random();
        st=new Stack<>();
        set=new HashSet();
        stringBuilder=new StringBuilder();
        tokens=new String[1000];
    }
    public void insertOp(){//入操作符
        int val = random.nextInt(4);
//        stringBuilder.append(ops[val]);
        tokens[index++]=ops[val];
    }

    public void insertNum(){//入操作数
//        stringBuilder.append(random.nextInt(num2+1));
        tokens[index++]= String.valueOf(random.nextInt(num2+1));
    }
    public void InitData(String[] args){

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
    public void InitTokens(){

        //1.生成运算符的个数
        int count = random.nextInt(num2+1);//左闭右开[0~num2)

        //2.入栈操作数和操作符
        insertNum();
        for(int i=0;i<count;i++){
            insertNum();// 1 2 + 3 / 4 - 6
            insertOp();
        }
    }

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        HashString.append(num1);
                        HashString.append("+");
                        HashString.append(num2);
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        HashString.append(num1);
                        HashString.append("-");
                        HashString.append(num2);
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        HashString.append(num1);
                        HashString.append("*");
                        HashString.append(num2);
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        HashString.append(num1);
                        HashString.append("/");
                        HashString.append(num2);
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }


    public void Run(){
        //1.初始化tokens
        InitTokens();
        //2.运算(运算+补偿+拼接表达式)
        int res=evalRPN(tokens);
        HashString.append("=");
        HashString.append(res);
        //3.校验表达式是否唯一，不唯一则要重试
        if(set.contains(HashString)){
            Run();
            HashString=new StringBuilder();
        }
        set.add(HashString);
        //4.完成表达式
        System.out.println(HashString.toString());
        HashString=new StringBuilder();
    }

    public int caculate(String op,int num1,int num2){
        switch (op){
            case "+":
                return num1+num2;
            case "-":
                if(num1<num2){

                    return num2-num1;
                }
                return num1-num2;
            case "*":
                return num1*num2;
            case "/":
                return num1/num2;
            default:
                return 0;
        }
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

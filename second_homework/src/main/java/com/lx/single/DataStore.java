package com.lx.single;

import com.lx.utils.FractionGenerator;
import com.lx.utils.IOUtils;
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
    static StringBuilder HashString;
    static int index;
    static String[] tokens;

    String[] ops={"+","-","*","/"};
    static {
        num1=3;
        num2=2;
        random = new Random();
        st=new Stack<>();
        set=new HashSet();
        stringBuilder=new StringBuilder();
        tokens=new String[1000];
        HashString=new StringBuilder();
    }
    public void insertOp(){//入操作符
        int val = random.nextInt(4);
//        stringBuilder.append(ops[val]);
        tokens[index++]=ops[val];
    }

    public void insertNum(){//入操作数
//        stringBuilder.append(random.nextInt(num2+1));
        tokens[index++]= String.valueOf(FractionGenerator.generateTrueFraction());
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
        tokens=new String[1000];
        index=0;
        //1.生成运算符的个数
        int val=random.nextInt(num2+1);
        int count = val==0?1:val;//左闭右开[0~num2)

        //2.入栈操作数和操作符
        insertNum();
        for(int i=0;i<count;i++){
            insertNum();// 1 2 + 3 / 4 - 6
            insertOp();
        }
    }

    public Double evalRPN(String[] tokens) {
        Deque<Double> stack = new LinkedList<Double>();
        Deque<String> express = new LinkedList<String>();

        express.push(tokens[0]);
        int n = index;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                Double num2 = stack.pop();
                Double num1 = stack.pop();
//
                StringBuilder HashString1 = new StringBuilder();
                switch (token) {
                    case "+":
                        String str1 = express.pop();
                        HashString1.append("(");
                        HashString1.append(str1);
                        HashString1.append("+");
                        HashString1.append(num2);
                        HashString1.append(")");
                        express.push(HashString1.toString());

                        stack.push(num1 + num2);
                        break;
                    case "-":
                        String str2 = express.pop();
                        HashString1.append("(");
                        HashString1.append(str2);
                        HashString1.append("-");
                        HashString1.append(num2);
                        HashString1.append(")");
                        express.push(HashString1.toString());

                        stack.push(num1 - num2);
                        break;
                    case "*":
                        String str3 = express.pop();
                        HashString1.append("(");
                        HashString1.append(str3);
                        HashString1.append("*");
                        HashString1.append(num2);
                        HashString1.append(")");
                        express.push(HashString1.toString());
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        if(num2==0)num2++;
                        String str4 = express.pop();
                        HashString1.append("(");
                        HashString1.append(str4);
                        HashString1.append("/");
                        HashString1.append(num2);
                        HashString1.append(")");
                        express.push(HashString1.toString());

                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        HashString=new StringBuilder(express.pop());
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }


    public void Run(){

        //运行指定的次数
        for(int j=0;j<num1;j++){
            //1.初始化tokens
            InitTokens();
            for(int i=0;i<index;i++){
                System.out.println(tokens[i]);
            }
            //2.运算(运算+补偿+拼接表达式)
            Double res=evalRPN(tokens);
            IOUtils.saveExercises(HashString.toString());
            HashString.append("=");
            HashString.append(res);
            IOUtils.saveAnswers(HashString.toString());
            //3.校验表达式是否唯一，不唯一则要重试
            if(set.contains(HashString)){
                j--;
                HashString=new StringBuilder();
                continue;
            }
            set.add(HashString);
            //4.完成表达式
            System.out.println(HashString.toString());
            HashString=new StringBuilder();
        }
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

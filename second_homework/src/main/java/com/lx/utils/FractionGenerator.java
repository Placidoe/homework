package com.lx.utils;

import java.util.Random;

public class FractionGenerator {

    public static void main(String[] args) {
        // 调用方法生成并打印一个真分数
        Fraction fraction = generateTrueFraction();
        System.out.println("随机生成的真分数是: " + fraction);
    }

    public static Fraction generateTrueFraction() {
        Random random = new Random();
        int numerator = 1; // 分子
        int denominator = 0; // 分母

        // 生成分母，确保分母大于分子
        do {
            denominator = random.nextInt(100) + 2; // 分母至少为2
        } while (numerator >= denominator);

        // 生成分子，确保分子小于分母
        numerator = random.nextInt(denominator - 1) + 1;

        // 返回生成的真分数
        return new Fraction(numerator, denominator);
    }

    // 分数类
    public static class Fraction {
        private int numerator; // 分子
        private int denominator; // 分母

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        @Override
        public String toString() {
            return numerator + "/" + denominator;
        }
    }
}
package com.lx.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * IOUtils类用于处理输入和输出操作
 * @Author Okg
 * @Date 2024/9/28 下午8:01
 **/
public class IOUtils {

    // 存储题目到文件
    public static void saveExercises(String exercises) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Exercises.txt", true))) {
            // 将题目写入文件
            writer.write(exercises);
            writer.newLine(); // 换行
        } catch (IOException e) {
            e.printStackTrace(); // 打印异常信息
        }
    }

    // 存储答案到文件
    public static void saveAnswers(String answers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Answers.txt", true))) {
            // 将答案写入文件
            writer.write(answers);
            writer.newLine(); // 换行
        } catch (IOException e) {
            e.printStackTrace(); // 打印异常信息
        }
    }
}
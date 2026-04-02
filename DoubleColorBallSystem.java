package com.itheima.demo1;

import java.util.Random;
import java.util.Scanner;

public class DoubleColorBallSystem {
    static void main(String[] args) {
        //得到了中奖号码
        int[] arr = creatNumber();

        //得到用户的号码
        int[] userArr = userInputNumber();

        //统计中奖的红球和篮球
        int blueCount = 0;
        int redCount = 0;
        for (int i = 0; i < userArr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] == userArr[i]) {
                    redCount++;
                    break;
                }
            }
        }

        if (userArr[userArr.length - 1] == arr[arr.length - 1]) {
            blueCount++;
        }

        if (redCount == 6 && blueCount == 1) {
            System.out.println("1000万");
        } else if (redCount == 6 && blueCount == 0) {
            System.out.println("500万");
        } else if (redCount == 5 && blueCount == 1) {
            System.out.println("3000");
        } else if ((redCount == 5 && blueCount == 0) || (redCount == 4 && blueCount == 1)) {
            System.out.println("200");
        }else if ((redCount == 4 && blueCount == 0) || (redCount == 3 && blueCount == 1)) {
            System.out.println("10");
        }else if ((redCount == 2 && blueCount == 1) || (redCount == 1 && blueCount == 1)|| (redCount == 0 && blueCount == 1)) {
            System.out.println("5");
        }else{
            System.out.println("thanks");
        }


    }

    public static int[] userInputNumber() {
        int[] arr = new int[7];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < arr.length - 1; ) {
            System.out.println("请输入号码");
            int redNumber = sc.nextInt();
            if (redNumber > 0 && redNumber < 34 && (!disguise(arr, redNumber))) {
                arr[i] = redNumber;
                i++;
            } else {
                System.out.println("输入无效");
            }
        }

        while (true) {
            System.out.println("请输入蓝球号码");
            int blueNumber = sc.nextInt();
            if (blueNumber > 0 && blueNumber < 17) {
                arr[arr.length - 1] = blueNumber;
                break;
            } else {
                System.out.println("输入无效");
            }
        }
        return arr;
    }


    public static int[] creatNumber() {
        int[] arr = new int[7];
        Random r = new Random();
        for (int i = 0; i < 6; ) {
            int redNumber = r.nextInt(33) + 1;
            if (!disguise(arr, redNumber)) {
                arr[i] = redNumber;
                i++;
            }
        }
        int blueNumber = r.nextInt(16) + 1;
        arr[arr.length - 1] = blueNumber;
        return arr;
    }

    public static boolean disguise(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return true;
            }
        }
        return false;
    }


}
package com.web.demo.algorithm;

import java.util.Scanner;

/**
 * 水仙花数算法（指一个三位数，其各位数字立方和等于该数本身）
 * 
 * @author jiangyf
 */
public class NarcissusNumber {

	@SuppressWarnings("resource")
	public static void printNarcissusNumber() {
		Scanner scan = new Scanner(System.in);// 接收控制台输入信息
		System.out.print("请输入一个整数：");
		try {
			int num = scan.nextInt();// 取出控制台输入的信息

			if (isNarcissusNumber(num)) {
				System.out.println(num + "是水仙花数！");
			} else {
				System.out.println(num + "不是水仙花数！");
			}
		} catch (Exception e) {
			System.out.println("必须输入整数");
		}
	}

	public static boolean isNarcissusNumber(int num) {
		int a = num / 100;
		int b = num / 10 % 10;
		int c = num % 10;
		double sum = Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3);
//		int sum = a * a * a + b * b * b + c * c * c;
		if (sum == num) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		for (int i = 100; i < 1000; i++) {
			if (isNarcissusNumber(i)) {
				System.out.println(i);
			}
		}
	}

}

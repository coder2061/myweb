package com.web.demo.basic;

public class DataType {
	public static void main(String[] args) {
		byte a = 1;
		byte b = 2;
		byte c = 1 + 2;
		// 虽然字面量在byte范围内可以直接赋值，但是变量不行，两个byte类型参与运算会放大成int型，要再赋值给byte类型变量，需要做类型转换
		byte d = (byte) (a + b);
		System.out.println(a + b + c + d);

		float e = 1.234f;
		// 没有f修饰的小数默认是double类型，
		float f = (float) 1.234;
		float g = e + f;
		System.out.println(g);
	}

}

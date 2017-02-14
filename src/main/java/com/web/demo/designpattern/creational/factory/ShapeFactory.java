package com.web.demo.designpattern.creational.factory;

import com.web.demo.designpattern.instance.shape.Circle;
import com.web.demo.designpattern.instance.shape.Rectangle;
import com.web.demo.designpattern.instance.shape.Shape;
import com.web.demo.designpattern.instance.shape.Square;

/**
 * 工厂模式（Factory Pattern）
 * 
 * @author jiangyf
 */
public class ShapeFactory {
	/**
	 * 获取形状类型的对象
	 * 
	 * @param shapeType
	 * @return Shape
	 */
	public Shape getShape(String shapeType) {
		if ("CIRCLE".equalsIgnoreCase(shapeType)) {
			return new Circle();
		} else if ("RECTANGLE".equalsIgnoreCase(shapeType)) {
			return new Rectangle();
		} else if ("SQUARE".equalsIgnoreCase(shapeType)) {
			return new Square();
		}
		return null;
	}

	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();

		// 获取 Circle 的对象，并调用它的 draw 方法
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		// 调用 Circle 的 draw 方法
		shape1.draw();

		// 获取 Rectangle 的对象，并调用它的 draw 方法
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		// 调用 Rectangle 的 draw 方法
		shape2.draw();

		// 获取 Square 的对象，并调用它的 draw 方法
		Shape shape3 = shapeFactory.getShape("SQUARE");
		// 调用 Square 的 draw 方法
		shape3.draw();
	}

}

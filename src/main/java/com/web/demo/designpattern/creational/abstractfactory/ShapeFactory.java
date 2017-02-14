package com.web.demo.designpattern.creational.abstractfactory;

import com.web.demo.designpattern.instance.color.Color;
import com.web.demo.designpattern.instance.shape.Circle;
import com.web.demo.designpattern.instance.shape.Rectangle;
import com.web.demo.designpattern.instance.shape.Shape;
import com.web.demo.designpattern.instance.shape.Square;

public class ShapeFactory extends AbstractFactory {

	@Override
	Color getColor(String color) {
		return null;
	}

	/**
	 * 获取形状类型的对象
	 * 
	 * @param shapeType
	 * @return Shape
	 */
	Shape getShape(String shapeType) {
		if ("CIRCLE".equalsIgnoreCase(shapeType)) {
			return new Circle();
		} else if ("RECTANGLE".equalsIgnoreCase(shapeType)) {
			return new Rectangle();
		} else if ("SQUARE".equalsIgnoreCase(shapeType)) {
			return new Square();
		} else {
			return null;
		}
	}

}

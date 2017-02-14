package com.web.demo.designpattern.creational.abstractfactory;

import com.web.demo.designpattern.instance.color.Blue;
import com.web.demo.designpattern.instance.color.Color;
import com.web.demo.designpattern.instance.color.Green;
import com.web.demo.designpattern.instance.color.Red;
import com.web.demo.designpattern.instance.shape.Shape;

public class ColorFactory extends AbstractFactory {

	@Override
	Color getColor(String color) {
		if ("RED".equalsIgnoreCase(color)) {
			return new Red();
		} else if ("GREEN".equalsIgnoreCase(color)) {
			return new Green();
		} else if ("BLUE".equalsIgnoreCase(color)) {
			return new Blue();
		}
		return null;
	}

	@Override
	Shape getShape(String shape) {
		return null;
	}

}

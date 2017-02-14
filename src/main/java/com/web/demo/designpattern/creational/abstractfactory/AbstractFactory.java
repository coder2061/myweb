package com.web.demo.designpattern.creational.abstractfactory;

import com.web.demo.designpattern.instance.color.Color;
import com.web.demo.designpattern.instance.shape.Shape;

/**
 * 创建抽象类获取工厂
 * 
 * @author jiangyf
 */
public abstract class AbstractFactory {
	abstract Color getColor(String color);

	abstract Shape getShape(String shape);
}

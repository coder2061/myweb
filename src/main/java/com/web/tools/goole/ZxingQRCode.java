package com.web.tools.goole;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class ZxingQRCode {
	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public static void encoderQRCode(String content, String imgPath, String imgType,
			int size) {
		try {
			BufferedImage bufImg = QRCodeCreate(content, size, size);
			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * QRCodeCreate(生成二维码)
	 * content必须用UTF8编码格式，content中不能带有“&“和换行符：“&“符号用 %26 代替,换行符使用 %0A代替
	 * 
	 * @param content
	 *            二维码内容
	 * @param W
	 *            宽度
	 * @param H
	 *            高度
	 * @return
	 */
	public static BufferedImage QRCodeCreate(String content, Integer W,
			Integer H) {
		// 生成二维码
		MultiFormatWriter mfw = new MultiFormatWriter();
		BitMatrix bitMatrix = null;
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			bitMatrix = mfw.encode(content, BarcodeFormat.QR_CODE, W, H, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
						: 0xFFFFFFFF);
			}
		}
		return image;
	}
	
//	public static void main(String[] args) {
//		encoderQRCode("http://baidu.com", "d:/temp/test.png", "png", 200);
//	}
}
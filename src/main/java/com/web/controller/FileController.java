package com.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.web.tools.goole.ZxingQRCode;
import com.web.utils.FileUtil;

@Controller
@RequestMapping("file")
public class FileController {

	@RequestMapping("/index")
	public String index() {
		return "jsp/file";
	}

	/**
	 * 上传一个文件，只需声明MultipartFile类型即可，无需显式指定@RequestParam注解
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(MultipartFile file, HttpServletRequest request) {
		System.out.println("文件长度: " + file.getSize());
		System.out.println("文件类型: " + file.getContentType());
		System.out.println("文件名称: " + file.getName());
		System.out.println("文件原名: " + file.getOriginalFilename());
		System.out.println("========================================");
		FileUtil.upload(request, "D:/temp/");

		return "index";
	}

	/**
	 * 上传多个文件，需用MultipartFile[]数组来接收文件，需显式指定@RequestParam注解，
	 * 不然会抛出java.lang.NoSuchMethodException
	 */
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public String uploadFiles(@RequestParam("files") MultipartFile[] files,
			HttpServletRequest request) {
		for (MultipartFile file : files) {
			System.out.println("文件长度: " + file.getSize());
			System.out.println("文件类型: " + file.getContentType());
			System.out.println("文件名称: " + file.getName());
			System.out.println("文件原名: " + file.getOriginalFilename());
			System.out.println("========================================");
		}
		FileUtil.saveFiles(files, "D:/temp/");
		return "index";
	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest request) {
		try {
			// 设置上下方文
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iterator = multiRequest.getFileNames();
				while (iterator.hasNext()) {
					// 由CommonsMultipartFile继承而来,拥有上面的方法.
					MultipartFile file = multiRequest.getFile(iterator.next());
					if (file != null) {
						String path = "D:/" + file.getOriginalFilename();
						File localFile = new File(path);
						file.transferTo(localFile);
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/qrcode")
	public void qrcode(HttpServletResponse response,
			@RequestParam(value = "text", defaultValue = "") String text)
			throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		BufferedImage image = ZxingQRCode.QRCodeCreate(text, 250, 250);
		ImageIO.write(image, "png", response.getOutputStream());
	}
}

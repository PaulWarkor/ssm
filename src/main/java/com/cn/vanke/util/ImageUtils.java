package com.cn.vanke.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.vanke.exception.AppException;

/**
 * 
 * 功能说明：图片操作工具类(待完善)
 * 
 * ImageUtils.java
 */
public class ImageUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class); 
	
	/**
	 * 读取图片,不是图片返回null
	 * @param imageFile 图片文件
	 * @return 返回图片。
	 */
	public static BufferedImage readImage(File imageFile) {
		BufferedImage imageBuffered = null;
		if(!isImage(imageFile)){
			logger.error("读取文件不是图片.");
			return imageBuffered;
		}
		try {
			imageBuffered =  ImageIO.read(imageFile);
		} catch (IOException e) {
			logger.error("读取图片出现IO异常：{}",e.getMessage());
		}
		return imageBuffered;
	}

	/**
	 * 判断文件是否为图片文件。
	 * @param file  文件
	 * @return 如果文件为图片文件返回true，否则返回false。
	 */
	public static Boolean isImage(File file) {
		Boolean isImage = false;
		try {
			ImageInputStream fileInput = ImageIO.createImageInputStream(file);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(fileInput);
			isImage = iter.hasNext();
			fileInput.close();
		} catch (IOException e) {
			throw new AppException("读取图片文件" + file.getName() + "时发生异常", e);
		}
		return isImage;
	}

	
}

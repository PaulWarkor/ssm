/**
 * 
 */
package com.cn.vanke.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * 
 * 功能说明：ZIP压缩工具类
 * 
 * ZipUtils.java
 */
public class ZipUtils {

	public static final String EXT=".zip";
	
	private static final String BASE_DIR = "";
	
	//符号"/"用来作为目录标识判断符
	public static final String PATH = "/";
	
	private static final int BUFFER_SIZE = 4 * 1024;
	
	/**
	 * zip压缩
	 * @param srcFile 压缩源文件
	 * @throws IOException 
	 */
	public static void compress(File srcFile) throws IOException{
		String name = srcFile.getName();
		String basePath = srcFile.getParent();
		String destPath = basePath + name + EXT;
		compress(srcFile,destPath);
	}
	
	/**
	 * 压缩文件
	 * @param srcFile 源文件
	 * @param destPath 目标文件路径
	 * @throws IOException 
	 */
	public static void compress(File srcFile,String destPath) throws IOException{
		compress(srcFile,new File(destPath));
	}
	
	
	/**
	 * 文件压缩
	 * @param srcPath 源文件路径
	 * @param destPath 目标文件路径
	 * @throws IOException 
	 */
	public static void compress(String srcPath,String destPath) throws IOException{
		File srcFile = new File(srcPath);
		compress(srcFile,destPath);
	}
	
	/**
	 * 压缩
	 * @param srcFile 源文件
	 * @param destFile 目标文件
	 * @throws IOException 
	 */
	public static void compress(File srcFile,File destFile) throws IOException{
		//对输出文件做CRC32校验
		CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(destFile),new CRC32());
		ZipOutputStream zos = new ZipOutputStream(cos);
		compress(srcFile,zos,BASE_DIR);
		zos.flush();
		zos.close();
	}
	
	/**
	 * 压缩
	 * @param srcFile 源文件
	 * @param zos 
	 * @param basePath 压缩包内相对路径
	 * @throws IOException
	 */
	private static void compress(File srcFile,ZipOutputStream zos,String basePath) throws IOException{
		if(srcFile.isDirectory()){
			compressDir(srcFile,zos,basePath);
		}else{
			compressFile(srcFile,zos,basePath);
		}
	}
	
	
	/**
	 * 压缩文件夹
	 * @param dir 文件夹
	 * @param zos
	 * @param basePath 
	 * @throws IOException
	 */
	private static void compressDir(File dir,ZipOutputStream zos,String basePath) throws IOException{
		File[] files = dir.listFiles();
		//构建空目录
		if(files.length < 1){
			ZipEntry entry = new ZipEntry(basePath + dir.getName() + PATH);
			zos.putNextEntry(entry);
			zos.closeEntry();
		}
		for(File file:files){
			//递归压缩
			compressFile(file, zos, basePath+dir.getName()+PATH);
		}
	}
	
	/**
	 * 文件压缩
	 * @param file 待压缩文件
	 * @param zos
	 * @param dir 压缩文件的当前路径
	 * @throws IOException
	 */
	private static void compressFile(File file,ZipOutputStream zos,String dir) throws IOException{
		ZipEntry entry = new ZipEntry(dir + file.getName());
		zos.putNextEntry(entry);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int count;
		byte[] data = new byte[BUFFER_SIZE];
		while((count = bis.read(data,0,BUFFER_SIZE)) != -1){
			zos.write(data,0,count);
		}
		bis.close();
		zos.closeEntry();
	}
	
	
	
}

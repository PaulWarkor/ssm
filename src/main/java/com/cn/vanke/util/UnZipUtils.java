/**
 * 
 */
package com.cn.vanke.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * 
 * 功能说明：ZIP解压工具类
 * 
 * UnZipUtils.java
 */
public class UnZipUtils {

	public static final String EXT=".zip";
	
	//private static final String BASE_DIR = "";
	
	//符号"/"用来作为目录标识判断符
	private static final String PATH = File.separator;
	
	private static final int BUFFER_SIZE = 4 * 1024;
	
	/**
	 * zip解压
	 * @param srcPath 源文件路径
	 * @throws IOException 
	 */
	public static void decompress(String srcPath) throws IOException{
		File srcFile = new File(srcPath);
		decompress(srcFile);
	}
	
	/**
	 * 解压缩
	 * @param srcFile 待解压的源文件
	 * @throws IOException
	 */
	public static void decompress(File srcFile) throws IOException{
		String bathPath = srcFile.getParent();
		decompress(srcFile,bathPath);
	}
	
	
	/**
	 * 解压缩
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void decompress(File srcFile,File destFile) throws IOException{
	 CheckedInputStream cis = new CheckedInputStream(new FileInputStream(srcFile),new CRC32());
	 ZipInputStream zis = new ZipInputStream(cis);
	 decompress(destFile,zis);
	 zis.close();
	}
	
	
	public static void decompress(File srcFile,String destPath) throws IOException{
		decompress(srcFile,new File(destPath));
	}
	

	

	public static void decompress(String srcPath,String destPath) throws IOException{
		File srcFile = new File(srcPath);
		decompress(srcFile,destPath);
	}
	
	
	
	/**
	 * 文件解压缩
	 * @param destFile 目标文件
	 * @param zis
	 * @throws IOException 
	 */
	private static void decompress(File destFile,ZipInputStream zis) throws IOException{
		ZipEntry entry = null;
		while((entry = zis.getNextEntry()) != null){
			//文件
			String dir = destFile.getPath() + PATH + entry.getName();
			File dirFile = new File(dir);
			//文件检查
			fileProber(dirFile);
			if(entry.isDirectory()){
				dirFile.mkdirs();
			}else{
				decompressFile(dirFile,zis);
			}
			zis.closeEntry();
		}
	}
	
	/**
	 * 文件探针
	 * 档父目录不存在时，创建目录
	 * @param dirFile
	 */
	private static void fileProber(File dirFile){
		File parentFile = dirFile.getParentFile();
		if(!parentFile.exists()){
			//递归寻找上级目录
			fileProber(parentFile);
			parentFile.mkdir();
		}
	}
	
	
	/**
	 * 文件解压
	 * @param destFile 目标文件
	 * @param zis
	 * @throws IOException
	 */
	private static void decompressFile(File destFile,ZipInputStream zis) throws IOException{
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
		int count;
		byte[] data = new byte[BUFFER_SIZE];
		while((count =zis.read(data, 0, BUFFER_SIZE)) != -1){
			bos.write(data, 0, count);
		}
		bos.close();
	}
	
	
	
}

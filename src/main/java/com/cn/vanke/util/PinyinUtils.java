package com.cn.vanke.util;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 功能说明：汉字拼音处理工具类
 * 
 * PinyinUtils.java
 */
public class PinyinUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(PinyinUtils.class);
	
	
	/**
	 * 获取中文汉字拼音首字母
	 * @param chinese 中文汉字
	 * @param caseType 大小写输出格式
	 * @return
	 */
	public static String getFirstSpellFromCN(String chinese,
			HanyuPinyinCaseType caseType){
		StringBuilder spell = new StringBuilder(100);
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
	    defaultFormat.setCaseType(caseType);  
	    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	    for (int i = 0; i < arr.length; i++){
	    	if (arr[i] > 128){
	    		try{
	    			String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i],defaultFormat);
	    			if (_t != null){
	    				spell.append(_t[0].charAt(0));
	    				}
	    			}catch(BadHanyuPinyinOutputFormatCombination e) {  
	    				logger.error("中文获取拼音首字母失败：{}",e.getCause());
	                    e.printStackTrace();  
	                }  
	            }else{
	              spell.append(arr[i]);  
	            }  
	        }
	    return spell.toString().replaceAll("\\W", "").trim();  
	}
	
	/**
	 * 获取中文汉字拼音首字母(默认大写)
	 * @param chinese 中文汉字
	 * @return
	 */
	public static String getFirstSpellFromCN(String chinese){
		return getFirstSpellFromCN(chinese,HanyuPinyinCaseType.UPPERCASE);
	}
	
	/**
	 * 获取中文汉字拼音全拼(未处理多音字)
	 * @param chinese 中文汉字
	 * @param caseType 大小写输出格式
	 * @return
	 */
	public static String getFullSpellFromCN(String chinese,
			HanyuPinyinCaseType caseType){
		StringBuilder spell = new StringBuilder(200);
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(caseType);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++){
			if (arr[i] > 128){
				try{
					spell.append(PinyinHelper.toHanyuPinyinStringArray(arr[i],defaultFormat)[0]);
					}catch(BadHanyuPinyinOutputFormatCombination e){
						logger.error("中文获取拼音全拼失败：{}",e.getCause());
						e.printStackTrace();
					 }
				}else{
					spell.append(arr[i]);  
	            }
			}
		return spell.toString();
	}
	
	/**
	 * 获取中文汉字拼音全拼(未处理多音字)
	 * @param chinese 中文汉字
	 * @return 默认输出小写拼音
	 */
	public static String getFullSpellFromCN(String chinese){
		return getFullSpellFromCN(chinese,HanyuPinyinCaseType.LOWERCASE);
	}
	
	
	/**
	 * 获取中文汉字拼音全拼(处理多音字)
	 * @param chinese 中文汉字
	 * @param caseType 大小写输出格式
	 * @return
	 */
	public static Set<String> getPinyinFromCN(String chinese,HanyuPinyinCaseType caseType){
		if(StringUtils.isNotBlank(chinese)){
			char[] srcChar = chinese.toCharArray();  
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();  
            // 输出设置，大小写，音标方式等  
            hanYuPinOutputFormat.setCaseType(caseType);  
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);  
            String[][] temp = new String[chinese.length()][];  
            for (int i = 0; i < srcChar.length; i++) {  
                char c = srcChar[i];  
                // 是中文或者a-z或者A-Z转换拼音
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {  
                    try {  
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(  
                                srcChar[i], hanYuPinOutputFormat);  
                    } catch (BadHanyuPinyinOutputFormatCombination e) {  
                        e.printStackTrace();  
                    }  
                } else if (((int) c >= 65 && (int) c <= 90)  
                        || ((int) c >= 97 && (int) c <= 122)){  
                    temp[i] = new String[]{String.valueOf(srcChar[i])};  
                } else {  
                    temp[i] = new String[]{ "" };  
                }  
            }  
            String[] pingyinArray = exchange(temp);  
            Set<String> pinyinSet = new HashSet<String>();  
            for (int i = 0; i < pingyinArray.length; i++){
                pinyinSet.add(pingyinArray[i]);  
            }  
            return pinyinSet;  
		}
		return null;
	}
	
	/**
	 * 获取中文汉字拼音全拼(处理多音字) 
	 * @param chinese 中文汉字
	 * @return 默认返回小写拼音
	 */
	public static Set<String> getPinyinFromCN(String chinese){
		return getPinyinFromCN(chinese,HanyuPinyinCaseType.LOWERCASE);
	}
	
	
	/**
	 * 获取中文汉字拼音全称(处理多音字) 返回以分隔符(separator)分开的字符串
	 * @param chinese 中文汉字
	 * @param caseType 大小写输出格式
	 * @param separator 分隔符
	 * @return
	 */
	public String getPinyinFromCNStr(String chinese,
			HanyuPinyinCaseType caseType,String separator){
		Set<String> pingyiSet = getPinyinFromCN(chinese,caseType);
		String pingyin = null;
		if(pingyiSet != null){
			pingyin = StringUtils.joion(pingyiSet, separator);
		}
		return pingyin;
	}
	
	/**
	 * 获取中文汉字拼音全称(处理多音字)，返回以分隔符(separator)分开的字符串
	 * @param chinese 中文汉字
	 * @param separator 分隔符
	 * @return 默认返回以分隔符(separator)分开的汉字拼音小写
	 */
	public static String getPinyinFromCNStr(String chinese,String separator){
		Set<String> pingyiSet = getPinyinFromCN(chinese);
		String pingyin = null;
		if(pingyiSet != null){
			pingyin = StringUtils.joion(pingyiSet, separator);
		}
		return pingyin;
	}
	
	/**
	 * 字符转换
	 * @param strJaggedArray
	 * @return
	 */
	private static String[] exchange(String[][] strJaggedArray){
		String[][] temp = doExchange(strJaggedArray);
		return temp[0];
	}  
	
	/**
	 * 字符递归转换
	 * @param strJaggedArray
	 * @return
	 */
	private static String[][] doExchange(String[][] strJaggedArray){
		int len = strJaggedArray.length;
		if (len >= 2) {
			int len1 = strJaggedArray[0].length;
			int len2 = strJaggedArray[1].length;
			int newlen = len1 * len2;
			String[] temp = new String[newlen];  
	            int index = 0;
	            for (int i = 0; i < len1; i++){
	            	for(int j = 0; j < len2; j++){
	            		temp[index] = strJaggedArray[0][i] + strJaggedArray[1][j];
	            		index++;
	            	}
	            }  
	            String[][] newArray = new String[len - 1][];  
	            for (int i = 2; i < len; i++) {  
	                newArray[i - 1] = strJaggedArray[i];  
	            }  
	            newArray[0] = temp;  
	            return doExchange(newArray);  
	        } else {  
	            return strJaggedArray;  
	        }
		}  
	  

}

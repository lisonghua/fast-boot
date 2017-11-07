package com.lish.dongfang.vote.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author lisong
 *
 */
public class DateUtils {
	
	private static Logger logger=LoggerFactory.getLogger(DateUtils.class); 
	
	private static SimpleDateFormat SHORT_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); 
	
	/**
	 * 判断是否是日期格式yyyy-MM-dd字符串
	 * @param str
	 * @return
	 */
	public static boolean isValidShortDate(String str) {
		if(StringUtils.isEmpty(str)) {
			return false;
		}
		
		try {
			SHORT_FORMAT.parse(str);
			return true;
		} catch (ParseException e) {
			
			return false;
		}
	}
	
	/**
	 * 字符串转日期格式yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date formatShortDate(String str) {
		try {
			return SHORT_FORMAT.parse(str);
		} catch (ParseException e) {
			logger.error("字符串："+str+"转日期格式：yyyy-MM-dd时，发生错误！",e);
			return null;
		}
	}
}

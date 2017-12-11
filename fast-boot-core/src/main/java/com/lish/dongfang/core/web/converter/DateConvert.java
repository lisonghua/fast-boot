package com.lish.dongfang.core.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 处理request提交参数String类型转Date类型
 * @author lisong
 *
 */
public class DateConvert implements Converter<String, Date>{
	
	private static Logger logger = LoggerFactory.getLogger(DateConvert.class);
	
	@Override
	public Date convert(String source) {
		if(StringUtils.isBlank(source)) {
			return null;
		}
		try {
			return DateUtils.parseDate(source, "yyyy-MM-dd");
		} catch (ParseException e) {
			logger.error("request提交参数：["+source+"]，不能转化为日期格式[yyyy-MM-dd]！",e);
			return null;
		}
	}
}

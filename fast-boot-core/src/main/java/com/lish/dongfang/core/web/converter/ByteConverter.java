package com.lish.dongfang.core.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 处理request提交参数String类型转Byte类型
 * @author lisong
 *
 */
public class ByteConverter implements Converter<String, Byte> {

	@Override
	public Byte convert(String source) {
		if(StringUtils.isBlank(source)) {
			return 0;
		}
		return new Byte(source);
	}

}

package com.lish.dongfang.core.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * springmvc controller interceptor
 * @author lisong
 *
 */
public class ControllerInterceptor implements HandlerInterceptor {
	
	private static Logger logger=LoggerFactory.getLogger(ControllerInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
//		logger.info("-------------打印请求参数开始-----------");
//		Enumeration<String> names = request.getParameterNames();
//		while (names.hasMoreElements()) {
//			String name = names.nextElement();
//			String value = request.getParameter(name);
//			logger.info(name+":"+value);
//		}
//		BufferedReader br = request.getReader();
//		BufferedReader br = new BodyReaderHttpServletRequestWrapper(request).getReader();
//		String str, wholeStr = "";
//		while((str = br.readLine()) != null){
//			wholeStr += str;
//		}
//		logger.info("调用controller之前，request body："+wholeStr);
//		logger.info("-------------打印请求参数结束-----------");
		return true;
	}

}

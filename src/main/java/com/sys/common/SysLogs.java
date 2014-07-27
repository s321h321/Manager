package com.sys.common;

import org.apache.log4j.Logger;



/**
 * 打印系统日志类
 * @author wangn
 *
 */
public class SysLogs {

	public static Logger log=Logger.getLogger(SysLogs.class);
	/**
	 * 
	 * @param ob 需要打日志的类
	 * @param type 日志类型：i=info、d=debug、e=error等
	 */
	public static void log(Class<?> clazz,String type,String msg){
		if("e".equals(type)){
		Logger.getLogger(clazz).error(msg);
		}else if("d".equals(type)){
			Logger.getLogger(clazz).debug(msg);
		}else{
			Logger.getLogger(clazz).info(msg);
		}
	}
}

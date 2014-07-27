package com.sys.sysconst;
/**
 * 配置文件名字以及路径信息,以及数据库配置信息
 * @author Administrator
 *
 */
public interface IConfigConst {

	/**
	 * 数据库配置文件路径
	 */
	public static final  String DB_CFG="db.properties";
	/**
	 * 数据库配置url
	 */
	public static final  String DB_URL="db.jdbcUrl";
	/**
	 * 数据库配置帐号
	 */
	public static final  String DB_USER="db.user";
	/**
	 * 数据库配置密码
	 */
	public static final  String DB_PASSWORD="db.password";
	/**
	 * 所属数据库类型如:mysql、oracle等
	 */
	public static final  String DB_TYPE="db.dbtype";
	/** 国际化文件的文件名前缀*/
	public static final String GLOBAL_FILE_NAME="global.file.name";
	public static final String GLOBAL_ENCODE="UTF-8";
	
	
}

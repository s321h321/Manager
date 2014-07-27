package com.sys.config;
import java.util.Locale;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.i18n.I18N;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.ViewType;
import com.sys.Interceptors.GlobalInterceptor;
import com.sys.common.I18n;
import com.sys.common.SysLogs;
import com.sys.controller.FirstController;
import com.sys.controller.LoginController;
import com.sys.model.UserModel;
import com.sys.sysconst.IConfigConst;
public class SysConfig extends JFinalConfig {

	/**
	 * 系统启动后，回调
	 */
	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
	}
	/**
	 * 系统关闭前回调
	 */
	@Override
	public void beforeJFinalStop() {
		super.beforeJFinalStop();
	}

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants constants) {
		SysLogs.log(SysConfig.class, "i", "系统开始初始化configConstant");
		SysLogs.log(SysConfig.class, "i", "加载并缓存db.properties文件");
		loadDBConfig();
		constants.setEncoding(IConfigConst.GLOBAL_ENCODE);
		//添加国际化支持
		I18N.init(getProperty(IConfigConst.GLOBAL_FILE_NAME), Locale.CHINESE, null);
		SysLogs.log(SysConfig.class, "i", "初始化Beetl模版");
		constants.setMainRenderFactory(new BeetlRenderFactory());
        // 获取GroupTemplate ,可以设置共享变量等操作
		GroupTemplate groupTemplate = BeetlRenderFactory.groupTemplate ;
		groupTemplate.registerFunction("i18n", new I18n());
		//设置为开发模式打印日志信息
		constants.setDevMode(true);
		SysLogs.log(SysConfig.class, "i", "初始化Beetl模版和I18n初始化结束");
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers handlers) {
		SysLogs.log(SysConfig.class, "i", "初始化druidhandler");
		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
		handlers.add(new ContextPathHandler("base"));
		handlers.add(dvh);
		SysLogs.log(SysConfig.class, "i", "初始化druidhandler完成");
	}

	/**
	 * 配置全局拦截器
	 * 此处配置的拦截器将会对所有请求 进行拦 截，除非使用 @ClearInterceptor在 ControllerController中清 除。
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {
		SysLogs.log(SysConfig.class, "i", "configInterceptor-添加拦截器");
		interceptors.add(new GlobalInterceptor());
		SysLogs.log(SysConfig.class, "i", "configInterceptor-拦截器初始化完成");
	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins plugins) {
		SysLogs.log(SysConfig.class, "i", "configPlugin-druid插件开始初始化");
		// 集成druid数据源支持并监控
		DruidPlugin dp = new DruidPlugin(getProperty(IConfigConst.DB_URL),
				getProperty(IConfigConst.DB_USER),
				getProperty(IConfigConst.DB_PASSWORD));
		dp.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType(getProperty(IConfigConst.DB_TYPE));
		dp.addFilter(wall);
		plugins.add(dp);
		SysLogs.log(SysConfig.class, "i", "configPlugin-druid插件结束初始化");
		// 集成druid数据源支持并监控
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		//配置数据库列名大小写不敏感
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		plugins.add(arp);
		/** 配置model与数据库表映射关系*/
		addModelPlugins(arp);
		
	}
	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes routes) {
		SysLogs.log(SysConfig.class, "i", "configRoute-初始化路由信息");
		routes.add("/",FirstController.class);
		routes.add("/login",LoginController.class);
		SysLogs.log(SysConfig.class, "i", "configRoute-初始化路由信息结束");
	}
	
	
	/**
	 * 加载数据库配置文件
	 */
	public void loadDBConfig(){
		loadPropertyFile(IConfigConst.DB_CFG);
	}
	/**
	 * 配置model与数据库表映射关系
	 * @param arp
	 */
	public void addModelPlugins(ActiveRecordPlugin arp){
		arp.addMapping("t_user", "tu_id",UserModel.class);
	}
}
package com.sys.controller;

import com.jfinal.core.Controller;
import com.sys.model.UserModel;
import com.sys.service.LoginService;

/**
 * 用户登录控制器
 * 
 * @author Administrator
 * 
 */
public class LoginController extends Controller {
	private LoginService loginservice=new LoginService();
	public void index() {
		render("index.html");
	}
	public void Login() {
		String username=getPara("uname");
		String password=getPara("password");
		UserModel user =loginservice.login(username, password);
		if (null != user) {
			render("index.html");
		} else {
			render("login.html");
		}

	}
}

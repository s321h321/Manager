package com.sys.service;
import com.sys.model.UserModel;
public class LoginService {
	public UserModel login(String name, String password) {
		return UserModel.dao.findFirst(
				"select *from t_user where tu_username=? and tu_password=?",
				name, password);

	}
}

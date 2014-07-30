package com.sys.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sys.model.UserModel;

public class UserController extends Controller{

	public void index(){
		
	}
	/**
	 * 分页的查询
	 * @param pageNumber 当前页数
	 * @param pageSize 每页显示的记录数
	 */
	public void pages(){
		int pageNumber=getParaToInt(0);
		int pageSize=getParaToInt(1);
		Page<UserModel>userpages=UserModel.dao.paginate(pageNumber, pageSize, "select tp_name,tu_name,tu_entry_time,"
				+ "tu_username,tu_createtime,(select tu_name from t_user where tu_operation_user_id"
				+ "=u.tu_id)operation_name ","from t_user u left join t_position p on p.tp_id=u.tu_id");
		
	}
}

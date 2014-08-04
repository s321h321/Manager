package com.sys.tester;



import org.junit.Before;
import org.junit.Test;

import com.sys.model.UserModel;

public class TestExam {

	@Before
	public void init(){
//		SysConfig syscon=new SysConfig();
//		syscon.configConstant(constants);
//		syscon.configPlugin(plugins);
//		syscon
	}
	@Test
	public void test() {
//		Page<UserModel>userpages=UserModel.dao.paginate(1, 10, "select tp_name,tu_name,tu_entry_time,"
//				+ "tu_username,tu_createtime,(select tu_name from t_user where tu_operation_user_id"
//				+ "=u.tu_id)operation_name ","from t_user u left join t_position p on p.tp_id=u.tu_id");
//		System.out.println(userpages);
		UserModel u=UserModel.dao.findFirst(
				"select *from t_user where tu_username=? and tu_password=?",
				"admin@126.com", "123456");
		System.out.println(u);
	}

}

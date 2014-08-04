package com.sys.tester;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class I18nTest {

	@Test
	public void TestI18n(){
	    ResourceBundle bundle = ResourceBundle.getBundle("sysManager", Locale.CHINESE);
	    String msg = bundle.getString("login.message");
	    System.out.println(MessageFormat.format(msg,"admin", new Date()));
	}
}

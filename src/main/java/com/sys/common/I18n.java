package com.sys.common;

import javax.servlet.http.HttpServletRequest;

import org.beetl.core.Context;
import org.beetl.core.Function;

import com.jfinal.i18n.I18N;

public class I18n implements Function {

	public Object call(Object[] aobj, Context context) {
		
		HttpServletRequest req = (HttpServletRequest) context.getGlobal("request");
		return I18N.getText((String) aobj[0], req.getLocale());
	}

}

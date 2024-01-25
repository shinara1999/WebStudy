package com.sist.model;

import javax.servlet.http.HttpServletRequest;

// interface => 관련 없는 데이터를 묶어서 한번에 처리

public interface Model {
	public String execute(HttpServletRequest request);
}

package com.itdr.services;

import com.itdr.common.ServerResponse;

import javax.servlet.http.HttpSession;

public interface UserService {
  //用户登录业务（服务）接口
/*  ServerResponse login(HttpSession session, String username, String password);*/
  // 用户忘记密码业务（服务）接口
  ServerResponse forget_get_question(String username);
  // 用户提交问题业务（服务）接口
  ServerResponse forget_check_answer(String username, String question, String answer);
}

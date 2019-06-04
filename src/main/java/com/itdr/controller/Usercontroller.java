package com.itdr.controller;

import com.itdr.common.ServerResponse;
import com.itdr.services.UserService;
import com.itdr.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user/")
public class Usercontroller {
      //注入业务层
    @Autowired
    UserService us;
   /* private  void login(HttpServletRequest request, HttpServletResponse response){
        ServerResponse sr=null;
        //获取前台传来的用户参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //获取session对象，用于创建用户session
        HttpSession session=request.getSession();
        //业务层处理业务
        sr=us.login(session,username,password);
        //数据转化为json数据格式
        UrlSetUtils.BackToJson(sr, response);
    }
*/
   //忘记密码函数
@RequestMapping("forget_get_question.do")
@ResponseBody
   private  ServerResponse  forget_get_question(String username){
       ServerResponse sr=null;
       //无
       //业务层处理业务
       sr=us.forget_get_question(username);
       //数据转化为json数据格式
       return sr;
   }

   //提交问题答案函数
   @RequestMapping("forget_check_answer.do")
   @ResponseBody
    private  ServerResponse forget_check_answer(String username,String question,String answer){
        ServerResponse sr=null;
        //业务层处理业务
        sr=us.forget_check_answer(username,question,answer);
        //数据转化为json数据格式
       return sr;
    }

    @RequestMapping("aaa.do")
    @ResponseBody
    private String geta(){
    return "ok";
    }
}

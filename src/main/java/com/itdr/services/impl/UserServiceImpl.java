package com.itdr.services.impl;

import com.itdr.common.Const;
import com.itdr.common.ServerResponse;
import com.itdr.dao.UserMapper;
import com.itdr.pojo.User;
import com.itdr.pojo.Useranswer;
import com.itdr.pojo.Userquestion;
import com.itdr.services.UserService;
import com.itdr.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //因为现在涉及数据查询，所以开始注入用户数据层
    @Autowired
    UserMapper um;
/*
    //用户登录业务（服务）实现类
    @Override
    public ServerResponse login(HttpSession session, String username, String password) {
        ServerResponse sr=null;
        //判断参数是否符合要求，比如是否为空，字符限制，内容限制之类
          if (username==null || username.equals("")){
              //返回错误状态码和信息
             sr=ServerResponse.createServerResponseByError(
                     Const.ReponseCodeEnum.EMPTY_USERNAME.getCode(),Const.ReponseCodeEnum.EMPTY_USERNAME.getDesc());
             return  sr;
          }
          //判断密码是否为空
          if (password==null || password.equals("")){
              //返回错误状态码和信息
              sr=ServerResponse.createServerResponseByError(
                      Const.ReponseCodeEnum.EMPTY_PASSWORD.getCode(),Const.ReponseCodeEnum.EMPTY_PASSWORD.getDesc());
         return  sr;
          }
        //查找是否存在该用户
        int a=userDao.selectByUname(username);
          if (a==0){
              //返回错误状态码和信息
              sr=ServerResponse.createServerResponseByError(
                      Const.ReponseCodeEnum.INEXISTENCE_USER.getCode(),Const.ReponseCodeEnum.INEXISTENCE_USER.getDesc());
         return sr;
          }
          //密码是否正确
         UserInfo ui =userDao.selectByUnameAndPsd(username,password);
         if(ui==null){
             //返回错误状态码和信息
             sr=ServerResponse.createServerResponseByError(
                     Const.ReponseCodeEnum.ERROR_PASSWORD.getCode(),Const.ReponseCodeEnum.ERROR_PASSWORD.getDesc());
          return sr;
         }

         //把用户信息保存在session中
        session.setAttribute(Const.RoleEnum.ROLE_CUSTOMER.getDesc(),ui);

         //返回用户数据
       *//* {
    "status":0,
    "data":{
        "id":12,
         "username":"aaa",
          "email":"aaa@126.com",
          "phone":null,
          "createTime":1222, "updateTime":1222
    }
}*//*   //返回用户详细信息的数据，不把密码返回
       UserIn u1=new UserIn();
       u1.setId(ui.getId());
       u1.setUsername(ui.getUsername());
       u1.setEmail(ui.getEmail());
       u1.setPhone(ui.getPhone());
       u1.setCreate_Time(ui.getCreate_Time());
       u1.setUpdate_Time(ui.getUpdate_Time());
       sr=ServerResponse.createServerResponseBySuccess(u1);
       return sr;
    }*/

    //用户忘记密码业务（服务）实现类
    @Override
    public ServerResponse forget_get_question(String username) {
        ServerResponse sr=null;
        //用户名不能为空
        if (username==null || username.equals("")){
            //返回错误状态码和信息
            sr=ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EMPTY_USERNAME.getCode(),Const.ReponseCodeEnum.EMPTY_USERNAME.getDesc());
            return  sr;
        }
        //用户名不存在
        User a=um.selectByUname(username);
        if (a == null){
            //返回错误状态码和信息
            sr=ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.INEXISTENCE_USER.getCode(),Const.ReponseCodeEnum.INEXISTENCE_USER.getDesc());
            return sr;
        }
        //该用户未设置找回密码问题
        User u =um.selectByUnameforquestion(username);
        if(u.getQuestion().equals("")){
            //返回错误状态码和信息
            sr=ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.NO_QUESTION.getCode(),Const.ReponseCodeEnum.NO_QUESTION.getDesc());
            return sr;
        }
        //返回用户问题
        /*{
            "status":0,
                "data":"这里是问题"
        }*/
        Userquestion u1=new Userquestion();
        u1.setQuestion(u.getQuestion());
        sr=ServerResponse.createServerResponseBySuccess(u1);
        return sr;
    }
    //用户提交问题答案业务（服务）实现类
    @Override
    public ServerResponse forget_check_answer(String username, String question, String answer) {
        ServerResponse sr=null;
        //用户名不能为空
        if (username==null || username.equals("")){
            //返回错误状态码和信息
            sr=ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EMPTY_USERNAME.getCode(),Const.ReponseCodeEnum.EMPTY_USERNAME.getDesc());
            return  sr;
        }
        //问题不能为空
        if (question==null || question.equals("")){
            //返回错误状态码和信息
            sr=ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EMPTY_QUESTION.getCode(),Const.ReponseCodeEnum.EMPTY_QUESTION.getDesc());
            return  sr;
        }
        //答案不能为空
        if (answer==null || answer.equals("")){
            //返回错误状态码和信息
            sr=ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EMPTY_ANSWER.getCode(),Const.ReponseCodeEnum.EMPTY_ANSWER.getDesc());
            return  sr;
        }
        //问题答案错误
        User u =um.selectByUnameforanswer(username,question);
        if(!(u.getAnswer().equals(answer))){
            //返回错误状态码和信息
            sr=ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.ERROR_ANSWER.getCode(),Const.ReponseCodeEnum.ERROR_ANSWER.getDesc());
            return sr;
        }
        //返回用户问题
        /*{
         "status":0,
         "data":"3235ffe-fewff-ff34534"
          }*/
        Useranswer u1=new Useranswer();
        String token= TokenUtils.getInstance().makeToken();
        u1.setToken(token);
        sr=ServerResponse.createServerResponseBySuccess(u1);
        return sr;
    }
}

package com.itdr.dao;


import com.itdr.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
   /* //数据层查询用户登录接口
    UserInfo selectByUnameAndPsd(String username, String password);*/
    //数据层查询用户是否存在接口
   User selectByUname(@Param("username")String username);
 //数据层查询用户问题接口
 User selectByUnameforquestion(@Param("username")String username);
 //数据层查询用户问题接口
 User selectByUnameforanswer(@Param("username")String username,@Param("question")String question);
}

package com.itdr.utils;

import com.itdr.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 这是用来处理请求的工具类*/
public class UrlSetUtils {
    public static String getMethod(HttpServletRequest request){
        //我要在这处理前台传过来的所用跟用户有关的请求
        String uri=request.getRequestURI();
        //获取的路径数据切割成想要的字符串
        String[] split =uri.split("/");
        //从数组中拿出对应方法的字符串
        String  method=split[split.length-1];
        return  method;
    }
    /**
     * 成功请求返回数据统一json处理
     */
    public static void BackToJson(ServerResponse sr, HttpServletResponse response){
        //把数据返回给前台
        //数据转换成json数据格式
        String s = JsonUtils.obj2String(sr);
        //数据返回给浏览器
        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

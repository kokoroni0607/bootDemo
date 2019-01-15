package com.bdth.bootDemo.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

@SuppressWarnings("all")
public class ReponseUtil {
    /**
     * 反馈的通用方法
     *
     * @param response 响应
     * @param code     状态码，正常状态 200 特殊信息 201 异常状态 404 权限不足 403 操作超时 402
     * @param msg      反馈日志
     * @param data     数据
     */
    public static void response(HttpServletResponse response, String code, String msg, Object data) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> resules = new HashMap<String, Object>();
        resules.put("code", code);
        resules.put("msg", msg);
        resules.put("data", data);
        String result = JSON.toJSONString(resules);
        try (PrintWriter writer = response.getWriter()){
            writer.println(result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询时返回列表同时返回总条数的特殊方法
     *
     * @param response
     * @param code
     * @param msg
     * @param data1
     * @param data2
     */
    public static void response(HttpServletResponse response, String code, String msg, Object data1, Object data2) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> resules = new HashMap<String, Object>();
        resules.put("code", code);
        resules.put("msg", msg);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", data1);
        data.put("total", data2);
        resules.put("data", data);
        String result = JSON.toJSONString(resules);
        try {
            PrintWriter writer = response.getWriter();
            writer.println(result);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询时返回列表同时返回总条数的特殊方法
     * 该方法会将元素所有为空的字符串都显示，而不是直接去掉，通过check控制
     */
    public static void response(HttpServletResponse response, String code, String msg, Object data1, boolean check, Object data2) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> resules = new HashMap<String, Object>();
        resules.put("code", code);
        resules.put("msg", msg);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", data1);
        data.put("total", data2);
        resules.put("data", data);
        String result;
        if (check) {
            result = JSON.toJSONString(resules, WriteNullStringAsEmpty);
        } else {
            result = JSON.toJSONString(resules);
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.println(result);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void response2(HttpServletResponse response, Map<String,Object> map) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String result = JSON.toJSONString(map);
        try {
            PrintWriter writer = response.getWriter();
            writer.println(result);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

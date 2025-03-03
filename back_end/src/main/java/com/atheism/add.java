package main.java.com.atheism;

import main.java.com.User;
import com.alibaba.fastjson2.JSONObject;
import main.java.com.runoob.mysql.connect;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson2.JSON;

@WebServlet("/add")
public class add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 允许跨域访问的源地址
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        // 允许的请求方法
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        // 允许的请求头字段
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        // 是否允许发送凭据（如cookie）
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        //获取参数
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String str = "";
        String wholeStr = "";
        while((str = reader.readLine()) != null){
            wholeStr += str;
        }
        JSONObject param = JSONObject.parseObject(wholeStr);
        int subject_id = param.getIntValue("subject_id");
        int chapter_id = param.getIntValue("chapter_id");
        String problem = param.getString("problem");
        String answer = param.getString("answer");
        String analysis = param.getString("analysis");
        String type = param.getString("type");
        String points = param.getString("points");
        String difficulty = param.getString("difficulty");
        System.out.println(subject_id);
        System.out.println(chapter_id);
        System.out.println(problem);
        System.out.println(answer);
        System.out.println(analysis);
        System.out.println(type);
        System.out.println(points);
        System.out.println(difficulty);

        connect sql = new connect();
        sql.add(subject_id,chapter_id,problem,answer,analysis,type,points,difficulty);
        //响应给浏览器
        resp.getWriter().write("ok");
        System.out.println("ok");
    }
}

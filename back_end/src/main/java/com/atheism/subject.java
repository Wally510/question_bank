package main.java.com.atheism;

import com.alibaba.fastjson2.JSON;
import main.java.com.User;
import main.java.com.runoob.mysql.connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/subject")
public class subject extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 允许跨域访问的源地址
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        // 允许的请求方法
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        // 允许的请求头字段
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        // 是否允许发送凭据（如cookie）
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        /*
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        resp.getWriter().write(username + "\n");
        resp.getWriter().write(password + "\n");

        resp.getWriter().write("ok\n");
         */

        connect sql = new connect();
        List<User> list = sql.subjects();

        try {
            //数据转成json格式
            String josn_string = JSON.toJSONString(list);
            System.out.println(list);
            System.out.println(josn_string);
            //设置文字编码
            resp.setContentType("application/json;charset=utf-8");
            //响应给浏览器
            resp.getWriter().write(josn_string);
            System.out.println("ok");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

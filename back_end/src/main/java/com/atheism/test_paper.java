package main.java.com.atheism;

import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import main.java.com.Dept;
import main.java.com.runoob.mysql.connect;
import main.java.com.User;
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

@WebServlet("/test_paper")
public class test_paper extends HttpServlet {
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

        connect sql = new connect();

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(date.format(formatter));
        String data = date.format(formatter);
        String test_paper = param.getString("test_paper");
        String subject_name = param.getString("subject_name");
        int test_paper_id = sql.add_paper(test_paper, data, subject_name);

        int subject_id = param.getIntValue("subject_id");
        JSONArray chapter = param.getJSONArray("chapter");
        int cl = chapter.size();
        int id[] = new int[cl];
        for (int i=0;i<cl;i++){
            //System.out.println(chapter.getJSONObject(i).get("id"));
            id[i] = (int) chapter.getJSONObject(i).get("id");
        }
        List<Dept> list = sql.test(subject_id,id);
        //System.out.println(list);

        JSONArray type = param.getJSONArray("type");
        float difficulty = param.getFloatValue("difficulty");
        System.out.println(type);
        System.out.println(difficulty);

        int tl = type.size();
        int hl = 0;
        String tixing[] = new String[tl];
        int shuliang[] = new int[tl];
        String fenzhi[] = new String[tl];
        //System.out.println(type);
        for (int i=0;i<tl;i++){
            System.out.println(type.getJSONObject(i).get("tixing"));
            tixing[i] = (String) type.getJSONObject(i).get("tixing");
        }
        for (int i=0;i<tl;i++){
            System.out.println(type.getJSONObject(i).get("shuliang"));
            shuliang[i] = (int) type.getJSONObject(i).get("shuliang");
            hl += shuliang[i];
        }
        for (int i=0;i<tl;i++){
            System.out.println(type.getJSONObject(i).get("fenzhi"));
            fenzhi[i] = (String) type.getJSONObject(i).get("fenzhi");
        }

        //随机生成难度
        int hard[] = new int[hl];
        int targetAverage = (int) (difficulty*10); // 目标平均值 (1 到 9 的中间值)
        int range = 9; // 随机数的范围 (1 到 9)
        int deviation = range / 2; // 偏差值，控制随机数的范围

        Random rand = new Random();
        for (int i = 0; i < hl; i++) { // 生成 10 个随机整数
            int randomValue = (int) (rand.nextDouble() * deviation - deviation / 2 + targetAverage);
            // 确保随机数在 1 到 9 之间
            randomValue = Math.max(1, randomValue); // 不小于 1
            randomValue = Math.min(9, randomValue); // 不大于 9
            System.out.println(randomValue);
            hard[i] = randomValue;
        }

        System.out.println(type);
        //System.out.println(tixing);
        System.out.println(list);
        int l = 0;
        for (int i=0;i<tl;i++){
            List<Dept> test_value = new ArrayList<>();
            for(int j=0;j<list.size();j++){
                if(tixing[i].equals(list.get(j).getType())){
                    test_value.add(list.get(j));
                }
            }
            System.out.println(test_value);
            for (int j=0;j<shuliang[i];j++){
                int tvl = test_value.size();
                System.out.println(tvl);
                int dif[] = new int[tvl];
                int d=0;
                for (int k=0;k<tvl;k++){
                    if(test_value.get(k).getDifficulty() - hard[l]*0.1 <= 0.01){
                        dif[d]=k;
                        d++;
                    }
                }
                int result = (int) (Math.random() * d);
                Dept item = test_value.get(dif[result]);
                sql.add_test(test_paper_id, subject_id, item);//添加
                test_value.remove(item);
                l++;
            }
        }
        System.out.println(hard);

        //响应给浏览器
        resp.getWriter().write("ok");
        System.out.println("ok");
    }
}

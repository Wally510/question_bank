package main.java.com.runoob.mysql;

import main.java.com.User;
import main.java.com.Dept;
import main.java.com.Papers;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connect {
    //这里是MySQLDemo 类
    /*
     *java连接mysql数据库
     *1、加载驱动程序
     *2、数据库连接字符串"jdbc:mysql://localhost:3306/数据库名?"
     *3、数据库登录名
     *3、数据库登录密码
     */

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/question?useSSL=false&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    public List<Dept> list(int chapter_id){
        Connection conn = null;
        Statement stmt = null;
        List<Dept> list = new ArrayList<>();
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT question_id, problem, answer, analysis, type, points, difficulty FROM questions WHERE chapter_id = " + "'" + chapter_id + "'"
            + "AND  question_id NOT IN (SELECT question_id FROM deletes)";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("question_id");
                String problem = rs.getString("problem");
                String answer = rs.getString("answer");
                String analysis = rs.getString("analysis");
                String type = rs.getString("type");
                String points = rs.getString("points");
                float difficulty = rs.getFloat("difficulty");

                //存入list
                Dept l = new Dept().setId(id).setProblem(problem).setAnswer(answer).setAnalysis(analysis).setType(type).setPoints(points).setDifficulty(difficulty);
                list.add(l);

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 题目: " + problem);
                System.out.print(", 答案: " + answer);
                System.out.print(", 解析: " + analysis);
                System.out.print(", 题型: " + type);
                System.out.print(", 知识点: " + points);
                System.out.print(", 难度: " + difficulty);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return list;
    }

    public List<User> subjects(){
        Connection conn = null;
        Statement stmt = null;
        List<User> list = new ArrayList<>();
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT subject_id,subject_name  FROM subjects";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int subject_id = rs.getInt("subject_id");
                String subject_name = rs.getString("subject_name");

                User user = new User().setId(subject_id).setName(subject_name);

                //存入list
                list.add(user);

                // 输出数据
                System.out.print("ID: " + subject_id);
                System.out.print(", 学科: " + subject_name);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return list;
    }

    public List<User> chapter(String name) {
        Connection conn = null;
        Statement stmt = null;
        List<User> list = new ArrayList<>();
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT subject_id,subject_name FROM subjects WHERE subject_name = " + "'" + name + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

            int subject_id = rs.getInt("subject_id");
            System.out.print("ID: " + subject_id);

            sql = "SELECT chapter_id,subject_id,chapter_name FROM chapters WHERE subject_id = " + "'" + subject_id + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                // 通过字段检索
                int chapter_id = rs.getInt("chapter_id");
                String chapter_name = rs.getString("chapter_name");
                User user = new User().setId(chapter_id).setName(chapter_name);
                //存入list
                list.add(user);
                // 输出数据
                System.out.print(", 章节: " + chapter_name);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return list;
    }

    public void delete(int question_id) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO deletes (question_id) VALUES (" + "'" + question_id + "'" + ")";
            stmt.executeUpdate(sql);

            // 完成后关闭
            //rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public void add(int subject_id,int chapter_id,String problem,String answer,String analysis,String type,String points,String difficulty){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO questions (subject_id,chapter_id,problem,answer,analysis,type,points,difficulty) VALUES ("
                    +subject_id+","+chapter_id+",'"+problem+"'"+",'"+answer+"'"+",'"+analysis+"'"+",'"+type+"'"
                    +",'"+points+"'"+",'"+difficulty+"'"+")";
            stmt.executeUpdate(sql);

            // 完成后关闭
            //rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
    public List<Dept> test(int subject_id,int chapter_id[]){
        Connection conn = null;
        Statement stmt = null;
        List<Dept> list = new ArrayList<>();
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql = "SELECT chapter_id, problem, answer, analysis, type, points, difficulty FROM questions WHERE subject_id = "
            + "'" + subject_id + "'" +"AND chapter_id IN (";
            for (int i=0;i<chapter_id.length;i++){
                sql += (i == 0 ? chapter_id[i] : "," + chapter_id[i]);
            }
            sql += ")";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("chapter_id");
                String problem = rs.getString("problem");
                String answer = rs.getString("answer");
                String analysis = rs.getString("analysis");
                String type = rs.getString("type");
                String points = rs.getString("points");
                float difficulty = rs.getFloat("difficulty");

                //存入list
                Dept l = new Dept().setId(id).setProblem(problem).setAnswer(answer).setAnalysis(analysis).setType(type).setPoints(points).setDifficulty(difficulty);
                list.add(l);

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 题目: " + problem);
                System.out.print(", 答案: " + answer);
                System.out.print(", 解析: " + analysis);
                System.out.print(", 题型: " + type);
                System.out.print(", 知识点: " + points);
                System.out.print(", 难度: " + difficulty);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return list;
    }
    public int add_paper(String test_paper_name, String data, String subject_name){
        Connection conn = null;
        Statement stmt = null;
        int paper = 0;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT COUNT(*) FROM test_papers;";
            ResultSet rs = stmt.executeQuery(sql);
            int rowCount = 0;
            if (rs.next()) {
                rowCount = rs.getInt(1); // 获取第一列的值，即COUNT(*)的结果
            }
            if(rowCount == 0){
                String sql1 = "INSERT INTO test_papers (test_paper_id,test_paper_name,data,subject_name) VALUES ("
                        +1+","+"'"+test_paper_name+"'"+","+"'"+data+"'"+","+"'"+subject_name+"'"+")";
                stmt.executeUpdate(sql1);
                paper = 1;
            }else{
                sql = "SELECT test_paper_id FROM test_papers ORDER BY test_paper_id DESC LIMIT 1";
                rs = stmt.executeQuery(sql);
                int idCount = 0;
                if (rs.next()) {
                    idCount = rs.getInt("test_paper_id");
                }
                idCount += 1;
                String sql1 = "INSERT INTO test_papers (test_paper_id,test_paper_name,data,subject_name) VALUES ("
                        +idCount+","+"'"+test_paper_name+"'"+","+"'"+data+"'"+","+"'"+subject_name+"'"+")";
                stmt.executeUpdate(sql1);
                paper = idCount;
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return paper;
    }
    public void add_test(int test_paper_id, int subject_id, Dept item){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO tests (paper_id,subject_id,chapter_id,problem,answer,analysis,type,points,difficulty) VALUES ("
                    +test_paper_id+","+subject_id+","+item.getId()+",'"+item.getProblem()+"'"+",'"+item.getAnswer()+"'"+",'"+item.getAnalysis()+"'"
                    +",'"+item.getType()+"'"+",'"+item.getPoints()+"'"+",'"+item.getDifficulty()+"'"+")";
            stmt.executeUpdate(sql);

            // 完成后关闭
            //rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public List<Papers> getPapers(){
        Connection conn = null;
        Statement stmt = null;
        List<Papers> list = new ArrayList<>();
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT test_paper_id,test_paper_name,data,subject_name  FROM test_papers";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int test_paper_id = rs.getInt("test_paper_id");
                String test_paper_name = rs.getString("test_paper_name");
                String data = rs.getString("data");
                String subject_name = rs.getString("subject_name");

                Papers papers = new Papers().setTest_paper_id(test_paper_id).setTest_paper_name(test_paper_name).setData(data).setSubject_name(subject_name);

                //存入list
                list.add(papers);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return list;
    }

    public List<Dept> query_test(int paper_id){
        Connection conn = null;
        Statement stmt = null;
        List<Dept> list = new ArrayList<>();
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT chapter_id, problem, answer, analysis, type, points, difficulty FROM tests WHERE paper_id = " + "'" + paper_id + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("chapter_id");
                String problem = rs.getString("problem");
                String answer = rs.getString("answer");
                String analysis = rs.getString("analysis");
                String type = rs.getString("type");
                String points = rs.getString("points");
                float difficulty = rs.getFloat("difficulty");

                //存入list
                Dept l = new Dept().setId(id).setProblem(problem).setAnswer(answer).setAnalysis(analysis).setType(type).setPoints(points).setDifficulty(difficulty);
                list.add(l);

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 题目: " + problem);
                System.out.print(", 答案: " + answer);
                System.out.print(", 解析: " + analysis);
                System.out.print(", 题型: " + type);
                System.out.print(", 知识点: " + points);
                System.out.print(", 难度: " + difficulty);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return list;
    }
}

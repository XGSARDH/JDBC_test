package Sardh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args)throws Exception {
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);

        //3. 定义sql语句
        String sql = "update account set money = 3000 where id = 2";

        //4. 获取执行sql的对象 Startment
        Statement stmt = conn.createStatement();

        //5. 执行sql
        //受影响的行数
        int count = stmt.executeUpdate(sql);


        //6. 处理结果
        System.out.println(count);

        //7. 释放资源
        //先释放后面打开的,后释放先打开的+
        stmt.close();
        conn.close();

    }

}

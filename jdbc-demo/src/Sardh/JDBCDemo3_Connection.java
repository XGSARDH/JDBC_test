package Sardh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
JDBC API详解: Connection 详解
*/

public class JDBCDemo3_Connection {
    public static void main(String[] args)throws Exception {
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);

        //3. 定义sql语句
        String sql1 = "update account set money = 3000 where id = 2";
        String sql2 = "update account set money = 3000 where id = 1";

        //4. 获取执行sql的对象 Startment
        Statement stmt = conn.createStatement();


        try {
            //开启手动事务(关闭自动事务)
            conn.setAutoCommit(false);

            //5. 执行sql
            //受影响的行数
            int count1 = stmt.executeUpdate(sql1);
            //6. 处理结果
            System.out.println(count1);
            int i = 3/0;
            //5. 执行sql
            //受影响的行数
            int count2 = stmt.executeUpdate(sql2);
            //6. 处理结果
            System.out.println(count2);

            //提交事务
            conn.commit();

        } catch (SQLException e) {
            //回滚事务
            conn.rollback();
            throw new RuntimeException(e);
        }




        //7. 释放资源
        //先释放后面打开的,后释放先打开的+
        stmt.close();
        conn.close();

    }

}

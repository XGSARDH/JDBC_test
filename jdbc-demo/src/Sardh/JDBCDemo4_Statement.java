package Sardh;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
JDBC API详解: Statement 详解
*/

public class JDBCDemo4_Statement {

    /**
     执行DML语句
     还有添加和删除语句
     */
    @Test
    public void testDML() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);
        //3. 定义sql语句
        String sql = "update account set money = 2000 where id = 1";
        //4. 获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();
        //5. 执行sql
        //执行完DML语句后:受影响的行数
        int count = stmt.executeUpdate(sql);
        //6. 处理结果
        //System.out.println(count);
        if(count > 0){
            System.out.println("Modified successfully");
        }else{
            System.out.println("Modification failed");
        }

        //7. 释放资源
        //先释放后面打开的,后释放先打开的+
        stmt.close();
        conn.close();
    }

    /**
     执行DDL语句
     还有添加和删除和修改语句
     */
    @Test
    public void testDDL() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);
        //3. 定义sql语句
        String sql = "drop database db2";
        //4. 获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();
        //5. 执行sql
        //执行完DDL语句后:可能是0,例如:drop database db2
        int count = stmt.executeUpdate(sql);
        //6. 处理结果
        System.out.println(count);
/*        if(count > 0){
            System.out.println("Modified successfully");
        }else{
            System.out.println("Modification failed");
        }*/

        //7. 释放资源
        //先释放后面打开的,后释放先打开的+
        stmt.close();
        conn.close();

    }

}

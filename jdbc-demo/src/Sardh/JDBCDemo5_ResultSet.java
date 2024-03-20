package Sardh;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JCBC API详解: ResultSet
 */

public class JDBCDemo5_ResultSet {
    /**
     执行DQL语句
     还有添加和删除和修改语句
     */
    @Test
    public void testResultSet() throws Exception{
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);

        //3. 定义sql
        String sql = "select * from account";

        //4. 获取statement对象
        Statement stmt = conn.createStatement();

        //5. 执行sql
        ResultSet rs = stmt.executeQuery(sql);

        //6. 处理结果. 遍历rs的所有数据
        //6.1 光标向下移动一行,并且判断当前行是否有数据
        while (rs.next()){
            //6.2 获取数据 getXXX()
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int money = rs.getInt(3);

            System.out.println(id);
            System.out.println(name);
            System.out.println(money);

            System.out.println("-----------------------");

        }

        //7. 释放资源
        rs.close();
        stmt.close();
        conn.close();


    }

}

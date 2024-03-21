package Sardh;

import org.junit.Test;

import java.sql.*;

/**
 * JDBC API详解: PrepareStatement 详解
 */

public class JDBCDemo7_PrepareStatement {

    @Test
    public void testLogin() throws Exception{

        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);

        // 接受用户输入的用户名和密码
        String name = "zhangsan";
        String pwd = "123";

        // 注意要单引号和双引号嵌套写
        String sql = "select * from tb_user where username = '"+name+"' and password = '"+pwd+"' ";

        // 获取stmt对象
        Statement stmt = conn.createStatement();

        // 执行sql语句
        ResultSet rs = stmt.executeQuery(sql);

        // 判断登录是否成功
        if(rs.next()){
            System.out.println("Login is success!");
        }else{
            System.out.println("Login is failed!");
        }

        //7. 释放资源
        rs.close();
        stmt.close();
        conn.close();

    }

    /**
     * 演示SQL注入
     * @throws Exception
     */
    @Test
    public void testLogin_inject() throws Exception{

        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);

        // 接受用户输入的用户名和密码
        String name = "zhangsan";
        String pwd = "123";

        // 定义sql
        String sql = "select * from tb_user where username = ? and password = ?";

        // 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置?的值
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);

        // 执行sql
        ResultSet rs = pstmt.executeQuery();

        // 判断登录是否成功
        if(rs.next()){
            System.out.println("Login is success!");
        }else{
            System.out.println("Login is failed!");
        }

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }

}

package Sardh;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录
 */

public class JDBCDemo6_UserLogin {

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
        String name = "asdfdfs";

        //要想测试奇怪的密码,请用这个(不要多加随意的空格): ' or '1' = '1
        String pwd = "' or '1' = '1";

        // 注意要单引号和双引号嵌套写
        String sql = "select * from tb_user where username = '"+name+"' and password = '"+pwd+"' ";

        // 会打印:select * from tb_user where username = 'asdfdfs' and password = '' or '1' = '1'
        System.out.println(sql);

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

}

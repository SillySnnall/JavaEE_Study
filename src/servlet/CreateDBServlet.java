package servlet;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库创建操作
 * 注意事项：
 * 需要将数据库驱动 mysql-connector-java-5.1.45-bin.jar 复制到 C:\Program Files (x86)\Java\jre1.8.0_151\lib\ext 中
 * 项目需要添加 mysql-connector-java-5.1.45-bin.jar依赖
 */
public class CreateDBServlet extends HttpServlet {
    private String url;
    private String user;
    private String password;

    public void init() throws ServletException {
        // 加载数据库驱动
        String driverClass = getInitParameter("driverClass");
        url = getInitParameter("url");
        user = getInitParameter("user");
        password = getInitParameter("password");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException ce) {
            throw new UnavailableException("加载数据库驱动失败！");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("create database bookstore");
            stmt.executeUpdate("use bookstore");
            stmt.executeUpdate("create table bookinfo(id INT not null primary key,title VARCHAR(50) not null,author VARCHAR(50) not null,bookconcern VARCHAR(100) not null,publish_date DATE not null,price FLOAT(4,2) not null,amount SMALLINT,remark VARCHAR(200)) ENGINE=InnoDB");
            stmt.addBatch("insert into bookinfo values(1,'Java从入门到精通','张三','张三出版社','2004-6-1',34.00,35,null)");
            stmt.addBatch("insert into bookinfo values(2,'JSP深入编程','李四','李四出版社','2004-10-1',56.00,20,null)");
            stmt.addBatch("insert into bookinfo values(3,'J2EE高级编程','王五','王五出版社','2005-3-1',78.00,10,null)");
            stmt.executeBatch();

            PrintWriter out = response.getWriter();
            out.println("success!");
            out.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                conn = null;
            }
        }
    }
}

package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * 数据库查找
 */
public class ListServlet extends HttpServlet {
    private String url;
    private String user;
    private String password;

    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        String driverClass = sc.getInitParameter("driverClass");
        url = sc.getInitParameter("url");
        user = sc.getInitParameter("user");
        password = sc.getInitParameter("password");
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
        ResultSet rs = null;
        request.setCharacterEncoding("gb2312");
        String condition = request.getParameter("cond");
        if (null == condition || condition.equals("")) {
            response.sendRedirect("search.html");
            return;
        }
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            if (condition.equals("all")) {
                rs = stmt.executeQuery("select * from bookinfo");
                printBookInfo(out, rs);
                out.close();
            } else if (condition.equals("precision")) {
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String bookconcern = request.getParameter("bookconcern");

                if ((null == title || title.equals("")) &&
                        (null == author || author.equals("")) &&
                        (null == bookconcern || bookconcern.equals(""))) {
                    response.sendRedirect("search.html");
                    return;
                }

                StringBuffer sb = new StringBuffer("select * from bookinfo where ");
                boolean bFlag = false;

                if (!title.equals("")) {
                    sb.append("title = " + "'" + title + "'");
                    bFlag = true;
                }
                if (!author.equals("")) {
                    if (bFlag)
                        sb.append("and author = " + "'" + author + "'");
                    else {
                        sb.append("author = " + "'" + author + "'");
                        bFlag = true;
                    }
                }
                if (!bookconcern.equals("")) {
                    if (bFlag)
                        sb.append("and bookconcern = " + "'" + bookconcern + "'");
                    else
                        sb.append("bookconcern = " + "'" + bookconcern + "'");
                }
                rs = stmt.executeQuery(sb.toString());
                printBookInfo(out, rs);
                out.close();
            } else if (condition.equals("keyword")) {
                String keyword = request.getParameter("keyword");
                if (null == keyword || keyword.equals("")) {
                    response.sendRedirect("search.html");
                    return;
                }
                String strSQL = "select * from bookinfo where title like '%" + keyword + "%'";

                rs = stmt.executeQuery(strSQL);
                printBookInfo(out, rs);
                out.close();
            } else {
                response.sendRedirect("search.html");
                return;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                rs = null;
            }
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

    private void printBookInfo(PrintWriter out, ResultSet rs)
            throws SQLException {
        out.println("<html><head>");
        out.println("<title>图书信息</title>");
        out.println("</head><body>");
        out.println("<table border=1><caption>图书信息</caption>");
        out.println("<tr><th>书名</th><th>作者</th><th>出版社</th><th>价格</th><th>发行日期</th></tr>");
        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getString("title") + "</td>");
            out.println("<td>" + rs.getString("author") + "</td>");
            out.println("<td>" + rs.getString("bookconcern") + "</td>");
            out.println("<td>" + rs.getFloat("price") + "</td>");
            out.println("<td>" + rs.getDate("publish_date") + "</td>");
            out.println("</tr>");
        }
        out.println("</table></body></html>");
    }
}

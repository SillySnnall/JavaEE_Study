package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");

        PrintWriter out = response.getWriter();

        out.println("<form method=post action=portal>");

        out.println("<table>");

        out.println("<tr>");
        out.println("<td>请输入用户名</td>");
        out.println("<td><input type=text name=user></td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>请输入密码</td>");
        out.println("<td><input type=password name=password></td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td><input type=reset value=重填></td>");
        out.println("<td><input type=submit value=登录></td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("</form>");
    }
}

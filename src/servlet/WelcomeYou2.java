package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * web.xml文件中的初始数据
 */
public class WelcomeYou2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("gb2312");
        String user = request.getParameter("user");
        String welcomeMsg = getInitParameter("msg");

        response.setContentType("text/html;charset=gb2312");

        PrintWriter out = response.getWriter();

        out.println("<html><head><title>");
        out.println("Welcome Page");
        out.println("</title><body>");
        out.println(welcomeMsg + ", " + user);
        out.println("</body></head></html>");
        out.close();
    }
}

package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求转发(重定向)、可用于隐藏所访问的Servlet
 */
public class PortalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");

        PrintWriter out = response.getWriter();

        out.println("<html><head><title>");
        out.println("登录页面");
        out.println("</title></head><body>");

        String name = request.getParameter("user");
        String pwd = request.getParameter("password");

        if ("zhangsan".equals(name) && "1234".equals(pwd)) {
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/welcome");
            rd.forward(request, response);// 执行这一句则下面的代码都不会执行
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/login2");
            rd.include(request, response);
        }
        out.println("</body></html>");
        out.close();
    }
}

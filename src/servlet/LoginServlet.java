package servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

/**
 * 登陆
 */
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=gb2312");

        String name = req.getParameter("user");
        String pwd = req.getParameter("password");

        if (name != null && pwd != null && name.equals("zhangsan") &&
                pwd.equals("1234")) {
            resp.sendRedirect("success.html");
        } else {
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "服务器忙，请稍后再登录!");
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
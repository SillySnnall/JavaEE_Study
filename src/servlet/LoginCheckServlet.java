package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String randomCode = (String) session.getAttribute("randomCode");
        if (null == randomCode) {
            response.sendRedirect("login2.html");
            return;
        }

        String reqRandom = request.getParameter("random");

        request.setCharacterEncoding("GBK");
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();

        if (randomCode.equals(reqRandom)) {

            out.println("验证码匹配！");
        } else {
            out.println("验证码校验失败，请返回重新输入!");
        }
        out.close();
    }
}

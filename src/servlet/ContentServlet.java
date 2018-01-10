package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 页面访问次数
 */
public class ContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        Integer count = (Integer) context.getAttribute("counter");
        if (null == count) {
            count = 1;
        } else {
            count = count + 1;
        }

        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();

        out.println("<html><head>");
        out.println("<title>页面访问统计</title>");
        out.println("</head><body>");
        out.println("该页面已被访问了" + "<b>" + count + "</b>" + "次");
        out.println("</body></html>");
        context.setAttribute("counter", count);
        out.close();
    }
}

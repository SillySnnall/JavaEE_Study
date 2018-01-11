package servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import utils.PicZoom;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 图像缩放，缩略图
 */
public class ImageHandlerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        String strId = req.getParameter("id");
        if (null == strId || "".equals(strId)) {
            throw new ServletException("图像参数错误！");
        }

        int id = Integer.parseInt(strId);

        String srcImgFileName = null;

        //此处为了简单，所以使用了switch/case语句，硬编码了images/1.jpg这幅图片，
        //读者可以将图像数据保存到数据库中，根据请求的参数取出相应的图片。
        //或者直接保存在硬盘上，为所有的图片文件做一个索引文件，
        //得到请求参数后，通过查找索引文件得到图片的路径。
        switch (id) {
            case 1:
                srcImgFileName = getServletContext().getRealPath("/") + "images/1.jpg";
                break;
            case 2:
                break;
            default:
                throw new ServletException("图像参数错误！");
        }

        resp.setContentType("image/jpeg");

        ServletOutputStream sos = resp.getOutputStream();

        //调用PicZoom类的静态方法zoom对原始图像进行缩放。
        BufferedImage buffImg = PicZoom.zoom(srcImgFileName, 80, 80);

        //创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。
        JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(sos);
        //编码BufferedImage对象到JPEG数据输出流。
        jpgEncoder.encode(buffImg);

        sos.close();
    }
}

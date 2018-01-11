package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PicZoom {
    public static BufferedImage zoom(String srcFileName, int outputWidth, int outputHeight) {
        //使用源图像文件名创建ImageIcon对象。
        ImageIcon imgIcon = new ImageIcon(srcFileName);
        //得到Image对象。
        Image img = imgIcon.getImage();

        return zoom(img, outputWidth, outputHeight);
    }

    public static BufferedImage zoom(Image srcImage, int outputWidth, int outputHeight) {
        //构造一个预定义的图像类型的BufferedImage对象。
        BufferedImage buffImg = new BufferedImage(outputWidth, outputHeight,
                BufferedImage.TYPE_INT_RGB);

        //创建Graphics2D对象，用于在BufferedImage对象上绘图。
        Graphics2D g = buffImg.createGraphics();

        //设置图形上下文的当前颜色为白色。
        g.setColor(Color.WHITE);
        //用图形上下文的当前颜色填充指定的矩形区域。
        g.fillRect(0, 0, outputWidth, outputHeight);

        //按照缩放的大小在BufferedImage对象上绘制原始图像。
        g.drawImage(srcImage, 0, 0, outputWidth, outputHeight, null);
        //释放图形上下文使用的系统资源。
        g.dispose();

        return buffImg;
    }
}

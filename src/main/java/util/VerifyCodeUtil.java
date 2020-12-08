package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class VerifyCodeUtil {

    private static final Random random = new Random();
    private static final String[] fontNames = {"宋体", "华文楷体", "黑体", "Georgia", "微软雅黑", "楷体_GB2312"};

    public static String drawImage(ByteArrayOutputStream output) {
        String code = "";
        int width = 50;
        int height = 25;

        //创建图片缓冲区
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g = bi.createGraphics();

        //设置背景颜色
        g.setBackground(new Color(255,250,250));
        g.clearRect(0, 0, width, height);

        StringBuilder stringBuilder = new StringBuilder();
        //这里只画入四个字符
        g.setColor(new Color(0,0,0));         //设置颜色，偏白色
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";      //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
            stringBuilder.append(s);           //添加到StringBuilder里面
            float x = i * 1.0F * width / 4;   //定义字符的x坐标
            g.setFont(randomFont());           //设置字体，随机
            g.drawString(s, x, height - 5);
        }
        code = stringBuilder.toString();//获取验证码字符串

        // 释放图形上下文
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;//为了方便取值，直接返回code，



    }

    //随机字体
    private static Font randomFont() {
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = random.nextInt(4);         //随机获取4种字体的样式
        int size = random.nextInt(20) % 6 + 15;    //随机获取字体的大小(10-20之间的值)
        return new Font(fontName, style, size);
    }


    //随机字符
    private static char randomChar() {
        String str = "0123456789ABCDEFGHJKMNPQRSTUVWXYZ";

        return str.charAt(random.nextInt(str.length()));
    }

}
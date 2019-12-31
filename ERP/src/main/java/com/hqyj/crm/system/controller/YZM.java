package com.hqyj.crm.system.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaoPeng
 * @createDate
 */
@Controller
@RequestMapping(value = "/verifyCodePicController")
public class YZM {

	@RequestMapping("/getVerifyCodePic.do")
	protected void getVerifyCodePic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 创建随机对象
		Random ran = new Random();

		// 写一个方法随机获取颜色
		int r = ran.nextInt(256);
		int b = ran.nextInt(256);
		int g = ran.nextInt(256);

		Color color = new Color(r, b, g);

		// 创建缓存图片：指定宽width=90，高height=30
		int width = 90;
		int height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取画笔对象
		Graphics graphics = image.getGraphics();
		// 设置画笔颜色，并且填充矩形区域
		graphics.setColor(Color.lightGray);// 设置背景为白色
		// 并且设置填充矩形区域
		graphics.fillRect(0, 0, width, height);
		// 从字符数组中随机得到字符
		char[] arr = { 'A', 'B', 'C', 'D', 'J', 'U', 'N', 'G', 'A', 'N', 'O', 'P', 'Q', 'E', 'F', 'G', 'H', '1', '2',
				'3', '4', '5', '6', '9'};
		// 设置一个变量用来存放入画布中的字符，用于待会而验证
		String msg = "";
		// 设置循环,得到四个数
		for (int i = 0; i < 4; i++) {
			// 得到索引
			int index = ran.nextInt(arr.length);
			// 取出一个字符
			char c = arr[index];
			// 设置字体，大小为18，设置字的颜色随机
			graphics.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 19));
			// 设置背景颜色
			graphics.setColor(color);
			// 把字符转成字符串
			// 将每个字符画到图片，位置：5+(i*20), 20
			graphics.drawString(String.valueOf(c), 10 + (i * 20), 20);
			// 每写一个字符就往msg中存一个字符
			msg += String.valueOf(c);
		}
		// 画干扰线8条线，线的位置是随机的，x范围在width之中，y的范围在height之中。
		for (int i = 0; i < 8; i++) {
			graphics.setColor(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
			int x1 = ran.nextInt(width);
			int x2 = ran.nextInt(width);
			int y1 = ran.nextInt(height);
			int y2 = ran.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}
		// 将缓存的图片输出到响应输出流中
		ImageIO.write(image, "jpg", response.getOutputStream());
		// 将图中所画的字符存入session中
		request.getSession().setAttribute("imgeCode", msg.toLowerCase());
	}
}

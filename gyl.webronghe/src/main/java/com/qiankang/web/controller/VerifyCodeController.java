package com.qiankang.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerifyCodeController{
	private static final Logger _log = LoggerFactory.getLogger(VerifyCodeController.class);
	private static final String VerifyCode_Session_Key = "verifyCode";
	// 图片的宽度
	private int WIDTH = 100;
	// 图片的高度
	private int HEIGHT = 40;
	// 验证码上字符数
	private int NUM = 4;

	private char[] seq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	@RequestMapping(value="validateCode", method=RequestMethod.POST)
	public void validateCode(String c, HttpSession session, HttpServletResponse resp){
		String sessionCode = (String)session.getAttribute(VerifyCode_Session_Key);
		try{
			if(sessionCode.equals(c.toUpperCase()))
				resp.getWriter().write("1");
			else
				resp.getWriter().write("0");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "verifyCode", method = RequestMethod.GET)
	public void createVerifyCode(HttpSession session, HttpServletResponse resp){
		byte[] data = randomImage(session);
		resp.setContentType("image/jpeg");
		try{
			OutputStream stream = resp.getOutputStream();
			stream.write(data);
	        stream.flush();
	        stream.close();
		}catch(Exception e){
			_log.error(e.getMessage());
		}
	}
	/**
	 * 生成图片
	 * 
	 * @return
	 */
	private byte[] randomImage(HttpSession session) {
		Random r = new Random();
		// 图片的内存映像
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// 获得画笔对象
		Graphics g = image.getGraphics();
		g.setColor(randomColor(r));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0, 0, 0));

		// 用于存储随机生成的验证码
		StringBuffer number = new StringBuffer();

		// 绘制验证码
		for (int i = 0; i < NUM; i++) {
			g.setColor(randomColor(r));
			int h = (int) ((HEIGHT * 60 / 100) * r.nextDouble() + (HEIGHT * 30 / 100));
			g.setFont(new Font(null, Font.BOLD | Font.ITALIC, h));
			String ch = String.valueOf(seq[r.nextInt(seq.length)]);
			number.append(ch);
			g.drawString(ch, i * WIDTH / NUM * 90 / 100, h);
		}

		// 将验证码打印到控制台方便查看调试，但是当程序真正上线的时候一定要删掉哦
		_log.debug(number.toString());
		// 将验证码放入到session中
		session.setAttribute(VerifyCode_Session_Key, number.toString());

		// 绘制干扰线，这里绘制12条，如果感觉太乱，可以减少点
		for (int i = 0; i <= 12; i++) {
			g.setColor(randomColor(r));
			g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r.nextInt(HEIGHT));

		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(os);

		// 把BufferedImage对象中的图像信息编码后
		// 向创建该对象(encoder)时指定的输出流输出
		try {
			encoder.encode(image);
			return os.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Color randomColor(Random r) {
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

}

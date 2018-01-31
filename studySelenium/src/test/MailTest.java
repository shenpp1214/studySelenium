package test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import baseService.BaseService;

public class MailTest extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower("mail_url");
	}

	@Test
	public void mailTest() throws InterruptedException {
		loginMain();// 登录邮箱
		entryWriteMail();// 进入到写信界面
		startWriteAndSend();// 开始写邮件并发送
		entrySended();// 进入已发送邮件界面校验内容
		logoutMail();// 退出邮箱登录
	}

	private void loginMain() throws InterruptedException {
		dr.findElement(By.id("qquin")).sendKeys("shenpp");
		dr.findElement(By.id("pp")).sendKeys("!QAZ2wsx");
		dr.findElement(By.className("login_btn")).click();
		sleep("//*[@id='composebtn']");
	}

	private void entryWriteMail() throws InterruptedException {
		dr.findElement(By.id("composebtn")).click();
		switchIframe("mainFrame");
		sleep("//div[@id='toAreaCtrl']/div[2]/input");
	}

	private void startWriteAndSend() throws InterruptedException {
		dr.findElement(By.xpath("//div[@id='toAreaCtrl']/div[2]/input")).sendKeys("779230186@qq.com");// 输入收件人
		dr.findElement(By.id("subject")).sendKeys("自动发送邮件测试");// 主题
		dr.switchTo().frame(dr.findElement(By.xpath("//*[@id='QMEditorArea']/table/tbody/tr[2]/td/iframe")));
		sleep("/html/body");

		dr.findElement(By.tagName("body")).sendKeys("hello,最近过的好吗？20年后你找到期望的自己了嘛？");// 输入正文内容
		switchIframe("mainFrame");
		sleep("//input[@name='sendbtn']");

		dr.findElement(By.name("sendbtn")).click();// 点击发送
		switchIframe("mainFrame");
		sleep("//*[@id='sendinfomsg']");

		assertEquals("您的邮件已发送", dr.findElement(By.id("sendinfomsg")).getText());// 校验邮件发送成功
	}

	private void entrySended() throws InterruptedException {
		dr.switchTo().defaultContent();
		dr.findElement(By.linkText("已发送")).click();// 点击已发送进入已发送的邮件列表
		switchIframe("mainFrame");
		sleep("//*[@id='frm']/div[3]/table[1]/tbody/tr/td[2]/div[2]");

		dr.findElement(By.xpath("//*[@id='frm']/div[3]/table[1]/tbody/tr/td[2]/div[2]")).click();// 点击邮件图标打开新窗口查看邮件详情

		String cw = dr.getWindowHandle();
		Set<String> hs = dr.getWindowHandles();
		for (String s : hs) {
			if (s.equals(cw)) {
				continue;
			} else {
				dr.switchTo().window(s);
				switchIframe("mainFrame");
				sleep("//*[@id='mailContentContainer']/div[1]");

				assertEquals("hello,最近过的好吗？20年后你找到期望的自己了嘛？",
						dr.findElement(By.xpath("//*[@id='mailContentContainer']/div[1]")).getText());
			}
		}
		dr.close();// 关闭当前浏览器窗口
		dr.switchTo().window(cw);// 回到第一个窗口下
	}

	private void logoutMail() throws InterruptedException {
		dr.switchTo().defaultContent();
		dr.findElement(By.xpath("//div[@id='SetInfo']/div/a[4]")).click();
		sleep("//*[@id='errorLogout']");// 退出登录

		assertEquals("你已成功退出邮箱", dr.findElement(By.id("errorLogout")).getText());
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

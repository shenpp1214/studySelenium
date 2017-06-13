package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseService.BaseService;

public class MailTest extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower("http://mail.cpsdna.com/");
	}

	@Test
	public void mailTest() throws InterruptedException {
		// 登录邮箱
		dr.findElement(By.id("qquin")).sendKeys("shenpp");
		dr.findElement(By.id("pp")).sendKeys("!QAZ2wsx");
		dr.findElement(By.xpath("//input[@value='登录']")).click();
		Thread.sleep(5000);
		// 点击写信
		dr.findElement(By.linkText("写信")).click();
		switchIframe("mainFrame");
		Thread.sleep(5000);

		dr.findElement(By.xpath("//table[@id='trTO']//div[@class='addr_text']/input")).sendKeys("779230186@qq.com");// 收件人
		dr.findElement(By.id("subject")).sendKeys("test");// 主题
		new WebDriverWait(dr, 30).until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='QMEditorArea']/table/tbody/tr[2]/td/iframe")));

		dr.findElement(By.tagName("body")).sendKeys("测试自动发送邮件");// 输入内容
		switchIframe("mainFrame");
		Thread.sleep(2000);

		dr.findElement(By.name("sendbtn")).click();// 发送
		Thread.sleep(3000);
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

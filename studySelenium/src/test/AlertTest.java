package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import baseService.BaseService;

public class AlertTest extends BaseService {

	@Before
	public void setUp() throws Exception {
		openBrower("http://www.baidu.com");
	}

	@Test
	public void alertTest() throws InterruptedException {
		// 鼠标悬停在百度首页的设置处
		Actions act = new Actions(dr);
		act.clickAndHold(dr.findElement(By.linkText("设置"))).perform();

		// 点击设置下的搜索设置
		dr.findElement(By.className("setpref")).click();
		Thread.sleep(2000);

		// 保存设置
		dr.findElement(By.linkText("保存设置")).click();
		Thread.sleep(2000);

		// 接收弹框
		dr.switchTo().alert().accept();// 点确定
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

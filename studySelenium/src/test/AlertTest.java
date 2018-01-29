package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import baseService.BaseService;

public class AlertTest extends BaseService {

	@Before
	public void setUp() throws Exception {
		openBrower("baidu_url");
	}

	@Test
	public void alertTest() throws InterruptedException {
		Actions ac = new Actions(dr);
		ac.clickAndHold(dr.findElement(By.linkText("设置"))).perform();

		dr.findElement(By.className("setpref")).click();
		Thread.sleep(2000);// 点击设置下的搜索设置

		dr.findElement(By.className("prefpanelgo")).click();
		Thread.sleep(2000);// 保存设置

		dr.switchTo().alert().accept();// 接收弹出框并关闭
		Thread.sleep(2000);

		assertEquals("百度一下", dr.findElement(By.id("su")).getAttribute("value"));
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

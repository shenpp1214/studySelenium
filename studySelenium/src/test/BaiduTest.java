package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import baseService.BaseService;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaiduTest extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower("baidu_url");
	}

	@Test
	public void baiduTest() throws InterruptedException {
		dr.findElement(By.id("kw")).sendKeys("张杰哈");// 输入张杰哈
		dr.findElement(By.id("kw")).sendKeys(Keys.BACK_SPACE);// 键盘按下回格键
		dr.findElement(By.id("su")).sendKeys(Keys.ENTER);// 键盘按下enter键
		Thread.sleep(2000);

		assertEquals("张杰_百度搜索", dr.getTitle());
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}
}

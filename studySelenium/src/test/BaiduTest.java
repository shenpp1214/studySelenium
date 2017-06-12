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
	public void setUp() throws InterruptedException {
		openBrower("http://www.baidu.com");
	}

	@Test
	public void baiduTest() throws InterruptedException {
		dr.findElement(By.id("kw")).sendKeys("张杰哈");
		dr.findElement(By.id("kw")).sendKeys(Keys.BACK_SPACE);// 输入回格删除
		dr.findElement(By.id("su")).sendKeys(Keys.ENTER);// enter键
		Thread.sleep(2000);

		assertEquals("张杰(中国内地流行男歌手)_百度百科",
				dr.findElement(By.xpath("//h3[@class='t']")).findElement(By.tagName("a")).getText());
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}
}
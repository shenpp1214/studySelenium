package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import baseService.BaseService;

public class JSTest extends BaseService {

	@Before
	public void setUp() throws Exception {
		openBrower("http://www.baidu.com");
	}

	@Test
	public void jSTest() throws InterruptedException {
		dr.findElement(By.id("kw")).sendKeys("selenium");
		dr.findElement(By.id("su")).click();
		Thread.sleep(3000);

		((JavascriptExecutor) dr).executeScript("window.scrollTo(100, document.body.scrollHeight);");

		Thread.sleep(2000);
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

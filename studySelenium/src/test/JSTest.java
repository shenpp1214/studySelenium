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
		openBrower("baidu_url");
	}

	@Test
	public void jSTest() throws InterruptedException {
		dr.findElement(By.id("kw")).sendKeys("java");
		dr.findElement(By.id("su")).click();
		sleep("//*[text()='帮助']");

		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);",
				dr.findElement(By.linkText("帮助")));
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

package test;

import static org.junit.Assert.assertEquals;
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
		sleep(3000);

		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);",
				dr.findElement(By.linkText("帮助")));
		sleep(2000);

		assertEquals("帮助", dr.findElement(By.xpath("//span[@id='help']/a[1]")).getText());
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

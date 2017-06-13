package vsp;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import baseService.BaseService;

public class Test002 extends BaseService {

	@BeforeClass
	public static void openBrowerTest01() throws Exception {
		openBrower("http://58.215.50.61:22080/vsp/index.jsp");
	}

	@Before
	public void setUp() throws InterruptedException {
		loginVsp("admin@dina", "000000", "1234");
		dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void test01() throws InterruptedException {
		dr.findElement(By.linkText("商户运营")).click();
		dr.findElement(By.linkText("商城商户管理")).click();
		dr.findElement(By.xpath("//a[@title='编辑']/span")).click();
		sleep(3000);
		dr.findElement(By.xpath("//span[text()='确定']")).click();
		sleep(1000);

		if (isElementExsit(dr, "//span[@class='noty_text']")) {
			assertEquals("成功 修改商户信息成功", dr.findElement(By.className("noty_text")).getText());
		} else {
			fail();
		}
		sleep(1000);
	}

	@After
	public void logoutTest01() throws InterruptedException {
		logout();
	}

	@AfterClass
	public static void tearTown() {
		close();
	}
}

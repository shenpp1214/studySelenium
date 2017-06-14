package vsp;

import org.openqa.selenium.By;

import baseService.BaseService;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test003 extends BaseService {

	@BeforeClass
	public static void openBrowerTest01() throws Exception {
		openBrower("http://58.215.50.61:22080/vsp/index.jsp");
	}

	@Before
	public void setUp() throws InterruptedException {
		loginVsp(props.getProperty("admin"), props.getProperty("adminpwd"), props.getProperty("loginvalidate"));
		dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void checkVsp() throws InterruptedException {
		String num1 = dr.findElement(By.id("hitAlarmCount")).getText();

		dr.findElement(By.className("task_hit")).click();
		Thread.sleep(3000);

		String text = dr.findElement(By.id("pages")).getText();
		String num2 = text.substring(1, text.length() - 3);

		if (num1.equals(num2)) {
			System.out.println("Test is Success!");
		} else {
			fail();
		}
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

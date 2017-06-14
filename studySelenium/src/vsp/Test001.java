package vsp;

import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

import baseService.BaseService;

public class Test001 extends BaseService {

	@BeforeClass
	public static void openBrowerTest01() throws Exception {
		openBrower("vsp_url");
	}

	@Before
	public void setUp() throws InterruptedException {
		loginVsp("vspuser", "vsppwd", "val");
		dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void vspTest() throws InterruptedException {
		assertEquals("个人客户", dr.findElement(By.linkText("个人客户")).getText());// 校验是否登录成功
		dr.findElement(By.linkText("2")).click();
		sleep(3000);

		dr.findElement(By.xpath("//*[@id='mainContentList']/table/tbody/tr[1]/td[11]/a/span")).click();
		sleep(3000);
		dr.findElement(By.xpath("/html/body/div[7]/div[1]/button/span[1]")).click();
		sleep(2000);

		dr.findElement(By.id("searchUserName")).sendKeys("yangjieT001");
		dr.findElement(By.id("searchName")).sendKeys("yangjieT001");
		dr.findElement(By.id("searchPhone")).sendKeys("15699988888");

		Select sel = new Select(dr.findElement(By.id("searchUserSex")));
		sel.selectByIndex(2);
		sleep(2000);

		Select sel1 = new Select(dr.findElement(By.id("searchOwnerType")));
		sel1.selectByVisibleText("VSP");
		sleep(2000);

		dr.findElement(By.id("searchBtn")).click();
		sleep(1000);

		assertEquals("共1条数据", dr.findElement(By.id("pages")).getText());
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

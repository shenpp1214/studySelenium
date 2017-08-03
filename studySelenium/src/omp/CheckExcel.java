package omp;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

import baseService.BaseService;

public class CheckExcel extends BaseService {
	@BeforeClass
	public static void openBrowerCheckExcel() throws Exception {
		openBrower("omp_url");
	}

	@Before
	public void setUp() throws InterruptedException {
		loginOmp();
	}

	@Test
	public void checkExcel() throws Exception {
		Actions act = new Actions(dr);
		act.clickAndHold(dr.findElement(By.xpath("//*[@id='backgroundHolder']/ul/li[2]/a/b"))).perform();// 鼠标悬停到组织机构上

		dr.findElement(By.linkText("企业查询")).click();
		sleep(3000);
		dr.findElement(By.xpath("//input[@value='导  出']")).click();
		sleep(5000);

		assertEquals("企业名", getCellValue("企业查询", 1, 1));
	}

	@After
	public void logoutOmp() {
		// 退出登录
	}

	@AfterClass
	public static void tearDown() {
		close();
	}

}

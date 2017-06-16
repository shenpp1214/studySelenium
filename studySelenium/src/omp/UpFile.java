package omp;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseService.BaseService;

public class UpFile extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower("omp_url");
	}

	@Test
	public void upFile() throws InterruptedException {
		loginOmp();

		Actions act = new Actions(dr);
		act.clickAndHold(dr.findElement(By.xpath("//*[@id='backgroundHolder']/ul/li[6]/a/b"))).perform();// 鼠标悬停

		dr.findElement(By.linkText("入库设备查询")).click();
		Thread.sleep(5000);

		dr.findElement(By.linkText("车辆设备批量导入")).click();
		Thread.sleep(3000);
		dr.findElement(By.id("importFile")).sendKeys(getTemplatePath("omp_temp"));// 获取模板
		dr.findElement(By.id("b_next")).click();// 点击"导入"
		Thread.sleep(1500);

		Alert alert = dr.switchTo().alert();
		alert.accept();// 点击提示框中的确定
		new WebDriverWait(dr, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='errorMessage']//span")));
		sleep(5000);

		assertEquals("导入文件上载失败!", dr.findElement(By.xpath("//ul[@class='errorMessage']//span")).getText());// 校验导入失败
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}
}

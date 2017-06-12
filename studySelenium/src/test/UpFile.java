package test;

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
	public void setUp() throws InterruptedException {
		openBrower("http://58.215.50.61:21080/omp/index");
	}

	@Test
	public void upFile() throws InterruptedException {
		dr.findElement(By.name("username")).sendKeys("shenpp");
		dr.findElement(By.name("password")).sendKeys("000000");
		dr.findElement(By.xpath("//input[@value='登  录']")).click();
		new WebDriverWait(dr, 15).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("main1")));
		Thread.sleep(2000);

		Actions act = new Actions(dr);
		act.clickAndHold(dr.findElement(By.xpath("//*[@id='backgroundHolder']/ul/li[6]/a/b"))).perform();// 鼠标悬停

		dr.findElement(By.linkText("入库设备查询")).click();
		Thread.sleep(5000);

		dr.findElement(By.linkText("车辆设备批量导入")).click();
		Thread.sleep(3000);
		dr.findElement(By.id("importFile")).sendKeys("F:\\shenpp\\测试模板.txt");// 获取模板
		dr.findElement(By.id("b_next")).click();// 点击"导入"
		Thread.sleep(1500);

		Alert alert = dr.switchTo().alert();
		alert.accept();// 点击提示框中的确定
		new WebDriverWait(dr, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='errorMessage']//span")));

		assertEquals("导入文件上载失败!", dr.findElement(By.xpath("//ul[@class='errorMessage']//span")).getText());// 校验导入失败
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import baseService.BaseService;

import static org.junit.Assert.assertEquals;

import java.util.Set;

public class WindowSwitch extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower(props.getProperty("win_url"));
	}

	@Test
	public void windowSwitch() throws InterruptedException {
		String curHandle = dr.getWindowHandle();// 获取当前窗口的句柄
		System.out.println(curHandle);

		dr.findElement(By.xpath("//*[@id='tank_selenium']/p[12]/a")).click();// 点击进入到第二个窗口页面
		Thread.sleep(5000);

		Set<String> handles = dr.getWindowHandles();// 获取当前打开的所有窗口的句柄
		// 判断是否为第二个窗口，并操作第二个窗口的元素
		for (String handle : handles) {
			if (handle.equals(curHandle)) {
				continue;
			}
			dr.switchTo().window(handle);
			System.out.println(handle);
			Thread.sleep(2000);
			dr.findElement(By.xpath("//*[@id='tank_menu']/ol/li[1]/a")).click();
			Thread.sleep(2000);
			dr.close();
		}
		// 判断是否是第一个窗口，并操作第一个窗口的元素
		for (String handle : handles) {
			if (handle.equals(curHandle)) {
				dr.switchTo().window(curHandle);
				Thread.sleep(2000);

				assertEquals("selenium 的命名", dr.findElement(By.id("tank_h1")).getText());
			}
		}
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}

}

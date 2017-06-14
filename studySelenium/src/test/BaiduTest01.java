package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import baseService.BaseService;

public class BaiduTest01 extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower(props.getProperty("baidu_url"));
	}

	public static String searchText(String text) throws InterruptedException {
		dr.findElement(By.id("kw")).sendKeys(text);
		dr.findElement(By.id("su")).click();
		Thread.sleep(2000);

		return dr.getTitle();
	}

	@Test
	public void baiduTest() throws InterruptedException {

		String[] ser_text = new String[3];
		ser_text[0] = "java";
		ser_text[1] = "selenium";
		ser_text[2] = "webdriver";

		for (int i = 0; i < ser_text.length; i++) {
			String text = searchText(ser_text[i]);
			System.out.println(text);
			dr.navigate().back();
			sleep(2000);
		}
	}

	@After
	public void tearDown() {
		dr.close();
	}

}

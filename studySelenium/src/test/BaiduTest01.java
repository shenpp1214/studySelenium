package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import baseService.BaseService;

public class BaiduTest01 extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower("baidu_url");
	}

	public static String searchTest(String text) throws InterruptedException {
		dr.findElement(By.id("kw")).sendKeys(text);
		dr.findElement(By.id("su")).click();
		Thread.sleep(2000);

		return dr.getTitle();
	}

	@Test
	public void baiduTest01() throws InterruptedException {
		String[] search_text = new String[3];
		search_text[0] = "java";
		search_text[1] = "selenium";
		search_text[2] = "webdriver";

		for (int i = 0; i < search_text.length; i++) {
			String aaa = searchTest(search_text[i]);
			System.out.println(aaa);

			dr.navigate().back();
			Thread.sleep(2000);
		}
	}

	@After
	public void tearDown() {
		dr.close();
	}

}

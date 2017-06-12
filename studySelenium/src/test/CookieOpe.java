package test;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;

import baseService.BaseService;

public class CookieOpe extends BaseService {
	@Before
	public void setUp() throws InterruptedException {
		openBrower("http://www.baidu.com");
	}

	@Test
	public void cookieOpe() {
		Cookie c1 = new Cookie("name1", "shenpp");
		Cookie c2 = new Cookie("name2", "panhao");
		// 添加cookis
		dr.manage().addCookie(c1);
		dr.manage().addCookie(c2);

		// 删除所有的cookis
		// dr.manage().deleteAllCookies();

		// 获得cookis
		Set<Cookie> coo = dr.manage().getCookies();
		System.out.println(coo);

		dr.manage().deleteCookie(c1);
		dr.manage().deleteCookieNamed("name2");

		Set<Cookie> coo1 = dr.manage().getCookies();
		System.out.println(coo1);
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}
}

package test;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import baseService.BaseService;

public class CookieOpe extends BaseService {
	@Before
	public void setUp() throws Exception {
		openBrower("baidu_url");
	}

	@Test
	public void cookieOpe() throws InterruptedException {
		Cookie c1 = new Cookie("name1", "shenpp");
		Cookie c2 = new Cookie("name2", "panhao");

		dr.manage().deleteAllCookies();
		getAndSysout();

		dr.manage().addCookie(c1);
		dr.manage().addCookie(c2);
		getAndSysout();

		dr.manage().deleteCookie(c2);
		getAndSysout();

		dr.manage().deleteCookieNamed("name1");
		getAndSysout();
	}

	private void getAndSysout() throws InterruptedException {
		Set<Cookie> coo = dr.manage().getCookies();
		System.out.println(coo);
	}

	@After
	public void tearTown() throws InterruptedException {
		close();
	}
}

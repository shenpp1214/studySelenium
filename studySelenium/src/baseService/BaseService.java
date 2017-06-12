package baseService;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class BaseService {
	protected static WebDriver dr;

	public static void openBrower(String url) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		dr.get(url);
	}

	public static void close() {
		dr.close();
	}

	public void switchIframe(String mainFrame) {
		dr.switchTo().defaultContent();
		WebElement frame = dr.findElement(By.name(mainFrame));
		dr.switchTo().frame(frame);
	}

	public void loginVsp(String user, String pwd, String val) throws InterruptedException {
		dr.findElement(By.id("loginemail")).sendKeys(user);// 输入用户名
		dr.findElement(By.id("loginpassword")).sendKeys(pwd);// 输入密码
		dr.findElement(By.id("loginvalidate")).sendKeys(val);// 输入验证码
		dr.findElement(By.id("loginbtn")).click();// 登录
		sleep(8000);
	}

	public void logout() throws InterruptedException {
		dr.switchTo().defaultContent();
		dr.findElement(By.id("logout")).click();
		sleep(3000);
		assertEquals("用户登录", dr.findElement(By.className("border-bottom")).getText());
	}

	public static void sleep(int num) throws InterruptedException {
		Thread.sleep(num);
	}

	public boolean isElementExsit(WebDriver driver, String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}

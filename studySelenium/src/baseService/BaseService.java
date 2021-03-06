package baseService;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseService.poi.SeleniumExcel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;
import baseService.PropertiesReader;

public class BaseService {
	protected static WebDriver dr;
	protected static Properties props;
	protected static SeleniumExcel excel;

	public static void openBrower(String url) throws Exception {
		props = new PropertiesReader().load();

		System.setProperty("webdriver.chrome.driver", props.getProperty("driver_path"));

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory",
				System.getProperty("user.dir") + System.getProperty("file.separator") + "temp");// 指定chrome下载文件路径

		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", chromePrefs);
		dr = new ChromeDriver(option);
		dr.manage().window().maximize();
		dr.get(props.getProperty(url));
	}

	public static void close() {
		dr.close();
	}

	public void switchIframe(String mainFrame) {
		dr.switchTo().defaultContent();
		WebElement frame = dr.findElement(By.name(mainFrame));
		dr.switchTo().frame(frame);
	}

	public void loginOmp() throws InterruptedException {
		dr.findElement(By.name("username")).sendKeys(props.getProperty("ompuser"));
		dr.findElement(By.name("password")).sendKeys(props.getProperty("omppwd"));
		dr.findElement(By.xpath("//input[@value='登  录']")).click();

		new WebDriverWait(dr, 15).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("main1")));
		sleep(2000);
	}

	public void logout() throws InterruptedException {
		dr.switchTo().defaultContent();
		dr.findElement(By.id("logout")).click();
		sleep(3000);

		assertEquals("用户登录", dr.findElement(By.className("border-bottom")).getText());
	}

	public static void sleep(int seconds) throws InterruptedException {
		Thread.sleep(seconds);
	}

	public boolean isElementExsit(WebDriver driver, String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected String getTemplatePath(String key) {
		return System.getProperty("user.dir") + System.getProperty("file.separator") + "template"
				+ System.getProperty("file.separator") + props.getProperty(key);
	}

	/**
	 * 
	 * @param sheetName
	 *            sheet名字
	 * @param row
	 *            行号
	 * @param column
	 *            列号
	 * @return
	 * @throws Exception
	 */
	protected static String getCellValue(String sheetName, int row, int column) throws Exception {
		String templateDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "temp"
				+ System.getProperty("file.separator");
		File[] files = new File(templateDir).listFiles();
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File file1, File file2) {
				return (int) (file2.lastModified() - file1.lastModified());
			}
		});

		excel = new SeleniumExcel(files[0], sheetName);
		return excel.getCellValue(row - 1, column - 1);
	}
}

package omp.user;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import baseService.BaseService;

public class GetUsersForward extends BaseService {
	@BeforeClass
	public static void openBrowerGetUser() throws Exception {
		openBrower("omp_url");
	}

	@Before
	public void setUp() throws InterruptedException {
		loginOmp();
	}

	@Test
	public void getUser() throws Exception {
		entryPage();// 进入页面
		register();// 用户注册
		queryByCondition();// 查询已新增的用户
		exportExcel();// 校验导出的excel中的数据
		resetPwd();// 密码重置
		deleteUser();// 删除用户
	}

	private void entryPage() throws InterruptedException {
		Actions act = new Actions(dr);
		act.clickAndHold(dr.findElement(By.linkText("用户管理"))).perform();// 将鼠标悬停到用户管理上
		dr.findElement(By.linkText("用户查询")).click();
		sleep(2000);
	}

	private void register() throws InterruptedException {
		dr.findElement(By.linkText("用户注册")).click();
		sleep(2000);

		clickSure();// 直接点确定
		assertEquals("请输入用户名!", dr.findElement(By.xpath("//label[@for='username']")).getText());
		assertEquals("请输入移动电话!", dr.findElement(By.xpath("//label[@for='mobilephone']")).getText());
		assertEquals("请输入邮箱地址!", dr.findElement(By.xpath("//label[@for='email']")).getText());

		sendkey("username", "￥1Qq是￥1Qq是");
		clickSure();
		assertEquals("请输入正确的用户名格式(包含字母,数字,小数点,下划线和@,不能少于4位)",
				dr.findElement(By.xpath("//label[@for='username']")).getText());

		clear("username");
		sendkey("username", "a1._@a1._@");
		sendkey("mobilephone", "1234567890");
		sendkey("email", "241312ads@安德森达.持续性");
		selectOption("provinceId", "江苏省");
		clickSure();
		getAlert("必须选择一个运营商!");

		selectOption("operatorCorpId", "迪纳运营");
		selectOption("operatorDeptId", "迪纳授权店");
		clickSure();
		assertEquals("用户注册成功！", dr.findElement(By.xpath("//ul[@class='actionMessage']//span")).getText());
	}

	private void queryByCondition() throws InterruptedException {
		String username = dr.findElement(By.xpath("//div[@id='datas']/following-sibling::table/tbody/tr[1]/td[1]"))
				.getText();
		String mail = dr.findElement(By.xpath("//div[@id='datas']/following-sibling::table/tbody/tr[1]/td[7]"))
				.getText();
		String realname = dr.findElement(By.xpath("//div[@id='datas']/following-sibling::table/tbody/tr[1]/td[4]"))
				.getText();
		String tel = dr.findElement(By.xpath("//div[@id='datas']/following-sibling::table/tbody/tr[1]/td[5]"))
				.getText();
		String carriersName = dr.findElement(By.xpath("//div[@id='datas']/following-sibling::table/tbody/tr[1]/td[8]"))
				.getText();

		assertEquals(username, dr.findElement(By.id("username")).getAttribute("value"));
		assertEquals(mail, dr.findElement(By.id("email")).getAttribute("value"));
		assertEquals(realname, dr.findElement(By.id("realname")).getAttribute("value"));
		assertEquals(tel, dr.findElement(By.id("mobilephone")).getAttribute("value"));
		assertEquals(true, dr.findElement(By.id("corpNames")).isDisplayed());
		assertEquals(true, dr.findElement(By.id("deptId")).isDisplayed());
		assertEquals("江苏省南京市",
				dr.findElement(By.xpath("//div[@id='datas']/following-sibling::table/tbody/tr[1]/td[10]")).getText());
		assertEquals("a1._@a1._@", username);
		assertEquals("241312ads@安德森达.持续性", mail);
		assertEquals("迪纳运营", carriersName);
		assertEquals("disabled", dr.findElement(By.xpath("//form[@id='qPagerForm']/span[1]")).getAttribute("class"));
		assertEquals("disabled", dr.findElement(By.xpath("//form[@id='qPagerForm']/span[3]")).getAttribute("class"));
		sleep(2000);
	}

	private void exportExcel() throws Exception {
		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		String realtime = getCellValue("用户查询", 2, 6);
		String realdate = realtime.substring(0, 10);
		dr.findElement(By.id("exportbutton")).click();
		sleep(2000);

		assertEquals("a1._@a1._@", getCellValue("用户查询", 2, 1));
		assertEquals("a1._@a1._@", getCellValue("用户查询", 2, 4));
		assertEquals("1234567890", getCellValue("用户查询", 2, 5));
		assertEquals("241312ads@安德森达.持续性", getCellValue("用户查询", 2, 7));
		assertEquals("迪纳运营", getCellValue("用户查询", 2, 8));
		assertEquals("迪纳授权店", getCellValue("用户查询", 2, 9));
		assertEquals("江苏省南京市", getCellValue("用户查询", 2, 10));
		assertEquals(date, realdate);
	}

	private void resetPwd() throws InterruptedException {
		dr.findElement(By.linkText("密码重置")).click();
		sleep(1500);
		getAlert("您确定要重置该用户密码吗?");
		getAlert("操作成功");
	}

	private void deleteUser() throws InterruptedException {
		dr.findElement(By.linkText("删除")).click();
		sleep(1500);
		getAlert("您确定要删除这个用户吗?");
		getAlert("删除用户成功!");
		checkDelUser();
	}

	private void checkDelUser() throws InterruptedException {
		sendkey("username", "a1._@a1._@");
		sendkey("email", "241312ads@安德森达.持续性");
		query();

		assertEquals("无数据显示",
				dr.findElement(By.xpath("//div[@id='datas']/following-sibling::table/tbody/tr[1]/td")).getText());
	}

	private void getAlert(String text) throws InterruptedException {
		Alert alert = dr.switchTo().alert();
		assertEquals(text, alert.getText());
		alert.accept();
		sleep(1000);
	}

	private void clear(String id) throws InterruptedException {
		dr.findElement(By.id(id)).clear();
		sleep(1500);
	}

	private void clickSure() throws InterruptedException {
		dr.findElement(By.xpath("//input[@value='确  定']")).click();
		sleep(1500);
	}

	private void sendkey(String id, String text) throws InterruptedException {
		dr.findElement(By.id(id)).sendKeys(text);
		sleep(1000);
	}

	private void selectOption(String id, String option) throws InterruptedException {
		Select sel = new Select(dr.findElement(By.id(id)));
		sel.selectByVisibleText(option);
		sleep(2000);
	}

	private void query() throws InterruptedException {
		dr.findElement(By.id("querybutton")).click();
		sleep(5000);// 直接点击查询按钮
	}

	@After
	public void logoutGetUsersForward() throws InterruptedException {
		logoutOmp();
	}

	@AfterClass
	public static void tearDown() {
		close();
	}

}

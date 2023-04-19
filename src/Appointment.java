import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class Appointment {
//พอเสร็จเสร็จต้อง Save All ทุกครั้งเพื่อไม่ให้ขึ้นแดง
	@Test
	void TC1001MakeAppointment_Success() throws Exception {
		WebDriver driver = null;//ตอนสร้าง JUnit หรือ pom.xml เสร็จ ต้อง Save All ก่อนถึงจะ Import ให้เส้นใต้สีแดงหาย

		System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://katalon-demo-cura.herokuapp.com/");

		// Page1 Make Appointment
		driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();

		// Page2 Login
		driver.findElement(By.xpath("//*[@id=\"txt-username\"]")).sendKeys("John Doe");
		driver.findElement(By.xpath("//*[@id=\"txt-password\"]")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();

		// Page3 Make Appointment Detail
		Select option1 = new Select(driver.findElement(By.xpath("//*[@id=\"combo_facility\"]")));
		option1.selectByVisibleText("Hongkong CURA Healthcare Center");
		driver.findElement(By.xpath("//*[@id=\"chk_hospotal_readmission\"]")).click();
		WebElement radio1 = driver.findElement(By.xpath("//*[@id=\"radio_program_medicaid\"]"));//ต้องลบ div id=searchCountPages ออกก่อนถึงจะรันได้//*[@id=\"radio_program_none\"]
		radio1.click();
		driver.findElement(By.xpath("//*[@id=\"txt_visit_date\"]")).sendKeys("25/01/2023");
		driver.findElement(By.xpath("//*[@id=\"txt_comment\"]")).sendKeys("Nawaporn Boongon");
		driver.findElement(By.xpath("//*[@id=\"btn-book-appointment\"]")).click();


		String result = driver.findElement(By.id("comment")).getText();

		assertEquals("Nawaporn Boongon", result);
		
		String title = driver.getTitle();
		System.out.println(title);

		Thread.sleep(5000);
		driver.quit();
	}

}

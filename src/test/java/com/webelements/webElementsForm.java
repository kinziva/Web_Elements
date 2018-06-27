package com.webelements;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;

/* Find all input boxes and assign to List of webelements -> 2
* Find all drop down boxes and assign to another List of webelements -> 3
* Find all check boxes and assign to another List of webelements -> 9
* Find all radio boxes and assign to another List of webelements -> 9
* Find all buttons and assign to another List of webelements -> 1
* assert each one’s count
*/

/* Homework:
         *     Loop through each inputbox and enter random names
         *  Loop through each dropDown and randomly select by index
         *  Loop through each checkBoxes and select each one
         *  Loop through each radioButton and click one by one by waiting one second intervals
         *  click all buttons
         */
public class webElementsForm {

	WebDriver driver;
	Faker faker;
	String name;
	List<WebElement> links;
	List<WebElement> dropdowns;
	List<WebElement> checkboxes;
	List<WebElement> radio;
	List<WebElement> buttons;
	Random random;

	@BeforeClass
	public void setUpClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		faker = new Faker();
		random = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@BeforeMethod
	public void navigateHomePage() {
		driver.get(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		links = driver.findElements(By.xpath("//input[@type='text']"));
		dropdowns = driver.findElements(By.tagName("select"));
		checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		radio = driver.findElements(By.xpath("//input[@type='radio']"));
		buttons = driver.findElements(By.tagName("button"));
	}

	@Test(priority = 6)
	public void Fill() throws InterruptedException {

		for (WebElement box : links) {
			name = faker.name().firstName() + " " + faker.name().lastName();
			box.sendKeys(name);
		}

		for (WebElement dropdown : dropdowns) {
			Select list = new Select(dropdown);
			list.selectByIndex(random.nextInt(2) + 1);
		}
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}

		for (WebElement each : radio) {
			each.click();
			Thread.sleep(1000);
		}

		for (WebElement button : buttons) {
			button.click();
		}
	}

	@Test(priority = 1)
	public void testInput() {
		System.out.println("input boxes " + links.size());
		Assert.assertEquals(links.size(), 2);
	}

	@Test(priority = 2)
	public void testDropdowns() {
		System.out.println("dropdowns " + dropdowns.size());
		Assert.assertEquals(dropdowns.size(), 3);
	}

	@Test(priority = 3)
	public void testCheckbox() {
		System.out.println("checkboxes " + checkboxes.size());
		Assert.assertEquals(checkboxes.size(), 9);
	}

	@Test(priority = 4)
	public void testRadiobox() {
		System.out.println("radio " + radio.size());
		Assert.assertEquals(radio.size(), 9);
	}

	@Test(priority = 5)
	public void buttons() {
		System.out.println("buttons " + buttons.size());
		Assert.assertEquals(buttons.size(), 1);
	}
}
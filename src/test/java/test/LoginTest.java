package test;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	WebDriver driver;

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {
		// landingpage(Homepage) code
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropDown().click();
		landingpage.loginOption().click();

		Thread.sleep(3000);

		// loginpage code
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailfield().sendKeys(email);
		loginpage.passwordfield().sendKeys(password);
		loginpage.loginButton().click();

		// Accountpage code to verify account edit account option
		AccountPage accountpage = new AccountPage(driver);

		String actualResult;
		try {
			if (accountpage.editYourAccountInformationOption().isDisplayed())
				;
			actualResult = "Successfull";

		} catch (Exception e) {
			actualResult = "Failure";

		}

		Assert.assertEquals(actualResult, expectedResult);

	}

	@BeforeMethod
	public void openAnApplication() throws IOException {

		driver = initialiseBrowser();
		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void closure() {
		driver.close();
	}

	// Code for data driven testing
	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = { { "sutardattatray123@gmail.com", "Sutar@123", "Successfull" },
				{ "sutardattatray@gmail.com", "Sutar1234", "Failure" } };
		return data;
	}
}

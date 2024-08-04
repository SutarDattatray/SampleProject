package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	WebDriver driver;
	public Properties prop;

	public WebDriver initialiseBrowser() throws IOException {

		// for loading properties from propeties file
		prop = new Properties();

		String propath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";

		FileInputStream fis = new FileInputStream(propath);

		prop.load(fis);

		// Code for initialize browser

		String BrowserName = prop.getProperty("browser");

		if (BrowserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (BrowserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();

		} else if (BrowserName.equalsIgnoreCase("IE")) {

			WebDriverManager.chromedriver().setup();
			driver = new InternetExplorerDriver();

		} else if (BrowserName.equalsIgnoreCase("Safari")) {

			WebDriverManager.chromedriver().setup();
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

}

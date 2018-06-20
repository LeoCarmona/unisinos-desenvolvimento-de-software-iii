package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


/**
 * savana
 * 
 * Classe para criar o WebDriver, que manipula o navegador.
 * O navegador a ser manipulado � especificado, pois cada tipo utiliza um 
 * driver diferente na execu��o.
 *
 */
public class WebDriverFactoryInstance {

	private static final WebDriverFactoryInstance INSTANCE = new WebDriverFactoryInstance();

	//final String chromeBinary = "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe";
	final String chromeDriverFilePath = System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe";
	
	//final String ieDriverServerFilePath = "C:/Java/drivers/IEDriverServer.exe";
	//final String firefoxGeckodriverFilePath = "C:/Program Files (x86)/Mozilla Firefox/geckodriver.exe";

	private WebDriverFactoryInstance() {

	}

	public static final WebDriverFactoryInstance getInstance() {
		return INSTANCE;
	}

	public WebDriver createConfigurationsWebDriver() {
		return setGoogleChromeFactoryDriver();
	}
	
	private WebDriver setGoogleChromeFactoryDriver() {
		System.setProperty("webdriver.chrome.driver", chromeDriverFilePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(options);

		return driver;
	}
	
	//TODO ver sobre os outros navegadores

//	private WebDriver firefoxFactoryDriver() {
//		System.setProperty("webdriver.gecko.driver", geckodriver);
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setCapability("marionette", true);
//		WebDriver driver = new FirefoxDriver(capabilities);
//		return driver;
//	}
//
//	private WebDriver internetExplorerDriverFactory() {
//		File file = new File(ieDriverServer);
//		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		WebDriver driver = new InternetExplorerDriver(capabilities);
//		return driver;
//	}

}

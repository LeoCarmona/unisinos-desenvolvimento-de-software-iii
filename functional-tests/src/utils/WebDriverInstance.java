package utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import testCases.LoginTestCase;

import java.util.concurrent.TimeUnit;

/**
 * savana
 */
public class WebDriverInstance {

    protected static WebDriver driver  = null;
    protected static String    baseUrl = System.getProperty("baseUrl",
            "file:///C:/Users/Leonardo%20Carmona/Desktop/Dev%20III/unisinos-desenvolvimento-de-software-iii/ecommerce/index.html");

    static LoginTestCase loginTestCase = PageFactory.initElements(driver, LoginTestCase.class);

    @Before
    public void setUp() throws Exception {
        driver = WebDriverFactoryInstance.getInstance().createConfigurationsWebDriver();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.get(baseUrl);
//        driver.manage().window().maximize();
        //loginTestCase.login();
    }

    @After
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {

        }
    }

//	@AfterClass
//	public static void tearDown() throws Exception {
//		driver.close();
//		driver.quit();
//	}

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

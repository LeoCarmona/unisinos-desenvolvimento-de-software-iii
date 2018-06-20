package testCases;

import objects.LoginObjs;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverInstance;

/**
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTestCase extends WebDriverInstance {

    LoginObjs loginObjs = PageFactory.initElements(driver, LoginObjs.class);

    @Test
    public void login() {
        this.login("leocarmona_@outlook.com", "123");

        Assert.assertEquals("Logout (leocarmona_@outlook.com)", loginObjs.btnLogout(driver).getAttribute("innerText"));
    }

    @Test
    public void logout() {
        this.login("leocarmona_@outlook.com", "123");

        try {
            loginObjs.btnLogout(driver);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private void login(String email, String password) {
        sleep(1000);
        loginObjs.btnLogin(driver).click();
        sleep(1000);
        loginObjs.campoUsuario(driver).sendKeys(email);
        loginObjs.campoSenha(driver).sendKeys(password);
        loginObjs.btnLogar(driver).click();
        sleep(2000);
    }

}

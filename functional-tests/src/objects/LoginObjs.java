package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * savana
 */
public class LoginObjs {

	public WebElement campoUsuario(WebDriver driver) {
		String login = "customer.login.email";
		return driver.findElement(By.id(login));
	}
	
	public WebElement campoSenha(WebDriver driver) {
		String id = "customer.login.password";
		return driver.findElement(By.id(id));
	}
	
	public WebElement btnLogar(WebDriver driver) {
		String id = "customer.login.submit";
		return driver.findElement(By.id(id));
	}
	
	public WebElement btnLogin(WebDriver driver) {
		String id = "header.login";
		return driver.findElement(By.id(id));
	}

	public WebElement btnLogout(WebDriver driver) {
		return driver.findElement(By.id("header.logout"));
	}
}

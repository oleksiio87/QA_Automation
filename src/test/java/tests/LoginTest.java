package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static final String URL_SITE = "https://secure.filelocker.com";
    public static final String LOGIN_FIELD = "#txtLogin";
    public static final String LOGIN = "minchekov160@hotmail.com";
    public static final String PASSWORD_FIELD = "#txtPassword";
    public static final String PASSWORD = "Qw1111";
    public static final String LOGIN_BUTTON = "#loginBtnSecText";
    public static final String USER_NAME = "Test Pass";
    public static final String INVALID_CREDENTIALS_MESSAGE = "Unable to verify credentials";
    public static final String INVALID_PASSWORD = "test";
    public static final String INVALID_LOGIN = "test";
    public WebDriver driver;

    @BeforeMethod
    public void startBrowser() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Browser\\chromedriver.exe");
        driver = new FirefoxDriver();
        driver.get(URL_SITE);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void canLoginWithValidCredentials() {
        driver.findElement(By.cssSelector(LOGIN_FIELD)).sendKeys(LOGIN);
        driver.findElement(By.cssSelector(PASSWORD_FIELD)).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(LOGIN_BUTTON)).click();
        Assert.assertTrue(driver.getPageSource().contains(USER_NAME));
    }

    @Test
    public void cannotLoginWithInvalidCredentials() {
        driver.findElement(By.cssSelector(LOGIN_FIELD)).sendKeys(INVALID_LOGIN);
        driver.findElement(By.cssSelector(PASSWORD_FIELD)).sendKeys(INVALID_PASSWORD);
        driver.findElement(By.cssSelector(LOGIN_BUTTON)).click();
        Assert.assertTrue(driver.getPageSource().contains(INVALID_CREDENTIALS_MESSAGE));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
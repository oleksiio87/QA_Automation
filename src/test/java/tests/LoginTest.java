package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static final String URL_SITE = "https://secure.filelocker.com";

    public static final String CSS_LOGIN_FIELD = "#txtLogin";
    public static final String CSS_LOGIN_BUTTON = "#loginBtnSecText";
    public static final String LOGIN_NAME = "minchekov160@hotmail.com";

    public static final String CSS_PASSWORD_FIELD = "#txtPassword";
    public static final String PASSWORD = "Qw1111";

    public static final String CSS_INVALID_CREDENTIALS_MESSAGE = "#alertBox > div:nth-child(1) > center:nth-child(1)";
    public static final String INVALID_PASSWORD = "test";
    public static final String INVALID_LOGIN = "test";

    public static final String CSS_USERNAME_NAME = ".name > a:nth-child(1)";

    public WebDriver driver;

    @BeforeClass
    public void startBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openSite() {
        driver.get(URL_SITE);
    }

    @Test
    public void canLoginWithValidCredentials() {
        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(LOGIN_NAME);
        driver.findElement(By.cssSelector(CSS_PASSWORD_FIELD)).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(CSS_LOGIN_BUTTON)).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(CSS_USERNAME_NAME)).getText(), "Test Pass");
    }

    @Test
    public void cannotLoginWithInvalidCredentials() {
        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(INVALID_LOGIN);
        driver.findElement(By.cssSelector(CSS_PASSWORD_FIELD)).sendKeys(INVALID_PASSWORD);
        driver.findElement(By.cssSelector(CSS_LOGIN_BUTTON)).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(CSS_INVALID_CREDENTIALS_MESSAGE)).getText(), "Unable to verify credentials");
    }

    @AfterMethod
    public void deleteCoockies() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
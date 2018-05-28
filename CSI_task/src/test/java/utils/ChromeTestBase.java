package utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
Класс с обвязками для работы тестов.
 */
public class ChromeTestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void TearDown() {
        driver.quit();
        driver = null;
    }
}

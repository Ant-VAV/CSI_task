package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
Базовый класс для всех страничек.
 */
public class Page {
    protected WebDriverWait wait;
    private WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    protected void Open(String Url) {
        driver.get(Url);
    }
}

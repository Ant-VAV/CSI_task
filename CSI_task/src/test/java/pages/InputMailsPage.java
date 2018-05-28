package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
Страница входящих писем.
 */
public class InputMailsPage extends Page {

    private WebDriver driver;

    @FindBy(css = "#PH_logoutLink")
    private WebElement logoutLink;

    @FindAll(@FindBy(css = ".b-datalist__item"))
    private List<WebElement> emailItems;

    public InputMailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /*
    Метод получился мерзким.
    По-хорошему, необходимо создать какой-то объект вида EmailItem, который представляет строку в списке входящих писем.
    Но как написать работу с таким классом - не придумалось.
     */
    public EmailPage openEmailBySubject(String expectedSubject) {
        for (WebElement item : this.emailItems) {
            WebElement oneEmailObject = item.findElement(By.cssSelector(".b-datalist__item__link"));
            String emailSubject = oneEmailObject.getAttribute("data-subject").trim();
            if (expectedSubject.trim().equals(emailSubject)) {
                oneEmailObject.click();
                // Описание этой конструкции надо искать в MainPage.java в методе Login.
                String mailTitleToWait = "Почта Mail.Ru";
                wait.until(titleIs(mailTitleToWait));

                return new EmailPage(driver);
            }
        }
        throw new Error(String.format("В списке нет письма с темой '%1$s'", expectedSubject));
    }

    public MainPage Logout() {
        this.logoutLink.click();
        return new MainPage(driver);
    }
}

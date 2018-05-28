package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
Главная страница восхитительного почтового сервиса.
 */
public class MainPage extends Page {

    private WebDriver driver;

    @FindBy(css = "[id=\"mailbox:login\"]")
    private WebElement MailLogin;

    @FindBy(css = "[id=\"mailbox:password\"]")
    private WebElement MailPassword;

    @FindBy(css = "[id=\"mailbox:submit\"]")
    private WebElement MailSubmitButton;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public InputMailsPage Login(String login, String password) {
        this.MailLogin.sendKeys(login);
        this.MailPassword.sendKeys(password);
        this.MailSubmitButton.click();

        // В теории перед каждым действием Селениум проверят, что страница загрузилась.
        // Однако это не срабатвает для ссылки разлогина.
        // Поэтому пришлось прописать явное ожидание.
        // Аналогично сделано в классе InputMailPage.java
        String mailTitleToWait = "Входящие - Почта Mail.Ru";
        wait.until(titleIs(mailTitleToWait));

        return new InputMailsPage(driver);
    }

    /*
    Логика такая: если мы разлогинились, то инпут для пароля должен присутствовать на странице.
     */
    public Boolean isLoggedOut() {
        return this.MailPassword.isDisplayed();
    }

    public void Open() {
        super.Open("https://mail.ru/");
    }
}

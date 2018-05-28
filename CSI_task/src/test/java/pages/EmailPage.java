package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
Страница открытого письма.
Отдельный класс - т.к. при открытии письма меняется url.
 */
public class EmailPage extends Page {

    private WebDriver driver;

    @FindBy(css = ".b-letter__head__subj__text")
    private WebElement subjectText;

    @FindBy(css = ".b-contact-informer-target")
    private WebElement contact;

    @FindBy(css = ".js-readmsg-msg")
    private WebElement mailContent;

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    public String getMailSubject() {
        return this.subjectText.getText();
    }

    public String getContactName() {
        return this.contact.getAttribute("data-contact-informer-name");
    }

    public String getContactEmail() {
        return this.contact.getAttribute("data-contact-informer-email");
    }

    public String getMailContent() {
        return mailContent.getText();
    }
}

package tests;

import constants.MailConstants;
import org.junit.Before;
import org.junit.Test;
import pages.EmailPage;
import pages.InputMailsPage;
import pages.MainPage;
import utils.ChromeTestBase;

public class MailTest extends ChromeTestBase {
    private InputMailsPage inputMails;

    @Before
    public void Login() {
        MainPage main = new MainPage(driver);
        main.Open();
        inputMails = main.Login(MailConstants.mailLogin, MailConstants.mailPassword);
    }

    @Test
    /*
    Тест на разлогин вынесен отдельно, т.к. это отдельный законченный сценарий.
     */
    public void LoginLogoutTest() {
        MainPage mainAfterLogout = inputMails.Logout();
        assert mainAfterLogout.isLoggedOut()
                : "По какой-то причине не смогли разлогиниться.";
    }

    @Test
    public void FindAndCheckMailTest() {
        String expectedSubject = "Тема";
        EmailPage openedMail = inputMails.openEmailBySubject(expectedSubject);

        String actualMail = openedMail.getContactEmail();
        String expectedMail = MailConstants.mailAddress;
        assert actualMail.equals(expectedMail) :
                String.format("Адрес отправителя не тот. Ожидали '%1$s', получили '%2$s'", expectedMail, actualMail);

        String actualSubject = openedMail.getMailSubject();
        assert actualSubject.equals(expectedSubject) :
                String.format("Тема письма не та. Ожидали '%1$s', получили '%2$s'", expectedSubject, actualSubject);

        String actualMailContent = openedMail.getMailContent();
        String expectedMailContent = "Содержание.\n" +
                "\n" +
                "--\n" +
                "Doctor Who";
        assert actualMailContent.equals(expectedMailContent) :
                String.format("Содержимое письма не то. Ожидали '%1$s', получили '%2$s'", expectedMailContent, actualMailContent);
    }
}

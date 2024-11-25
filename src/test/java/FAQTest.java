import util.Answers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import page.HomePage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQTest {

    public WebDriver driver;

    @Parameterized.Parameter()
    public String browser;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"chrome"}, {"firefox"}});
    }

    @Before
    public void initBrowser() {
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }
    }

    @After
    public void close() {
        driver.quit();
    }


    @Test
    public void checkFAQ() {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage homePage = new HomePage(driver);
        //Скрол до конца страницы
        jsDriver.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        String FAQ_ERROR_MESSAGE = "Ответ не соответствует ожидаемому результату или кнопка не найдена";

        //Раскрываем каждый вопрос и проверяем сходится ли ответ со страницы с ожидаемым значением
        String payAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_pay(), Answers.PAY_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.PAY_ANSWER, payAnswer);

        String scootersAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_scooters(), Answers.SCOOTERS_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.SCOOTERS_ANSWER, scootersAnswer);

        String rentAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_rent(), Answers.RENT_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.RENT_ANSWER, rentAnswer);

        String todayAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_today(), Answers.TODAY_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.TODAY_ANSWER, todayAnswer);

        String extendAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_extend(), Answers.EXTEND_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.EXTEND_ANSWER, extendAnswer);

        String chargeAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_charge(), Answers.CHARGE_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.CHARGE_ANSWER, chargeAnswer);

        String cancelAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_cancel(), Answers.CANCEL_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.CANCEL_ANSWER, cancelAnswer);

        String regionAnswer = homePage.getFAQAnswer(homePage.getButtonFAQ_region(), Answers.REGION_ANSWER);
        assertEquals(FAQ_ERROR_MESSAGE, Answers.REGION_ANSWER, regionAnswer);
    }

}

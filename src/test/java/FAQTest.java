import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.HomePage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static util.PageText.*;

@RunWith(Parameterized.class)
public class FAQTest {

    public WebDriver driver;
    public JavascriptExecutor jsDriver;

    @Parameterized.Parameter()
    public String buttonText;
    @Parameterized.Parameter(value = 1)
    public String answerText;


    @Before
    public void initBrowser() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        jsDriver = (JavascriptExecutor) driver;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {PAY_BUTTON, PAY_ANSWER},
                {SCOOTERS_BUTTON, SCOOTERS_ANSWER},
                {RENT_BUTTON, RENT_ANSWER},
                {TODAY_BUTTON, TODAY_ANSWER},
                {EXTEND_BUTTON, EXTEND_ANSWER},
                {CHARGE_BUTTON, CHARGE_ANSWER},
                {CANCEL_BUTTON, CANCEL_ANSWER},
                {REGION_BUTTON, REGION_ANSWER}
        });
    }

    @After
    public void close() {
        driver.quit();
    }


    @Test
    public void checkFAQ() {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage homePage = new HomePage(driver);
        //Скрол до конца страницы
        jsDriver.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        //Раскрываем каждый вопрос и проверяем сходится ли ответ со страницы с ожидаемым значением
        String answerFromPage = homePage.getFAQAnswer(buttonText, answerText);
        assertEquals(FAQ_ERROR_MESSAGE, answerText, answerFromPage);

    }

}

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import page.FinalOrderPage;
import page.HomePage;
import page.OderPage;
import page.OrderInfoPage;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    @After
    public void close() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome", "Тест", "Тестов", "ул. Прокатная", "79994391022", "Театральная", "20.11.2024", "трое суток", "grey", "Коммент"},
                {"firefox", "Иван", "Иванов", "ул. Красная", "79123456712", "Строгино", "01.12.2024", "сутки", null, null}
        });
    }

    @Parameterized.Parameter(value = 1)
    public String name;

    @Parameterized.Parameter(value = 2)
    public String lastName;

    @Parameterized.Parameter(value = 3)
    public String address;

    @Parameterized.Parameter(value = 4)
    public String phone;

    @Parameterized.Parameter(value = 5)
    public String subway;

    @Parameterized.Parameter(value = 6)
    public String deliveryDate;

    @Parameterized.Parameter(value = 7)
    public String rent;

    @Parameterized.Parameter(value = 8)
    public String color;

    @Parameterized.Parameter(value = 9)
    public String comment;

    @Parameterized.Parameter()
    public String browser;


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


    @Test
    public void orderTestTop() {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);
        homePage.clickButton(homePage.getTopOrderButton());

        OderPage orderPage = new OderPage(driver);

        orderPage.setValue(orderPage.getFieldName(), name);
        orderPage.setValue(orderPage.getFieldLastName(), lastName);
        orderPage.setValue(orderPage.getFieldAddress(), address);
        orderPage.setValue(orderPage.getFieldPhone(), phone);
        orderPage.setSubway(orderPage.getFieldSubway(), subway);

        orderPage.clickButton(orderPage.getButtonNext());

        OrderInfoPage orderInfoPage = new OrderInfoPage(driver);
        orderInfoPage.setDeliveryDate(orderInfoPage.getFieldDeliveryDate(), deliveryDate);
        orderInfoPage.selectRent(rent);
        orderInfoPage.setValue(orderInfoPage.getFieldComment(), comment);
        orderInfoPage.clickCheckbox(color);
        orderInfoPage.clickButton(orderInfoPage.getButtonOrder());

        FinalOrderPage finalOrderPage = new FinalOrderPage(driver);
        finalOrderPage.clickButton(finalOrderPage.getYesButton());
        assertThat(finalOrderPage.getText(finalOrderPage.getFinishOrderText()), containsString("Заказ оформлен"));
    }

    @Test
    public void orderTestBottom() {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);
        WebElement orderButtonElement = driver.findElement(homePage.getBottomOrderButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderButtonElement);
        homePage.clickButton(homePage.getBottomOrderButton());

        OderPage orderPage = new OderPage(driver);
        orderPage.setValue(orderPage.getFieldName(), name);
        orderPage.setValue(orderPage.getFieldLastName(), lastName);
        orderPage.setValue(orderPage.getFieldAddress(), address);
        orderPage.setValue(orderPage.getFieldPhone(), phone);
        orderPage.setSubway(orderPage.getFieldSubway(), subway);
        orderPage.clickButton(orderPage.getButtonNext());

        OrderInfoPage orderInfoPage = new OrderInfoPage(driver);
        orderInfoPage.setDeliveryDate(orderInfoPage.getFieldDeliveryDate(), deliveryDate);
        orderInfoPage.selectRent(rent);
        orderInfoPage.setValue(orderInfoPage.getFieldComment(), comment);
        orderInfoPage.clickCheckbox(color);
        orderInfoPage.clickButton(orderInfoPage.getButtonOrder());

        FinalOrderPage finalOrderPage = new FinalOrderPage(driver);
        finalOrderPage.clickButton(finalOrderPage.getYesButton());
        assertThat(finalOrderPage.getText(finalOrderPage.getFinishOrderText()), containsString("Заказ оформлен"));
    }
}

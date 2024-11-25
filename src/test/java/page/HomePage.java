package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    //кнопка "Заказать" сверху станицы
    private final By topOrderButton = By.xpath("(.//button[contains(@class, 'Button_Button__ra12g')])[1]");

    //кнопка "заказать" снизу страницы
    private final By bottomOrderButton = By.xpath("(.//button[contains(@class, 'Button_Button__ra12g')])[3]");

    //Заголовок вопросы о важном
    private final By headerFAQ = By.xpath("//*[text()='Вопросы о важном']");

    //Кнопка "Сколько это стоит? И как оплатить?"
    private final By buttonFAQ_pay = By.xpath("//*[text()='Сколько это стоит? И как оплатить?']");

    //Кнопка "Хочу сразу несколько самокатов! Так можно?"
    private final By buttonFAQ_scooters = By.xpath("//*[text()='Хочу сразу несколько самокатов! Так можно?']");

    //Кнопка "Как рассчитывается время аренды?"
    private final By buttonFAQ_rent = By.xpath("//*[text()='Как рассчитывается время аренды?']");

    //Кнопка "Можно ли заказать самокат прямо на сегодня?"
    private final By buttonFAQ_today = By.xpath("//*[text()='Можно ли заказать самокат прямо на сегодня?']");

    //Кнопка "Можно ли продлить заказ или вернуть самокат раньше?"
    private final By buttonFAQ_extend = By.xpath("//*[text()='Можно ли продлить заказ или вернуть самокат раньше?']");

    //Кнопка "Вы привозите зарядку вместе с самокатом?"
    private final By buttonFAQ_charge = By.xpath("//*[text()='Вы привозите зарядку вместе с самокатом?']");

    //Кнопка "Можно ли отменить заказ?"
    private final By buttonFAQ_cancel = By.xpath("//*[text()='Можно ли отменить заказ?']");

    //Кнопка "Я живу за МКАДом, привезёте?"
    //Опечатка в названии кнопки на сайте
    private final By buttonFAQ_region = By.xpath("//*[text()='Я живу за МКАДом, привезёте?']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public String getFAQAnswer(By buttonLocator, String text) {
        String answer = "";
        //проверяем, существует ли вообще такая кнопка *findElement кидает NoSuchElementException
        if (!driver.findElements(buttonLocator).isEmpty()) {
            driver.findElement(buttonLocator).click();
            By answerLocator = By.xpath(String.format("//*[text()='%s']", text));
            new WebDriverWait(driver, Duration.ZERO.plusSeconds(1))
                    .until(waitDriver -> (!waitDriver.findElement(answerLocator).getText().isEmpty()));
            answer = driver.findElement(answerLocator).getText();
        }
        return answer;
    }

    public By getButtonFAQ_pay() {
        return buttonFAQ_pay;
    }

    public By getButtonFAQ_scooters() {
        return buttonFAQ_scooters;
    }

    public By getButtonFAQ_rent() {
        return buttonFAQ_rent;
    }

    public By getButtonFAQ_today() {
        return buttonFAQ_today;
    }

    public By getButtonFAQ_extend() {
        return buttonFAQ_extend;
    }

    public By getButtonFAQ_charge() {
        return buttonFAQ_charge;
    }

    public By getButtonFAQ_cancel() {
        return buttonFAQ_cancel;
    }

    public By getButtonFAQ_region() {
        return buttonFAQ_region;
    }

    public By getBottomOrderButton() {
        return bottomOrderButton;
    }

    public By getTopOrderButton() {
        return topOrderButton;
    }
}

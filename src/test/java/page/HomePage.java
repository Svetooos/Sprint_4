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


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public String getFAQAnswer(String buttonText, String answerText) {
        String answer = "";
        //проверяем, существует ли вообще такая кнопка *findElement кидает NoSuchElementException
        By buttonLocator = By.xpath(String.format("//*[text()='%s']", buttonText));
        if (!driver.findElements(buttonLocator).isEmpty()) {
            driver.findElement(buttonLocator).click();
            By answerLocator = By.xpath(String.format("//*[text()='%s']", answerText));
            new WebDriverWait(driver, Duration.ZERO.plusSeconds(1))
                    .until(waitDriver -> (!waitDriver.findElement(answerLocator).getText().isEmpty()));
            answer = driver.findElement(answerLocator).getText();
        }
        return answer;
    }

    public By getBottomOrderButton() {
        return bottomOrderButton;
    }

    public By getTopOrderButton() {
        return topOrderButton;
    }
}

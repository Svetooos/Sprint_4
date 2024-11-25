package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinalOrderPage {

    private WebDriver driver;

    public FinalOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка Да (Завершение заказа)
    private final By yesButton = By.xpath("(.//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM')])[4]");

    //Окно с текстом завершения заказа
    private final By finishOrderText = By.className("Order_ModalHeader__3FDaJ");


    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public By getYesButton() {
        return yesButton;
    }

    public By getFinishOrderText() {
        return finishOrderText;
    }
}

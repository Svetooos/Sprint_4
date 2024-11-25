package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OderPage {

    private WebDriver driver;

    //Кнопка далее
    private final By buttonNext = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM')]");

    //Поле ввода Имя
    private final By fieldName = By.xpath("//input[@placeholder='* Имя']");

    //Поле ввода Фамилия
    private final By fieldLastName = By.xpath("//input[@placeholder='* Фамилия']");

    //Поле ввода Адрес
    private final By fieldAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле с выпадающим списком Станция метро
    private final By fieldSubway = By.xpath("//input[@placeholder='* Станция метро']");

    //Поле станция Телефон
    private final By fieldPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");


    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public void setValue(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

    public void setSubway(By locator, String value) {
        setValue(locator, value);
        driver.findElement(locator).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    public OderPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getFieldName() {
        return fieldName;
    }

    public By getFieldLastName() {
        return fieldLastName;
    }

    public By getFieldAddress() {
        return fieldAddress;
    }

    public By getFieldSubway() {
        return fieldSubway;
    }

    public By getFieldPhone() {
        return fieldPhone;
    }

    public By getButtonNext() {
        return buttonNext;
    }
}



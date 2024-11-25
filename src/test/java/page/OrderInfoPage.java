package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderInfoPage {

    private WebDriver driver;

    public OrderInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка далее
    private final By buttonOrder = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM')][2]");

    //Поле ввода Когда привезти самокат
    private final By fieldDeliveryDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    //Поле ввода Срок аренды
    private final By fieldRent = By.xpath("//*[text() = '* Срок аренды']");

    //Поле ввода Комментарий
    private final By fieldComment = By.xpath("//input[@placeholder='Комментарий для курьера']");

    public void clickCheckbox(String id) {
        if (id != null && !id.isEmpty()) {
            driver.findElement(By.id(id)).click();
        }
    }

    public void clickButton(By locator) {
        driver.findElement(locator).click();
    }

    public void setValue(By locator, String value) {
        if (value != null && value.isEmpty()) {
            driver.findElement(locator).sendKeys(value);
        }
    }

    public void setDeliveryDate(By locator, String value) {
        setValue(locator, value);
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    public void selectRent(String rent) {
        driver.findElement(fieldRent).click();
        By locator = By.xpath(String.format("//*[text() = '%s']", rent));
        driver.findElement(locator).click();
    }

    public By getButtonOrder() {
        return buttonOrder;
    }

    public By getFieldDeliveryDate() {
        return fieldDeliveryDate;
    }

    public By getFieldComment() {
        return fieldComment;
    }
}

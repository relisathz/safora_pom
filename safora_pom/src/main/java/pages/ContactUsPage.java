package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactUsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameField = By.xpath("//form[@id='contact-form']//input[@id='name']");
    private final By emailField = By.xpath("//form[@id='contact-form']//input[@id='email']");
    private final By phoneField = By.xpath("//form[@id='contact-form']//input[@id='phone']");
    private final By messageField = By.xpath("//form[@id='contact-form']//textarea[@id='message']");
    private final By submitButton = By.xpath("//form[@id='contact-form']//button[normalize-space()='Send Message']");
    private final By successTitle = By.xpath("//h2[@id='swal2-title' and normalize-space()='Success!']");
    private final By successText = By
            .xpath("//div[@id='swal2-html-container' and contains(text(),'Your message has been sent successfully')]");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ContactUsPage enterName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public ContactUsPage enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public ContactUsPage enterPhone(String phone) {
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
        return this;
    }

    public ContactUsPage enterMessage(String message) {
        driver.findElement(messageField).clear();
        driver.findElement(messageField).sendKeys(message);
        return this;
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public String getSuccessMessage() {
    WebElement title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(successTitle)
    );

    WebElement message = wait.until(
            ExpectedConditions.visibilityOfElementLocated(successText)
    );

    return title.getText() + " " + message.getText();
}

    public void clickByXpath(String xpath) throws InterruptedException {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                element);

        Thread.sleep(1000);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element);
    }
}
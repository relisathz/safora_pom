package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.ContactUsPage;


public class ContactUsTest extends BaseTest {

    /**
     * @throws InterruptedException
     */
    @Test
    public void testContactFormSubmission() throws InterruptedException {
        String contactUrl = "https://safora.se/en/";
        driver.get(contactUrl);

        ContactUsPage contactUsPage = new ContactUsPage(driver);

        contactUsPage.clickByXpath("(//nav[@id='mobile-menu']//a[@href='/en/contact.html'])[1]");
        Thread.sleep(3000);


        WebElement getInTouch = driver.findElement(By.xpath("//h3[normalize-space()='Get In Touch']"));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                getInTouch);

        Thread.sleep(3000);

        contactUsPage
                .enterName("Dulakshmi")
                .enterEmail("dulakshmi@example.com")
                .enterPhone("0771234567")
                .enterMessage("This is a test automation message.");

        System.out.println("Please manually complete reCAPTCHA, Waiting 1 min.....");
        Thread.sleep(60000);

        contactUsPage.clickSubmit();

        String success = contactUsPage.getSuccessMessage();
        Assert.assertNotNull("Success message should not be null after submission", success);
        Assert.assertFalse("Success message should not be empty", success.isEmpty());
    }


}
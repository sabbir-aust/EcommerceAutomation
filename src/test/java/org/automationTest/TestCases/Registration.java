package org.automationTest.TestCases;

import org.automationTest.Browser.OpenBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.time.Duration;

public class Registration extends OpenBrowser {
    public static WebDriver driver = null;


    @BeforeTest
    public void setup() throws MalformedURLException, InterruptedException {

        driver = start("chrome");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.trendyol.com");
        Thread.sleep(3000);

    }


    @Test(priority = 1)
    public void selectCountry() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement clickonCountryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='country-select']//select")));
        WebElement selectCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value='DK']")));
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Select']")));
        clickonCountryDropdown.click();
        selectCountry.click();
        select.click();
        WebElement selectCookies = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
        selectCookies.click();

    }

    @Test(priority = 2)
    public void selectRegistration() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement clickonRegistrationBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='by clicking you can access the user page if you already logged in']")));
        clickonRegistrationBtn.click();

        //Verify that all the specified fields are present on the registration page.
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='register-email-input']")));
        Assert.assertTrue(email.isDisplayed());

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='register-password-input']")));
        Assert.assertTrue(password.isDisplayed());

        WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Man']")));
        Assert.assertTrue(genderMale.isDisplayed());

        WebElement genderFemale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toggle-button-group gender-options']//button[1]")));
        Assert.assertTrue(genderFemale.isDisplayed());

        WebElement privacyCheckBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkbox-']")));
        Assert.assertTrue(privacyCheckBox.isDisplayed());

        WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Sign up']")));
        Assert.assertTrue(signUp.isDisplayed());


        //Verify that all the specified fields are present on the registration page.
        signUp.click();
        WebElement errorPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='p-content']//div[@class='container']")));
        Assert.assertTrue(errorPopUp.isDisplayed());
        Thread.sleep(2000);
        WebElement closePopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='OK']")));
        closePopUp.click();


        //Verify that entering blank spaces on mandatory fields leads to validation error.
        email.sendKeys(" ");
        Thread.sleep(1000);
        password.sendKeys(" ");
        Thread.sleep(1000);
        genderMale.click();
        Thread.sleep(1000);

        WebElement emailErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Invalid email']")));
        WebElement passwordErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Your password must be between 7 and 64 characters.']")));

        Assert.assertEquals(emailErrorMsg.getText(),"Invalid email");
        Assert.assertEquals(passwordErrorMsg.getText(),"Your password must be between 7 and 64 characters.");

    }

    @Test(priority = 3)
    public void purchaseProducts() throws InterruptedException{
        //Login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loginEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-email-input']")));
        loginEmail.sendKeys("sabinulhaque7@gmail.com");

        WebElement loginPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-password-input']")));
        loginPassword.sendKeys("1234567a");

        WebElement clickLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Log in']")));
        clickLogin.click();

        //Click on start shopping
        WebElement startShoppingBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='START SHOPPING']")));
        startShoppingBtn.click();

        //Click on shop now
        WebElement shopNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/main/div[@id='widget-list']/div[@class='widget-container']/div[@class='widget-list-wrapper']/div[1]/a[1]/img[1]")));
        shopNowBtn.click();

        Thread.sleep(2000);
        //Add items to wishlist
        WebElement item1WishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='body']//div[1]//a[1]//div[2]//div[1]//div[2]//div[1]//span[1]")));
        item1WishList.click();

        Thread.sleep(1000);
        WebElement item2WishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='bLACK/Sail/Gym Red Wmns 1 Low For Women / Girls']//span[@class='p-icon icon-int-favorites favorite-icon']")));
        item2WishList.click();

        Thread.sleep(1000);
        //Validate Wishlist
        WebElement clickOnWishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='by clicking you can access to favorites page']")));
        clickOnWishList.click();

        //Validate item 1 is displayed
        WebElement item1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-cards']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]")));
        Assert.assertTrue(item1.isDisplayed());

        Thread.sleep(1000);
        //Validate item 2 is displayed
        WebElement item2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/main/div[@id='favorites']/div[@class='favorites']/div[@class='favorites__content']/div[@class='product-cards']/div[1]")));
        Assert.assertTrue(item2.isDisplayed());

        driver.navigate().back();

        Thread.sleep(3000);
        //Purchase products
        WebElement clickOnitem1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/main/div[@id='search-result']/div[@class='search-result']/section[@class='contents-container']/div[@class='contents']/div[@class='body']/div[@class='search-result-wrapper']/ul[@class='product-list']/div[1]/a[1]/div[2]/div[2]")));
        clickOnitem1.click();
        Thread.sleep(1000);

        WebElement clickOnAddtoCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Add to bag']")));
        clickOnAddtoCart.click();
        Thread.sleep(1000);

        driver.navigate().back();

        Thread.sleep(3000);
        WebElement clickOnitem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='bLACK/Sail/Gym Red Wmns 1 Low For Women / Girls']")));
        clickOnitem2.click();
        Thread.sleep(1000);
        WebElement clickOnAddtoCart2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Add to bag']")));
        clickOnAddtoCart2.click();
        Thread.sleep(1000);

        //Click on Bag

        WebElement clickOnBag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='by clicking you can open the basket page']")));
        clickOnBag.click();
        Thread.sleep(1000);

        //click on checkout
        WebElement clickOnCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Checkout']")));
        clickOnCheckout.click();
        Thread.sleep(1000);

//        //Search address
//        WebElement searchAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search for your address']")));
//        searchAddress.sendKeys("Denmark");
//        Thread.sleep(1000);
//
//        //Confirm Address
//        WebElement confirmAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='CONFIRM ADDRESS']")));
//        confirmAddress.click();
//        Thread.sleep(1000);
//
//        //Add Address Details
//        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']")));
//        firstName.sendKeys("Sabbir");
//        Thread.sleep(1000);
//        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='surname']")));
//        lastName.sendKeys("Ahmed");
//        Thread.sleep(1000);
//
//        WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='surname']")));
//        mobile.sendKeys("93706153");
//        Thread.sleep(1000);
//
//        WebElement saveAndContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='p-button-wrapper p-primary p-large']")));
//        saveAndContinue.click();
//        Thread.sleep(1000);
//        //Select Card
//        WebElement selectCardOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='card']")));
//        selectCardOption.click();
//        Thread.sleep(1000);
//
//        WebElement cardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='card-number']")));
//        cardNumber.sendKeys("3700 0000 0000 002");
//
//        WebElement expiryMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='card-date-month']")));
//        expiryMonth.click();
//        WebElement selectMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value='3']")));
//        selectMonth.click();
//
//        WebElement expiryYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='card-date-year']")));
//        expiryYear.click();
//        WebElement selectYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value='2030']")));
//        selectYear.click();

        Thread.sleep(1000);
        //Click on place order
        WebElement placeOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Place order']")));
        placeOrder.click();

    }

    @Test(priority = 4)
    public void logout() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement clickonLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Trendyol Logo']")));
        clickonLogo.click();

        WebElement clickonRegistrationBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='by clicking you can access the user page if you already logged in']")));

        //Instantiating Actions class
        Actions actions = new Actions(driver);

        //Hovering on main menu
        actions.moveToElement(clickonRegistrationBtn).perform();
        Thread.sleep(3000);

        // Locating the element from Sub Menu
        WebElement subMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Sign out']")));

        //To mouseover on sub menu
        actions.moveToElement(subMenu).perform();

        //build()- used to compile all the actions into a single step
        actions.click().build().perform();
        Thread.sleep(3000);

    }

    @Test(priority = 5)
    public void faq() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement clickonSupport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='help-icon']")));
        clickonSupport.click();

        WebElement clickonFAQ = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='FAQ']")));
        clickonFAQ.click();

        //WebElement clickonFirstFAQ = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='When will my order arrive?']")));

        //clickonFirstFAQ.click();
        Thread.sleep(2000);
//        WebElement firstFAQDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'As soon as your order is on its way, you will rece')]")));
//
//        Assert.assertFalse(firstFAQDescription.getText().isEmpty());

    }
//
    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {
            System.out.println("Test Done!!!");
            driver.quit();
        }
    }

}

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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Registration extends OpenBrowser {
    public static WebDriver driver = null;

    // Define a list to store test results
    private List<String[]> testResults = new ArrayList<>();

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

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement clickonCountryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='country-select']//select")));
            WebElement selectCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value='DK']")));
            WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Select']")));
            clickonCountryDropdown.click();
            selectCountry.click();
            select.click();
            WebElement selectCookies = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
            selectCookies.click();

        }catch (Exception e){

        }
    }

    //Case 1
    @Test(priority = 2, description = "Case 1")
    public void registrationFieldAvailable(){
        try {
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

            testResults.add(new String[]{"Case 1", "Pass"});
        }catch (Exception e){
            testResults.add(new String[]{"Case 1", "Fail"});
        }
    }

    //Case 2
    @Test(priority = 3, description = "Case 2")
    public void validationCheckOnEmptyField() throws InterruptedException {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Sign up']")));

            //Verify that all the specified fields are present on the registration page.
            signUp.click();
            WebElement errorPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='p-content']//div[@class='container']")));
            Assert.assertTrue(errorPopUp.isDisplayed());
            Thread.sleep(2000);
            WebElement closePopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='OK']")));
            closePopUp.click();

            testResults.add(new String[]{"Case 2", "Pass"});
        }catch (Exception e){
            testResults.add(new String[]{"Case 2", "Fail"});
        }

    }

    //Case 3
    @Test(priority = 4, description = "Case 3")
    public void validationCheckBlankSpace() throws InterruptedException {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            //Verify that all the specified fields are present on the registration page.
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='register-email-input']")));
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='register-password-input']")));
            WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Man']")));

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

            testResults.add(new String[]{"Case 3", "Pass"});
        }catch (Exception e){
            testResults.add(new String[]{"Case 3", "Fail"});
        }

    }


    //Case 5
    @Test(priority = 5, description = "Case 5" )
    public void addProductsToWishlist() throws InterruptedException{

        try {
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
            WebElement item1WishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='bLACK/Sail/Gym Red Wmns 1 Low For Women / Girls']//span[@class='p-icon icon-int-favorites favorite-icon']")));
            item1WishList.click();

            Thread.sleep(1000);
            WebElement item2WishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/main[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[2]/ul[1]/div[2]/a[1]/div[2]/div[1]/div[2]/div[1]")));
            item2WishList.click();

            Thread.sleep(1000);
            //Validate Wishlist
            WebElement clickOnWishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='favorites-wrapper menu-item']")));
            clickOnWishList.click();

            //Validate item 1 is displayed
            WebElement item1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-cards']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]")));
            Assert.assertTrue(item1.isDisplayed());

            Thread.sleep(3000);
            System.out.println("Test Case: Case 5 - Passed"); // Debug print statement
            testResults.add(new String[]{"Case 5", "Pass"});
        } catch (Exception e) {
            System.out.println("Test Case: Case 5 - Failed"); // Debug print statement
            testResults.add(new String[]{"Case 5", "Fail"});
        }
    }

    //Case 4
    @Test(priority = 6, description = "Case 4")
    public void addProductsToCart() throws InterruptedException{
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.navigate().back();
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

            //Click on place order
//            WebElement placeOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Place order']")));
//            placeOrder.click();
            testResults.add(new String[]{"Case 4", "Pass"});
        }catch (Exception e){
            testResults.add(new String[]{"Case 4", "Fail"});
        }

    }

    //Case 6
//    @Test(priority = 7, description = "Case 6")
//    public void purchaseProducts() throws InterruptedException{
//
//        try {
//            //Login
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            //click on checkout
//            WebElement clickOnCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Checkout']")));
//            clickOnCheckout.click();
//
//            Thread.sleep(1000);
//            //Click on place order
//            WebElement placeOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Place order']")));
//            placeOrder.click();
//            testResults.add(new String[]{"Case 6", "Pass"});
//        }catch (Exception e){
//            testResults.add(new String[]{"Case 6", "Fail"});
//        }
//
//    }

    //case 7
    @Test(priority = 8, description = "Case 7")
    public void logout() throws InterruptedException {
        try {
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
            testResults.add(new String[]{"Case 7", "Pass"});
        }catch (Exception e){
            testResults.add(new String[]{"Case 7", "Fail"});
        }

    }


    @AfterTest
    public void tearDown() throws Exception {
        writeTestResultsToExcel();
        if (driver != null) {
            System.out.println("Test Done!!!");
            driver.quit();
        }
    }

    public void writeTestResultsToExcel() {
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Results");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Test case title");
        headerRow.createCell(1).setCellValue("Status");

        // Write test case names and results to Excel
        for (int i = 0; i < testResults.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(testResults.get(i)[0]);
            row.createCell(1).setCellValue(testResults.get(i)[1]);
        }

        // Write workbook to file
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("TestResults.xlsx"));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            System.out.println("Test results written successfully to TestResults.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

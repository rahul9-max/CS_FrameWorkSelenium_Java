package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    @FindBy(xpath = "//a[@href='/products']")
    WebElement productsButton;

    @FindBy(xpath = "//h2[@class='title text-center']")
    WebElement allProductsHeading;

    @FindBy(xpath = "//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[2]//ul[1]//li[1]//a[1]")
    WebElement firstProductViewBtn;

    @FindBy(xpath = "//div[@class='product-information']")
    WebElement productDetailsBox;
    
    @FindBy(css= "div[class='product-information'] h2")
    WebElement productName;

    @FindBy(xpath = "//h2[normalize-space()='Category']")
    WebElement productCategory;

    @FindBy(css = "div[class='product-information'] span span")
    WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-details']//p[2]")
    WebElement productAvailability;

    @FindBy(xpath = "//body//section//p[3]")
    WebElement productCondition;

    @FindBy(xpath = "//body//section//p[4]")
    WebElement productBrand;

    // Actions
    public void clickProductsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(productsButton)).click();
    }

    public boolean isAllProductsPageVisible() {
        return wait.until(ExpectedConditions.visibilityOf(allProductsHeading)).isDisplayed();
    }

    public boolean isProductListVisible() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400);"); // Scroll to reveal products
        return firstProductViewBtn.isDisplayed();
    }

    public void clickViewFirstProduct() {
        firstProductViewBtn.click();
    }

    public boolean isProductDetailSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOf(productDetailsBox)).isDisplayed();
    }
    
    public boolean areProductDetailsVisible() {
        return productName.isDisplayed() &&
               productCategory.isDisplayed() &&
               productPrice.isDisplayed() &&
               productAvailability.isDisplayed() &&
               productCondition.isDisplayed() &&
               productBrand.isDisplayed();
    }
}

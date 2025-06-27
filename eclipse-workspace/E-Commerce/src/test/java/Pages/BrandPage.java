package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrandPage {
    WebDriver driver;

    public BrandPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/products']")
    WebElement productsButton;

    @FindBy(xpath = "//h2[normalize-space()='Brands']")
    WebElement brandsTitle;

    @FindBy(xpath = "//a[@href='/brand_products/Babyhug']")
    WebElement babyhugBrandLink;

    @FindBy(xpath = "//a[@href='/brand_products/H&M']")
    WebElement hmBrandLink;

    @FindBy(xpath = "//h2[contains(@class, 'title') and contains(., 'Brand - Babyhug Products')]")
    public WebElement babyhugBrandHeading;

    @FindBy(xpath = "//h2[contains(@class, 'title') and contains(., 'Brand - H&M Products')]")
    public WebElement hmBrandHeading;


    public void clickProductsButton() {
        productsButton.click();
    }

    public boolean isBrandsVisible() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        return brandsTitle.isDisplayed();
    }

    public void clickOnBabyhugBrand() {
        babyhugBrandLink.click();
    }

    public void clickOnHMBrand() {
        hmBrandLink.click();
    }

    public boolean isBabyhugHeadingVisible() {
        return babyhugBrandHeading.isDisplayed();
    }

    public boolean isHMHeadingVisible() {
        return hmBrandHeading.isDisplayed();
    }

}

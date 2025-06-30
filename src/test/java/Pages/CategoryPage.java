package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class CategoryPage {

    WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='Category']")
    WebElement categorySection;

    @FindBy(xpath = "//a[normalize-space()='Women']")
    WebElement womenCategory;
    
    @FindBy(xpath = "//a[normalize-space()='Women']//i[@class='fa fa-plus']")
    WebElement womenCategoryExpand;


    @FindBy(xpath = "//a[normalize-space()='Tops']")
    WebElement womenTops;
    
    @FindBy(xpath = "//a[normalize-space()='Men']")
    WebElement menCategory;

    @FindBy(xpath = "//a[normalize-space()='Men']//i[@class='fa fa-plus']")
    WebElement menCategoryExpand;

    @FindBy(xpath = "//a[normalize-space()='Tshirts']")
    WebElement menTshirts;

//    @FindBy(xpath = "//h2[normalize-space()='MEN - TSHIRTS PRODUCTS']")
//    WebElement menTshirtHeading;

    @FindBy(xpath = "//h2[contains(text(), 'Women - Tops Products')]")
    WebElement womenTopHeading;


    // Scroll to category section
    public void scrollToCategorySection() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
    }

    public boolean isCategorySectionVisible() {
        return categorySection.isDisplayed();
    }

    // WOMEN category and subcategory
    public void clickWomenCategory() {
        womenCategory.click();
    }

    public void expandWomenCategory() {
    	womenCategoryExpand.click();
    }

    
    public void clickWomenTopsSubCategory() {
        womenTops.click();
    }
    
    public String getCategoryHeadingText1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement heading = wait.until(ExpectedConditions.visibilityOf(womenTopHeading));
        return heading.getText();
    }

    
    
    public void clickMenCategory() {
    	menCategory.click();
    }

    // MEN category and subcategory
    public void expandMenCategory() {
        menCategoryExpand.click();
    }

    public void clickMenTshirtsSubCategory() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	    wait.until(ExpectedConditions.elementToBeClickable(menTshirts)).click();
    }
    
    // Heading check
    public String getCategoryHeadingText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h2[contains(translate(normalize-space(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'MEN - TSHIRTS PRODUCTS')]")
        ));
        return heading.getText();
    }
 // Waits for the heading to appear and matches it in a case-insensitive and space-normalized way.
 // Uses 'normalize-space()' to remove extra spaces and 'translate()' to convert all letters to uppercase,
 // allowing flexible matching like "Men - Tshirts Products", "MEN - TSHIRTS PRODUCTS", etc.

    
}

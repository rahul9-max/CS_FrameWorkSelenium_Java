package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemoveCartPage {
    WebDriver driver;

    @FindBy(xpath = "//a[@href='/view_cart']") 
    WebElement cartButton;

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    WebElement removeProductButton;
    
    @FindBy(xpath = "//tr[@class='cart_product']")
    List<WebElement> cartItems;

    @FindBy(xpath = "//div[@id='cart_info']") // Container for the cart products
    WebElement cartInfoSection;

    public RemoveCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCartButton() {
        cartButton.click();
    }

//    public boolean isCartPageDisplayed() {
//        return cartInfoSection.isDisplayed();
//    }

    public void removeProduct() {
        removeProductButton.click();
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

}

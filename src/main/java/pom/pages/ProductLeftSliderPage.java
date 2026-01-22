package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.ElementActions;

public class ProductLeftSliderPage {
    //Variables

    //Locators
    private By categoryTitle = By.xpath("//div[@class='left-sidebar']/h2");
    private By WomenCategory = By.xpath("//a[@href='#Women']");
    private By MenCategory = By.xpath("//a[@href='#Men']");
    private By KidsCategory = By.xpath("//a[@href='#Kids']");
    private By womenSubCategoryLinks = By.cssSelector("#Women a");
    private By menSubCategoryLinks = By.cssSelector("#Men a");
    private By kidsSubCategoryLinks = By.cssSelector("#Kids a");


    private By BrandsTitle = By.xpath("//div[@class='brands_products']/h2");
    private By BrandSubCategoryLinks = By.cssSelector(".brands-name a");

    //Constructor

    //Actions
    public ProductLeftSliderPage clickWomenCategory() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(WomenCategory);
        return this;
    }

    public ProductLeftSliderPage clickMenCategory() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(MenCategory);
        return this;
    }

    public ProductLeftSliderPage clickKidsCategory() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(KidsCategory);
        return this;
    }

    public ProductLeftSliderPage clickWomenSubCategory(String categoryName) {
        ElementActions.clickElementFromList(womenSubCategoryLinks, categoryName);
        return this;
    }

    public ProductLeftSliderPage clickMenSubCategory(String categoryName) {
        ElementActions.clickElementFromList(menSubCategoryLinks, categoryName);
        return this;
    }

    public ProductLeftSliderPage clickKidsSubCategory(String categoryName) {
        ElementActions.clickElementFromList(kidsSubCategoryLinks, categoryName);
        return this;
    }

    public ProductLeftSliderPage clickSpecificBrand(String brandName) {
        ElementActions.scrollToElement(BrandSubCategoryLinks);
        ElementActions.clickElementFromList(BrandSubCategoryLinks, brandName);
        return this;
    }


    //Validation
    @Step("Verify products page title contains")
    public ProductLeftSliderPage assertOnCategoryTitle(String Title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(categoryTitle).toLowerCase().contains(Title));
        return this;
    }

    public ProductLeftSliderPage assertOnBrandsTitle(String Title){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(BrandsTitle).toLowerCase().equalsIgnoreCase(Title));
        return this;
    }
}

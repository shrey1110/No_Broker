package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testBase.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Nobroker  {

    private WebDriver driver;

    @FindBy(xpath = "//*[text()='Buy']")
    WebElement Buy;

    @FindBy(id = "listPageSearchLocality")
    WebElement SearchLocality;
    
    @FindBy(xpath = "//*[@id='searchCity']/div/div[2]/div")
    WebElement SearchCityDropDpwnButton;

    @FindBy(xpath = "//div[5]/div[1]//*[@id='searchCity']//div/div[2]")
    WebElement ApartmentTypeDropDownButton;

    @FindBy(xpath = "//*[@type='button' and text()='Search']")
    WebElement SearchButton;

    @FindBy(id = "//*[@id='propertyCardConatiner']/div[1]")
    WebElement propertyCard;

    @FindBy(xpath = "//article/div[2]/div/div")
    List<WebElement> Apartment;

    @FindBy(xpath = "//article//section[1]/div[1]/a")
    List<WebElement> ApartmentDetails;

    @FindBy(xpath = "/html/body/div/div/div/div/span")
    WebElement ClosePopUp;

    @FindBy(id = "description")
    WebElement Description;

    WebDriverWait wait;

    public Nobroker(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 30);
    }

    public void selectBuy(){
        Buy.click();
    }

    public void searchLocality(String[] Locality) throws InterruptedException {
        for(int i=0;i<Locality.length;i++){
            SearchLocality.sendKeys(Locality[i]);
            //Thread.sleep(4000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"autocomplete-dropdown-container\"]/div")));
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"autocomplete-dropdown-container\"]/div")); // to locate Locality dropdown
            for( WebElement element:elements)
            {
                //System.out.println(element.getAttribute("innerHTML"));
                String[] str= Locality[i].split(" ");
                if(element.getAttribute("innerHTML").contains(str[str.length-1])) // to select locality by checking last word in localoty
                {
                    element.click();
                    Thread.sleep(1000);
                    break;

                }
            }

        }

        //driver.findElement(By.xpath("//*[contains(text(),\"" + Locality+"\")]")).click();
    }

    public void selectCity(String City) throws InterruptedException {
        SearchCityDropDpwnButton.click();
        driver.findElement(By.xpath("//*[text()='"+City+"']")).click(); // to slect city
        //Thread.sleep(1000);
    }

    public void selectApartmentType(String[] Type) throws InterruptedException {
        WebElement Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='BHK Type']")));
        Category_Body.click();
        //Thread.sleep(3000);
        for(int i=0;i<Type.length;i++){
            driver.findElement(By.xpath("//*[text()='"+Type[i]+"']")).click(); //to select apartment type
            //Thread.sleep(2000);
        }

    }

    public void ClickSearchButton(){
        SearchButton.click();
    }

    public void getFourthApartmentDetails() throws InterruptedException {
        //Thread.sleep(15000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='propertyCardConatiner']/div[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Apartment.get(3)); //to scroll into view for 4th property
        Apartment.get(3).click();
        ArrayList<String> handles=new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(1));
        ;

    }

    public void checkDescription(){
        Assert.assertNotNull(Description);
    }

}

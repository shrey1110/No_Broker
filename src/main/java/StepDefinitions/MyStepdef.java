package StepDefinitions;


import Data.TestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.Nobroker;
import testBase.TestBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyStepdefs  {

    WebDriver driver;
    Nobroker nobroker;
    TestData testdata;

    //Nobroker nobroker =new Nobroker(driver);
    @Before
    public void InitializeBrowser(){
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "\\src\\test\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    //@After
    //public void closeBrowser(){driver.quit();}



    @Given("^User is on NoBroker$")
    public void user_on_nobroker() throws InterruptedException, IOException, ParseException {
        driver.get("https://www.nobroker.in/");
        nobroker = new Nobroker(driver);
        testdata = new TestData();
        Thread.sleep(5000);
        //driver.findElement(By.xpath("//*[@id='app']/div/div/div[1]/div[3]/div[1]")).click();
        nobroker.selectBuy();
    }

    @And("User selects City and Locality")
    public void userSelectsCityAndLocality() throws InterruptedException {
        nobroker.selectCity(testdata.getCity());
        nobroker.searchLocality(testdata.getLocality());
    }

    @And("User selects Apratment Type and click on Search button")
    public void userSelectsApratmentTypeAndClickOnSearchButton() throws InterruptedException {
        nobroker.selectApartmentType(testdata.getApartment());
        nobroker.ClickSearchButton();
    }



    @And("User selects a property")
    public void userSelectsAProperty() throws InterruptedException {
        nobroker.getFourthApartmentDetails();
    }

    @Then("User should see Description")
    public void userShouldSeeDescription() {
        nobroker.checkDescription();
    }
}

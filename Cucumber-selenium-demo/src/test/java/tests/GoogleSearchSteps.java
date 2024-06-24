package tests;


import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class GoogleSearchSteps {
    WebDriver driver;
 
    
    @Given("I open Google search page")
    public void i_open_Google_search_page() {
    System.out.println("I open Google search page");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @When("I search for {string}")
    public void i_search_for(String searchText) {
    	System.out.println("I search for  ");
    //	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	//WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("CSS selector")));
    	//wait.until(ExpectedConditions.titleContains(searchText));
        driver.findElement(By.name("q")).sendKeys(searchText + Keys.ENTER);
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
    	//(//h3[normalize-space()='Selenium'])[1]
    	System.out.println("I should see search results");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	// Define the expected condition
    	ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
    	    public Boolean apply(WebDriver driver) {
    	        List<WebElement> h3Elements = driver.findElements(By.cssSelector("h3"));
    	        for (WebElement element : h3Elements) {
    	            if (!element.getText().isEmpty()) {
    	                return true;
    	            }
    	        }
    	        return false;
    	    }
    	};

    	// Wait until the condition is met (i.e., at least one h3 element has non-empty text)
    	wait.until(condition);

    	System.out.println("vaue displaye or not ? : "+driver.findElement(By.cssSelector("h3")).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector("h3")).isDisplayed());
        driver.quit();
    }
}

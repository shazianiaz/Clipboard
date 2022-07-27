package AmazonTest;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAssessment {
public WebDriver driver;

    //  	Base Case-1: Open browser and navigate to site
    @BeforeTest 
    public void setUpTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();

    }
    
    
    //		Case-2 Go to main menu, scroll and click on TV , Appliances and Electronics
    @Test (priority=1)
    public void naviagateMainmenu() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement menubar = driver.findElement(By.xpath("//*[@id=\"nav-hamburger-menu\"]/span"));
        menubar.click();
        // scroll and click on element
        WebElement mainMenu = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/ul[1]/li[16]/a"));
        action.moveToElement(mainMenu).build().perform();
        mainMenu.click();
        Thread.sleep(3000);
    }
    
 //		Case-3 Go to main menu, scroll and click on TV , Appliances and Electronics
    @Test (priority=2)
    public void televisonsClick() throws InterruptedException 
    {
  		WebElement televisons = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/ul[9]/li[3]/a")); 
  		televisons.click();
    	Thread.sleep(6000);
    	}


//		Case-4 Scroll down and filter the results by brand "Samsung"
@Test (priority=3)
public void sortBrandSamsung() throws InterruptedException 
   {
	
    WebElement sortSamsung = driver.findElement(By.xpath("//*[@id=\"s-refinements\"]//span[text()='Samsung']")); 
    Actions action = new Actions(driver);
    action.moveToElement(sortSamsung).build().perform();
	sortSamsung.click();
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	Thread.sleep(6000);
}

//		Case-5 Sort by Price High to Low from Featured dorpdown
@Test (priority=4)
public void sortByPriceHightoLow() throws InterruptedException 
{
	WebElement sortBy=driver.findElement(By.xpath("/html/body/div[1]/div[2]/span/div/h1/div/div[2]/div/div/form/span/span/span/span"));
    sortBy.click();
	
	WebElement sortByValue = driver.findElement(By.xpath("/html/body/div[4]/div/div/ul/li[3]/a")); 
	Actions action = new Actions(driver);
	action.moveToElement(sortByValue).build().perform();
    sortByValue.click();
	Thread.sleep(3000);	
  }

//		Case-6 Click on second highest price
@Test (priority=5)
public void clickSecondHighestPrice() throws InterruptedException 
{	
		WebElement secondHprice=driver.findElement(By.cssSelector("div[cel_widget_id='MAIN-SEARCH_RESULTS-2']"));
		secondHprice.click();
		Thread.sleep(8000);	
}


//		Case-7 Switching to next tab
@Test (priority=6)
public void switchingTab() throws InterruptedException 
{
	//Switching to next tab
	Set<String> windows =  driver.getWindowHandles();
	Iterator <String> it = windows.iterator();
	String parenttab = it.next();
	String childtab = it.next();
	driver.switchTo().window(childtab);
	
	JavascriptExecutor js = (JavascriptExecutor)driver;

	js.executeScript("window.scrollBy(0,1000)", "");
	Thread.sleep(8000);	

	
	}


//		Case-8 Assert that About this Item text is present or not  
@Test (priority=7)
public void aboutThisItem() throws InterruptedException 
{
	if(driver.getPageSource().contains("About this item")) 
{
		System.out.println("text is present");

}

else{
	System.out.println("text is not present");

}
	Thread.sleep(8000);	
}

//		Case-9 Printing Text of About this Item in console log  
@Test (priority=8)
public void logSectionText() throws InterruptedException 
{
	String gettext = driver.findElement(By.xpath("//h1[normalize-space()='About this item']")).getText();
	System.out.println(gettext);

	String gettext2 = driver.findElement(By.xpath("(//ul[@class='a-unordered-list a-vertical a-spacing-mini'])[1]")).getText();
	System.out.println(gettext2);
	Thread.sleep(6000);	
	driver.close();
}



}
    
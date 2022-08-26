package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InvkokeAmazon {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		WebElement Search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		Search.click();
		Search.sendKeys("samsung");
		
		WebElement Go = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		Go.click();

		List<WebElement> ProductList = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));
		List<WebElement> PriceList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		System.out.println("Total Products found " +ProductList.size());
		for(int i=0;i<ProductList.size();i++) {
			System.out.println(ProductList.get(i).getText()+" "+ PriceList.get(i).getText());
		}

		ProductList.get(0).click();

		
		String ParentWH= driver.getWindowHandle();
		
		String ExpectedValue = ProductList.get(0).getText();
		
		String ExpectedPrice = PriceList.get(0).getText();

		

		Set<String> AllWindowHandler =	driver.getWindowHandles();
		for(String win : AllWindowHandler ) {
			System.out.println(win);

			if(!win.equals(ParentWH)) {
				driver.switchTo().window(win);

			}
		}

		WebElement title = driver.findElement(By.id("productTitle"));

		String str = title.getText();
		

		if(str.equals(ExpectedValue)) {
			System.out.println("Title is matching");
		}else {
			System.out.println("Title is not matching");
		}
		
		
		WebElement PriceSymbol = driver.findElement(By.xpath("//div[@class='a-section a-spacing-none aok-align-center']//span[@class='a-price-symbol']"));
		WebElement Price = driver.findElement(By.xpath("//div[@class='a-section a-spacing-none aok-align-center']//span[@class='a-price-whole']"));
		
		String Prices = PriceSymbol.getText()  + Price.getText();
	
		
		if(Prices.equals(ExpectedPrice)) {
			System.out.println("Price is matching");
		}else {
			System.out.println("Price is not matching");
		}
		
		driver.quit(); 

	}

}
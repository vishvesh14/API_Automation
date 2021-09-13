package InterviewQuestions.InterviewSet;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindLinksOnWebpage {

	public static void main(String[] args) {
	
		System.setProperty("webdriver.chrome.driver","D:\\Softwares\\AutomationTools\\Chrome_Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://gptoday.com/");
		driver.manage().window().maximize();
		
//To get the number of links on the page
		List<WebElement> WebLinks = driver.findElements(By.tagName("a"));
		System.out.println("Count of links on webpage: "+WebLinks.size());
		
		//Print all links from Webpage
		for (int i = 0; i<WebLinks.size(); i++)
		{
			System.out.println("Printing all the links: "+WebLinks.get(i).getAttribute("href"));
			System.out.println("Printing all the links: "+WebLinks.get(i).getText());
		}
		
		
//To click links in the footer and open new page in new tab		
		WebElement x = driver.findElement(By.xpath("//*[@id=\"footernavwrap\"]/div"));   //Footer xpath
		List<WebElement> footerSize = x.findElements(By.tagName("a"));
		System.out.println("Footer Count: "+footerSize.size());
		
		for (int i = 0; i<footerSize.size(); i++) {
			String moveToNewTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
			driver.findElements(By.tagName("a")).get(i).sendKeys(moveToNewTab);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		}
	
		
		//driver.close();
	}

}

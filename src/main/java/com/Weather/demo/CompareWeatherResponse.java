package com.Weather.demo;

import com.jayway.jsonpath.JsonPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.restassured.RestAssured;
import junit.framework.Assert;

public class CompareWeatherResponse 
{
    public static void main( String[] args )
    {    	
    	try
		{
    		//Get State and City names for parameterizing
    		String state = "OH";
			String city = "Orrville";
			
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver/geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.weather.com");
			driver.manage().deleteAllCookies();
			WebDriverWait wait=new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='undefined']")));
			driver.findElement(By.xpath("//input[@value='undefined']")).click();
			driver.findElement(By.xpath("//input[@value='undefined']")).sendKeys(city+", "+ state);
			Thread.sleep(1000);
		//	WebElement lnkCity = driver.findElement(By.linkText("Orrville, Ohio"));
			
		//	lnkCity.click();
			
			driver.findElement(By.xpath("//input[@value='undefined']")).sendKeys(Keys.RETURN);
			Thread.sleep(2000);
			
			WebElement lnkCity = driver.findElement(By.xpath("//ul[starts-with(@class,'styles')]/li//a[contains(text(),'Orrville')]"));
			
			lnkCity.click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Today']")));
			
			//Get Time
			String strTime = driver.findElement(By.xpath("//p[@class='today_nowcard-timestamp']")).getText();
			
			System.out.println("Time is : " + strTime);
			
			//Get weather Temperature
			String currTemp = driver.findElement(By.xpath("//div[@class='today_nowcard-temp']")).getText();
			System.out.println("Current Temperature is : " + currTemp);
			
			//Get weather description
			String desc = driver.findElement(By.xpath("//div[@class='today_nowcard-phrase']")).getText();
			System.out.println("Today's weather is : " + desc);
			
			//Get feels like temperature
			String feelsLike = driver.findElement(By.xpath("span[@class='deg-feels']")).getText();
			System.out.println("Current weather feels like : " + feelsLike);
			
			//Get Temperature
			int highTemp = Integer.parseInt(driver.findElements(By.xpath("//span[@class='deg-hilo-nowcard']/span")).get(0).getText());
			
			int lowTemp = Integer.parseInt(driver.findElements(By.xpath("//span[@class='deg-hilo-nowcard']/span")).get(0).getText());
			
			System.out.println("Today's High Temperature is : " + highTemp);
			
			System.out.println("Today's Low Temperature is : " + lowTemp);
			
			//Get feels like temperature
			String uvIndex = driver.findElement(By.xpath("//div[@class='today_nowcard-hilo']//div/span[2]")).getText();
			System.out.println("Current weather feels like : " + uvIndex); 			

			    
			//Validate temperature difference between API and weather.com
			String[] arrState = new String[5];    		
    		
			arrState[0] = "OH";
	    /*	arrState[1] = "CO";
	    	arrState[2] = "MA";
	    	arrState[3] = "NJ";
	    	arrState[4] = "TX";*/
	    	
	    	String[] arrCity = new String[5];
	    	
	    	arrCity[0] = "Orrville";
	    /*	arrCity[1] = "Denver";
	    	arrCity[2] = "Boston";
	    	arrCity[3] = "Edison";
	    	arrCity[4] = "Dallas";*/
	    	
	    	String[] arrTemp = new String[5];
	    	
	    	arrTemp[0] = weather_Temperature("OH","Orrville");
	    /*	arrTemp[1] = weather_Temperature("CO","Denver");
	    	arrTemp[2] = weather_Temperature("MA","Boston");
	    	arrTemp[3] = weather_Temperature("NJ","Edison");
	    	arrTemp[4] = weather_Temperature("TX","Dallas");*/
	    	
	    	 String[] arrWeather = new String[5];
		    	
		     arrWeather[0] = weather_Weather("OH","Orrville");
		  /* arrWeather[1] = weather_Weather("CO","Denver");
		     arrWeather[2] = weather_Weather("MA","Boston");
		     arrWeather[3] = weather_Weather("NJ","Edison");
		     arrWeather[4] = weather_Weather("TX","Dallas"); */ 	  
	    	
	        String[] arrFeelsLike = new String[5];
	    	
	        arrFeelsLike[0] = weather_FeelsLike("OH","Orrville");
	       /* arrFeelsLike[1] = weather_FeelsLike("CO","Denver");
	        arrFeelsLike[2] = weather_FeelsLike("MA","Boston");
	        arrFeelsLike[3] = weather_FeelsLike("NJ","Edison");
	        arrFeelsLike[4] = weather_FeelsLike("TX","Dallas"); */ 	    	
	    	
	    	String[] arrWindChill = new String[5];
	    	
	    	arrWindChill[0] = weather_WindChill("OH","Orrville");
	    	/*arrWindChill[1] = weather_WindChill("CO","Denver");
	    	arrWindChill[2] = weather_WindChill("MA","Boston");
	    	arrWindChill[3] = weather_WindChill("NJ","Edison");
	    	arrWindChill[4] = weather_WindChill("TX","Dallas");  */  	
	    	
	    	String[] arrWind = new String[5];
	    	
	    	arrWind[0] = weather_Wind("OH","Orrville");
	    /*	arrWind[1] = weather_Wind("CO","Denver");
	    	arrWind[2] = weather_Wind("MA","Boston");
	    	arrWind[3] = weather_Wind("NJ","Edison");
	    	arrWind[4] = weather_Wind("TX","Dallas");*/
	    	
	    	String[] arrHumidity = new String[5];
	    	
	    	arrHumidity[0] = weather_Humidity("OH","Orrville");
	    /*	arrHumidity[1] = weather_Humidity("CO","Denver");
	    	arrHumidity[2] = weather_Humidity("MA","Boston");
	    	arrHumidity[3] = weather_Humidity("NJ","Edison");
	    	arrHumidity[4] = weather_Humidity("TX","Dallas");*/
	    	
	    	String[] arrPreceip = new String[5];
	    	
	    	arrPreceip[0] = weather_Preciptation("OH","Orrville");
	    /*	arrPreceip[1] = weather_Preciptation("CO","Denver");
	    	arrPreceip[2] = weather_Preciptation("MA","Boston");
	    	arrPreceip[3] = weather_Preciptation("NJ","Edison");
	    	arrPreceip[4] = weather_Preciptation("TX","Dallas");*/
	    	
	    	//Compare API values with Weather.com values
	    	
	    	Assert.assertEquals(currTemp, arrTemp[0]);
	    	
	    	Assert.assertEquals(desc, arrTemp[0]);
	    	
	    	Assert.assertEquals(feelsLike, arrFeelsLike[0]);
	    	
	    	Assert.assertEquals(highTemp, arrFeelsLike[0]);
			
    		System.out.println("State|City|  Temperature|Feels Lik|Wind Chill |Wind|Humidity|Preciptation");
    	 	
        	for(int i = 0; i< 5; i++)
        	{
        		System.out.println(arrState[i]+ " |" + arrCity[i] + "    |" + arrTemp[i] + "        |" + arrFeelsLike[i] + "       |" + arrWindChill[i] + "       |"+ arrWind[i] + "      |" +  arrHumidity[i] + "       |" + arrPreceip[i]);
        	}    
		
			driver.quit();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
    }
    
    public static String weather_Temperature(String strState, String strCity)
    {	
    	String strTemperature = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strTemperature = JsonPath.read(body, "$.current_observation.temp_f").toString();
        
		return strTemperature;         
    }
    
    public static String weather_Weather(String strState, String strCity)
    {	
    	String strWeather = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strWeather = JsonPath.read(body, "$.current_observation.weather").toString();
        
		return strWeather;         
    }    
    
    public static String weather_FeelsLike(String strState, String strCity)
    {	
    	String strFeelsLike = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strFeelsLike = JsonPath.read(body, "$.current_observation.feelslike_f").toString();
        
		return strFeelsLike;         
    }
    
    public static String weather_WindChill(String strState, String strCity)
    {	
    	String strWindChill = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strWindChill = JsonPath.read(body, "$.current_observation.windchill_f").toString();
        
		return strWindChill;         
    }
    
    public static String weather_Wind(String strState, String strCity)
    {	
    	String strWind = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strWind = JsonPath.read(body, "$.current_observation.wind_mph").toString();
        
		return strWind;         
    }
    
    public static String weather_Humidity(String strState, String strCity)
    {	
    	String strHumidity = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strHumidity = JsonPath.read(body, "$.current_observation.relative_humidity").toString();
        
		return strHumidity;         
    }
    
    public static String weather_Preciptation(String strState, String strCity)
    {	
    	String strPreciptation = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strPreciptation = JsonPath.read(body, "$.current_observation.precip_today_in").toString();
        
		return strPreciptation;         
    }
    
   }


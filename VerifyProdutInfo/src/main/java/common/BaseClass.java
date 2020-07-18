package common;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class BaseClass {
	public static AndroidDriver<MobileElement> driver ;
	public static String Apkpath="./APP/";
	public static String apkname;
	
	 protected static AppiumHelper helper=new AppiumHelper();
	protected static Logger log=Logger.getLogger(BaseClass.class.getName());
	   static AppiumDriverLocalService service;
	   static String service_url;
	   public static File folder = new File(Apkpath);
		   	   
	   @BeforeSuite
	  
	public void setUp() throws IOException{
	
	DesiredCapabilities caps = new DesiredCapabilities();
	caps.setCapability(CapabilityType.BROWSER_NAME,"");
		
		
	//	Hema
		//caps.setCapability(MobileCapabilityType.UDID, "3c5c437");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel XL API 28 2");
		//caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
	
//caps.setCapability(MobileCapabilityType.APP, getLatestFilefromDir(folder).getAbsolutePath());
		

	//	caps.setCapability("noReset", "false");
		caps.setCapability("fullReset","false"); 
		caps.setCapability("unicodeKeyboard", "true");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.ebay.mobile");
		caps.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
		caps.setCapability("autoGrantPermissions",true);
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
		/*	try {
		helper.clickOnElementByXpath(driver, "//android.widget.Button[@text='SKIP']");
		}
		catch(NoSuchElementException e) {
			
		}*/
		
	}

	  
@AfterClass
public void TestcaseEnd() {
	Reporter.log("==================================================================================================");
	
}

	@AfterSuite
	public void tearDown() throws InterruptedException, IOException {
	Thread.sleep(2000);
		//RemoveFile();
		
		driver.quit();
		Reporter.log("========Testcase ========"+driver.getClass().getSimpleName()+"====== Ended ========");
		Reporter.log("===================   ==================    =======================   =====================");
		//service.stop();
		 // service.stop();
	}
	public static void TakeScreenShot(AndroidDriver<MobileElement> driver,String FileName) throws IOException{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,new File("./ScreenShots/"+FileName+".png"));
	}
	public static String listFilesForFolder(final File folder) {
		
		    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	apkname=fileEntry.getName();
	       	    log.info(fileEntry.getName());
	            
	        
	    }
	   
	}
		return apkname;
	}

	

	




}

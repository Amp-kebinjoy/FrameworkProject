package au.com.ampion;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {
    public WebDriver initializeDriver() throws IOException {
        WebDriver driver;

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\global.properties");
        properties.load(fis);

        String browser = properties.getProperty("browser");

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                // Chrome options are optional
                /* ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless");
                driver = new ChromeDriver(options); */
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser in global.properties is not supported. Add new browser setup to Base.java or change browser name to chrome/firefox/edge.");
        }

        // Implicit wait can be set if necessary
        // driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        return driver;
    }
}

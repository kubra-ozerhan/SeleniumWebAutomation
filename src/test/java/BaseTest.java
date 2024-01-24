import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testlogger.TestResultLogger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestResultLogger.class)

public class BaseTest {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        System.out.println("Navigating to https://www.beymen.com/");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.beymen.com/");
        Thread.sleep(5);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

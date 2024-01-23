import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

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

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class SearchBox extends BasePage{

    By searchBoxLocator = By.xpath("//div[@class='o-header__search--wrapper']//input[@class='o-header__search--input']");
    By clearSearchedProductLocator = By.xpath("//button[@class='o-header__search--close -hasButton']");

    public SearchBox(WebDriver driver) {
        super(driver);
    }

    public void fillInTheSearchBarWithTheFirstProductFromExcelFile() throws InterruptedException, IOException {
        List<List<String>> data = readExcel();
        int rowIndex = 0;
        int columnIndex = 0;
        String value = data.get(rowIndex).get(columnIndex);

        Thread.sleep(2);
        if (isDisplayed(searchBoxLocator)){
            Thread.sleep(2);
            type(searchBoxLocator, value);
        }

    }

    public void deleteTheProductFromTheSearchBar() throws InterruptedException {
        Thread.sleep(2);
        click(clearSearchedProductLocator);
    }

    public void searchTheSecondProductFromExcelFile() throws InterruptedException, IOException {
        List<List<String>> data = readExcel();
        int rowIndex = 0;
        int columnIndex = 1;
        String value = data.get(rowIndex).get(columnIndex);

        Thread.sleep(50);
        if (isDisplayed(searchBoxLocator)){
            Thread.sleep(2);
            find(searchBoxLocator).sendKeys(value, Keys.ENTER);
        }
//        Thread.sleep(300);
//        find(searchBoxLocator).sendKeys(value, Keys.ENTER);
        Thread.sleep(500);
    }

    public static List<List<String>>  readExcel() throws IOException {

        // Excel dosyasının yolu
        String excelFilePath = "src/SearchingList.xlsx";

        // Excel dosyasını aç
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);

        // İlk sayfayı al (Varsayılan olarak, Excel dosyalarında sayfalar 0'dan başlar)
        Sheet sheet = workbook.getSheetAt(0);
        List<List<String>> excelData = new ArrayList<>();


        // Tüm satırları döngüye al
        for (Row row : sheet) {

            List<String> rowData = new ArrayList<>();
            // Satırdaki tüm hücreleri döngüye al
            for (Cell cell : row) {
                // Hücredeki değeri yazdır
                rowData.add(cell.toString() + "\t");
            }
            excelData.add(rowData);
        }

        // Workbook ve InputStream'i kapat
        workbook.close();
        inputStream.close();
        return excelData;
    }
}

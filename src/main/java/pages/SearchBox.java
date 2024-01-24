package pages;

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
    // Locators
    By searchBoxLocator = By.xpath("//div[@class='o-header__search--wrapper']//input[@class='o-header__search--input']");
    By searchBoxSuggestionInputLocator = By.xpath("//div[@class='o-header__search--wrapper']//input[@name='qSugesstion']");
    By clearSearchedProductLocator = By.xpath("//button[@class='o-header__search--close -hasButton']");

    // constructor
    public SearchBox(WebDriver driver) {
        super(driver);
    }

    public void fillInTheSearchBarWithTheFirstProductFromExcelFile() throws InterruptedException, IOException {
        // get and put all excel data into a multidimensional list array
        List<List<String>> data = readExcel();

        // get the value at [0,0] index ("şort)
        int rowIndex = 0;
        int columnIndex = 0;
        String value = data.get(rowIndex).get(columnIndex);

        Thread.sleep(2);
        // check if seachbox is diplayed
        if (isDisplayed(searchBoxLocator)){
            Thread.sleep(2);
            // then type the value [0,0] into search bar
            type(searchBoxLocator, value);
        }
        Thread.sleep(1000);
    }

    public void deleteTheProductFromTheSearchBar() throws InterruptedException {
        // by clicking the clean button in the search bar, clean the searhed product
        Thread.sleep(2);
        click(clearSearchedProductLocator);
        Thread.sleep(1000);
    }

    public void searchTheSecondProductFromExcelFile() throws InterruptedException, IOException {
        // get and put all excel data into a multidimensional list array
        List<List<String>> data = readExcel();

        // get the value at [0,1] index ("gömlek)
        int rowIndex = 0;
        int columnIndex = 1;
        String value = data.get(rowIndex).get(columnIndex);

        Thread.sleep(100);
        // check if seachbox is diplayed
        if (isDisplayed(searchBoxSuggestionInputLocator)){
            Thread.sleep(2);
            // then send the value [0,1] into search bar and press enter
            find(searchBoxSuggestionInputLocator).sendKeys(value, Keys.ENTER);
        }
        Thread.sleep(10);
    }

    public static List<List<String>>  readExcel() throws IOException {

        String excelFilePath = "src/SearchingList.xlsx";

        // Open the Excel file
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);

        // get the first Excel sheet
        Sheet sheet = workbook.getSheetAt(0);
        List<List<String>> excelData = new ArrayList<>();

        // Loop for all the rows
        for (Row row : sheet) {
            // Create an array list to collect each cell's data on a row
            List<String> rowData = new ArrayList<>();
            // Loop for all the columns in a row
            for (Cell cell : row) {
                // Write the value on the cell into created array list
                rowData.add(cell.toString() + "\t");
            }
            // Write all the cell values in a row into multidimensional array list
            excelData.add(rowData);
        }

        // close the Workbook and InputStream
        workbook.close();
        inputStream.close();

        // Return the multidimensional list (with row and column values)
        return excelData;
    }
}

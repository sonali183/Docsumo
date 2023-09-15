import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

 

public class DocsumoTest {
    public static void main(String[] args) {
        // ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

 

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

 

        // Create a WebDriver instance
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, 10);

 

        try {
            // Navigate to the Docsumo website
            driver.get("https://www.docsumo.com/free-tools/merge-pdf");

 

            // Click on "Split pdf by Page"
            WebElement splitPdfButton = driver.findElement(By.xpath("//a[contains(text(), 'Split pdf by Page')]"));
            splitPdfButton.click();

 

            // Upload a multipage PDF document (replace with the path to your PDF file)
            WebElement uploadInput = driver.findElement(By.id("pdf-file"));
            uploadInput.sendKeys("path/to/multipage.pdf");

 

            // Wait for the total number of pages to be displayed
            WebElement totalPagesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("total-pages")));

 

            // Get the total number of pages as an integer
            int totalNumberOfPages = Integer.parseInt(totalPagesElement.getText());

 

            // Split the PDF file
            WebElement splitButton = driver.findElement(By.id("split-btn"));
            splitButton.click();

 

            // Download the Zip file
            WebElement downloadButton = driver.findElement(By.id("download-zip"));
            downloadButton.click();

 

         
            // Verify the total number of downloaded files
            File downloadedFolder = new File("path/to/downloaded-folder");
            File[] downloadedFiles = downloadedFolder.listFiles();

 

            if (downloadedFiles != null && downloadedFiles.length == totalNumberOfPages) {
                System.out.println("Number of downloaded files matches the number of pages.");
            } else {
                System.out.println("Number of downloaded files does not match the number of pages.");
            }

 

            // Verify the extension of the downloaded files is PDF
            for (File file : downloadedFiles) {
                if (!file.getName().endsWith(".pdf")) {
                    System.out.println("One or more downloaded files do not have the PDF extension.");
                    break;
                }
            }

 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver
            driver.quit();
        }
    }
}


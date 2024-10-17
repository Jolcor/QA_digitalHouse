package TestFront;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testGoogle {
    // creamos un instancia de WevDriver
    public WebDriver driver;

    @Test
    public void test_1() {
        driver = new ChromeDriver();
        driver.get("https://google.com");

        driver.getTitle(); // aseguramos que entramos en la pagina de ggole

        // Creación de variables
        WebElement searchBox = driver.findElement(By.name("q"));
        //WebElement searchButton = driver.findElement(By.name("btnk"));

        searchBox.sendKeys("Selenium");
        searchBox.sendKeys(Keys.ENTER);
        //searchButton.click();

        searchBox = driver.findElement(By.name("q"));
        searchBox.getAttribute("value"); // obtenemos la palabra selenium

        //siempre cerrar el navegador
        driver.quit();
    }
}

package TestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) { // para solo usar desde las clases que lo herenda
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(5000)); //
    }

    public void setup(){
        driver.manage().window().maximize();// para que las pruebas se en el explrador se mantenga grande
    }

    public void url(String url) {
        driver.get(url); // url
    }

    public void close(){
        driver.quit(); // cerrar la prueba
    }

    protected WebElement findElement(By locator){
        return driver.findElement(locator);// localizador
    }

    protected void sendText(String inputText, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }

    protected void sendKey(CharSequence key, By locator){
        this.findElement(locator).sendKeys(key);
    }

    protected void click(By locator) { // realizar click
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    protected String getText(By locator) { // mostrar texto
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }
}

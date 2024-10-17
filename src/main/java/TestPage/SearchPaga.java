package TestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPaga extends BasePage {
    // Variable de localizdores
    private By searchBox = By.id("ciudad");
    private By seatchButton = By.id("btn-buscador");
    private By searchOk = By.className("categoria");

    public SearchPaga(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void escribirBusqueda(String ciudad) {
        this.sendKey(Keys.ENTER, searchBox);
    }

    public void clickBuscar() {
        this.click(seatchButton);
    }

    public String resultadoBusqueda() {
        String res = this.getText(searchOk);
        System.out.println("RESUSTALDO DE LA BUSQUEDA: " + res );
        return res;
    }
}

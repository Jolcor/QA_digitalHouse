package TestFront;

import TestPage.SearchPaga;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentFactory;
import java.time.Duration;

public class SearchTest {
    public WebDriver driver;
    public WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte.html"); // Donde se crea y guedara toda la información del reporte
    static ExtentReports extent;

    @BeforeAll
    public static void createReport(){
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void preconditions() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        SearchPaga searchPaga = new SearchPaga(driver, wait);
        searchPaga.setup();
        searchPaga.url("https://digital-booking-front.digitalhouse.com/");
    }

    @Test
    @Tag("BUSQUEDA") // suite
    @Tag("SMOKE") // Test de hunmo suite
    public void BusquedaExitosa_uruguay() {
        ExtentTest test = extent.createTest("Busqueda exitosa en Uruguay"); //
        test.log(Status.INFO, "Comienza nuestro test de busqueda");
        SearchPaga searchPaga = new SearchPaga(driver, wait);
        searchPaga.escribirBusqueda("Punta del este");
        searchPaga.clickBuscar();

        searchPaga.resultadoBusqueda();
        test.log(Status.PASS, "Se valido que la busqueda haya sido correcta");
    }

    @Test
    @Tag("BUSQUEDA") //suite
    @Tag("REGRESSION") // test regresión suite
    public void BusquedaExitosa_Grecia() throws InterruptedException {
        ExtentTest test = extent.createTest("Busqueda exitosa en Grecia"); //
        SearchPaga searchPaga = new SearchPaga(driver, wait);
        searchPaga.escribirBusqueda("paros");
        searchPaga.clickBuscar();
        searchPaga.resultadoBusqueda();
    }

    @AfterEach
    public void close() {
        SearchPaga searchPaga = new SearchPaga(driver, wait);
        searchPaga.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}
package TestFront;

import TestPage.LoginPage;
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

public class LoginTest {
    public WebDriver driver;
    public WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/ReporteLogin.html"); // Donde se crea y guedara toda la información del reporte
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
        searchPaga.url("https://digital-booking-front.digitalhouse.com/login");
    }

    @Test
    @Tag("LOGIN") // suite
    @Tag("SMOKE") // Test de humo suite
    public void Login_Exitoso() {
        ExtentTest test = extent.createTest("Login Exitoso"); //
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirEmail("prueba9999@gmail.com");
        loginPage.escribirPassword("123456789");
        loginPage.clickLogin();

        test.log(Status.PASS, "Se cargan los datos validos del login");

        Assertions.assertEquals(loginPage.saludo(), "Hola,");
        Assertions.assertEquals(loginPage.nombre(), "Jorge Corrales");
        test.log(Status.PASS, "Se valida el login exitoso");
    }

    @Test
    @Tag("LOGIN") // suite
    public void Login_Vacio_Contrasenia(){
        ExtentTest test = extent.createTest("Login fallido: contrasenia vacia");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirEmail("prueba9999@gmail.com");
        loginPage.clickLogin();
        loginPage.contraseniaObligaroeio();

        test.log(Status.PASS, "Se visualiza mensaje de campo obligatorio de contrasenia");
    }

    @Test
    @Tag("LOGIN") // suite
    public void Login_Email_Invalido(){
        ExtentTest test = extent.createTest("Login fallido: email invalido");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirEmail("jorgeCorralesgmail.com");
        loginPage.clickLogin();

        test.log(Status.PASS, "Se visualiza mensaje de email invalido");
    }

    @Test
    @Tag("LOGIN") // suite
    public void Login_Vacio(){
        ExtentTest test = extent.createTest("Login fallido vacio");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.clickLogin();
        loginPage.correoObligatorio();
        loginPage.contraseniaObligaroeio();

        test.log(Status.PASS, "Se visualiza mensaje de credenciales o credencial invalida");
    }

    @Test
    @Tag("LOGIN") // suite
    public void Login_Password_Min() {
        ExtentTest test = extent.createTest("Login fallido: error contraseña");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirEmail("prueba9999@gmail.com");
        loginPage.escribirPassword("qwe");
        loginPage.clickLogin();
        loginPage.caracteresInvalidos();

        test.log(Status.PASS, "Se visualiza mensaje minimo 6 caracteres");

    }

    @AfterEach
    public void close() {
        LoginPage loginPaga = new LoginPage(driver, wait);
        loginPaga.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}

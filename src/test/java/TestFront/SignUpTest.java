package TestFront;

import TestPage.LoginPage;
import TestPage.SearchPaga;
import TestPage.SignUpPage;
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

public class SignUpTest {
    public WebDriver driver;
    public WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/ReportSignUp.html"); // Donde se crea y guedara toda la información del reporte
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
        searchPaga.url("https://digital-booking-front.digitalhouse.com/registro");
    }

    @Test
    @Tag("SIGNUP")
    public void SignUp_Exitoso() {
        ExtentTest test = extent.createTest("Sign Up Exitoso"); //
        test.log(Status.INFO, "Comienza nuestro test de Sign Up");
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        signUpPage.writeFirstName("camilo");
        signUpPage.writeLastName("lopez");
        signUpPage.writeEmail("camiloLopez123423@gmail.com");
        signUpPage.writePassword("123456789");
        signUpPage.writeRePassword("123456789");

        test.log(Status.PASS, "Ingresar todo los datos del Registro");

        signUpPage.clickRegister();
        //signUpPage.cuentaCreada();

        //Condicional donde se crea una cuenta o cuenta existente
        if (signUpPage.cuentaCreada().equals("¡Cuenta registrada con éxito!")) {
            test.log(Status.PASS, "Validadción de Registro Exitoso");
        } else {
            test.log(Status.PASS, "Falla el mensajede Registro Exitoso");
        }

        if (signUpPage.confirmarEmail().equals("Te enviamos un email para confirmar tu cuenta")) {
            test.log(Status.PASS, "Validadción de Registro Exitoso");
        } else {
            test.log(Status.PASS, "Falla el mensajede de envio de email luego del Registro Exitoso");
        }
    }

    @Test
    @Tag("SIGNUP")
    public void SignUP_Vacio() {
        ExtentTest test = extent.createTest("Sign Up Exitoso"); //
        test.log(Status.INFO, "Sign Up fallido: campos vacios, se requiere llenar los campos");
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        signUpPage.clickRegister();
        signUpPage.campoObligatorio();

        test.log(Status.PASS, "Se visualizan los campos los mensajes de los campos vacios");
    }


    @Test
    @Tag("SIGNUP")
    public void Email_Invalido() {
        ExtentTest test = extent.createTest("Sign Up Exitoso"); //
        test.log(Status.INFO, "Sign Up fallido: email invalido");
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        signUpPage.writeFirstName("Carlos");
        signUpPage.writeLastName("Corrales Reales");
        signUpPage.writeEmail("jorgecorrales89gmail.com");
        signUpPage.writePassword("123456789");
        signUpPage.writeRePassword("123456789");

        signUpPage.clickRegister();
        signUpPage.emailInvalido();

        test.log(Status.PASS, "Se valida el sign up error en Email");
    }

    @Test
    @Tag("SIGNUP")
    public void Email_Existente() {
        ExtentTest test = extent.createTest("Sign Up Exitoso"); //
        test.log(Status.INFO, "Sign Up fallido: la cuenta ya existe");
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        signUpPage.writeFirstName("Jorge Luis");
        signUpPage.writeLastName("Corrales Reales");
        signUpPage.writeEmail("jorgecorrales89@gmail.com");
        signUpPage.writePassword("123456789");
        signUpPage.writeRePassword("123456789");

        signUpPage.clickRegister();
        signUpPage.cuentaExistente();

        test.log(Status.PASS, "Se valida que la cuenta ya haboa sido creada");
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
    //pendiente para realizar test de crear cuenta
}

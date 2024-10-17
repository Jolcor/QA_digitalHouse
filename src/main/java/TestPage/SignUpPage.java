package TestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {

    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("email");
    private By password = By.id("password");
    private By repassword = By.id("repassword");
    private By register = By.xpath("//button[normalize-space(text())='Registrarse']");
    private By Requested = By.className("small-feedback");
    private By emailInv = By.xpath("//small[normalize-space(text())='El email es inválido']");
    private By passwprdInv = By.xpath("//small[normalize-space(text())='La contraseña debe tener más de 6 caracteres']");
    private By accountExist = By.xpath("//p[normalize-space(text())='Ese email ya se encuentra registrado']");
    private By RegitroExistoso = By.className("txt-gracias");
    private By emailconfir = By.className("txt-exito");

    public SignUpPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void writeFirstName(String first_name) {
        this.sendText(first_name, firstName);
    }

    public void writeLastName(String last_name) {
        this.sendText(last_name, lastName);
    }

    public void clickRegister() {
        this.click(register);
    }

    public void writeEmail(String mail) {
        this.sendText(mail, email);
    }

    public void writePassword(String pass) {
        this.sendText(pass, password);
    }

    public void writeRePassword(String pass) {
        this.sendText(pass, repassword);
    }

    public String campoObligatorio() {
        System.out.println("MENSAJE DE CAMPO OBLIGATORIO: " + this.getText(Requested));
        return this.getText(Requested);
    }

    public String emailInvalido() {
        System.out.println("MENSAJE DE EMAIL INVALIDO: " + this.getText(emailInv));
        return this.getText(emailInv);
    }

    public String caracteresInvalidos() {
        System.out.println("MENSAJE DE CONTRASEÑA INVALIDA MIN 6 CARACTERES: " + this.getText(passwprdInv) );
        return this.getText(passwprdInv);
    }

    public String cuentaExistente() {
        System.out.println("MENSAJE DE CUENTA YA ESTA RESGISTRADA: " + this.getText(accountExist) );
        return this.getText(accountExist);
    }

    public String cuentaCreada() {
        System.out.println("MENSAJE DE CUENTA RESGISTRADA CON EXITOSO: " + this.getText(RegitroExistoso));
        return this.getText(RegitroExistoso);
    }

    public String confirmarEmail() {
        System.out.println("MENSAJE DE CUENTA RESGISTRADA CON EXITOSO: " + this.getText(emailconfir));
        return this.getText(emailconfir);
    }
 }

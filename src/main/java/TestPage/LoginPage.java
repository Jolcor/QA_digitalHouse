package TestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    // Variable de localizdores
    private By emailBox = By.id("email");
    private By passwordBox = By.id("password");
    private By login = By.className("btn-primario");
    private By hello = By.className("txt-hola");
    private By name = By.className("txt-nombre");
    private By emailReq = By.xpath("//small[@class=\"small-feedback\"]");
    private By passwordReq = By.xpath("//small[@class=\"small-feedback\"]");
    private By passwprdInv = By.xpath("//small[normalize-space(text())='La contraseña debe tener más de 6 caracteres']");
    private By emailInv = By.xpath("//small[normalize-space(text())='El email es inválido']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void escribirEmail(String correo) {
        this.sendText(correo, emailBox);
    }

    public void escribirPassword(String password) {
        this.sendText(password, passwordBox);
    }

    public void clickLogin() {
        this.click(login);
    }

    public String saludo() {
        System.out.println("MENSAJE DE SALUDO: " + this.getText(hello));
        return this.getText(hello);
    }

    public String nombre() {
        System.out.println("SE VERIFICA EL NOMBRE DEL LOGIN: " + this.getText(name));
        return this.getText(name);
    }

    public String correoObligatorio() {
        System.out.println("MENSAJE DE CORREO OBLIGATORIO: " + this.getText(emailReq));
        return this.getText(emailReq);
    }

    public String contraseniaObligaroeio() {
        System.out.println("MENSAJE DE CONTRASEÑA OBLIGATORIO: " + this.getText(passwordReq) );
        return this.getText(passwordReq);
    }

    public String caracteresInvalidos() {
        System.out.println("MENSAJE DE CONTRASEÑA INVALIDA: " + this.getText(passwprdInv) );
        return this.getText(passwprdInv);
    }
}

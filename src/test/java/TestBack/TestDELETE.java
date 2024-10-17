package TestBack;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class TestDELETE {

    @Test
    public void Delete_Test01() {
        Response responseBaby = given().delete("https://reqres.in/api/user/20");
        Assert.assertEquals(responseBaby.statusCode(), 204);

        System.out.println("EL CODIGO DE RESPUESTA ES : " + responseBaby.statusCode() + " QUE ASEGURA QUE SE HAYA DADO DE BAJA EL USUARIO");
        System.out.println("EL SERVICIO SE TARDO: " + responseBaby.getTime() + " MILISEGUNDOS EN RESPONDER");
    }

    @Test
    public void Delete_Test02() {
        //configuaracion para agregar y quede organizado con sentido ocmo llamar la url y poder guardarlo en variables
        String urlREQRES = "https://reqres.in/api/"; // base para el test
        String pathUser = "users/";
        String deleteUser = "15";

        Response responseBaby = given().delete(urlREQRES + pathUser + deleteUser);
        Assert.assertEquals(responseBaby.statusCode(), 204);

        System.out.println("EL CODIGO DE RESPUESTA ES : " + responseBaby.statusCode() + " NOS ASEGURA QUE SE HAYA DADO DE BAJA EL USUARIO: " + deleteUser);
        System.out.println("EL SERVICIO SE TARDO: " + responseBaby.getTime() + " MILISEGUNDOS EN RESPONDER");




    }
}

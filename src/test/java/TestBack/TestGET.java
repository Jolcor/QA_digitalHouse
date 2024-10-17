package TestBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;


public class TestGET {
    @Test
    public void Get_Test01() {
        Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(resGet.getBody().asString());// nos convierte en string el body
        System.out.println(resGet.getStatusCode());
        System.out.println(resGet.getHeader("Date"));// nos de vuelve solo esa parte del DATE
        System.out.println(resGet.getTime());
    }

    @Test
    public void Get_Test02() {
        // vamos a consultar y guardar en variables para poder validadiones de lo que se esta obteniendo
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        int statusCode = response.statusCode();
        JsonPath body = response.jsonPath();
        String nombre_0 = body.getString("data.first_name[0]"); // oconsultamos el primer nombre
        String mail_0 = body.getString("data.email[0]"); // oconsultamos el primer nombre
        String nombre_4 = body.getString("data.first_name[3]");
        String mail_4 = body.getString("data.email[3]");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(nombre_0, "Michael");
        Assert.assertEquals(mail_0, "michael.lawson@reqres.in");
        Assert.assertEquals(nombre_4, "Byron");
        Assert.assertEquals(mail_4, "byron.fields@reqres.in");

        System.out.println("El codido del estado es: " + statusCode);
        System.out.println("El nombre del primer registro es: " + nombre_0);
        System.out.println("El email del primer registro es: " + mail_0);
        System.out.println("El nombre del cuarto registro es: " + nombre_4);
        System.out.println("El email del cuarte registro es: " + mail_4);
    }

    @Test
    public void Get_Test03() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .log()
                .body(false);// se le quita el formato de JSON y lo devuleve como un STRING
    }
}

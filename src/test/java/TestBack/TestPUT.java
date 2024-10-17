package TestBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestPUT {

    @Test
    public void Put_Test01() {
        JsonObject resquet = new JsonObject();
        resquet.addProperty("name", "Luis");
        resquet.addProperty("job", "Developed");
        given()
                .contentType("application/json") // Especifica que el tipo de contenido de la solicitud es JSON
                .body(resquet) // Establece el cuerpo de la solicitud con el contenido del objeto JSON
                .put("https://reqres.in/api/users/20")
                .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("name", equalTo("Luis"))
                .body("job", not(equalTo("Ingeniero")))
                .body("updatedAt", containsString("2024"));
    }

    @Test
    public void Put_Test02() {
        JsonObject resquet = new JsonObject();
        resquet.addProperty("name", "Luis");
        resquet.addProperty("job", "Developed");

        Response responseBody = given()
                .contentType("application/json")
                .body(resquet)
                .put("https://reqres.in/api/users/30");

        String nombreModificado = responseBody.jsonPath().getString("name");
        String trabajoModificado = responseBody.jsonPath().getString("job");

        Assert.assertEquals(nombreModificado, "Luis");
        Assert.assertEquals(trabajoModificado, "Developed");
        Assert.assertEquals(responseBody.statusCode(), 200);

        responseBody
                .then()
                .body("updatedAt", containsString("2024"));

        System.out.println("EL NOMBRE MODIFICADO ES: " + nombreModificado);
        System.out.println("EL TRABAJO MODIFICADO ES: " + trabajoModificado);
        System.out.println("EL CODIGO DE RESPUESTA ES: " + responseBody.statusCode());
        System.out.println("EL SERVICIO SE TARDO: " + responseBody.getTime() + " MILISEGUNDOS EN RESPONDER");
    }
}

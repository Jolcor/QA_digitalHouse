package TestBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class TestPOST {

    @Test
    public void Post_Test01() {
        JsonObject resquet = new JsonObject();
        resquet.addProperty("name", "Jorge");
        resquet.addProperty("job", "QA");
        given()
                .contentType("application/json") // Especifica que el tipo de contenido de la solicitud es JSON
                .body(resquet) // Establece el cuerpo de la solicitud con el contenido del objeto JSON
                .post("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body();
    }

    @Test
    public void Post_Test02() {
        JsonObject resquet = new JsonObject();
        resquet.addProperty("name", "Carlos");
        resquet.addProperty("job", "Design");

        given()
                .contentType("application/json")
                .body(resquet)
                .post("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .statusCode(201)
                .log().body()
                .body("name", equalTo("Carlos"))
                .body("job", equalTo("Design"))
                .body("createdAt", containsString("2024-10-16"));
    }

    @Test
    public void PostTestFallido() {
        JsonObject resquet = new JsonObject();
        resquet.addProperty("email", "Corrales@Corrales");

        Response response = given().contentType("application/json")
                                    .body(resquet)
                                    .post("https://reqres.in/api/login");

        String error = response.jsonPath().getString("error");
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertEquals(error, "Missing password");

        System.out.println(response.getBody().asString());
        System.out.println("EL MENSAJE DE ERROR ES: " + error);
        System.out.println("EL CODIGO ERROR ES: " + response.statusCode());
        System.out.println("EL SERVICIO SE TARDO: " + response.time() + " MILISEGUNDOS");
    }
}
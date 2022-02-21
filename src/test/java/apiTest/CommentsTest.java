package apiTest;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes do endpoint https://jsonplaceholder.typicode.com/comments")
public class CommentsTest {

    @Test
    @DisplayName("Chamada do endpoint comments testando verbo GET filtrando pelo campo name")
    public void testValidarVerboGetComParams(){
        // dados da api
        baseURI = "https://jsonplaceholder.typicode.com";
        basePath = "";

        Response response = given()
                .contentType(ContentType.JSON)
                .param("name","alias odio sit")
            .when()
                .get("/comments")
            .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Lew@alysha.tv", response.jsonPath().getString("email[0]"));
    }
}

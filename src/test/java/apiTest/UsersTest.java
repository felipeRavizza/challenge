package apiTest;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes do endpoint https://jsonplaceholder.typicode.com/users")
public class UsersTest {

    @BeforeAll
    public static void setup() {
        baseURI = "https://jsonplaceholder.typicode.com";
    }

        @Test
        @DisplayName("Chamada do endpoint users testando verbo POST")
        public void testValidarVerboPost(){

            JSONObject body = new JSONObject();
            body.put("name","Teste usuário novo");
            body.put("username","testeUsuario");
            body.put("email","teste@teste.com.br");
            body.put("phone","99999-9999");
            body.put("website","www.testes.com.br");

            JSONObject adress = new JSONObject();
            adress.put("street","rua de testes");
            adress.put("suite"," apto. 9865 B");
            adress.put("zipcode","89065330");
            JSONObject geo = new JSONObject();
            geo.put("lat","-37.3159");
            geo.put("lng","81.1496");
            adress.put("geo",geo);
            body.put("adress",adress);

            JSONObject company = new JSONObject();
            company.put("name","Tetes Ind.");
            company.put("catchPhrase","Proactive didactic contingency");
            company.put("bs","synergize scalable supply-chains");
            body.put("company",company);



            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(body.toJSONString())
                    .when()
                    .post("/users")
                    .then()
                    .extract()
                    .response();

            Assertions.assertEquals(201, response.statusCode());
            Assertions.assertEquals(11L, response.jsonPath().getLong("id"));
        }

    @Test
    @DisplayName("Chamada do endpoint users testando verbo PUT")
    public void testValidarVerboPut(){

        JSONObject body = new JSONObject();
        body.put("email","teste@teste.com.br");

        JSONObject adress = new JSONObject();
        JSONObject geo = new JSONObject();
        geo.put("lat","-37.3159");
        geo.put("lng","81.1496");
        adress.put("geo",geo);
        body.put("adress",adress);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body.toJSONString())
                .when()
                .put("/users/5")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(5L, response.jsonPath().getLong("id"));
        Assertions.assertEquals(-37.3159D, response.jsonPath().getDouble("adress.geo.lat"));
        Assertions.assertEquals(81.1496D, response.jsonPath().getDouble("adress.geo.lng"));
        Assertions.assertEquals("teste@teste.com.br", response.jsonPath().getString("email"));
    }
}


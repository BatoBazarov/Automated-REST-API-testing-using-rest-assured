import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class SpoonacularTest extends AbstractTest {

    @Test
    void getIncludeIngredients() {
        given()                                                                   // пишутся предусловия
                .queryParam("apiKey", getApiKey())
                .queryParam("includeIngredients", "tomato,cheese")
                .pathParam("id", 715495)
                .when()                                                           //пишутся требуемые шаги
                .get(getBaseUrl() + "recipes/{id}/information")  //базовый адрес + адрес ресурса
                .then()                                                           //пишутся проверки (assertions)
                .statusCode(200);
    }

    @Test
    void getMaxVitaminA() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxVitaminA", "100")
                .pathParam("id", 716276)
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .statusCode(200);
    }

    @Test
    void getExcludeIngredients() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeIngredients", "egg")
                .pathParam("id", 716381)
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .statusCode(200);
    }

    @Test
    void getAuthor() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("author", "coffeebean")
                .pathParam("id", 149068)
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .statusCode(200);
    }

    @Test
    void getMinSugar() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minSugar", "0")
                .pathParam("id", 715594)
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .statusCode(200);
    }

    @Test
    void getClassifyCuisine() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getClassifyCuisine2() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Homemade Garlic and Basil French Fries")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getClassifyCuisine3() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Berry Banana Breakfast Smoothie")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getClassifyCuisine4() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Herring Fish Cakes with Fresh Dill")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getClassifyCuisine45() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Herb and Cheddar Cordon Bleu")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getResponseData() {
        Response response = given()
                .when()
                .get(getBaseUrl() + "recipes/715495/information?" +
                        "includeIngredients=tomato,cheese&apiKey=" + getApiKey());

        Headers allHeaders = response.getHeaders();
        System.out.println("Content-Encoding: " + response.getHeader("Content-Encoding"));
        System.out.println("Code: " + response.getStatusCode());

        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .path("cuisine");

        System.out.println("cuisine: " + cuisine);
    }

    @Test
    void getVerifyingResponseData() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxVitaminA", "100")
                .when()
                .get("https://api.spoonacular.com/recipes/716276/information")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType(ContentType.JSON);
    }
}


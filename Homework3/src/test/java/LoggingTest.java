    import io.restassured.RestAssured;
    import org.junit.jupiter.api.BeforeAll;
    import org.junit.jupiter.api.Test;

    import static io.restassured.RestAssured.given;

    public class LoggingTest extends AbstractTest {

        @BeforeAll
        static void setUp() {

            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }

        @Test
        void getTest() {
            given()
                    .queryParam("apiKey", getApiKey())
                    .queryParam("includeIngredients", "tomato,cheese")
                    .when()
                    .get("https://api.spoonacular.com/recipes/715495/information")
                    .then().statusCode(200);
        }
    }

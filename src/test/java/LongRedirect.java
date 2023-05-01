import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class LongRedirect {

    @Test
    public void testLongRedirect() {

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        int count = 1;
        while (statusCode != 200) {
            count++;
            response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(locationHeader)
                    .andReturn();
            locationHeader = response.getHeader("Location");
            statusCode = response.getStatusCode();

        }
        System.out.println(locationHeader);
        System.out.println("StatusCode:" + statusCode);
        System.out.println("Count:" + count);
    }
}
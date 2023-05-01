import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TokenTest {

    @Test
    public void testToken() throws InterruptedException {
        String taskUrl = "https://playground.learnqa.ru/ajax/api/longtime_job";
        JsonPath responseToken = RestAssured
                .get(taskUrl)
                .jsonPath();

        String token = responseToken.get("token");
        int time = responseToken.get("seconds");
        System.out.println("Token: " + token);
        System.out.println("Time to ready: " + time);

        Map<String, String> params = new HashMap<>();
        params.put("token", token);

        JsonPath getTaskStatus = RestAssured
                .given()
                .queryParams(params)
                .get(taskUrl)
                .jsonPath();

        String status = getTaskStatus.get("status");
        String checkPerformance = "Job is NOT ready";
        System.out.println("Статус задачи: " + checkPerformance);

        if (Objects.equals(status, checkPerformance)) {
            Thread.sleep(time * 1000L);

            JsonPath putTaskTime = RestAssured
                    .given()
                    .queryParams(params)
                    .when()
                    .get(taskUrl)
                    .jsonPath();
            String lastStatus = putTaskTime.get("status");
            System.out.println("Статус выполнения задачи: " + lastStatus);

            String resultTaskTime = putTaskTime.get("result");
            System.out.println("Результат выполнения задачи: " + resultTaskTime);

        } else {
            String result = getTaskStatus.get("status");
            System.out.println(result);
        }

    }
}

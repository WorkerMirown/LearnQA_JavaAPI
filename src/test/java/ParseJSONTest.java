import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


class Parsing_JSON {

    @Test

    public void RestAssured() {
        Map<String, String> params = new HashMap<>();
        params.put("message", "timestamp");

        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        ArrayList<LinkedHashMap<String, String>> answer = response.get("messages");
        LinkedHashMap<String, String> message = answer.get(1);

        System.out.println(message);
        System.out.println(message.get("message"));
    }
}

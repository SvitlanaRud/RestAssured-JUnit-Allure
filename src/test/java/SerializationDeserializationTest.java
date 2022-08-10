import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.http.ContentType;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import pojos.ResponseBody;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializationDeserializationTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9876));


    @Test
    public void requestZipCodeForPolandDeserialization() {
        ResponseBody body = given().
                log().all().
                when().
                get("http://api.zippopotam.us/PL/00-001").
                as(ResponseBody.class);

        assertEquals(body.getPlaces().get(0).getPlaceName(), "Warszawa");
    }

    @Test
    @Ignore
    public void requestZipCodeForPolandSerialization() {
        ResponseBody location = new ResponseBody();

       given().
               contentType(ContentType.JSON).
               body(location).
                log().all().
                when().
                post("http://localhost:9876/lv/1050").
                then().
               assertThat().
               statusCode(200);

    }
}

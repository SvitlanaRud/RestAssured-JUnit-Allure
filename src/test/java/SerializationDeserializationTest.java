import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Places;
import utils.Config;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Serialization and deserialization tests")
@Feature("Api serialization deserialization tests")
public class SerializationDeserializationTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9876));


    @Test
    @DisplayName("Request zip code for Poland and deserialize response body")
    public void requestZipCodeForPolandDeserialization() {
        Places body = given().
                log().all().
                baseUri(Config.BASE_URL).
        when().
                get("/PL/00-001").
        then().
                extract().
                as(Places.class);

        assertEquals(body.getPlaces().get(0).getPlaceName(), "Warszawa");
    }

    @Test
    @Ignore
    @DisplayName("Request zip code for Poland with serialized request body")
    public void requestZipCodeForPolandSerialization() {
        Places location = new Places();

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

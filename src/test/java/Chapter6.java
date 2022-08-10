import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.http.ContentType;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import pojos.ResponseBody;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

public class Chapter6 {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9876));


    @Test
    public void requestZipCodeForPolandDecerialization() {
        ResponseBody body = given().
                log().all().
                when().
                get("http://api.zippopotam.us/PL/00-001").
                as(ResponseBody.class);

        Assert.assertEquals(body.getPlaces().get(0).getPlaceName(), "Warszawa");
    }

    @Test
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

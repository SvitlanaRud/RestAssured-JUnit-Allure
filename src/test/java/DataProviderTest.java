import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class DataProviderTest {

    public static Object[][] zipCodesAndCities() {
        return new Object[][]{
                {"PL", "00-001", "Warszawa"},
                {"NO", "0001", "Oslo"},
                {"PT", "1000-001", "Lisboa"}
        };
    }

    @ParameterizedTest()
    @MethodSource("zipCodesAndCities")
    public void requestZipCodeForPoland(String countryCode, String zip, String city) {
        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zip).
                log().all().
                when().
                get("http://api.zippopotam.us/{countryCode}/{zipCode}").
                then().log().body().
                assertThat().
                body("places[0].'place name'", equalTo(city));
    }
}

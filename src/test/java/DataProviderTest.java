import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Data provider tests")
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
    @DisplayName("Request zip codes for different countries")
    public void requestZipCodesForDifferentCountries(String countryCode, String zip, String city) {
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
